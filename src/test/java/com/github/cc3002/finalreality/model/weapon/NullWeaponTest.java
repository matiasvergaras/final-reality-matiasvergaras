package com.github.cc3002.finalreality.model.weapon;

import com.github.matiasvergaras.finalreality.model.weapon.NullWeapon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to test the behavior of a NullWeapon.
 * @author Matías Vergara Silva
 * @since Homework 2
 */
public class NullWeaponTest extends AbstractWeaponTest {


    /**
     * Checks that the class constructor and equals, hashcode methods works properly.
     */
    @Test
    void constructorTest() {
        exampleThief.equipWeapon(nullWeapon);
        Assertions.assertEquals(nullWeapon, new NullWeapon());
        Assertions.assertTrue(nullWeapon.equals(nullWeapon));
        Assertions.assertEquals(nullWeapon.hashCode(), new NullWeapon().hashCode());
        assertNotEquals(nullWeapon, exampleAxe);
        assertNotEquals(nullWeapon.hashCode(), exampleAxe.hashCode());
    }

}
