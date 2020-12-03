package com.github.matiasvergaras.finalreality.model.character.cpu;

import com.github.matiasvergaras.finalreality.model.character.AbstractCharacter;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * A class that holds all the information of a CPU character of the game.
 * @since Homework 1
 * @author Matías Vergara Silva
 */
public abstract class AbstractCPUCharacter extends AbstractCharacter implements ICPUCharacter {

    private final int weight;
    private final int power;

    private PropertyChangeSupport deadCharacter = new PropertyChangeSupport(this);


    /**
     * Basic constructor for an Enemy Character.
     *
     * @param turnsQueue the queue with the characters ready to play
     * @param name       the character's name
     * @param weight     the character's weight
     * @param HP         the character's heal points
     * @param DP         the character's defense points
     * @param power      the character's power points
     */

    public AbstractCPUCharacter(@NotNull final BlockingQueue<ICharacter> turnsQueue,
                                @NotNull final String name, int weight,
                                int HP, int DP, int power) {
        super(turnsQueue, name, HP, DP);
        this.weight = weight;
        this.power = power;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waitTurn() {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor
                .schedule(super::addToQueue, this.getWeight() / 10, TimeUnit.SECONDS);
    }

    /**
     * Property Change Support to observe the eventual death of a CPU character
     * @return Property Change Support to DeathPlayerCharacter
     */
    public PropertyChangeSupport deathCharacter(){
        return this.deadCharacter;
    }

    /**
     * Get the power of this enemy.
     *
     * @return the power of this enemy.
     */
    public int getPower() {
        return power;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void normalAttack(IPlayerCharacter character) {
        if( this.isAlive()) {
            if (character.isAlive()) {
                character.receiveNormalAttack(this);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void receiveNormalAttack(IPlayerCharacter character) {
        if (character.isEquipped() & character.getEquippedWeapon().getPower()>this.getDP()) {
            reduceHP(character.getEquippedWeapon().getPower() - getDP());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return the weight of this enemy.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * {@inheritDoc}
     *
     * @return An ArrayList of Integer with the attributes of this character all together,
     * in the following order: maxHP, currentHP, DP, weight, power.
     */
    public Map<String, Object> getAttributes(){
        Map<String, Object> attributes = super.getAttributes();
        attributes.put("weight", weight);
        attributes.put("power", power);
        return attributes;
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
            deadCharacter.firePropertyChange(new PropertyChangeEvent(this, "Dead CPU Character",
                    "Alive", "Dead"));

        }
    }

}
