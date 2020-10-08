package com.github.matiasvergaras.finalreality.model.weapon.Normal;

import java.util.Objects;

/**
 * Class to represent a ''Knife'' weapon.
 * <p>
 *
 *
 * @author Matías Vergara Silva
 *
 */

public class Knife extends AbstractNormalWeapon {
    /**
     * Creates a Knife object with a name, a base damage, weight and type.
     * @param name
     *              the Knife name
     * @param power
     *              the Knife power
     * @param weight
     *              the Knife weight
     */
    public Knife(final String name, final int power, final int weight) {
        super(name, power, weight, "KNIFE");
    }

    /**
     * Check if this is equal to a given object o.
     * @param o The target object
     * @return True if are equals, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Knife)) return false;
        Knife that = (Knife) o;
        return this.getName().equals(that.getName()) &&
                this.getType().equals(that.getType()) &&
                this.getPower() == that.getPower() &&
                this.getWeight() == that.getWeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(),this.getType(), this.getPower(),
                this.getWeight());
    }


}
