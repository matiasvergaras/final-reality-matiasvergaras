package com.github.matiasvergaras.finalreality.model.character;


import com.github.matiasvergaras.finalreality.model.AttributeSet;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 * @since Homework 1
 * @author Ignacio Slater Muñoz.
 * @author Matias Vergara Silva.
 */
public abstract class AbstractCharacter implements ICharacter {

    private final BlockingQueue<ICharacter> turnsQueue;
    private final String name;
    private final int maxHP;
    private int currentHP;
    private final int DP;
    protected ScheduledExecutorService scheduledExecutor;

    /**
     * Constructor for a new Character.
     *
     * @param name       the character's name
     * @param turnsQueue the queue with the characters ready to play
     * @param HP         this character's max heals points
     * @param DP         this character's max defense points
     */
    public AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                @NotNull String name,
                                int HP, int DP) {
        this.turnsQueue = turnsQueue;
        this.name = name;
        this.maxHP = HP;
        this.currentHP = HP;
        this.DP = DP;
    }

    /**
     * {@inheritDoc}
     * @param character the character to be attacked.
     */
    public void normalAttack(ICharacter character) {
        if (character.isAlive() && this.isAlive()) {
            character.receiveNormalAttack(this);
        }
    }

    /**
     * {@inheritDoc}
     * @param character         The ICharacter character that is performing the attack.
     */
    public abstract void receiveNormalAttack(ICharacter character);

    protected void addToQueue() {
        turnsQueue.add(this);
        scheduledExecutor.shutdown();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract int getAttackPower();
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentHP() {
        return currentHP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxHP() {
        return maxHP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDP() {
        return DP;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isAlive() {
        return currentHP> 0;
    }

    /**
     * modify the HP of this character.
     *
     * @param diff a positive Integer to rest to the Character HP.
     */
    @Override
    public void reduceHP(double diff) {
        this.currentHP -= diff;
        if(this.currentHP<0){
            //To avoid characters of having negative HP.
            this.currentHP=0;
        }
    }

    /**
     * To get the all the attributes of this character together.
     * @return An ArrayList of Integer with the attributes in the following
     * order: maxHP, currentHP, DP.
     */
    public abstract AttributeSet getAttributes();

}






