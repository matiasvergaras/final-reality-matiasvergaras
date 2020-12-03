package com.github.matiasvergaras.finalreality.factory.Weapons;

import com.github.matiasvergaras.finalreality.model.weapon.magic.Staff;

public class StaffFactory extends WeaponFactory{
    String name = "Common Staff";

    public StaffFactory(int power, int weight, int magicPower){
        super(power,weight);
        this.magicPower = magicPower;
    }

    public Staff create(){
        return new Staff(name, power, weight, magicPower);
    }

    public Staff create(String name){
        return new Staff(name, power, weight, magicPower);
    }
}
