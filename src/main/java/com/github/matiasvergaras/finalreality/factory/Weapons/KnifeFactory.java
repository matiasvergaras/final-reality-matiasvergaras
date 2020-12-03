package com.github.matiasvergaras.finalreality.factory.Weapons;

import com.github.matiasvergaras.finalreality.model.weapon.normal.Knife;

public class KnifeFactory extends WeaponFactory {
    String name = "Common Knife";

    public KnifeFactory(int power, int weight){
        super(power, weight);
    }

    public Knife create(){
        return new Knife(name, power, weight);
    }

    public Knife create(String name){
        return new Knife(name, power, weight);
    }
}
