package com.github.matiasvergaras.finalreality.model.character;


import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Matias Vergara Silva.
 */
public abstract class AbstractCharacter implements ICharacter {

    private final BlockingQueue<ICharacter> turnsQueue;
    private final String name;
    private final int maxHP;
    private final int maxDP;
    private int currentDP;
    private int currentHP;
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
        this.maxDP = DP;
        this.currentDP = DP;
    }

    /**
     * Adds this character to the turns queue.
     */
    protected void addToQueue() {
        turnsQueue.add(this);
        scheduledExecutor.shutdown();
    }

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
    public int getCurrentDP() {
        return currentDP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxDP() {
        return maxDP;
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
    }

}






