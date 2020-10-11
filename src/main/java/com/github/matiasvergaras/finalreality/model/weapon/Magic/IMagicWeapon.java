package com.github.matiasvergaras.finalreality.model.weapon.Magic;

import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;

public interface IMagicWeapon extends IWeapon {
    /**
     * Get the magic damage of this weapon.
     * @return this weapon's magic damage.
     */
    int getMagicDamage();
}
