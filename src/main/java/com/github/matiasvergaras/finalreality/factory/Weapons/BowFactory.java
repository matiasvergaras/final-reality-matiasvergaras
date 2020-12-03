package com.github.matiasvergaras.finalreality.factory.Weapons;

import com.github.matiasvergaras.finalreality.model.weapon.normal.Bow;

public class BowFactory extends WeaponFactory {
    String name = "Common Bow";

    public BowFactory(int power, int weight){
        super(power, weight);
    }

    public Bow create(){
        return new Bow(name, power, weight);
    }

    public Bow create(String name){
        return new Bow(name, power, weight);
    }
}