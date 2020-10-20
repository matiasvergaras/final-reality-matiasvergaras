package com.github.cc3002.finalreality.model.interaction;

import com.github.cc3002.finalreality.model.abstractModelTest;
import com.github.matiasvergaras.finalreality.model.character.cpu.ICPUCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A class to test that the isAlive method of AbstractCharacter works properly, i.e., that every character
 * can get to die.
 *
 * @author Matías Vergara Silva.
 * @see com.github.matiasvergaras.finalreality.model.character.ICharacter
 */
public class isAliveTest extends abstractModelTest {

    protected void checkAlive(ICPUCharacter enemy, IPlayerCharacter character,
                              IWeapon weapon) {
        ICPUCharacter anotherEnemy = enemy;
        IPlayerCharacter anotherCharacter = character;

        assertEquals(enemy.getMaxHP(), enemy.getCurrentHP());
        assertTrue(enemy.getCurrentHP()>0);
        assertEquals(character.getMaxHP(), character.getCurrentHP());
        assertTrue(character.getCurrentHP()>0);

        character.equipWeapon(weapon);
        while (enemy.isAlive()) {
            character.normalAttack(enemy);
        }

        while (character.isAlive()) {
            anotherEnemy.normalAttack(character);
        }
        assertTrue(enemy.getCurrentHP() <= 0);
        assertTrue(character.getCurrentHP() <= 0);

        int DeadHP = character.getCurrentHP();
        anotherEnemy.normalAttack(character);
        assertEquals(character.getCurrentHP(), DeadHP);

        int DeadEnemyHP = enemy.getCurrentHP();
        anotherCharacter.normalAttack(enemy);
        assertEquals(enemy.getCurrentHP(), DeadEnemyHP);
    }


    /**
     * Checks that the condition of isAlive is reached
     * <p>
     * Since we have tests to prove that every character can attack an enemy,
     * we will assume that if one kind of character can get to kill, then everyone can.
     * </p>
     */
    @Test
    void aliveTest() {
        checkAlive(exampleEnemy, exampleKnight, exampleKnife);
    }

}
