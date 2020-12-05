package com.github.matiasvergaras.finalreality.model.character.cpu;

import com.github.matiasvergaras.finalreality.model.Mastermind.IMastermind;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;

import java.util.Map;

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
     * Get the weight of this enemy.
     *
     * @return the weight of this enemy.
     */
    int getWeight();

    /**
     * To get all the attributes of this character together, adding those
     * exclusives of an CPU character.
     * @return An ArrayList of Integer whit the attributes in the following
     * order: maxHP, currentHP, DP, weight, power.
     */
    Map<String, Object> getAttributes();


}
