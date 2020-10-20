package com.github.cc3002.finalreality.model.interaction;

import com.github.cc3002.finalreality.model.abstractModelTest;
import com.github.matiasvergaras.finalreality.model.character.cpu.Enemy;
import com.github.matiasvergaras.finalreality.model.character.cpu.ICPUCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.magic.Staff;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Axe;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Bow;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Knife;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Sword;
import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A class to test that the attack between PlayerCharacters and Enemies works properly.
 * <p>
 * An attack with a weapon power greater than opponent defense deals a damage of power-defense to the opponent hp
 * An attack with a weapon power smallest than opponent defense do not deal any damage.
 *
 * @author Matías Vergara Silva.
 * @see IPlayerCharacter
 * @see ICPUCharacter
 */
abstract class abstractNormalAttackTest extends abstractModelTest {

    protected boolean powerIsEnough(int power, int DP){
        return power > DP;
    }

    /*
     * Basic set-up : A turns queue
     */
    protected void turnSetUp() {
        super.turnSetUp();
    }

    /*
     * To check that an attack of the player to an enemy with a weapon power greater than opponent defense deals
     * a damage equal to weapon.power-enemy.defense to the opponent hp
     */
    protected void checkEffectiveNormalPlayerAttack(ICPUCharacter enemy,  IPlayerCharacter character,
                                     IWeapon powerfulWeapon) {
        //First we check that the power of the weapon is enough to deal damage to the enemy.
        if (powerIsEnough(powerfulWeapon.getPower(), enemy.getDP())) {
            character.equipWeapon(powerfulWeapon);
            character.normalAttack(enemy);
            //if enemy HP - (equipped weapon power - enemy defense points) < 0, then enemy current HP is set to 0
            int enemyHP = Math.max(0, enemy.getMaxHP() - (character.getEquippedWeapon().getPower() - enemy.getDP()));
            assertEquals(enemyHP, enemy.getCurrentHP());
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
            character.equipWeapon(weakWeapon);
            character.normalAttack(enemy);
            assertEquals(enemy.getMaxHP(), enemy.getCurrentHP());
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
            int characterHP = Math.max(0, character.getMaxHP() - (powerfulEnemy.getPower() - character.getDP()));
            assertEquals(characterHP, character.getCurrentHP());
        }
    }

    /*
     * To check that an attack of an enemy to a player character with a  power smallest than the player defense do
     *  not deal any damage.
     */
    protected void checkIneffectiveNormalCPUAttack(ICPUCharacter weakEnemy,  IPlayerCharacter character) {
        //First we check that the power of the enemy is not enough to deal damage to the character.
        if(powerIsEnough(weakEnemy.getPower(), character.getDP())) {
            weakEnemy.normalAttack(character);
            int characterHP = character.getMaxHP() - weakEnemy.getPower();
            assertEquals(characterHP, character.getCurrentHP());
        }
    }

    /*
     * The definitive check attack method, involving all the previous ones.
     */
    /**
     * Checks that the interactions between Enemy and BlackMage works properly.
     */
    void checkInteractions(ICPUCharacter anEnemy, ICPUCharacter weakEnemy, IPlayerCharacter anCharacter,
                           IPlayerCharacter weakCharacter,
                           IWeapon powerfulWeapon, IWeapon weakWeapon){
        checkEffectiveNormalPlayerAttack(anEnemy, anCharacter, powerfulWeapon);
        checkIneffectiveNormalPlayerAttack(anEnemy, anCharacter, weakWeapon);
        checkEffectiveNormalCPUAttack(anEnemy, weakCharacter);
        checkIneffectiveNormalCPUAttack(weakEnemy, anCharacter);

    }


}
