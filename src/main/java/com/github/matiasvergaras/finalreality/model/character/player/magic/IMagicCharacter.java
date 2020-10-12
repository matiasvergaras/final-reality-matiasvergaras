package com.github.matiasvergaras.finalreality.model.character.player.magic;

import com.github.matiasvergaras.finalreality.model.character.cpu.IEnemyCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;

public interface IMagicCharacter extends IPlayerCharacter {

    /**
     * Performs a Fire Spell attack
     *
     * @param enemy the enemy to be attacked.
     */
    void useFireSpell(IEnemyCharacter enemy);

    /**
     * Performs a Thunder Spell attack
     *
     * @param enemy the enemy to be attacked.
     */
    void useThunderSpell(IEnemyCharacter enemy);

    /**
     * Performs a Poison Spell attack
     *
     * @param enemy the enemy to be attacked.
     */
    void usePoisonSpell(IEnemyCharacter enemy);

    /**
     * Performs a Paralysis Spell attack
     *
     * @param enemy the enemy to be attacked.
     */
    void useParalysisSpell(IEnemyCharacter enemy);


    /**
     * Performs a Heal Spell to an ally
     *
     * @param ally the player character to be attacked.
     */
    void useHealSpell(IPlayerCharacter ally);

}
