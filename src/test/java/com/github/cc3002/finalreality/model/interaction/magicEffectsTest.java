package com.github.cc3002.finalreality.model.interaction;

import com.github.cc3002.finalreality.model.abstractModelTest;
import com.github.matiasvergaras.finalreality.model.character.cpu.ICPUCharacter;
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
        exampleBlackMage.equipWeapon(new Staff("Another Example", 20, 12, 50));
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
     * To test that receiveHeal method works properly.
     */
    @Test
    void paralysisTest() {
        exampleWhiteMage.useParalysisSpell(exampleEnemy);
        assertEquals(exampleEnemy.getState(), "PARALYZED");
    }

    /**
     * To test that receiveHeal method works properly.
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
        exampleBlackMage.useFireSpell(exampleEnemy);
        IMagicWeapon weapon = (IMagicWeapon) exampleBlackMage.getEquippedWeapon();
        assertEquals(exampleEnemy.getCurrentHP(), exampleEnemy.getMaxHP() - weapon.getMagicDamage());
    }

    /**
     * To test that Fire Spell Attack works properly, and can get to put the enemy in burned state.
     */
    @Test
    void thunderTest() {
        exampleBlackMage.useThunderSpell(exampleEnemy);
        IMagicWeapon weapon = (IMagicWeapon) exampleBlackMage.getEquippedWeapon();
        assertEquals(exampleEnemy.getCurrentHP(), exampleEnemy.getMaxHP() - weapon.getMagicDamage());
    }

    /**
     * To test that the burned state setter method works properly.
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



}
