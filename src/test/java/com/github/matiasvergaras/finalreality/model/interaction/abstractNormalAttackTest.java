package com.github.matiasvergaras.finalreality.model.interaction;

import com.github.matiasvergaras.finalreality.model.abstractModelTest;
import com.github.matiasvergaras.finalreality.model.character.cpu.ICPUCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A class to test that the attack between PlayerCharacters and Enemies works properly.
 * <p>
 * An attack with a weapon power greater than opponent defense deals a damage of power-defense to the opponent hp
 * An attack with a weapon power smallest than opponent defense do not deal any damage.
 * @see IPlayerCharacter
 * @see ICPUCharacter
 * @since Homework 1
 * @author Matías Vergara Silva.

 */
abstract class abstractNormalAttackTest extends abstractModelTest {

    protected boolean powerIsEnough(int power, int DP){
        return power > DP;
    }


    /*
     * To check that an attack of the player to an enemy with a weapon power greater than opponent defense deals
     * a damage equal to weapon.power-enemy.defense to the opponent hp
     */
    protected void checkEffectiveNormalPlayerAttack(ICPUCharacter enemy,  IPlayerCharacter character,
                                     IWeapon powerfulWeapon) {
        //First we check that the power of the weapon is enough to deal damage to the enemy.
        if (powerIsEnough(powerfulWeapon.getPower(), enemy.getDP())) {
            assertEquals(enemy.getMaxHP(), enemy.getCurrentHP(), "Enemy MaxHP and Initial CurrentHP are " +
                    "not the same.");
            character.equipWeapon(powerfulWeapon);
            character.normalAttack(enemy);
            //if enemy HP - (equipped weapon power - enemy defense points) < 0, then enemy current HP is set to 0
            int enemyHP = Math.max(0, enemy.getCurrentHP() - (character.getEquippedWeapon().getPower() - enemy.getDP()));
            int currentHP = enemy.getCurrentHP();
            assertEquals(enemyHP, currentHP, "Player attacked Enemy with a powerful weapon able to inflict" +
                    "damage, but Enemy HP did not change.");
        }
    }

    /*
     * To check that an attack of the player to an enemy with a weapon power smallest than opponent defense do not deal
     * any damage.
     */
    protected void checkIneffectiveNormalPlayerAttack(ICPUCharacter enemy, IPlayerCharacter character,
                                                      IWeapon weakWeapon) {
        //First we check that the power of the weapon is not enough to deal damage to the enemy.
        if (powerIsEnough(weakWeapon.getPower(), enemy.getDP())) {
            assertEquals(enemy.getMaxHP(), enemy.getCurrentHP(), "Enemy MaxHP and Initial Current HP are " +
                    "not the same.");
            character.equipWeapon(weakWeapon);
            character.normalAttack(enemy);
            assertEquals(enemy.getMaxHP(), enemy.getCurrentHP(), "Player attacked Enemy with a weak weapon " +
                    "that should not inflict damage, but it did.");
        }
    }

    /*
     * To check that an attack of an enemy to a player character with a  power greater than player defense deals
     * a damage equal to enemy.power-player.defense to the player hp
     */
    protected void checkEffectiveNormalCPUAttack(ICPUCharacter powerfulEnemy,  IPlayerCharacter character) {
        //First we check that the power of the enemy is enough to deal damage to the character.
        if(powerIsEnough(powerfulEnemy.getPower(), character.getDP())) {
            powerfulEnemy.normalAttack(character);
            //if enemy HP - (equipped weapon power - enemy defense points) < 0, then enemy current HP is set to 0
            assertEquals(character.getCurrentHP(),  Math.max(0, character.getMaxHP() - (powerfulEnemy.getPower() -
                    character.getDP())), "A strong enemy attacked to the playerCharacter, but his " +
                    "HP did not change the correct amount.");
        }
    }

    /*
     * To check that an attack of an enemy to a player character with a  power smallest than the player defense do
     *  not deal any damage.
     */
    protected void checkIneffectiveNormalCPUAttack(ICPUCharacter weakEnemy,  IPlayerCharacter character) {
        //First we check that the power of the enemy is not enough to deal damage to the character.
        if(!powerIsEnough(weakEnemy.getPower(), character.getDP())) {
            weakEnemy.normalAttack(character);
            assertEquals(character.getCurrentHP(), character.getMaxHP(), "A weak" +
                    "enemy attacked to the playerCharacter, but his HP changed (and they should not do it).");
        }
    }



}
