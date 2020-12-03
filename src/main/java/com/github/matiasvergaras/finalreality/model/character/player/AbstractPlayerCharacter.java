package com.github.matiasvergaras.finalreality.model.character.player;

import com.github.matiasvergaras.finalreality.model.character.AbstractCharacter;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.NullPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.character.cpu.ICPUCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.NullWeapon;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * A class that holds all the information of all Player Characters of the game.
 * @since Homework 1
 * @author Ignacio Slater Muñoz.
 * @author Matias Vergara Silva.
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements IPlayerCharacter {
    protected IWeapon equippedWeapon;
    private boolean isEquipped; // Added in homework 2 to avoid asking for the weapon when unequipped.
                                // This did not cause an error, but was not a good practice.

    private PropertyChangeSupport deadCharacter = new PropertyChangeSupport(this);
    /**
     * Creates a new Player Character
     *
     * @param name       the character's name
     * @param turnsQueue the queue with the characters ready to play
     * @param HP         the character's max heal points
     * @param DP         the character's max defense points
     */
    protected AbstractPlayerCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                      @NotNull String name,
                                      int HP, int DP) {
        super(turnsQueue, name, HP, DP);
        this.isEquipped = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waitTurn() {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor
                .schedule(super::addToQueue, equippedWeapon.getWeight() / 10, TimeUnit.SECONDS);
    }


    /**
     * Property Change Support to observe the eventual death of a Player character
     * @return Property Change Support to DeathPlayerCharacter
     */
    public PropertyChangeSupport deathCharacter(){
        return this.deadCharacter;
    }

    /**
     * {@inheritDoc}
     *
     * @param weapon The weapon to equip.
     */
    public abstract void equipWeapon(IWeapon weapon);

    /**
     * Get the equipped weapon.
     *
     * @return the equipped weapon of this character.
     * @see IWeapon
     */
    @Override
    public IWeapon getEquippedWeapon() {
        return equippedWeapon;
    }

    /**
     * {@inheritDoc}
     * @return true if equiped, false otherwise.
     */
    public boolean isEquipped(){
        return this.isEquipped;
    }

    /**
     * {@inheritDoc}
     *
     * @param weapon The weapon to be equip
     */
    @Override
    public void equip(IWeapon weapon) {
        equippedWeapon = weapon;
        this.isEquipped = true;
        weapon.setOwner(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unequip() {
        equippedWeapon.setOwner(new NullPlayerCharacter());
        equippedWeapon = new NullWeapon();
    }


    /**
     * {@inheritDoc}
     * @param character the character to be attacked.
     */
    public void normalAttack(ICPUCharacter character) {
        if (character.isAlive() && this.isAlive()) {
            character.receiveNormalAttack(this);
        }
    }


    /**
     * Receive a non-magic attack
     *
     * @param character the attacking character.
     */
    public void receiveNormalAttack(ICPUCharacter character) {
        if(character.getPower()>this.getDP()) {
            this.reduceHP(character.getPower() - getDP());
        }
    }

    /**
     * modify the HP of this character.
     * And check if it is necessary to notify.
     * @param diff a positive Integer to rest to the Character HP.
     */
    @Override
    public void reduceHP(double diff) {
        super.reduceHP(diff);
        if(!this.isAlive()){
            deadCharacter.firePropertyChange(new PropertyChangeEvent(this, "Dead Player Character",
                    "Alive", "Dead"));

        }
    }


    @Override
    public Map<String, Object> getAttributes(){
        Map<String, Object> attributes = super.getAttributes();
        attributes.put("equippedWeapon", equippedWeapon);
        return attributes;
    }

}


