package com.github.cc3002.finalreality.model.character.fight;

import com.github.cc3002.finalreality.model.abstractModelTest;
import com.github.matiasvergaras.finalreality.model.character.cpu.IEnemy;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A class to test that the isAlive method of AbstractCharacter works properly, i.e., that every character
 * can get to die.
 *
 * @author Matías Vergara Silva.
 * @see com.github.matiasvergaras.finalreality.model.character.ICharacter
 */
public class isAliveTest extends abstractModelTest {

    protected void checkAlive(IEnemy enemy, IPlayerCharacter character,
                              IWeapon weapon) {
        IEnemy anotherEnemy = enemy;
        character.equipWeapon(weapon);
        while (enemy.isAlive()) {
            character.normalAttack(enemy);
        }

        while (character.isAlive()) {
            anotherEnemy.normalAttack(character);
        }
        assertTrue(enemy.getCurrentHP() <= 0);
        assertTrue(character.getCurrentHP() <= 0);
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
