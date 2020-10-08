package com.github.cc3002.finalreality.model.character;
import com.github.matiasvergaras.finalreality.model.character.player.Normal.Knight;
import com.github.matiasvergaras.finalreality.model.character.player.Normal.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KnightTest extends AbstractPlayerCharacterTest {

    private static final String KNIGHT_NAME = "Adelbert";

    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
        testCharacters.add(new Knight(turns, KNIGHT_NAME, 200, 100));
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        checkConstruction(new Knight(turns, KNIGHT_NAME, 10, 200),
                testCharacters.get(0),
                new Knight(turns, KNIGHT_NAME, 11, 200),
                new Thief(turns, KNIGHT_NAME, 11, 200));
    }
}