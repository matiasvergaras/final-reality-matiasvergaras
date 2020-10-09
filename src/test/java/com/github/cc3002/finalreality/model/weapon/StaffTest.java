package com.github.cc3002.finalreality.model.weapon;

import com.github.matiasvergaras.finalreality.model.weapon.Magic.Staff;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Knife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StaffTest extends AbstractWeaponTest {
    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
        exampleBlackMage.equip(expectedStaff);
    }

    /**
     * Checks that the class constructor and equals, hashcode methods works properly.
     */
    @Test
    void constructorTest(){
        checkConstruction(new Staff(STAFF_NAME, DAMAGE, WEIGHT, MAGIC_DAMAGE),
                exampleBlackMage.getEquippedWeapon(),
                new Staff(STAFF_NAME, DAMAGE*2, WEIGHT, MAGIC_DAMAGE),
                new Knife(STAFF_NAME, DAMAGE, WEIGHT));
    }
}