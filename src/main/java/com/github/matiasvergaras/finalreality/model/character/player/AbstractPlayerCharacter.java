package com.github.matiasvergaras.finalreality.model.character.player;

import com.github.matiasvergaras.finalreality.model.character.AbstractCharacter;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.cpu.ICPUCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * A class that holds all the information of all Player Characters of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Matias Vergara Silva.
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements IPlayerCharacter {
    protected IWeapon equippedWeapon;

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


    public void normalAttack(ICPUCharacter character) {
        if (character.isAlive()) {
            character.receiveNormalAttack(this);
        }
        else{
            System.out.println("Attack not possible: \n" +
                    "You cannot attack someone who has already died!");
        }
    }

    /**
     * Receive a non-magic attack
     *
     * @param character the attacking character.
     */
    public void receiveNormalAttack(ICPUCharacter character) {
        this.reduceHP(character.getPower()- getMaxDP());
    }
}


