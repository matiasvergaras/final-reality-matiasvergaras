package com.github.cc3002.finalreality.model.interaction;

import com.github.cc3002.finalreality.model.abstractModelTest;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.cpu.ICPUCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to test that the isAlive method of AbstractCharacter works properly, i.e., that every character
 * starts alive and can get to die. It also check that dead's HP ends at 0.
 * @see ICharacter
 * @since Homework 1
 * @author Matías Vergara Silva.
 *
 */
public abstract class abstractIsAliveTest extends abstractModelTest {

    protected void checkStartAlive(ICharacter someCharacter){
        if (someCharacter.getCurrentHP()>0) {
            assertTrue(someCharacter.isAlive());
        }
        else{
            assertFalse(someCharacter.isAlive());
        }
    }

    protected void checkCPUDies(ICPUCharacter enemy, IPlayerCharacter character, IWeapon powerfulWeapon) {
        //first we check that the weapon is powerful enough to damage the enemy, if not, then test is vacuously approved.
        if (powerfulWeapon.getPower() > enemy.getDP()) {
            character.equipWeapon(powerfulWeapon);
            while (enemy.isAlive()) {
                character.normalAttack(enemy);
            }
            assertEquals(enemy.getCurrentHP(), 0);
        }
    }

    protected void checkPlayerDies(ICPUCharacter powerfulEnemy, IPlayerCharacter character) {
        //first we check that the enemy is powerful enough to damage the character, if not, then test is vacuously approved.
        if (powerfulEnemy.getPower() > character.getDP()) {
            while (character.isAlive()) {
                powerfulEnemy.normalAttack(character);
            }
            assertEquals(character.getCurrentHP(), 0);
        }
    }
}
