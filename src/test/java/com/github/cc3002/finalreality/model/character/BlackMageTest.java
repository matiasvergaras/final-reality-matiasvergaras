package com.github.cc3002.finalreality.model.character;
import com.github.matiasvergaras.finalreality.model.character.player.Magic.BlackMage;
import com.github.matiasvergaras.finalreality.model.character.player.Magic.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test basics features of a Black Mage Character.
 *
 * @author Matias Vergara Silva.
 */

class BlackMageTest extends AbstractPlayerCharacterTest {

    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        super.basicSetUp();
        super.playerSetUp();
        testPlayerCharacters.add(new BlackMage(turns, BLACK_MAGE_NAME, 200, 100, 250));
        testWeapons.add(EXAMPLE_KNIFE);
        testWeapons.add(EXAMPLE_STAFF);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        testCharacters.add(new BlackMage(turns, BLACK_MAGE_NAME, 200, 100, 250));
        super.checkConstruction(new BlackMage(turns, BLACK_MAGE_NAME, 200, 100, 250),
                testCharacters.get(0),
                new BlackMage(turns, BLACK_MAGE_NAME, 110, 200, 250),
                new WhiteMage(turns, WHITE_MAGE_NAME, 200, 100, 250));
    }

    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    @Test
    void waitTurnTest() {
        BlackMage TEST_BLACK_MAGE = new BlackMage(turns, BLACK_MAGE_NAME, 200, 100, 250);
        TEST_BLACK_MAGE.equip(EXAMPLE_STAFF);
        testCharacters.add(TEST_BLACK_MAGE);
        super.checkTurns();
    }

    /**
     * Checks that this Black Mage character starts without any weapon,
     * and that he can equip Knives (Knife) and Staves (Staff).
     * @see BlackMage
     */
    @Test
    void equipWeaponTest() {
        super.checkEquipWeapon();
    }




}

