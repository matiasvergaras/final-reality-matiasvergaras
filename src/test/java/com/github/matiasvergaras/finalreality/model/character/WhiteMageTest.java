package com.github.matiasvergaras.finalreality.model.character;

import com.github.matiasvergaras.finalreality.model.character.player.magic.BlackMage;
import com.github.matiasvergaras.finalreality.model.character.player.magic.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test basics features of a White Mage Character.
 * @since Homework 1
 * @author Matias Vergara Silva.
 */

class WhiteMageTest extends AbstractMagicCharacterTest {

    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        super.basicSetUp();
        super.playerSetUp();
        testPlayerCharacters.add(new WhiteMage(turns, WHITE_MAGE_NAME, 200, 100, 250));
        testWeapons.add(exampleStaff);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        testCharacters.add(new WhiteMage(turns, WHITE_MAGE_NAME, 200, 100, 250));
        checkConstruction(new WhiteMage(turns, WHITE_MAGE_NAME, 200, 100, 250),
                testCharacters.get(0),
                new WhiteMage(turns, "Another White Mage", 11, 200, 250),
                new BlackMage(turns, BLACK_MAGE_NAME, 11, 200, 250),
                new BlackMage(turns, WHITE_MAGE_NAME, 11, 200, 250));
    }

    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    @Test
    void waitTurnTest() {
        WhiteMage TEST_WHITE_MAGE = new WhiteMage(turns, WHITE_MAGE_NAME, 200, 100, 250);
        TEST_WHITE_MAGE.equip(exampleStaff);
        testCharacters.add(TEST_WHITE_MAGE);
        super.checkTurns();
    }

    /**
     * Checks that this White Mage character starts without any weapon,
     * and that he can equip Staves (Staff).
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
        super.checkGetMaxHP(exampleWhiteMage, HP);
        super.checkGetMaxDP(exampleWhiteMage, DP);
        super.checkGetCurrentHP(exampleWhiteMage, HP);

    }


}