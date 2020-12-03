package com.github.matiasvergaras.finalreality.factory.Weapons;

import com.github.matiasvergaras.finalreality.model.weapon.normal.Bow;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Sword;

public class SwordFactory extends WeaponFactory{
    String name = "Common Sword";

    public Sword create(){
        return new Sword(name, power, weight);
    }

    public Sword create(String name){
        return new Sword(name, power, weight);
    }
}
