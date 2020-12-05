package com.github.matiasvergaras.finalreality.model.interaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * A class to test that all characters starts alive, and they can get to die when they receive attacks.
 * @see abstractIsAliveTest
 * @author Matias Vergara Silva.
 * @since Homework 2 - Partial 3
 */
public class isAliveTest extends abstractIsAliveTest {
    @BeforeEach
    void setUp(){
        super.turnSetUp();
    }

    /**
     * To test that player characters are created alive
     */
    @Test
    void deadPlayerCharacterTest(){
        checkStartAlive(deadBlackMage);

    }

    /**
     * To test that CPU characters are created alive
     */
    @Test
    void deadCPUCharacterTest(){
        checkStartAlive(deadEnemy);
    }

    /**
     * To test that Black Mage characters can die
     */
    @Test
    void BlackMageDyingTest(){
        checkStartAlive(exampleBlackMage);
        checkPlayerDies(exampleEnemy, exampleBlackMage);
    }

    /**
     * To test that White Mage characters can die
     */
    @Test
    void WhiteMageDyingTest(){
        checkStartAlive(exampleWhiteMage);
        checkPlayerDies(exampleEnemy, exampleWhiteMage);
    }

    /**
     * To test that Engineer characters can die
     */
    @Test
    void EngineerDyingTest(){
        checkStartAlive(exampleEngineer);
        checkPlayerDies(exampleEnemy, exampleEngineer);
    }

    /**
     * To test that Thief characters can die
     */
    @Test
    void ThiefDyingTest(){
        checkStartAlive(exampleThief);
        checkPlayerDies(exampleEnemy, exampleThief);

    }

    /**
     * To test that Knight characters can die
     */
    @Test
    void KnightDyingTest(){
        checkStartAlive(exampleKnight);
        checkPlayerDies(exampleEnemy, exampleKnight);

    }

    /**
     * To test that Enemy characters can die
     */
    @Test
    void EnemyDyingTest(){
        checkStartAlive(exampleEnemy);
        checkCPUDies(exampleEnemy, exampleKnight, powerfulKnife);
    }



}
