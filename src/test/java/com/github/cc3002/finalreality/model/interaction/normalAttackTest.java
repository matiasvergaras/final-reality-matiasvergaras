package com.github.cc3002.finalreality.model.interaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * <p>
 * A class to test the normal attacks between CPU and Player Characters,
 * using two instances of each type, a weak and a powerful one, and the same for weapons.
 * Weapons can be of the same type or different, but they must be equippable to the given
 * player character.
 * <p>
 * The tests are divided in two directions: From character to enemy and from enemy to character.
 * This is to avoid having to create copies of the characters involved, since there is no copy method,
 * and creating more instances would mess up the code too much. 
 * </p>
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
     * Side: From Black Mage to Enemy
     */
    @Test
    void BlackMageAgainstEnemies(){
        checkEffectiveNormalPlayerAttack(exampleEnemy, exampleBlackMage, powerfulStaff);
        checkIneffectiveNormalPlayerAttack(exampleEnemy, exampleBlackMage, weakKnife);

    }


    /**
     * Checks that the interactions between Enemies and Black Mages works properly.
     * Side: From Enemy to Black Mage
     */
    @Test
    void EnemyAgainstBlackMage(){
        checkEffectiveNormalCPUAttack(exampleEnemy, weakBlackMage);
        checkIneffectiveNormalCPUAttack(weakEnemy, exampleBlackMage);
    }



    /**
     * Checks that the interactions between Enemies and White Mages works properly.
     * Side: From White Mage to Enemy
     */
    @Test
    void WhiteMageAgainstEnemies(){
        checkEffectiveNormalPlayerAttack(exampleEnemy, exampleWhiteMage, powerfulStaff);
        checkIneffectiveNormalPlayerAttack(exampleEnemy, exampleWhiteMage, weakStaff);

    }

    /**
     * Checks that the interactions between Enemies and White Mages works properly.
     * Side: From Enemy to White Mage
     */
    @Test
    void EnemyAgainstWhiteMage(){
        checkEffectiveNormalCPUAttack(exampleEnemy, weakWhiteMage);
        checkIneffectiveNormalCPUAttack(weakEnemy, exampleWhiteMage);
    }


    /**
     * Checks that the interactions between Enemies and Knights works properly.
     * Side: From Knight to Enemy
     */
    @Test
    void KnightAgainstEnemies(){
        checkEffectiveNormalPlayerAttack(exampleEnemy, exampleKnight, powerfulSword);
        checkIneffectiveNormalPlayerAttack(exampleEnemy, exampleKnight, weakAxe);

    }

    /**
     * Checks that the interactions between Enemies and Knights works properly.
     * Side: From Enemy to Knight
     */
    @Test
    void EnemyAgainstKnight(){
        checkEffectiveNormalCPUAttack(exampleEnemy, weakKnight);
        checkIneffectiveNormalCPUAttack(weakEnemy, exampleKnight);
    }

    /**
     * Checks that the interactions between Enemies and Engineers works properly.
     * Side: From Engineer to Enemy
     */
    @Test
    void EngineerAgainstEnemies(){
        checkEffectiveNormalPlayerAttack(exampleEnemy, exampleEngineer, powerfulBow);
        checkIneffectiveNormalPlayerAttack(exampleEnemy, exampleEngineer, weakAxe);

    }

    /**
     * Checks that the interactions between Enemies and Engineers works properly.
     * Side: From Enemy to Engineer
     */
    @Test
    void EnemyAgainstEngineer(){
        checkEffectiveNormalCPUAttack(exampleEnemy, weakEngineer);
        checkIneffectiveNormalCPUAttack(weakEnemy, exampleEngineer);
    }

    /**
     * Checks that the interactions between Enemies and Thieves works properly.
     * Side: From Thieve to Enemy
     */
    @Test
    void ThiefAgainstEnemies(){
        checkEffectiveNormalPlayerAttack(exampleEnemy, exampleThief, powerfulKnife);
        checkIneffectiveNormalPlayerAttack(exampleEnemy, exampleThief, weakBow);

    }

    /**
     * Checks that the interactions between Enemies and Thieves works properly.
     * Side: From Enemy to Thieve
     */
    @Test
    void EnemyAgainstThief(){
        checkEffectiveNormalCPUAttack(exampleEnemy, weakThief);
        checkIneffectiveNormalCPUAttack(weakEnemy, exampleThief);
    }

}
