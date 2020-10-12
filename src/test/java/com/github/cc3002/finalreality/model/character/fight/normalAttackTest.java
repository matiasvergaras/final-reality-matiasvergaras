package com.github.cc3002.finalreality.model.character.fight;

import com.github.cc3002.finalreality.model.abstractModelTest;
import com.github.matiasvergaras.finalreality.model.character.CPU.IEnemy;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A class to test that the attack between PlayerCharacters and Enemies works properly.
 * <p>
 *     We will show that every character can get to attack an enemy, even when the methods are inherited from
 *     their abstract class IPlayerCharacter (so every character will work the same way). We will do this only for
 *     this test, but for the next we will assume that test with one kind of character is enough - except if they
 *     overrides methods -.
 * </p>
 * @see IPlayerCharacter
 * @see IEnemy
 * @author Matías Vergara Silva.
 */
public class normalAttackTest extends abstractModelTest {

    protected void checkNormalAttack(IEnemy enemy, IPlayerCharacter character,
                                     IWeapon weapon) {

        character.equipWeapon(weapon);

        character.normalAttack(enemy);
        int enemyHP = enemy.getMaxHP() - character.getEquippedWeapon().getPower();
        assertEquals(enemyHP, enemy.getCurrentHP());


        enemy.normalAttack(character);
        int characterHP = character.getMaxHP() - enemy.getPower();

        assertEquals(characterHP,  character.getCurrentHP());

    }

    // We will show that every character can attack, even when the methods are inherited from their abstract class
    /**
     * Check that a Knight can attack an Enemy.
     */
    @Test
    void KnightAttackTest(){
        checkNormalAttack(exampleEnemy, exampleKnight, exampleAxe);
    }

    /**
     * Check that an Engineer can attack an Enemy.
     */
    @Test
    void EngineerAttackTest(){
        checkNormalAttack(exampleEnemy, exampleEngineer, exampleBow);
    }

    /**
     * Check that a Thief can attack an Enemy.
     */
    @Test
    void ThiefAttackTest(){
        checkNormalAttack(exampleEnemy, exampleThief, exampleKnife);
    }

    /**
     * Check that a Black Mage can attack an Enemy (using only normal attacks).
     */
    @Test
    void BlackMageAttackTest(){
        checkNormalAttack(exampleEnemy, exampleBlackMage, exampleStaff);
    }

    /**
     * Check that a White Mage can attack an Enemy (using only normal attacks).
     */
    @Test
    void WhiteMageAttackTest(){
        checkNormalAttack(exampleEnemy, exampleWhiteMage, exampleStaff);
    }





}
