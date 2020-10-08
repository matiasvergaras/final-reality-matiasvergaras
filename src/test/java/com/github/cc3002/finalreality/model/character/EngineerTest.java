package com.github.cc3002.finalreality.model.character;
import com.github.matiasvergaras.finalreality.model.character.player.Normal.Engineer;
import com.github.matiasvergaras.finalreality.model.character.player.Normal.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EngineerTest extends AbstractPlayerCharacterTest {

    private static final String ENGINEER_NAME = "Cid";

    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
        testCharacters.add(new Engineer(turns, ENGINEER_NAME, 200, 100));
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        checkConstruction(new Engineer(turns, ENGINEER_NAME, 10, 200),
                testCharacters.get(0),
                new Engineer(turns, ENGINEER_NAME, 11, 200),
                new Thief(turns, ENGINEER_NAME, 11, 200));
    }
}