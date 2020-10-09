package com.github.cc3002.finalreality.model.character;
import com.github.matiasvergaras.finalreality.model.character.player.Magic.BlackMage;
import com.github.matiasvergaras.finalreality.model.character.player.Magic.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WhiteMageTest extends AbstractPlayerCharacterTest {

    private static final String WHITE_MAGE_NAME = "Eiko";

    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
        testCharacters.add(new WhiteMage(turns, WHITE_MAGE_NAME, 200, 100,250));
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        checkConstruction(new WhiteMage(turns, WHITE_MAGE_NAME, 200, 100, 250),
                testCharacters.get(0),
                new WhiteMage(turns, WHITE_MAGE_NAME, 11, 200, 250),
                new BlackMage(turns, WHITE_MAGE_NAME, 11, 200, 250));
    }
}