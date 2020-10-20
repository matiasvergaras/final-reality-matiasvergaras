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

    //Magic attacks not implemented yet.
    //int getCurrentMana();

    //void reduceMana(int diff);


}