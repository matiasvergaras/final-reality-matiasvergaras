package com.github.matiasvergaras.finalreality.factory.weapon;

import com.github.matiasvergaras.finalreality.factory.Weapons.KnifeFactory;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Knife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to tests the features of the KnifeFactory class.
 *
 * @author Matias Vergara Silva.
 * @since Homework 2
 */
public class KnifeFactoryTest extends WeaponFactoryTest{
    Knife expectedWeapon;
    KnifeFactory factory;

    @BeforeEach
    void setUp(){
        this.expectedWeapon = new Knife(weaponsName, 135, 14);
        this.factory = new KnifeFactory(weaponsName,135, 14);
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
     * Checks that the factory WEIGHT setter method works properly.
     * <p> The first call to checkFactorySet is to set a new value. </p>
     * <p> The second one is to test what happens if the new value is the same as the default. </p>
     */
    @Test
    void factorySetWeightTest() {
        checkFactorySetWeight(this.factory, 12);
        checkFactorySetWeight(this.factory, 12);
    }

    /**
     * Checks that the methods that should not influence this factory productions works
     * properly.
     */
    @Test
    void factoryIneffectiveSetsTest() {
        checkIneffectiveSetMagicPower(this.factory, 123);
    }

}
