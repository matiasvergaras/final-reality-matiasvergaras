package com.github.matiasvergaras.finalreality.model.character;

import com.github.matiasvergaras.finalreality.model.character.player.normal.Knight;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test basics features of a Knight Character.
 * @since Homework 1
 * @author Matias Vergara Silva.
 */

class KnightTest extends AbstractPlayerCharacterTest {

    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        super.basicSetUp();
        super.playerSetUp();
        testPlayerCharacters.add(new Knight(turns, KNIGHT_NAME, 200, 100));
        testWeapons.add(exampleAxe);
        testWeapons.add(exampleKnife);
        testWeapons.add(exampleSword);
    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        testCharacters.add(new Knight(turns, KNIGHT_NAME, 200, 100));
        checkConstruction(new Knight(turns, KNIGHT_NAME, 200, 100),
                testCharacters.get(0),
                new Knight(turns, "Another Knight", 11, 200),
                new Thief(turns, THIEF_NAME, 11, 200),
                new Thief(turns, KNIGHT_NAME, 11, 200));
    }

    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    @Test
    void waitTurnTest() {
        Knight TEST_KNIGHT = new Knight(turns, KNIGHT_NAME, 200, 100);
        TEST_KNIGHT.equip(exampleAxe);
        testCharacters.add(TEST_KNIGHT);
        super.checkTurns();
    }

    /**
     * Checks that this Knight character starts without any weapon,
     * and that he can equip Axes, Knives (Knife) and Swords.
     *
     * @see Knight
     */
    @Test
    void equipWeaponTest() {
        super.checkEquipWeapon();
    }

    /**
     * Check that the getters methods works properly.
     */
    @Test
    void gettersTest() {
        super.checkGetMaxHP(exampleKnight, HP);
        super.checkGetMaxDP(exampleKnight, DP);
        super.checkGetCurrentHP(exampleKnight, HP);
    }

    /**
     * Checks that the attack power attribute is calculated correctly.
     */
    @Test
    void attackPowerTest(){
        checkGetAttackPower(exampleKnight, exampleSword);
    }


}