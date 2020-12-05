package com.github.matiasvergaras.finalreality.factory.weapon;

import com.github.matiasvergaras.finalreality.factory.Weapons.StaffFactory;
import com.github.matiasvergaras.finalreality.model.weapon.magic.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to tests the features of the StaffFactory class.
 *
 * @author Matias Vergara Silva.
 * @since Homework 2
 */
public class StaffFactoryTest extends WeaponFactoryTest{
    Staff expectedWeapon;
    StaffFactory factory;

    @BeforeEach
    void setUp(){
        this.expectedWeapon = new Staff(weaponsName, 135, 14, 100);
        this.factory = new StaffFactory(weaponsName, 135, 14, 100);
        this.factory.setName(weaponsName);
    }

    /**
     * Checks that the factory create methods works properly.
     */
    @Test
    void factoryCreateTest() {
        checkFactoryCreate(this.factory, this.expectedWeapon);
    }

    /**
     * Checks that the factory Power setter method works properly.
     * <p> The first call to checkFactorySet is to set a new value. </p>
     * <p> The second one is to test what happens if the new value is the same as the default. </p>
     */
    @Test
    void factorySetPowerTest() {
        checkFactorySetPower(this.factory, 120);
        checkFactorySetPower(this.factory, 120);
    }


    /**
     * Checks that the factory Weight setter method works properly.
     * <p> The first call to checkFactorySet is to set a new value. </p>
     * <p> The second one is to test what happens if the new value is the same as the default. </p>
     */
    @Test
    void factorySetWeightTest() {
        checkFactorySetWeight(this.factory, 15);
        checkFactorySetWeight(this.factory, 15);
    }


    /**
     * Checks that the factory magicPower setter method works properly.
     * <p> The first call to checkFactorySet is to set a new value. </p>
     * <p> The second one is to test what happens if the new value is the same as the default. </p>
     */
    @Test
    void factorySetMagicPower() {
        checkFactorySetMagicPower(this.factory, 120);
        checkFactorySetMagicPower(this.factory, 120);
    }




}
