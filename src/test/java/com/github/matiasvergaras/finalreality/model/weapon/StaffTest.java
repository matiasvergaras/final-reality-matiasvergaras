package com.github.matiasvergaras.finalreality.model.weapon;

import com.github.matiasvergaras.finalreality.model.weapon.magic.Staff;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Knife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test all the features of an Staff weapon.
 * @since Homework 1
 * @author Matias Vergara Silva.
 */
public class StaffTest extends AbstractWeaponTest {
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
        exampleBlackMage.equipWeapon(exampleStaff);
        checkConstruction(new Staff(STAFF_NAME, DAMAGE, WEIGHT, MAGIC_DAMAGE),
                exampleBlackMage.getEquippedWeapon(),
                new Staff(STAFF_NAME, DAMAGE * 2, WEIGHT, MAGIC_DAMAGE),
                new Knife(KNIFE_NAME, DAMAGE, WEIGHT));
    }

    /**
     * Checks that the weapon can be properly equipped an unequipped
     * from a character
     */
    @Test
    void equipUnequipTest() {
        checkEquipUnequip(exampleStaff, exampleBlackMage,
                exampleWhiteMage);
    }


    /**
     * Check that the weapon is not equipped when not appropriate
     */
    @Test
    void unequippableBehaviorTest() {
        exampleStaff.setWeaponFree();
        checkUnequippableBehavior(exampleStaff, exampleEngineer);
        checkUnequippableBehavior(exampleStaff, exampleKnight);
        checkUnequippableBehavior(exampleStaff, exampleThief);
    }

    /**
     * Check that the weapon is not equipped when the character to equip is appropriate
     * but is dead
     */
    @Test
    void deadEquipmentBehaviorTest() {
        checkDeadEquipmentBehavior(exampleStaff, deadWhiteMage);
        checkDeadEquipmentBehavior(exampleStaff, deadBlackMage);
    }

    /**
     * Check that the weapon getPower method works properly.
     */
    @Test
    void gettersTest() {
        checkGetPower(exampleStaff, DAMAGE);
        checkGetName(exampleStaff, STAFF_NAME);
        checkGetWeight(exampleStaff, WEIGHT);

    }


}