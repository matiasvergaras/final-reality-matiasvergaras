package com.github.matiasvergaras.finalreality.model.character;

import com.github.matiasvergaras.finalreality.model.character.player.normal.Engineer;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test basics features of a Engineer Character.
 * @since Homework 1
 * @author Matias Vergara Silva.
 */

class EngineerTest extends AbstractPlayerCharacterTest {

    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        super.basicSetUp();
        super.playerSetUp();
        testPlayerCharacters.add(new Engineer(turns, ENGINEER_NAME, 200, 100));
        testWeapons.add(exampleAxe);
        testWeapons.add(exampleAxe);

    }

    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        testCharacters.add(new Engineer(turns, ENGINEER_NAME, 100, 200));
        super.checkConstruction(new Engineer(turns, ENGINEER_NAME, 100, 200),
                testCharacters.get(0),
                new Engineer(turns, "Another Engineer", 110, 200),
                new Thief(turns, THIEF_NAME, 100, 200),
                new Thief(turns, ENGINEER_NAME, 100, 200));
    }

    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     */
    @Test
    void waitTurnTest() {
        Engineer TEST_ENGINEER = new Engineer(turns, ENGINEER_NAME, 200, 100);
        TEST_ENGINEER.equip(exampleAxe);
        testCharacters.add(TEST_ENGINEER);
        super.checkTurns();
    }

    /**
     * Checks that this Engineer character starts without any weapon,
     * and that he can equip Axes and Bows.
     *
     * @see Engineer
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
        super.checkGetMaxHP(exampleEngineer, HP);
        super.checkGetMaxDP(exampleEngineer, DP);
        super.checkGetCurrentHP(exampleEngineer, HP);
    }

    /**
     * Checks that the attack power attribute is calculated correctly.
     */
    @Test
    void attackPowerTest(){
        checkGetAttackPower(exampleEngineer, exampleBow);
    }

}