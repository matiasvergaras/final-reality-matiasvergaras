package com.github.cc3002.finalreality.model.character;

import com.github.matiasvergaras.finalreality.model.character.player.magic.IMagicCharacter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Abstract class containing the common tests for all playable magic characters.
 * @since Homework 2 - Partial 3
 * @author Matias Vergara Silva.
 * @see IMagicCharacter
 */

public abstract class AbstractMagicCharacterTest extends AbstractPlayerCharacterTest {

    /**
     * Checks that the Magic Character getMaxMana method works properly.
     *
     * @param character        the Magic Character to be tested
     * @param expectedMaxMana     the expected value of mana of this character.
     */
    protected void checkGetMaxMana(IMagicCharacter character, int expectedMaxMana) {
        assertEquals(character.getMaxMana(), expectedMaxMana);
    }

    /**
     * Checks that the Magic Character getCurrentMana method works properly.
     * Attention: In each magic character class we will test only that the value starts at max Mana.
     * The loss of points when performing magic attacks will be tested in the interactions package,
     * once we develop magic interactions.
     * @param character        the Magic Character to be tested
     * @param expectedMana     the expected value of mana of this character.
     */
    protected void checkGetCurrentMana(IMagicCharacter character, int expectedMana) {
        assertEquals(character.getCurrentMana(), expectedMana);
    }


}
