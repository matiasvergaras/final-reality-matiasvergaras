package com.github.matiasvergaras.finalreality.model.weapon.magic;

import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;

/**
 *
 * This represent a Magic Weapon from the game.
 * <p>
 * Magic Weapons are part of the Weapons of the game, but they
 * have an special field magicDamage, used to calculate the power
 * of the magic attacks of their owner. Only Magic Characters
 * are able to use their magicDamage.
 * @see IMagicWeapon
 * @author Matías Vergara Silva
 */
public interface IMagicWeapon extends IWeapon {
    /**
     * Get the magic damage of this weapon.
     *
     * @return this weapon's magic damage.
     */
    int getMagicDamage();
}
