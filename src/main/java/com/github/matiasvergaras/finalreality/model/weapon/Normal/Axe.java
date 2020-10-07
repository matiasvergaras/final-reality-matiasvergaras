package com.github.matiasvergaras.finalreality.model.weapon.Normal;

import com.github.matiasvergaras.finalreality.model.weapon.Magic.Staff;

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

    /**
     * Check if this is equal to a given object o.
     * @param o The target object
     * @return True if are equals, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Axe)) return false;
        Axe that = (Axe) o;
        return this.getName().equals(that.getName()) &&
                this.getType().equals(that.getType()) &&
                this.getPower() == that.getPower() &&
                this.getWeight() == that.getWeight();
    }

}

