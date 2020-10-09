package com.github.matiasvergaras.finalreality.model.character.player;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;


public interface IPlayerCharacter extends ICharacter {
    /**
     * Equips a given weapon to this character
     * @see IWeapon
     * @param weapon
     *              The weapon to equip
     */
    void equip(IWeapon weapon);

    /**
     * @see IWeapon
     * @return  the equipped weapon of this character
     */
    IWeapon getEquippedWeapon();


}
