package com.github.matiasvergaras.finalreality.model.character.player;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;


public interface IPlayerCharacter extends ICharacter {

    void equip(IWeapon weapon);

    IWeapon getEquippedWeapon();


}
