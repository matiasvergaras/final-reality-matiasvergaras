package com.github.cc3002.finalreality.controller;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class to test that the SetSelectedWeaponFactory{Attribute} methods works properly.
 * <p> First we set a new value for each variable of the selectedWeaponFactory.
 * Then we create a new weapon of the same type and with the same properties,
 * and we test it to be equal to the given by factory with new settings.</p>
 * @since Homework 2
 * @author Matías Vergara Silva
 */
public class WeaponFactoriesSettingsTest {
    private GameController gc;

    /**
     * Sets a new configuration for the SelectedWeaponFactory.
     * @param index     The index of the factory to configure in the GameController WeaponFactories
     *                  list.
     * @param seed      An int that will be used to set new values.
     */
    void setFactoryForTesting(int index, int seed){
        gc.setSelectedWeaponFactory(index);
        gc.setSelectedWeaponFactoryPower(seed*51);
        gc.setSelectedWeaponFactoryWeight(seed*2);
        gc.setSelectedWeaponFactoryMagicPower(seed*11);
    }

    /**
     * Tests that the SelectedWeapon has the correct attribute values,
     * given by the seed that was used to configure its factory.
     * <p> This test is specific to the Magic Weapons. </p>
     * @param seed              The seed used to configure the factory. Has to be exactly the same.
     */
    void testFactorySettingForMagic(int seed){
        assertEquals(gc.getWeaponPower(gc.getSelectedWeapon()), seed*51);
        assertEquals(gc.getWeaponWeight(gc.getSelectedWeapon()), seed*2);
        assertEquals(gc.getWeaponMagicPower(gc.getSelectedWeapon()), seed*11);
    }

    /**
     * Tests that the SelectedWeapon has the correct attribute values,
     * given by the seed that was used to configure its factory.
     * <p> This test is specific to the Normal Weapons. </p>
     * @param seed              The seed used to configure the factory. Has to be exactly the same.
     * @param defaultName       The default name that the factory keeps for its products or
     *                          the given special name, if produced with it.
     */
    void testFactorySettingForNormal(int seed, String defaultName){
        assertEquals(gc.getWeaponName(gc.getSelectedWeapon()), defaultName);
        assertEquals(gc.getWeaponPower(gc.getSelectedWeapon()), seed*51);
        assertEquals(gc.getWeaponWeight(gc.getSelectedWeapon()), seed*2);
        assertEquals(gc.getWeaponMagicPower(gc.getSelectedWeapon()), 0);
    }


    /**
     * Basic set-up: a GameController instance.
     */
    @BeforeEach
    void setUp(){
        gc = new GameController("Foreman",
                "Urabantol", 3);
    }


    /**
     * Tests for a BowFactory.
     */
    @Test
    void BowFactorySettingTest(){
        setFactoryForTesting(0, 3);
        gc.selectedWeaponFactoryProduce("Assault Shell");
        gc.setSelectedWeapon(gc.getInventorySize()-1);
        testFactorySettingForNormal(3, "Assault Shell");
    }


    /**
     * Tests for a SwordFactory.
     */
    @Test
    void SwordFactorySettingTest(){
        setFactoryForTesting(3, 5);
        gc.selectedWeaponFactoryProduce("Sword of Light");
        gc.setSelectedWeapon(gc.getInventorySize()-1);
        testFactorySettingForNormal(5, "Sword of Light");
    }

    /**
     * Tests for a KnifeFactory.
     */
    @Test
    void KnifeFactorySettingTest(){
        setFactoryForTesting(2, 5);
        gc.selectedWeaponFactoryProduce("MiniSpear");
        gc.setSelectedWeapon(gc.getInventorySize()-1);
        testFactorySettingForNormal(5, "MiniSpear");
    }

    /**
     * Tests for an AxeFactory.
     */
    @Test
    void AxeFactorySettingTest(){
        setFactoryForTesting(4, 5);
        gc.selectedWeaponFactoryProduce("Heavy Ax");
        gc.setSelectedWeapon(gc.getInventorySize()-1);
        testFactorySettingForNormal(5, "Heavy Ax");
    }


    /**
     * Tests for an StaffFactory.
     */
    @Test
    void StaffFactorySettingTest(){
        setFactoryForTesting(1, 5);
        gc.selectedWeaponFactoryProduce("Guardian Staff");
        gc.setSelectedWeapon(gc.getInventorySize()-1);
        testFactorySettingForMagic(5);
    }

    /**
     * Test the equal method of WeaponAttributeSet
     */
    @Test
    void equalHashCodeAttributeTest(){
        setFactoryForTesting(1, 5);
        IWeapon weapon = gc.getSelectedWeaponFactory().create("A weapon");
        IWeapon copy = gc.getSelectedWeaponFactory().create("A weapon");
        assertEquals(weapon, copy);
        assertEquals(weapon.hashCode(), copy.hashCode());

    }



}

