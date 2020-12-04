package com.github.cc3002.finalreality.model.weapon;

import com.github.matiasvergaras.finalreality.model.weapon.normal.Bow;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Knife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test all the features of an Knife weapon.
 * @since Homework 1
 * @author Matias Vergara Silva.
 */
public class KnifeTest extends AbstractWeaponTest {
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
        exampleThief.equipWeapon(exampleKnife);
        checkConstruction(new Knife(KNIFE_NAME, DAMAGE, WEIGHT),
                exampleThief.getEquippedWeapon(),
                new Knife(KNIFE_NAME, DAMAGE * 2, WEIGHT),
                new Bow(BOW_NAME, DAMAGE, WEIGHT));
    }

    /**
     * Check that the weapon can be properly equipped and unequipped for
     * all character classes that can use it
     */
    @Test
    void equipUnequipTest() {
        checkEquipUnequip(exampleKnife, exampleThief,
                exampleBlackMage);
        exampleKnife.setWeaponFree();
        checkEquipUnequip(exampleKnife, exampleKnight,
                exampleThief);
        exampleKnife.setWeaponFree();
        checkEquipUnequip(exampleKnife, exampleBlackMage,
                exampleThief);
    }

    /**
     * Check that the weapon is not equipped when not appropriate
     */
    @Test
    void unequippableBehaviorTest() {
        checkUnequippableBehavior(exampleKnife, exampleEngineer);
        checkUnequippableBehavior(exampleKnife, exampleWhiteMage);
    }

    /**
     * Check that the weapon is not equipped when the character to equip is appropriate
     * but is dead
     */
    @Test
    void deadEquipmentBehaviorTest() {
        checkDeadEquipmentBehavior(exampleKnife, deadKnight);
        checkDeadEquipmentBehavior(exampleKnife, deadBlackMage);
        checkDeadEquipmentBehavior(exampleKnife, deadThief);
    }

    /**
     * Check that the weapon getPower method works properly.
     */
    @Test
    void gettersTest() {
        checkGetPower(exampleKnife, DAMAGE);
        checkGetName(exampleKnife, KNIFE_NAME);
        checkGetWeight(exampleKnife, WEIGHT);

    }

}