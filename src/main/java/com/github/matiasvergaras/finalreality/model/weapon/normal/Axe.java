package com.github.matiasvergaras.finalreality.model.weapon.normal;

import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

/**
 * Class to represent an ''Axe'' weapon.
 * <p>
 * Axes can be used by Knights and Engineers.
 * @since Homework 1
 * @author Matías Vergara Silva
 */
public class Axe extends AbstractWeapon {

    /**
     * Creates a Axe object with a name, a base damage, weight and type.
     *
     * @param name   the name of this Axe
     * @param power  the power of this Axe
     * @param weight the weight of this Axe
     */
    public Axe(final String name, final int power, final int weight) {
        super(name, power, weight);
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
     * {@inheritDoc}
     */
    @Override
    public void equipToKnight(IPlayerCharacter character) {
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
        if (!(o instanceof Axe)) return false;
        Axe that = (Axe) o;
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
    public boolean isAxe(){
        return true;
    }

}

