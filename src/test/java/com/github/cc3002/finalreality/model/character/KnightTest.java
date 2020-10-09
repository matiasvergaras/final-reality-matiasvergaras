package com.github.cc3002.finalreality.model.character;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Axe;
import com.github.matiasvergaras.finalreality.model.character.player.Normal.Knight;
import com.github.matiasvergaras.finalreality.model.character.player.Normal.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KnightTest extends AbstractPlayerCharacterTest {

    private static final String KNIGHT_NAME = "Adelbert";
    private static final Axe EXAMPLE_AXE = new Axe("Example Axe", 200, 14);
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
        testCharacters.add(new Knight(turns, KNIGHT_NAME, 200, 100));
        checkConstruction(new Knight(turns, KNIGHT_NAME, 200, 100),
                testCharacters.get(0),
                new Knight(turns, KNIGHT_NAME, 11, 200),
                new Thief(turns, KNIGHT_NAME, 11, 200));
    }

    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    @Test
    void waitTurnTest() {
        Knight TEST_KNIGHT = new Knight(turns, KNIGHT_NAME, 200, 100);
        TEST_KNIGHT.equip(EXAMPLE_AXE);
        testCharacters.add(TEST_KNIGHT);
        super.checkTurns();
    }


}