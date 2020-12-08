package com.github.cc3002.finalreality.model.weapon;

import com.github.matiasvergaras.finalreality.model.weapon.normal.Axe;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test all the features of an Axe weapon.
 * @since Homework 1
 * @author Matias Vergara Silva.
 */

public class AxeTest extends AbstractWeaponTest {
    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    protected void setUp() {
        basicSetUp();
    }

    /**
     * Checks that the class constructor and equals, hashcode methods works properly.
     */
    @Test
    void constructorTest() {
        exampleKnight.equipWeapon(exampleAxe);
        checkConstruction(new Axe(AXE_NAME, DAMAGE, WEIGHT),
                exampleKnight.getEquippedWeapon(),
                new Axe("Another", DAMAGE, WEIGHT),
                new Axe(AXE_NAME, DAMAGE, WEIGHT*2),
                new Axe(AXE_NAME, DAMAGE*2, WEIGHT),
                new Sword(SWORD_NAME, DAMAGE, WEIGHT));
    }

    /**
     * Checks that the weapon can be properly equipped an unequipped
     * from a character
     */
    @Test
    void equipUnequipTest() {
        checkEquipUnequip(exampleAxe, exampleKnight,
                exampleEngineer);
        exampleAxe.setWeaponFree();
        checkEquipUnequip(exampleAxe, exampleEngineer,
                exampleKnight);
    }

    /**
     * Check that the weapon is not equipped when not appropriate
     */
    @Test
    void unequippableBehaviorTest() {
        checkUnequippableBehavior(exampleAxe, exampleThief);
        checkUnequippableBehavior(exampleAxe, exampleBlackMage);
        checkUnequippableBehavior(exampleAxe, exampleWhiteMage);
    }

    /**
     * Check that the weapon is not equipped when the character to equip is appropriate
     * but is dead
     */
    @Test
    void deadEquipmentBehaviorTest() {
        checkDeadEquipmentBehavior(exampleAxe, deadEngineer);
        checkDeadEquipmentBehavior(exampleAxe, deadKnight);
    }

    /**
     * Check that the weapon getPower method works properly.
     */
    @Test
    void gettersTest() {
        checkGetPower(exampleAxe, DAMAGE);
        checkGetName(exampleAxe, AXE_NAME);
        checkGetWeight(exampleAxe, WEIGHT);
    }

}

