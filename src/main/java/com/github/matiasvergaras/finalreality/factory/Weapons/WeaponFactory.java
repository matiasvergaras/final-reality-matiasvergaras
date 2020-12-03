package com.github.matiasvergaras.finalreality.factory.Weapons;

import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;

public abstract class WeaponFactory implements IWeaponFactory {
    int weight = 10;
    int power = 80;
    int magicPower = 80;

    public WeaponFactory(){
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public void setPower(int power){
        this.power = power;
    }

    public void setMagicPower(int magicPower){
        this.magicPower = magicPower;
    }

}
