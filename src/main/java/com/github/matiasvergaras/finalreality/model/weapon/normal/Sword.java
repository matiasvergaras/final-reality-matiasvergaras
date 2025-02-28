package com.github.matiasvergaras.finalreality.model.weapon.normal;

import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

/**
 * Class to represent a ''Sword'' weapon.
 * <p>
 * Swords can be used by Knights and Thieves (Thief).
 * @since Homework 1
 * @author Matías Vergara Silva
 */

public class Sword extends AbstractWeapon {

    /**
     * Creates a Sword object with a name, a base damage, weight and type.
     *
     * @param name   the Sword name
     * @param power  the Sword power
     * @param weight the Sword weight
     */
    public Sword(final String name, final int power, final int weight) {
        super(name, power, weight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void equipToKnight(IPlayerCharacter character) {
        setWeaponFree();
        character.equip(this);
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
     * Check if this is equal to a given object o.
     *
     * @param o The target object
     * @return True if are equals, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sword)) return false;
        Sword that = (Sword) o;
        return this.getName().equals(that.getName()) &&
                this.getPower() == that.getPower() &&
                this.getWeight() == that.getWeight();
    }

    /**
     * Returns an integer value, generated by a hashing algorithm, distinct
     * for distinct objects. Required for the equals method.
     *
     * @return Integer hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getPower(),
                this.getWeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSword(){
        return true;
    }

}
