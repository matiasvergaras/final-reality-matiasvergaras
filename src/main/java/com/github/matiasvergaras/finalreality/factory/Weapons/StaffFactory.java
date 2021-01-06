package com.github.matiasvergaras.finalreality.factory.Weapons;

import com.github.matiasvergaras.finalreality.model.weapon.magic.Staff;

/**
 * StaffFactory.
 * <p> It produces Staff Weapons, using the constructor of its parent class WeaponFactory, following
 * the FactoryPattern. </p>
 * @since Homework 2
 * @author Matias Vergara Silva
 */
public class StaffFactory extends WeaponFactory{

    /**
     * Constructor for a new StaffFactory.
     * @param name      The default name that the weapons produced by this factory will have.
     * @param power         The default Power value that the weapons produced by this factory will have.
     * @param weight        The default Weight value that the weapons produced by this factory will have.
     * @param magicPower    The default magicPower value that the weapons produced by this factory will have.
     */
    public StaffFactory(int power, int weight, int magicPower){
        super(power,weight);
        this.magicPower = magicPower;
    }

    /**
     * {@inheritDoc}
     * @param name      The new Weapon's name.
     * @return           A new Staff Object with the default values and the given name.
     */
    public Staff create(String name){
        return new Staff(name, power, weight, magicPower);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isMagic(){
        return true;
    }

}
