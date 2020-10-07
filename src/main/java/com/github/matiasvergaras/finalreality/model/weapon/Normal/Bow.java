package com.github.matiasvergaras.finalreality.model.weapon.Normal;

/**
 * Class to represent a ''Bow'' weapon.
 * <p>
 *
 *
 * @author Matías Vergara Silva
 *
 */


public class Bow extends AbstractNormalWeapon{
    /**
     * Creates a Bow object with a name, a base damage, weight and type.
     * @param name
     *              the Bow name
     * @param power
     *              the Bow power
     * @param weight
     *              the Bow weight
     */
    public Bow(final String name, final int power, final int weight) {
        super(name, power, weight, "BOW");
    }

    /**
     * Check if this is equal to a given object o.
     * @param o The target object
     * @return True if are equals, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bow)) return false;
        Bow that = (Bow) o;
        return this.getName().equals(that.getName()) &&
                this.getType().equals(that.getType()) &&
                this.getPower() == that.getPower() &&
                this.getWeight() == that.getWeight();
    }

}
