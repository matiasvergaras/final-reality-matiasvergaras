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
     * Adds this character to a mastermind party's.
     * @param mastermind        The mastermind that will receive the character in its party.
     */
    public void addToParty(IMastermind mastermind);

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

    /**
     * To get all the attributes of this character together, adding those
     * exclusives of an CPU character.
     * @return An ArrayList of Integer whit the attributes in the following
     * order: maxHP, currentHP, DP, weight, power.
     */
    Map<String, Object> getAttributes();


}
