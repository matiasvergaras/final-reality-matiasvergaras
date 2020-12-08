package com.github.matiasvergaras.finalreality.model.character.player;

import com.github.matiasvergaras.finalreality.model.AttributeSet.CharacterAttributeSet;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;


/**
 *
 * This represent a Playable Character from the game.
 * <p>
 * Playable characters can equip and use weapons. Their turnsQueue
 * time is calculated based on their equipped weapon weight.
 * @since Homework 1
 * @author Matías Vergara Silva
 */
public interface IPlayerCharacter extends ICharacter {

    /**
     * Starts the process of equip a weapon to this character,
     * checking in first place if it is alive. In that case,
     * it starts the equipment  via double dispatch. Otherwise,
     * it equips the null weapon.
     *
     * @param weapon The weapon to be equipped
     * @see IWeapon
     */
    void equipWeapon(IWeapon weapon);

    /**
     * Sets this weapon to this character, once it's feasibility
     * has been checked.
     *
     * @param weapon The weapon to be equipped
     */
    void equip(IWeapon weapon);

    /**
     * Unequip a weapon from this character.
     */
    void unequip();

    /**
     * @return the equipped weapon of this character
     * @see IWeapon
     */
    IWeapon getEquippedWeapon();


    /**
     * A boolean telling if this character has a weapon equipped
     * @return true if equiped, false otherwise.
     */
    boolean isEquipped();

    /**
     * To get all the attributes of this character together,
     * adding the equipped Weapon to those of
     * the parent class.
     * @return An LinkedLIST of Integer whit the attributes in the following
     * order: maxHP, currentHP, DP, maxMana, currentMana
     */
    CharacterAttributeSet getAttributes();

    /**
     * Equips a NullWeapon.
     * <p> This method is necessary because it wont check for the character
     * to be alive, unlike normal equipWeapon method. And to avoid
     * complicating that method, it is more practical to add this one.</p>
     */
    void equipNull();
}





