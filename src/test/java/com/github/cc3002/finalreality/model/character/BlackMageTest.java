package com.github.cc3002.finalreality.model.character;
import com.github.matiasvergaras.finalreality.model.character.player.Magic.BlackMage;
import com.github.matiasvergaras.finalreality.model.character.player.Magic.WhiteMage;
import com.github.matiasvergaras.finalreality.model.weapon.Magic.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BlackMageTest extends AbstractCharacterTest {

    private static final String BLACK_MAGE_NAME = "Ahri";
    private static final Staff EXAMPLE_STAFF = new Staff("Example Staff",
            200, 15, 250);


    /**
     * Method to try to equip a Weapon to a Character inside of the waitTurn Test
     * @param character
     *                  The character to try to equip
     */
    //void tryToEquip(IPlayerCharacter character) {
    //    character.equip(EXAMPLE_STAFF);
    //}

    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        super.basicSetUp();

    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        testCharacters.add(new BlackMage(turns, BLACK_MAGE_NAME, 200, 100, 250));
        super.checkConstruction(new BlackMage(turns, BLACK_MAGE_NAME, 200, 100, 250),
                testCharacters.get(0),
                new BlackMage(turns, BLACK_MAGE_NAME, 11, 200, 250),
                new WhiteMage(turns, BLACK_MAGE_NAME, 200, 100, 250));
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



}

