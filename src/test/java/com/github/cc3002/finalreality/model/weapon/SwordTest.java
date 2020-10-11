package com.github.cc3002.finalreality.model.weapon;

import com.github.matiasvergaras.finalreality.model.weapon.Normal.Knife;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test all the features of an Sword weapon.
 *
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
    void constructorTest(){
        exampleEngineer.equip(exampleSword);
        checkConstruction(new Sword(SWORD_NAME, DAMAGE, WEIGHT),
                exampleEngineer.getEquippedWeapon(),
                new Sword(SWORD_NAME, DAMAGE*2, WEIGHT),
                new Knife(KNIFE_NAME, DAMAGE, WEIGHT));
    }

    /**
     * Checks that the weapon can be properly equipped an unequipped
     * from a character
     */
    @Test
    void equipUnequipTest(){
        checkEquipUnequip(exampleSword, exampleKnight,
                exampleThief);
    }

    /**
     * Check that the weapon is not equipped when not appropriate
     */
    @Test
    void unequippableBehaviorTest(){
        exampleSword.setWeaponFree();
        checkUnequippableBehavior(exampleSword, exampleEngineer);
        checkUnequippableBehavior(exampleSword, exampleBlackMage);
        checkUnequippableBehavior(exampleSword, exampleWhiteMage);
    }

    /**
     * Check that the getters methods works properly.
     */
    @Test
    void gettersTest(){
        Sword testSword = new Sword("AnSword", 200, 10);
        checkGetPower(testSword, 200);
        checkGetName(testSword, "AnSword");
        checkGetWeight(testSword, 10);

    }


}
