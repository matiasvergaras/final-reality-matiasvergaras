package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.abstractModelTest;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * Abstract class containing the common tests for all types of  playable characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author Matias Vergara Silva.
 * @see ICharacter
 * @since Homework 1
 */

public abstract class AbstractCharacterTest extends abstractModelTest {
    protected List<ICharacter> testCharacters;
    protected static final String BLACK_MAGE_NAME = "Airi";
    protected static final String ENGINEER_NAME = "Cid";
    protected static final String KNIGHT_NAME = "Adelbert";
    protected static final String THIEF_NAME = "Zidane";
    protected static final String WHITE_MAGE_NAME = "Eiko";
    protected List<IWeapon> testWeapons;
    protected List<IPlayerCharacter> testPlayerCharacters;

    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    void checkTurns() {
        Assertions.assertTrue(turns.isEmpty());
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


    /**
     * Checks that the class' constructor and equals method works properly.
     *
     * @param expectedCharacter           A character of this type
     * @param testEqualCharacter          A character of this type and with the same common fields
     *                                    of expectedCharacter
     * @param sameClassDifferentCharacter A character of this type with at least one field different
     *                                    from those of expectedCharacter
     * @param differentClassDifferentName A character from another type with the same common  fields of
     *                                    expectedCharacters
     * @param differentClassSameName      A character with the same name but from a different class
     */
    protected void checkConstruction(final ICharacter expectedCharacter,
                                     final ICharacter testEqualCharacter,
                                     final ICharacter sameClassDifferentCharacter,
                                     final ICharacter differentClassDifferentName,
                                     final ICharacter differentClassSameName) {
        assertEquals(expectedCharacter, expectedCharacter);
        assertEquals(expectedCharacter, testEqualCharacter, "ExpectedCharacter differs from EqualCharacter.");
        assertNotEquals(sameClassDifferentCharacter, testEqualCharacter, "sameClassDifferentCharacter is" +
                " equal to EqualCharacter.");
        assertNotEquals(testEqualCharacter, differentClassDifferentName, "EqualCharacter is equal to " +
                "differentClassDifferentName.");
        assertEquals(expectedCharacter.hashCode(), testEqualCharacter.hashCode(), "expectedCharacter and" +
                " EqualCharacter hashcode are different.");
        assertNotEquals(expectedCharacter.hashCode(), differentClassDifferentName.hashCode(), "expectedCharacter" +
                " and differentClassDifferentName hashCode are equals.");
        //To test that the hashcode depends only on the name, and not on the class.
        // In practice, we have to avoid to fall in this case.
        assertEquals(expectedCharacter.hashCode(), differentClassSameName.hashCode(), "expectedCharacter and" +
                "differentClassSameName hashcode are not equals, but they should be since hashcode depends on name.");

    }

    /**
     * Sets the basic set up for a test of characters.
     */
    protected void basicSetUp() {
        turnSetUp();
        testCharacters = new ArrayList<>();
    }

    /**
     * Checks that the Player Character getMaxHP method works properly.
     *
     * @param character     the character to be tested
     * @param expectedMaxHP the expected value of Max HP of this character.
     */
    protected void checkGetMaxHP(ICharacter character, int expectedMaxHP) {
        assertEquals(character.getMaxHP(), expectedMaxHP);
    }

    /**
     * Checks that the Player Character getMaxDP method works properly.
     *
     * @param character  the Player Character to be tested
     * @param expectedDP the expected value of DP of this character.
     */
    protected void checkGetMaxDP(ICharacter character, int expectedDP) {
        assertEquals(character.getDP(), expectedDP);
    }

    /**
     * Checks that the Player Character getCurrentHP method works properly.
     * Attention: In each character class we will test only that the value starts at max HP.
     * The loss of points when receiving attacks will be tested in the interactions package.
     *
     * @param character  the Player Character to be tested
     * @param expectedHP the expected value of Current HP of this character.
     */
    protected void checkGetCurrentHP(ICharacter character, int expectedHP) {
        assertEquals(character.getCurrentHP(), expectedHP);
    }

    //Attention: the getCurrentHP method is tested in 'interaction' package, since
    //it requires attacks.
}
