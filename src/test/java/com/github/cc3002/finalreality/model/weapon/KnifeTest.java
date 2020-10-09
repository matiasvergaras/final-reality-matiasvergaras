package com.github.cc3002.finalreality.model.weapon;

import com.github.matiasvergaras.finalreality.model.weapon.Normal.Bow;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Knife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KnifeTest extends AbstractWeaponTest {
    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
        exampleThief.equip(expectedKnife);
    }

    /**
     * Checks that the class constructor and equals, hashcode methods works properly.
     */
    @Test
    void constructorTest(){
        checkConstruction(new Knife(KNIFE_NAME, DAMAGE, WEIGHT),
                exampleThief.getEquippedWeapon(),
                new Knife(KNIFE_NAME, DAMAGE*2, WEIGHT),
                new Bow(KNIFE_NAME, DAMAGE, WEIGHT));
    }
}