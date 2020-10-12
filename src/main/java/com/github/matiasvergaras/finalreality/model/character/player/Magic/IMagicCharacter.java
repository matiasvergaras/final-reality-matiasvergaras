package com.github.matiasvergaras.finalreality.model.character.player.magic;

import com.github.matiasvergaras.finalreality.model.character.cpu.IEnemy;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;

public interface IMagicCharacter extends IPlayerCharacter {

    /**
     * Performs a Fire Spell attack
     *
     * @param enemy the enemy to be attacked.
     */
    void useFireSpell(IEnemy enemy);

    /**
     * Performs a Thunder Spell attack
     *
     * @param enemy the enemy to be attacked.
     */
    void useThunderSpell(IEnemy enemy);

    /**
     * Performs a Poison Spell attack
     *
     * @param enemy the enemy to be attacked.
     */
    void usePoisonSpell(IEnemy enemy);

    /**
     * Performs a Paralysis Spell attack
     *
     * @param enemy the enemy to be attacked.
     */
    void useParalysisSpell(IEnemy enemy);


    /**
     * Performs a Heal Spell to an ally
     *
     * @param ally the player character to be attacked.
     */
    void useHealSpell(IPlayerCharacter ally);

}
