package com.github.matiasvergaras.finalreality.factory.Weapons;

import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;

/**
 * Interface to represent the WeaponFactory class and all its child classes.
 * @since  Homework 2
 * @author Matías Vergara Silva
 */
public interface IWeaponFactory {

    /**
     * Sets the default weight value to the one given as parameter.
     * @param weight        The new weight to set as default value.
     */
    void setWeight(int weight);

    /**
     * Sets the default power value to the one given as parameter.
     * @param power        The new power to set as default value.
     */
    void setPower(int power);

    /**
     * Sets the default magicPower value to the one given as parameter.
     * @param magicPower    The new magicPower to set as default value.
     */
    void setMagicPower(int magicPower);

    /**
     * Sets the default name value to the one given as parameter.
     * @param name      The new name to set as default value.
     */
    void setName(String name);

    /**
     * Produces a new common weapon whose type depends of the Factory that receives the message.
     * @return      A new Weapon.
     */
    IWeapon create();

    /**
     * Produces a new special named weapon whose type depends of the Factory that receives the message.
     * <p> This method is useful to create distinguishable weapons. </p>
     * @param name      The new Weapon's name.
     * @return          A new Weapon.
     */
    IWeapon create(String name);
}
