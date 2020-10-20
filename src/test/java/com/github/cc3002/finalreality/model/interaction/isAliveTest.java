package com.github.cc3002.finalreality.model.interaction;

import com.github.matiasvergaras.finalreality.model.character.cpu.Enemy;
import com.github.matiasvergaras.finalreality.model.character.player.magic.BlackMage;
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
    protected BlackMage ElmuErtito = new BlackMage(turns, "Elmu Ertito", 0, 200, 123);
    protected Enemy UncaDaver = new Enemy(turns, "Unca Daver", 0, 200, 200, 200);
    @BeforeEach
    void setUp(){
        super.turnSetUp();
    }

    @Test
    void deadPlayerCharacterTest(){
        checkStartAlive(ElmuErtito);

    }

    @Test
    void deadCPUCharacterTest(){
        checkStartAlive(UncaDaver);
    }

    @Test
    void BlackMageDyingTest(){
        checkStartAlive(exampleBlackMage);
        checkPlayerDies(exampleEnemy, exampleBlackMage);
        checkCPUDies(exampleEnemy, exampleBlackMage, powerfulStaff);
    }
    @Test
    void WhiteMageDyingTest(){
        checkStartAlive(exampleWhiteMage);
        checkPlayerDies(exampleEnemy, exampleWhiteMage);
    }
    @Test
    void EngineerDyingTest(){
        checkStartAlive(exampleEngineer);
        checkPlayerDies(exampleEnemy, exampleEngineer);
    }
    @Test
    void ThiefDyingTest(){
        checkStartAlive(exampleThief);
        checkPlayerDies(exampleEnemy, exampleThief);

    }
    @Test
    void KnightDyingTest(){
        checkStartAlive(exampleKnight);
        checkPlayerDies(exampleEnemy, exampleKnight);

    }

}
