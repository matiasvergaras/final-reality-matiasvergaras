package com.github.matiasvergaras.finalreality.factory.Weapons;


import com.github.matiasvergaras.finalreality.model.weapon.normal.Axe;

public class AxeFactory extends WeaponFactory {
    String name = "Common Axe";

    public Axe create(){
        return new Axe(name, power, weight);
    }

    public Axe create(String name){
        return new Axe(name, power, weight);
    }

}