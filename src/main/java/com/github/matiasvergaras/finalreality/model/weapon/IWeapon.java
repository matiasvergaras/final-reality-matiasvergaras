package com.github.matiasvergaras.finalreality.model.weapon;

import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;

public interface IWeapon {

    /**
     * Method to get the weight of this weapon.
     * @return this weapon weight.
     */
    int getWeight();

    /**
     * Method to get the name of this weapon.
     * @return this weapon name.
     */
    String getName();

    /**
     * Method to get the power of this weapon.
     * @return this weapon power.
     */
    int getPower();

    /**
     * Equips this weapon to a character.
     * @param character
     *        the character that will be equipped with this weapon
     */
    void equipTo(IPlayerCharacter character);

    /**
     * Sets this weapon's owner.
     * @param character
     *        the character that has this weapon in its hand.
     */
    void setOwner(IPlayerCharacter character);

    /**
     * Method to get the character that is using this weapon. Useful for testing.
     * @return the unit that has currently equipped this item
     */
    IPlayerCharacter getOwner();


}
