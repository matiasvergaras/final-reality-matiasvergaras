package com.github.matiasvergaras.finalreality.model.weapon.Magic;

import com.github.matiasvergaras.finalreality.model.spell.Fire;

/**
 * Class to represent a ''Stick'' weapon.
 * <p>
 *
 *
 * @author Matías Vergara Silva
 *
 */


public class Staff extends AbstractMagicWeapon{

    /**
     * Creates a Staff object with a name, a base damage, weight and type.
     * @param name
     *              the Staff name
     * @param power
     *              the Staff power
     * @param weight
     *              the Staff weight
     * @param magicDamage
     *              the Staff magic Damage
     */
    public Staff(final String name, final int power, final int weight, final int magicDamage) {
        super(name, power, weight, "STAFF", magicDamage);
    }

    /**
     * Check if this is equal to a given object o.
     * @param o The target object
     * @return True if are equals, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;
        Staff that = (Staff) o;
        return this.getName().equals(that.getName()) &&
                this.getType().equals(that.getType()) &&
                this.getPower() == that.getPower() &&
                this.getWeight() == that.getWeight() &&
                this.getMagicDamage() == that.getMagicDamage();
    }

}
