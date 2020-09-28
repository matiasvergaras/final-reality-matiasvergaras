package com.github.matiasvergaras.finalreality.model.weapon.Normal;

/**
 * Class to represent an ''Axe'' weapon.
 * <p>
 *
 *
 * @author Matías Vergara Silva
 *
 */

public class Axe extends AbstractNormalWeapon{
    /**
     * Creates a Axe object with a name, a base damage, weight and type.
     * @param name
     *              the Axe name
     * @param power
     *              the Axe power
     * @param weight
     *              the Axe weight
     */
    public Axe(final String name, final int power, final int weight) {
        super(name, power, weight, "AXE");
    }
}

