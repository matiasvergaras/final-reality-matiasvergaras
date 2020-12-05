package com.github.matiasvergaras.finalreality.model.character.player.magic;
import com.github.matiasvergaras.finalreality.model.AttributeSet;
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
     * @return the max mana points of this character
     * @see IMagicCharacter
     */
    int getMaxMana();

    /**
     * @return the actual mana points of this character
     * @see IMagicCharacter
     */
    int getCurrentMana();

    /**
     * To get all the attributes of this character together,
     * adding the maxMana and the currentMana to those of
     * the parent class.
     * @return An ArrayList of Integer whit the attributes in the following
     * order: maxHP, currentHP, DP, maxMana, currentMana
     */
    @Override
    AttributeSet getAttributes();


    //Magic attacks not implemented yet.
    //void reduceMana(int diff);
}