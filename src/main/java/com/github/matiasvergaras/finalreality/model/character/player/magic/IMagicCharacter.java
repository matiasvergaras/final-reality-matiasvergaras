package com.github.matiasvergaras.finalreality.model.character.player.magic;

import com.github.matiasvergaras.finalreality.model.character.cpu.ICPUCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;

/**
 *
 * This represent a Magic Character character from the game.
 * <p>
 * They are part of the normal characters, but they can use magic.
 * They are divided into black and white magicians, and can use
 * Health / Poison / Paralysis - Thunder / Fire magic respectively.
 * @see IPlayerCharacter
 * @author Matías Vergara Silva
 */
public interface IMagicCharacter extends IPlayerCharacter {

    /**
     * Performs a Fire Spell attack
     *
     * @param enemy the enemy to be attacked.
     */
    void useFireSpell(ICPUCharacter enemy);

    /**
     * Performs a Thunder Spell attack
     *
     * @param enemy the enemy to be attacked.
     */
    void useThunderSpell(ICPUCharacter enemy);

    /**
     * Performs a Poison Spell attack
     *
     * @param enemy the enemy to be attacked.
     */
    void usePoisonSpell(ICPUCharacter enemy);

    /**
     * Performs a Paralysis Spell attack
     *
     * @param enemy the enemy to be attacked.
     */
    void useParalysisSpell(ICPUCharacter enemy);


    /**
     * Performs a Heal Spell to an ally
     *
     * @param ally the player character to be attacked.
     */
    void useHealSpell(IPlayerCharacter ally);

}
