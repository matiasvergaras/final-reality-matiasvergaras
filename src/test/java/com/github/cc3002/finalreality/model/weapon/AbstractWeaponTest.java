package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.abstractModelTest;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class containing the common tests for all types of weapons.
 * @since Homework 1
 * @author Ignacio Slater Muñoz.
 * @author Matias Vergara Silva.
 * @see ICharacter
 */

public abstract class AbstractWeaponTest extends abstractModelTest {

    /**
     * Sets up the basics to test a weapon type against the others.
     */
    void basicSetUp() {
        super.turnSetUp();
    }

    /**
     * Checks that the class' constructor and hashcode, equals methods works properly.
     */
    protected void checkConstruction(final IWeapon expectedWeapon,
                                     final IWeapon testEqualWeapon,
                                     final IWeapon sameClassDifferentWeapon,
                                     final IWeapon differentWeapon) {
        assertEquals(expectedWeapon, testEqualWeapon, "expectedWeapon differs from EqualWeapon.");
        assertNotEquals(sameClassDifferentWeapon, testEqualWeapon, "sameClassDifferentWeapon equals EqualWeapon.");
        assertNotEquals(testEqualWeapon, differentWeapon, "EqualWeapon equals differentWeapon.");
        assertEquals(expectedWeapon.hashCode(), testEqualWeapon.hashCode(), "expectedWeapon hashcode differs" +
                "from EqualWeapon hashcode.");
        assertNotEquals(expectedWeapon.hashCode(), differentWeapon.hashCode(), "expectedWeapon hashcode " +
                "equals differentWeapon hashcode.");

    }

    /**
     * Checks that the weapon can be properly equipped an unequipped
     * from an (alive) character.
     *
     * @param weapon     the weapon to be tested
     * @param characterA A character from the first class that can equip the weapon
     * @param characterB the second character that will equip the weapon
     */
    protected void checkEquipUnequip(IWeapon weapon,
                                     IPlayerCharacter characterA,
                                     IPlayerCharacter characterB) {
        assertFalse(characterA.isEquipped(), "Character 'isEquipped' attribute started as true");
        characterA.equipWeapon(weapon);
        assertTrue(characterA.isEquipped(), "Character 'isEquipped' attribute rest as false after being equipped.");
        assertEquals(characterA.getEquippedWeapon(), weapon, "Weapon was not equipped successfully.");
        assertEquals(weapon.getOwner(), characterA, "Weapon owner was not set successfully.");
        characterB.equipWeapon(weapon);
        assertNull(characterA.getEquippedWeapon(), "Weapon was not unequipped when tried to equip to another" +
                "character.");
        assertEquals(characterB.getEquippedWeapon(), weapon, "Weapon was not equipped successfully when " +
                "tried to equip from another character.");
        assertEquals(weapon.getOwner(), characterB, "Weapon owner was not set successfully when tried to" +
                "equip from another character.");
    }

    /**
     * Checks that the weapon will not be equipped to an inappropriate character,
     *
     * @param weapon    the weapon to be tested
     * @param character A character from a class that cannot equip this weapon.
     */
    protected void checkUnequippableBehavior(IWeapon weapon,
                                             IPlayerCharacter character) {
        weapon.setWeaponFree();
        character.equipWeapon(weapon);
        assertNull(weapon.getOwner(), "Weapon was not supposed to be equipped, but it got an owner.");
        assertNull(character.getEquippedWeapon(), "Character was not supossed to be equipped with this " +
                "weapon, but he got an equippedWeapon.");
    }

    /**
     * Checks that the weapon will not be equipped to a dead (but appropriate) character.
     * @param weapon    the weapon to be tested
     * @param character A character from a class that can equip the weapon, but with 0 HP.
     */
    protected void checkDeadEquipmentBehavior(IWeapon weapon,
                                             IPlayerCharacter character) {
        //Preliminaries
        weapon.setWeaponFree();
        assertFalse(character.isAlive(), "This test must be used with a character already dead, but " +
                "it is getting an Alive character.");
        //To avoid code repeating but also to have individual case tests
        checkUnequippableBehavior(weapon, character);
    }


    /**
     * Checks that the weapon getPower method works properly.
     *
     * @param weapon        the weapon to be tested
     * @param expectedPower the expected value of power of this weapon.
     */
    protected void checkGetPower(IWeapon weapon, int expectedPower) {
        assertEquals(weapon.getPower(), expectedPower);
    }

    /**
     * Checks that the weapon getName method works properly.
     *
     * @param weapon        the weapon to be tested
     * @param expectedName the expected name of this weapon.
     */
    protected void checkGetName(IWeapon weapon, String expectedName) {
        assertEquals(weapon.getName(), expectedName);
    }

    /**
     * Checks that the weapon getWeight method works properly.
     *
     * @param weapon        the weapon to be tested
     * @param expectedWeight the expected value of weight of this weapon.
     */
    protected void checkGetWeight(IWeapon weapon, int expectedWeight) {
        assertEquals(weapon.getWeight(), expectedWeight);
    }
}
