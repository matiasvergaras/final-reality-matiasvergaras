package com.github.matiasvergaras.finalreality.model.weapon.Normal;

import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

/**
 * Class to represent a ''Bow'' weapon.
 * <p>
 * Bow's can be used by Engineers and Thieves (Thief).
 *
 * @author Matías Vergara Silva
 *
 */
public class Bow extends AbstractWeapon {

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
        super(name, power, weight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void equipToThief(IPlayerCharacter character) {
        setWeaponFree();
        character.equip(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void equipToEngineer(IPlayerCharacter character) {
        setWeaponFree();
        character.equip(this);
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
                this.getPower() == that.getPower() &&
                this.getWeight() == that.getWeight();
    }

    /**
     * Returns an integer value, generated by a hashing algorithm, distinct
     * for distinct objects. Required for the equals method.
     * @return Integer hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getPower(),
                this.getWeight());
    }

}
