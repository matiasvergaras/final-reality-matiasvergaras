package com.github.matiasvergaras.finalreality.model.weapon.Normal;

/**
 * Class to represent a ''Sword'' weapon.
 * <p>
 *
 *
 * @author Matías Vergara Silva
 *
 */


public class Sword extends AbstractNormalWeapon {
    /**
     * Creates a Sword object with a name, a base damage, weight and type.
     * @param name
     *              the Sword name
     * @param power
     *              the Sword power
     * @param weight
     *              the Sword weight
     */
    public Sword(final String name, final int power, final int weight) {
        super(name, power, weight, "SWORD");
    }

    /**
     * Check if this is equal to a given object o.
     * @param o The target object
     * @return True if are equals, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sword)) return false;
        Sword that = (Sword) o;
        return this.getName().equals(that.getName()) &&
                this.getType().equals(that.getType()) &&
                this.getPower() == that.getPower() &&
                this.getWeight() == that.getWeight();
    }


}
