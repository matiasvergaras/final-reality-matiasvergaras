package com.github.matiasvergaras.finalreality.model.character.cpu;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.magic.IMagicWeapon;

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


    /**
     * Receive a Fire-Spell attack
     *
     * @param weapon the weapon with which the character is being attacked
     */
    void receiveFireAttack(IMagicWeapon weapon);

    /**
     * Receive a Thunder-Spell attack
     *
     * @param weapon the weapon with which the character is being attacked
     */
    void receiveThunderAttack(IMagicWeapon weapon);

    /**
     * Sets this character state to Paralyzed.
     */
    void setParalyzed();

    /**
     * Sets this character state to Burned.
     */
    void setBurned();

    /**
     * Sets this character state to Poisoned.
     */
    void setPoisoned();

}
