package com.github.matiasvergaras.finalreality.model.character.player;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

public interface IPlayerCharacter {

    void waitTurn();

    void equip(IWeapon weapon);

    IWeapon getEquippedWeapon();


}
