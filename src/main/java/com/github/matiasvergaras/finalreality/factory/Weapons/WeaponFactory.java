package com.github.matiasvergaras.finalreality.factory.Weapons;

public abstract class WeaponFactory implements IWeaponFactory {
    int weight;
    int power;
    int magicPower = 0;

    public WeaponFactory(int weight, int power){
        this.weight = weight;
        this.power = power;
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
