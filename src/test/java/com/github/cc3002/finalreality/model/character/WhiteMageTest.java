package com.github.cc3002.finalreality.model.character;
import com.github.matiasvergaras.finalreality.model.character.player.Magic.BlackMage;
import com.github.matiasvergaras.finalreality.model.character.player.Magic.WhiteMage;
import com.github.matiasvergaras.finalreality.model.weapon.Magic.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WhiteMageTest extends AbstractPlayerCharacterTest {

    private static final String WHITE_MAGE_NAME = "Eiko";
    private static final Staff EXAMPLE_STAFF = new Staff("Example Staff", 200, 15, 250);

    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        testCharacters.add(new WhiteMage(turns, WHITE_MAGE_NAME, 200, 100,250));
        checkConstruction(new WhiteMage(turns, WHITE_MAGE_NAME, 200, 100, 250),
                testCharacters.get(0),
                new WhiteMage(turns, WHITE_MAGE_NAME, 11, 200, 250),
                new BlackMage(turns, WHITE_MAGE_NAME, 11, 200, 250));
    }

    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    @Test
    void waitTurnTest() {
        BlackMage TEST_BLACK_MAGE = new BlackMage(turns, WHITE_MAGE_NAME, 200, 100, 250);
        TEST_BLACK_MAGE.equip(EXAMPLE_STAFF);
        testCharacters.add(TEST_BLACK_MAGE);
        super.checkTurns();
    }

}