package com.github.cc3002.finalreality.model.interaction;

import com.github.cc3002.finalreality.model.abstractModelTest;
import com.github.matiasvergaras.finalreality.model.character.cpu.Enemy;
import com.github.matiasvergaras.finalreality.model.character.cpu.ICPUCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.magic.BlackMage;
import com.github.matiasvergaras.finalreality.model.character.player.magic.WhiteMage;
import com.github.matiasvergaras.finalreality.model.weapon.magic.IMagicWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.magic.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A class to test that the methods related to magic effects works properly.
 *
 * @author Matías Vergara Silva.
 * @see ICPUCharacter
 */

public class magicEffectsTest extends abstractModelTest {


    /**
     * Setup: Equip a magic weapon to the characters that we will use.
     */
    @BeforeEach
    void magicSetUp() {
        exampleWhiteMage.equipWeapon(exampleStaff);
        exampleBlackMage.equipWeapon(new Staff("Another Staff", 20, 12, 50));
    }


    /**
     * To test that receiveHeal method works properly.
     */
    @Test
    void healTest() {
        exampleWhiteMage.useHealSpell(exampleKnight);
        assertEquals(exampleKnight.getCurrentHP(), exampleKnight.getMaxHP() * 1.3);
    }

    /**
     * To test that useParalysisSpell method works properly.
     */
    @Test
    void paralysisTest() {
        exampleWhiteMage.useParalysisSpell(exampleEnemy);
        assertEquals(exampleEnemy.getState(), "PARALYZED");
    }

    /**
     * To test that usePoisonSpell method works properly.
     */
    @Test
    void poisonTest() {
        exampleWhiteMage.usePoisonSpell(exampleEnemy);
        assertEquals(exampleEnemy.getState(), "POISONED");
    }


    /**
     * To test that Fire Spell Attack works properly, and can get to put the enemy in burned state.
     */
    @Test
    void fireTest() {
        BlackMage exampleBlackMage = new BlackMage(turns, "New Black", 200, 200, 999999);
        Enemy exampleEnemy = new Enemy(turns, "New Enemy", 15, 999999, 100, 100);
        exampleBlackMage.equipWeapon(exampleStaff);

        exampleBlackMage.useFireSpell(exampleEnemy);
        IMagicWeapon weapon = (IMagicWeapon) exampleBlackMage.getEquippedWeapon();
        assertEquals(exampleEnemy.getCurrentHP(), exampleEnemy.getMaxHP() - weapon.getMagicDamage());

        while(exampleEnemy.getState().equals("NORMAL")){
            exampleBlackMage.useFireSpell(exampleEnemy);
        }
        assertEquals(exampleEnemy.getState(), "BURNED");
    }
    /**
     * To test that Fire Spell Attack works properly, and can get to put the enemy in paralyzed state.     */
    @Test
    void thunderTest() {
        BlackMage exampleBlackMage = new BlackMage(turns, "New Black", 200, 200, 999999);
        Enemy exampleEnemy = new Enemy(turns, "New Enemy", 15, 999999, 100, 100);
        exampleBlackMage.equipWeapon(exampleStaff);

        exampleBlackMage.useThunderSpell(exampleEnemy);
        IMagicWeapon weapon = (IMagicWeapon) exampleBlackMage.getEquippedWeapon();
        assertEquals(exampleEnemy.getCurrentHP(), exampleEnemy.getMaxHP() - weapon.getMagicDamage());

        while (exampleEnemy.getState().equals("NORMAL")) {
            exampleBlackMage.useThunderSpell(exampleEnemy);
        }
        assertEquals(exampleEnemy.getState(), "PARALYZED");
    }

    /**
     * To test that the burned state setter method works properly.
     * (i.e. a Black Mage using White Magic, and viceversa).
     */
    @Test
    void burnedTest() {
        exampleEnemy.setBurned();
        assertEquals(exampleEnemy.getState(), "BURNED");
    }

    /**
     * To test the null behavior of inappropriate use of spells
     */
    @Test
    void inappropriateUsesTest() {
        exampleBlackMage.useHealSpell(exampleKnight);
        assertEquals(exampleKnight.getCurrentHP(), exampleKnight.getMaxHP());
        exampleBlackMage.usePoisonSpell(exampleEnemy);
        assertEquals(exampleEnemy.getState(), "NORMAL");
        exampleBlackMage.useParalysisSpell(exampleEnemy);
        assertEquals(exampleEnemy.getState(), "NORMAL");
        exampleWhiteMage.useThunderSpell(exampleEnemy);
        assertEquals(exampleEnemy.getCurrentHP(), exampleEnemy.getMaxHP());
        exampleWhiteMage.useFireSpell(exampleEnemy);
        assertEquals(exampleEnemy.getCurrentHP(), exampleEnemy.getMaxHP());
    }

    /**
     * To test behavior of spells when the mage ran out of mana
     * (i.e. check that they do nothing).
     */
    @Test
    void outOfManaTest() {
        BlackMage theBlackWithoutMana = new BlackMage(turns, "The Black", 200, 200, 5);
        WhiteMage theWhiteWithoutMana = new WhiteMage(turns, "The White", 200, 200, 1);
        theWhiteWithoutMana.useHealSpell(exampleKnight);
        assertEquals(exampleKnight.getCurrentHP(), exampleKnight.getMaxHP());
        theWhiteWithoutMana.usePoisonSpell(exampleEnemy);
        assertEquals(exampleEnemy.getState(), "NORMAL");
        theWhiteWithoutMana.useParalysisSpell(exampleEnemy);
        assertEquals(exampleEnemy.getState(), "NORMAL");
        theBlackWithoutMana.useThunderSpell(exampleEnemy);
        assertEquals(exampleEnemy.getCurrentHP(), exampleEnemy.getMaxHP());
        theBlackWithoutMana.useFireSpell(exampleEnemy);
        assertEquals(exampleEnemy.getCurrentHP(), exampleEnemy.getMaxHP());
    }


}
