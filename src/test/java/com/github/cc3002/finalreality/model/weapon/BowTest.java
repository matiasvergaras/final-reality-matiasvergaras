package com.github.cc3002.finalreality.model.weapon;

import com.github.matiasvergaras.finalreality.model.weapon.normal.Axe;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Bow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test all the features of Bow weapon.
 * @since Homework 1
 * @author Matias Vergara Silva.
 */

public class BowTest extends AbstractWeaponTest {
    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
    }

    /**
     * Checks that the class constructor and equals, hashcode methods works properly.
     */
    @Test
    void constructorTest() {
        exampleThief.equipWeapon(exampleBow);
        checkConstruction(new Bow(BOW_NAME, DAMAGE, WEIGHT),
                exampleThief.getEquippedWeapon(),
                new Bow(BOW_NAME, DAMAGE * 2, WEIGHT),
                new Axe(AXE_NAME, DAMAGE, WEIGHT));
    }

    /**
     * Checks that the weapon can be properly equipped an unequipped
     * from a character
     */
    @Test
    void equipUnequipTest() {
        checkEquipUnequip(exampleBow, exampleEngineer,
                exampleThief);
    }

    /**
     * Check that the weapon is not equipped when not appropriate
     */
    @Test
    void unequippableBehaviorTest() {
        checkUnequippableBehavior(exampleBow, exampleKnight);
        checkUnequippableBehavior(exampleBow, exampleBlackMage);
        checkUnequippableBehavior(exampleBow, exampleWhiteMage);
    }

    /**
     * Check that the weapon is not equipped when the character to equip is appropriate
     * but is dead
     */
    @Test
    void deadEquipmentBehaviorTest() {
        checkDeadEquipmentBehavior(exampleBow, deadEngineer);
        checkDeadEquipmentBehavior(exampleBow, deadThief);
    }


    /**
     * Check that the weapon getPower method works properly.
     */
    @Test
    void gettersTest() {
        checkGetPower(exampleBow, DAMAGE);
        checkGetName(exampleBow, BOW_NAME);
        checkGetWeight(exampleBow, WEIGHT);

    }
}

