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
     * Equip this weapon to a given Knight.
     * @param character
     *        the Knight that will be equipped with this weapon
     */
    void equipToKnight(IPlayerCharacter character);

    /**
     * Equip this weapon to a given Engineer.
     * @param character
     *        the Engineer that will be equipped with this weapon
     */
    void equipToEngineer(IPlayerCharacter character);

    /**
     * Equip this weapon to a given Thief.
     * @param character
     *        the Thief that will be equipped with this weapon
     */
    void equipToThief(IPlayerCharacter character);

    /**
     * Equip this weapon to a given Black Mage.
     * @param character
     *        the Black Mage that will be equipped with this weapon
     */
    void equipToBlackMage(IPlayerCharacter character);

    /**
     * Equip this weapon to a given White Mage.
     * @param character
     *        the White Mage that will be equipped with this weapon
     */
    void equipToWhiteMage(IPlayerCharacter character);


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
