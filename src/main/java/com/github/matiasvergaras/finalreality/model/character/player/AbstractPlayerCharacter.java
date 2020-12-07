package com.github.matiasvergaras.finalreality.model.character.player;

import com.github.matiasvergaras.finalreality.model.CharacterAttributeSet;
import com.github.matiasvergaras.finalreality.model.character.AbstractCharacter;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.NullWeapon;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
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


    private PropertyChangeSupport deadCharacter = new PropertyChangeSupport(this);
    /**
     * Creates a new Player Character
     * <p> Every playerCharacter will start equipped with a NullWeapon. </p>
     * @param name       the character's name
     * @param turnsQueue the queue with the characters ready to play
     * @param HP         the character's max heal points
     * @param DP         the character's max defense points
     */
    protected AbstractPlayerCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                      @NotNull String name,
                                      int HP, int DP) {
        super(turnsQueue, name, HP, DP);
        this.equipWeapon(new NullWeapon());
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
        return !this.equippedWeapon.equals(new NullWeapon());
    }

    /**
     * {@inheritDoc}
     *
     * @param weapon The weapon to be equip
     */
    @Override
    public void equip(IWeapon weapon) {
        equippedWeapon = weapon;
        weapon.setOwner(this);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void unequip() {
        equippedWeapon = null;
    }

    /**
     * {@inheritDoc}
     *
     */
    public int getAttackPower(){
        return this.getEquippedWeapon().getPower();
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

    /**
     * {@inheritDoc}
     * @return a CharacterAttributeSet with the attributes of this character.
     */
    @Override
    public CharacterAttributeSet getAttributes(){
        return new CharacterAttributeSet(this.getName(), this.getMaxHP(), this.getCurrentHP(), this.getDP(),
                this.getEquippedWeapon());
    }

}


