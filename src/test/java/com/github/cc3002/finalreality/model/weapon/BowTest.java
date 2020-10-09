package com.github.cc3002.finalreality.model.weapon;

import com.github.matiasvergaras.finalreality.model.weapon.Normal.Axe;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Bow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BowTest extends AbstractWeaponTest {
    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
        exampleThief.equip(expectedBow);
    }

    /**
     * Checks that the class constructor and equals, hashcode methods works properly.
     */
    @Test
    void constructorTest(){
        checkConstruction(new Bow(BOW_NAME, DAMAGE, WEIGHT),
                exampleThief.getEquippedWeapon(),
                new Bow(BOW_NAME, DAMAGE*2, WEIGHT),
                new Axe(BOW_NAME, DAMAGE, WEIGHT));
    }
}

