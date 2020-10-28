package com.github.cc3002.finalreality.model.character;

import com.github.matiasvergaras.finalreality.model.character.player.magic.BlackMage;
import com.github.matiasvergaras.finalreality.model.character.player.magic.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test basics features of a Black Mage Character.
 * @since Homework 1
 * @author Matias Vergara Silva.
 */

class BlackMageTest extends AbstractMagicCharacterTest {

    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        super.basicSetUp();
        super.playerSetUp();
        testPlayerCharacters.add(new BlackMage(turns, BLACK_MAGE_NAME, 200, 100, 250));
        testWeapons.add(exampleKnife);
        testWeapons.add(exampleStaff);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        testCharacters.add(new BlackMage(turns, BLACK_MAGE_NAME, 200, 100, 250));
        super.checkConstruction(new BlackMage(turns, BLACK_MAGE_NAME, 200, 100, 250),
                testCharacters.get(0),
                new BlackMage(turns, "Another Black Mage", 110, 200, 250),
                new WhiteMage(turns, WHITE_MAGE_NAME, 200, 100, 250),
                new WhiteMage(turns, BLACK_MAGE_NAME, 200, 100, 250));
    }

    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    @Test
    void waitTurnTest() {
        BlackMage TEST_BLACK_MAGE = new BlackMage(turns, BLACK_MAGE_NAME, 200, 100, 250);
        TEST_BLACK_MAGE.equip(exampleStaff);
        testCharacters.add(TEST_BLACK_MAGE);
        super.checkTurns();
    }

    /**
     * Checks that this Black Mage character starts without any weapon,
     * and that he can equip Knives (Knife) and Staves (Staff).
     *
     * @see BlackMage
     */
    @Test
    void equipWeaponTest() {
        super.checkEquipWeapon();
    }

    /**
     * Check that the getters methods works properly.
     */
    @Test
    void gettersTest() {
        checkGetMaxMana(exampleBlackMage, MANA);
        checkGetCurrentMana(exampleBlackMage, MANA);
        super.checkGetMaxHP(exampleBlackMage, HP);
        super.checkGetMaxDP(exampleBlackMage, DP);
        super.checkGetCurrentHP(exampleBlackMage, HP);

    }

}

