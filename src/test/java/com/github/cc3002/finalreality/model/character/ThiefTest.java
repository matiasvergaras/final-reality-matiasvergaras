package com.github.cc3002.finalreality.model.character;
import com.github.matiasvergaras.finalreality.model.character.player.Magic.BlackMage;
import com.github.matiasvergaras.finalreality.model.character.player.Normal.Engineer;
import com.github.matiasvergaras.finalreality.model.character.player.Normal.Thief;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Knife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ThiefTest extends AbstractPlayerCharacterTest {

    private static final String THIEF_NAME = "Zidane";
    private static final Knife EXAMPLE_KNIFE = new Knife("TEST_KNIFE", 200, 12);

    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
    }

    /**
     * Checks that the class constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        testCharacters.add(new Thief(turns, THIEF_NAME, 200, 100));
        super.checkConstruction(new Thief(turns, THIEF_NAME, 200, 100),
                testCharacters.get(0),
                new Thief(turns, THIEF_NAME, 110, 200),
                new Engineer(turns, THIEF_NAME, 110, 200));
    }

    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    @Test
    void waitTurnTest() {
        Thief TEST_THIEF = new Thief(turns, THIEF_NAME, 200, 100);
        TEST_THIEF.equip(EXAMPLE_KNIFE);
        testCharacters.add(TEST_THIEF);
        super.checkTurns();
    }


}