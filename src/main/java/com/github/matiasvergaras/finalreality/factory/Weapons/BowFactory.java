package com.github.matiasvergaras.finalreality.factory.Weapons;

import com.github.matiasvergaras.finalreality.model.weapon.normal.Bow;

/**
 * BowFactory.
 * <p> It produces Bow Weapons, using the constructor of its parent class WeaponFactory, following
 * the FactoryPattern. </p>
 * @since Homework 2
 * @author Matias Vergara Silva
 */
public class BowFactory extends WeaponFactory {

    /**
     * Constructor for a new BowFactory.
     * @param name      The default name that the weapons produced by this factory will have.
     * @param power     The default Power value that the weapons produced by this factory will have.
     * @param weight    The default Weight value that the weapons produced by this factory will have.
     */
    public BowFactory(String name, int power, int weight){
        super(name, power, weight);
    }

    /**
     * {@inheritDoc}
     * @return      a new Bow Object with the default values and name.
     */
    public Bow create(){
        return new Bow(name, power, weight);
    }

    /**
     * {@inheritDoc}
     * @param name      The new Weapon's name.
     * @return           A new Bow Object with the default values and the given name.
     */
    public Bow create(String name){
        return new Bow(name, power, weight);
    }
}