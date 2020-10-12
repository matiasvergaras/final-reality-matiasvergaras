package com.github.matiasvergaras.finalreality.model.character.cpu;

import com.github.matiasvergaras.finalreality.model.character.AbstractCharacter;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.magic.IMagicCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.magic.IMagicWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AbstractEnemy extends AbstractCharacter implements IEnemy {

    private final int weight;
    private final int power;
    private String state;

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

    public AbstractEnemy(@NotNull final BlockingQueue<ICharacter> turnsQueue,
                         @NotNull final String name, int weight,
                         int HP, int DP, int power) {
        super(turnsQueue, name, HP, DP);
        this.weight = weight;
        this.power = power;
        this.state = "NORMAL";
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
        character.receiveNormalAttack(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void receiveNormalAttack(IPlayerCharacter character) {
        reduceHP(character.getEquippedWeapon().getPower());
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
     * @return the state of this enemy.
     */
    public String getState() {
        return state;
    }

    /**
     * {@inheritDoc}
     *
     * @param weapon the weapon with which the character is being attacked
     */
    public void receiveFireAttack(IMagicWeapon weapon) {
        this.reduceHP(weapon.getMagicDamage());
        Random rand = new Random();
        double prob = rand.nextDouble();
        if (prob <= 0.2) {
            setBurned();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param weapon the weapon with which the character is being attacked
     */
    public void receiveThunderAttack(IMagicWeapon weapon) {
        this.reduceHP(weapon.getMagicDamage());
        Random rand = new Random();
        double prob = rand.nextDouble();
        if (prob <= 0.3) {
            setParalyzed();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param character the attacking character
     */
    public void receivePoisonAttack(IMagicCharacter character) {
        setPoisoned();
    }

    /**
     * {@inheritDoc}
     *
     * @param character the attacking character
     */
    public void receiveParalysisAttack(IMagicCharacter character) {
        setParalyzed();
    }


    /**
     * {@inheritDoc}
     */
    public void setBurned() {
        state = "BURNED";
    }

    /**
     * {@inheritDoc}
     */
    public void setParalyzed() {
        state = "PARALYZED";
    }

    /**
     * {@inheritDoc}
     */
    public void setPoisoned() {
        state = "POISONED";
    }


}
