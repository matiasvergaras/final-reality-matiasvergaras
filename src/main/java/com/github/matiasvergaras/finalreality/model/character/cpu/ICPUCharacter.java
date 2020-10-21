package com.github.matiasvergaras.finalreality.model.character.cpu;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;

/**
 *
 * This represent a CPU-controlled character from the game.
 * <p>
 * CPU characters are enemies. They behaves as an ICharacter.
 * They have two special fields
 * ''weight'' that does the work of define their time in the turnsQueue and
 * ''damage'', that does the work of the equipped weapon damage.
 * @see ICharacter
 * @since Homework 1
 * @author Matías Vergara Silva
 */
public interface ICPUCharacter extends ICharacter {
    /**
     * Get the power of this enemy.
     *
     * @return the power of this enemy.
     */
    int getPower();

    /**
     * Get the state of this enemy.
     *
     * @return the state of this enemy.
     */
    String getState();

    /**
     * Get the weight of this enemy.
     *
     * @return the weight of this enemy.
     */
    int getWeight();

    /**
     * Performs a normal attack against a player's character.
     *
     * @param character the character to be attacked.
     */
    void normalAttack(IPlayerCharacter character);

    /**
     * Receive a normal attack from a player's character.
     *
     * @param character the attacking character.
     */
    void receiveNormalAttack(IPlayerCharacter character);


}
