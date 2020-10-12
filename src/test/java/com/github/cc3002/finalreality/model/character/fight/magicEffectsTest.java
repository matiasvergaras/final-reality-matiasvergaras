package com.github.cc3002.finalreality.model.character.fight;

import com.github.cc3002.finalreality.model.abstractModelTest;
import com.github.matiasvergaras.finalreality.model.character.cpu.IEnemy;
import com.github.matiasvergaras.finalreality.model.weapon.magic.IMagicWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.magic.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A class to test that the methods related to magic effects works properly.
 *
 * @author Matías Vergara Silva.
 * @see IEnemy
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
        IMagicWeapon weapon = (IMagicWeapon)exampleBlackMage.getEquippedWeapon();
        assertEquals(exampleEnemy.getCurrentHP(), exampleEnemy.getMaxHP()- weapon.getMagicDamage());
    }

    /**
     * To test that Fire Spell Attack works properly, and can get to put the enemy in burned state.
     */
    @Test
    void thunderTest() {
        exampleBlackMage.useThunderSpell(exampleEnemy);
        IMagicWeapon weapon = (IMagicWeapon)exampleBlackMage.getEquippedWeapon();
        assertEquals(exampleEnemy.getCurrentHP(), exampleEnemy.getMaxHP()- weapon.getMagicDamage());
    }




}
