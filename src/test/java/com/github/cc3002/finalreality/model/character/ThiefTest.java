package com.github.cc3002.finalreality.model.character;
import com.github.matiasvergaras.finalreality.model.character.player.Normal.Engineer;
import com.github.matiasvergaras.finalreality.model.character.player.Normal.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ThiefTest extends AbstractPlayerCharacterTest {

    private static final String THIEF_NAME = "Zidane";

    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
        testCharacters.add(new Thief(turns, THIEF_NAME, 200, 100));
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        checkConstruction(new Thief(turns, THIEF_NAME, 10, 200),
                testCharacters.get(0),
                new Thief(turns, THIEF_NAME, 11, 200),
                new Engineer(turns, THIEF_NAME, 11, 200));
    }
}