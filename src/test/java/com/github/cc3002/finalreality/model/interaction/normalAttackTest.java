package com.github.cc3002.finalreality.model.interaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * <p>
 * A class to test the normal attacks between CPU and Player Characters,
 * using two instances of each type, a weak and a powerful one, and the same for weapons.
 * Weapons can be of the same type or different, but they must be equippable to the given
 * player character.
 * @see abstractNormalAttackTest
 * @author Matias Vergara Silva.
 * @since Homework 2 - Partial 3
 */
public class normalAttackTest extends abstractNormalAttackTest{
    /**
     * Sets up a queue.
     */
    @BeforeEach
    void setUp() {
        super.turnSetUp();
    }

    /**
     * Checks that the interactions between Enemies and Black Mages works properly.
     */
    @Test
    void BlackMageAgainstEnemies(){
        checkInteractions(exampleEnemy, weakEnemy, exampleBlackMage, weakBlackMage, powerfulStaff, weakKnife);
    }

    /**
     * Checks that the interactions between Enemies and White Mages works properly.
     */
    @Test
    void WhiteMageAgainstEnemies(){
        checkInteractions(exampleEnemy, weakEnemy, exampleWhiteMage, weakWhiteMage, powerfulStaff, weakStaff);
    }

    /**
     * Checks that the interactions between Enemies and Knights works properly.
     */
    @Test
    void KnightAgainstEnemies(){
        checkInteractions(exampleEnemy, weakEnemy, exampleKnight, weakKnight, powerfulSword, weakAxe);
    }

    /**
     * Checks that the interactions between Enemies and Engineers works properly.
     */
    @Test
    void EngineerAgainstEnemies(){
        checkInteractions(exampleEnemy, weakEnemy, exampleEngineer, weakEngineer, powerfulAxe, weakBow);
    }

    /**
     * Checks that the interactions between Enemies and Thieves works properly.
     */
    @Test
    void ThiefAgainstEnemies(){
        checkInteractions(exampleEnemy, weakEnemy, exampleThief, weakThief, powerfulKnife, weakBow);
    }

}
