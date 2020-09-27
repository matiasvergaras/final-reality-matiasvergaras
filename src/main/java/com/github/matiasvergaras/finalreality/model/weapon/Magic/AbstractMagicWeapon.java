package com.github.matiasvergaras.finalreality.model.weapon.Magic;

import com.github.matiasvergaras.finalreality.model.weapon.AbstractWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.WeaponType;


/**
 * An abstract class that holds the common behaviour of all  Magic Weapons in the game.
 *
 * @author Matias Vergara Silva.
 * <p>
 * This class could serve us, for example, if we wanted to add properties of
 * '' additional mana '' or '' lifesteal '' to magic weapons.
 * </p>
 */
public abstract class AbstractMagicWeapon extends AbstractWeapon {
    /**
     * Creates a Magic Weapon with a name, a base damage, speed and it's type.
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
    public AbstractMagicWeapon(final String name, final int power, final int weight,
                          final WeaponType type) {
        super(name, power, weight, type);
    }
}
