package com.github.cc3002.finalreality.model.weapon;

import com.github.matiasvergaras.finalreality.model.weapon.normal.Knife;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test all the features of an Sword weapon.
 * @since Homework 1
 * @author Matias Vergara Silva.
 */
public class SwordTest extends AbstractWeaponTest {
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
        exampleThief.equipWeapon(exampleSword);
        checkConstruction(new Sword(SWORD_NAME, DAMAGE, WEIGHT),
                exampleThief.getEquippedWeapon(),
                new Sword(SWORD_NAME, DAMAGE * 2, WEIGHT),
                new Knife(KNIFE_NAME, DAMAGE, WEIGHT));
    }

    /**
     * Checks that the weapon can be properly equipped an unequipped
     * from a character
     */
    @Test
    void equipUnequipTest() {
        checkEquipUnequip(exampleSword, exampleKnight,
                exampleThief);
    }

    /**
     * Check that the weapon is not equipped when not appropriate
     */
    @Test
    void unequippableBehaviorTest() {
        exampleSword.setWeaponFree();
        checkUnequippableBehavior(exampleSword, exampleEngineer);
        checkUnequippableBehavior(exampleSword, exampleBlackMage);
        checkUnequippableBehavior(exampleSword, exampleWhiteMage);
    }

    /**
     * Check that the weapon is not equipped when the character to equip is appropriate
     * but is dead
     */
    @Test
    void deadEquipmentBehaviorTest() {
        checkDeadEquipmentBehavior(exampleSword, deadKnight);
        checkDeadEquipmentBehavior(exampleSword, deadThief);
    }

    /**
     * Check that the getters methods works properly.
     */
    @Test
    void gettersTest() {
        checkGetPower(exampleSword, DAMAGE);
        checkGetName(exampleSword, SWORD_NAME);
        checkGetWeight(exampleSword, WEIGHT);
    }


}
