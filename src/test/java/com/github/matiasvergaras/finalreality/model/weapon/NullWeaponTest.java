package com.github.matiasvergaras.finalreality.model.weapon;

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
        assertEquals(nullWeapon, new NullWeapon());
        assertTrue(nullWeapon.equals(nullWeapon));
        assertEquals(nullWeapon.hashCode(), new NullWeapon().hashCode());
        assertNotEquals(nullWeapon, exampleAxe);
        assertNotEquals(nullWeapon.hashCode(), exampleAxe.hashCode());
    }

}
