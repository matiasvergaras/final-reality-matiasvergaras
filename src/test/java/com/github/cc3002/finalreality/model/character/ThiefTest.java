package com.github.cc3002.finalreality.model.character;

import com.github.matiasvergaras.finalreality.model.character.player.normal.Engineer;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test basics features of a Thief Character.
 * @since Homework 1
 * @author Matias Vergara Silva.
 */

public class ThiefTest extends AbstractPlayerCharacterTest {

    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        super.basicSetUp();
        super.playerSetUp();
        testPlayerCharacters.add(new Thief(turns, THIEF_NAME, 200, 100));
        testWeapons.add(exampleKnife);
        testWeapons.add(exampleSword);
        testWeapons.add(exampleBow);
    }

    /**
     * Checks that the class constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        testCharacters.add(new Thief(turns, THIEF_NAME, 200, 100));
        super.checkConstruction(new Thief(turns, THIEF_NAME, 200, 100),
                testCharacters.get(0),
                new Thief(turns, "Another Thief", 110, 200),
                new Engineer(turns, ENGINEER_NAME, 200, 100),
                new Engineer(turns, THIEF_NAME, 200, 100));
    }

    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    @Test
    void waitTurnTest() {
        Thief TEST_THIEF = new Thief(turns, THIEF_NAME, 200, 100);
        TEST_THIEF.equip(exampleKnife);
        testCharacters.add(TEST_THIEF);
        super.checkTurns();
    }

    /**
     * Checks that this Thief character starts without any weapon,
     * and that he can equip Knives (Knife), Swords and Bows.
     *
     * @see Thief
     */
    @Test
    void equipWeaponTest() {
        super.checkEquipWeapon();
    }


}