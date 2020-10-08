package com.github.cc3002.finalreality.model.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


import com.github.matiasvergaras.finalreality.model.character.player.AbstractPlayerCharacter;
import java.util.List;

import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Set of tests for the {@code GameCharacter} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Matias Vergara Silva.
 * @see AbstractPlayerCharacter
 */
public abstract class AbstractPlayerCharacterTest extends AbstractCharacterTest {
    protected List<IPlayerCharacter> testCharacters;
    protected IWeapon testWeapon;

    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    @Test
    void waitTurnTest() {
        Assertions.assertTrue(turns.isEmpty());
        tryToEquip(testCharacters.get(0));
        testCharacters.get(0).waitTurn();
        try {
            // Thread.sleep is not accurate so this values may be changed to adjust the
            // acceptable error margin.
            // We're testing that the character waits approximately 1 second.
            Thread.sleep(900);
            Assertions.assertEquals(0, turns.size());
            Thread.sleep(200);
            Assertions.assertEquals(1, turns.size());
            Assertions.assertEquals(testCharacters.get(0), turns.peek());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void tryToEquip(IPlayerCharacter character) {
        character.equip(testWeapon);
    }

    /**
     * Sets up the basics to test this class.
     */
    @BeforeEach
    void setUp() {
        super.basicSetUp();
    }

    /**
     * Checks that the class' equip method works properly.
     */
    @Test
    void equipWeaponTest() {
        for (var character :
                testCharacters) {
            assertNull(character.getEquippedWeapon());
            character.equip(testWeapon);
            assertEquals(testWeapon, character.getEquippedWeapon());
        }
    }
}
