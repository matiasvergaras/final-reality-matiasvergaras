package com.github.matiasvergaras.finalreality.model.weapon.Normal;

import com.github.matiasvergaras.finalreality.model.weapon.AbstractWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.WeaponType;

/**
 * An abstract class that holds the common behaviour of all Normal Weapons in the game.
 *
 * @author Matias Vergara Silva.
 * <p>
 * This class could serve us, for example, if we wanted to add properties of
 * '' sharp '' or '' penetration '' to normal weapons.
 * </p>
 */
public abstract class AbstractNormalWeapon extends AbstractWeapon {
    /**
     * Creates a Normal Weapon with a name, a base damage, speed and it's type.
     * @param name
     *     the weapon's name
     * @param power
     *     the weapon's base power (to heal or to damage).
     * @param weight
     *     the weight of this weapon
     * @param type
     *      the type of this weapon.
     * @see WeaponType
     */
    public AbstractNormalWeapon(final String name, final int power, final int weight,
                               final WeaponType type) {
        super(name, power, weight, type);
    }
}