package com.github.matiasvergaras.finalreality.factory.Weapons;

import com.github.matiasvergaras.finalreality.model.weapon.normal.Axe;

/**
 * AxeFactory.
 * <p> It produces Axe Weapons, using the constructor of its parent class WeaponFactory, following
 * the FactoryPattern. </p>
 * @since Homework 2
 * @author Matias Vergara Silva
 */
public class AxeFactory extends WeaponFactory {

    /**
     * Constructor for a new AxeFactory.
     * @param name      The default name that the weapons produced by this factory will have.
     * @param power     The default Power value that the weapons produced by this factory will have.
     * @param weight    The default Weight value that the weapons produced by this factory will have.
     */
    public AxeFactory(String name, int power, int weight){
        super(name, power, weight);
    }

    /**
     * {@inheritDoc}
     * @return      a new Axe Object with the default values and name.
     */
    public Axe create(){
        return new Axe(name, power, weight);
    }

    /**
     * {@inheritDoc}
      * @param givenName      The new Weapon's name.
     * @return           A new Axe Object with the default values and the given name.
     */
    public Axe create(String givenName){
        return new Axe(givenName, power, weight);
    }

}