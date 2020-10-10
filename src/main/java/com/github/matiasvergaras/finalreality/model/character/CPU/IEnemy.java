package com.github.matiasvergaras.finalreality.model.character.CPU;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.Magic.IMagicCharacter;

public interface IEnemy extends ICharacter {
    /**
     * Get the power of this enemy.
     * @return the power of this enemy.
     */
     int getPower();

    /**
     * Performs a normal attack against a player's character.
     * @param character
     *                  the character to be attacked.
     */
    void normalAttack(IPlayerCharacter character);

    /**
     * Receive a normal attack from a player's character.
     * @param character
     *                  the attacking character.
     */
    void receiveNormalAttack(IPlayerCharacter character);


    /**
     * Receive a Fire-Spell attack
     * @param character the attacking character.
     *
     */
    void receiveFireAttack(IMagicCharacter character);

    /**
     * Receive a Thunder-Spell attack
     * @param character the attacking character.
     *
     */
    void receiveThunderAttack(IMagicCharacter character);

    /**
     * Receive a Poison-Spell attack
     * @param character the attacking character.
     *
     */
    void receivePoisonAttack(IMagicCharacter character);

    /**
     * Receive a Paralysis-Spell attack
     * @param character the attacking character.
     *
     */
    void receiveParalysisAttack(IMagicCharacter character);

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
