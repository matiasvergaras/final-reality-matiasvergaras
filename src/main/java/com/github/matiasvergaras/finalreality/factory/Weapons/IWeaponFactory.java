package com.github.matiasvergaras.finalreality.factory.Weapons;

import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;

public interface IWeaponFactory {

    void setWeight(int weight);

    void setPower(int power);

    void setMagicPower(int magicPower);

    IWeapon create();
}
