package com.github.matiasvergaras.finalreality.model.character.player.Magic;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;

public interface IMagicCharacter {
    int getMagicDamage();

    /**
     * Performs a Fire attack
     * @param character the character to be attacked.
     *
     */
    void FireAttack(ICharacter character);

    /**
     * Performs a Thunder attack
     * @param character the character to be attacked.
     *
     */
    void ThunderAttack(ICharacter character);

    /**
     * Performs a Poison attack
     * @param character the character to be attacked.
     *
     */
    void PoisonAttack(ICharacter character);

    /**
     * Performs a Heal 'attack'
     * @param character the character to be attacked.
     *
     */
    void HealAlly(ICharacter character);


}
