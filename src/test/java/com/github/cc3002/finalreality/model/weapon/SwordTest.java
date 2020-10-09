package com.github.cc3002.finalreality.model.weapon;

import com.github.matiasvergaras.finalreality.model.weapon.Magic.Staff;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Knife;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SwordTest extends AbstractWeaponTest {
    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
        exampleEngineer.equip(expectedSword);
    }

    /**
     * Checks that the class constructor and equals, hashcode methods works properly.
     */
    @Test
    void constructorTest(){
        checkConstruction(new Sword(SWORD_NAME, DAMAGE, WEIGHT),
                exampleEngineer.getEquippedWeapon(),
                new Sword(SWORD_NAME, DAMAGE*2, WEIGHT),
                new Knife(SWORD_NAME, DAMAGE, WEIGHT));
    }
}
