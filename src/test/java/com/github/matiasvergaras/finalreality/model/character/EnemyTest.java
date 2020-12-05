package com.github.matiasvergaras.finalreality.model.character;

import com.github.matiasvergaras.finalreality.model.character.cpu.Enemy;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Engineer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test basics features of a Enemy Character.
 * @since Homework 1
 * @author Matias Vergara Silva.
 */

class EnemyTest extends AbstractCPUCharacterTest {

    private static final String ENEMY_NAME = "Goblin";

    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        super.basicSetUp();
        testCharacters.add(new Enemy(turns, ENEMY_NAME, 10, 200, 100, 200));
    }


    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        checkConstruction(new Enemy(turns, ENEMY_NAME, 10, 200, 100, 200),
                testCharacters.get(0),
                new Enemy(turns, "Another Enemy", 11, 200, 100, 200),
                new Engineer(turns, ENGINEER_NAME, 200, 100),
                new Engineer(turns, ENEMY_NAME, 200, 100));
    }

    /**
     * Checks that this Enemy character waits the appropriate amount of time for it's turn.
     */
    @Test
    void waitTurnTest() {
        super.checkTurns();
    }

    /**
     * Check that the getters methods works properly.
     */
    @Test
    void gettersTest() {
        checkGetPower(exampleEnemy, POWERFUL_DAMAGE);
        checkGetWeight(exampleEnemy, WEIGHT);
        super.checkGetMaxHP(exampleEnemy, HP);
        super.checkGetMaxDP(exampleEnemy, DP);
        super.checkGetCurrentHP(exampleEnemy, HP);
    }

}