package com.github.matiasvergaras.finalreality.factory.Weapons;

import com.github.matiasvergaras.finalreality.model.weapon.normal.Sword;

/**
 * SwordFactory.
 * <p> It produces Sword Weapons, using the constructor of its parent class WeaponFactory, following
 * the FactoryPattern. </p>
 * @since Homework 2
 * @author Matias Vergara Silva
 */
public class SwordFactory extends WeaponFactory{

    /**
     * Constructor for a new SwordFactory.
     * @param name      The default name that the weapons produced by this factory will have.
     * @param power     The default Power value that the weapons produced by this factory will have.
     * @param weight    The default Weight value that the weapons produced by this factory will have.
     */
    public SwordFactory(String name, int power, int weight){
        super(name, power, weight);
    }

    /**
     * {@inheritDoc}
     * @return      a new Staff Object with the default values and name.
     */
    public Sword create(){
        return new Sword(name, power, weight);
    }

    /**
     * {@inheritDoc}
     * @param name      The new Weapon's name.
     * @return           A new Sword Object with the default values and the given name.
     */
    public Sword create(String name){
        return new Sword(name, power, weight);
    }
}
