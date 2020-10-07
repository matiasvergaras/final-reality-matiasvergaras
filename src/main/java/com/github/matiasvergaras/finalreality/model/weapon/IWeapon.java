package com.github.matiasvergaras.finalreality.model.weapon;

public interface IWeapon {

    /**
     * Returns this weapon weight.
     */
    int getWeight();

    /**
     * Returns this weapon name.
     */
    String getName();

    /**
     * Returns this weapon power.
     */
    int getPower();

    /**
     * Returns this weapon type.
     */
    String getType();

}
