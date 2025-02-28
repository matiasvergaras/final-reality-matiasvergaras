package com.github.matiasvergaras.finalreality.model.weapon;

import com.github.matiasvergaras.finalreality.model.AttributeSet.WeaponAttributeSet;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;

/**
 *
 * This represent a Weapon  from the game.
 * <p>
 * Weapons are an essential part of the game. Only playable
 * characters can equip and use them, and they need them to
 * attack and to get into the turnsQueue.
 * @since Homework 1
 * @see IPlayerCharacter
 * @author Matías Vergara Silva
 */
public interface IWeapon {

    /**
     * Returns a WeaponAttributeSet with all the attributes of this
     * weapon and its values. If a weapon does not have an special attribute,
     * the WeaponAttributeSet will keep a record of 0 as value for the said attribute.
     */
    WeaponAttributeSet getAttributes();

    /**
     * Method to get the weight of this weapon.
     *
     * @return this weapon weight.
     */
    int getWeight();

    /**
     * Method to get the name of this weapon.
     *
     * @return this weapon name.
     */
    String getName();

    /**
     * Method to get the power of this weapon.
     *
     * @return this weapon power.
     */
    int getPower();

    /**
     * Equip this weapon to a given Knight.
     *
     * @param character the Knight that will be equipped with this weapon
     */
    void equipToKnight(IPlayerCharacter character);

    /**
     * Equip this weapon to a given Engineer.
     *
     * @param character the Engineer that will be equipped with this weapon
     */
    void equipToEngineer(IPlayerCharacter character);

    /**
     * Equip this weapon to a given Thief.
     *
     * @param character the Thief that will be equipped with this weapon
     */
    void equipToThief(IPlayerCharacter character);

    /**
     * Equip this weapon to a given Black Mage.
     *
     * @param character the Black Mage that will be equipped with this weapon
     */
    void equipToBlackMage(IPlayerCharacter character);

    /**
     * Equip this weapon to a given White Mage.
     *
     * @param character the White Mage that will be equipped with this weapon
     */
    void equipToWhiteMage(IPlayerCharacter character);

    /**
     * Get the character that is using this weapon. Useful for testing.
     *
     * @return the unit that has currently equipped this item
     */
    IPlayerCharacter getOwner();

    /**
     * Sets this weapon's owner.
     *
     * @param character the character that has this weapon in its hand.
     */
    void setOwner(IPlayerCharacter character);

    /**
     * Set this weapon free, if it is being used by a character.
     */
    void setWeaponFree();

    /**
     * Indicates if this weapon is an Axe.
     * @return  True if this weapon is an Axe.
     */
    boolean isAxe();

    /**
     * Indicates if this weapon is a Sword.
     * @return  True if this weapon is a Sword.
     */
    boolean isSword();

    /**
     * Indicates if this weapon is a Knife.
     * @return  True if this weapon is a Knife.
     */
    boolean isKnife();

    /**
     * Indicates if this weapon is a Staff.
     * @return  True if this weapon is a Staff.
     */
    boolean isStaff();

    /**
     * Indicates if this weapon is a Bow.
     * @return  True if this weapon is a Bow.
     */
    boolean isBow();

    /**
     * Indicates if this weapon is Null.
     * @return True if this weapon is Null.
     */
    boolean isNull();
}
