package com.github.cc3002.finalreality.model.character;
import com.github.matiasvergaras.finalreality.model.character.player.Magic.BlackMage;
import com.github.matiasvergaras.finalreality.model.character.player.Magic.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackMageTest extends AbstractPlayerCharacterTest {

    private static final String BLACK_MAGE_NAME = "Eiko";

    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
        testCharacters.add(new BlackMage(turns, BLACK_MAGE_NAME, 200, 100,250));
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void checkConstruction() {
        super.checkConstruction(new BlackMage(turns, BLACK_MAGE_NAME, 200, 100, 250),
                testCharacters.get(0),
                new BlackMage(turns, BLACK_MAGE_NAME, 11, 200, 250),
                new WhiteMage(turns, BLACK_MAGE_NAME, 200, 100, 250));
    }
}

