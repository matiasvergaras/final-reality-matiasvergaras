package com.github.matiasvergaras.finalreality.factory.Weapons;

import com.github.matiasvergaras.finalreality.model.weapon.normal.Knife;

/**
 * KnifeFactory.
 * <p> It produces Knife Weapons, using the constructor of its parent class WeaponFactory, following
 * the FactoryPattern. </p>
 * @since Homework 2
 * @author Matias Vergara Silva
 */
public class KnifeFactory extends WeaponFactory {

    /**
     * Constructor for a new KnifeFactory.
     * @param name      The default name that the weapons produced by this factory will have.
     * @param power     The default Power value that the weapons produced by this factory will have.
     * @param weight    The default Weight value that the weapons produced by this factory will have.
     */
    public KnifeFactory(int power, int weight){
        super(power, weight);
    }


    /**
     * {@inheritDoc}
     * @param name      The new Weapon's name.
     * @return           A new Knife Object with the default values and the given name.
     */
    public Knife create(String name){
        return new Knife(name, power, weight);
    }
}
