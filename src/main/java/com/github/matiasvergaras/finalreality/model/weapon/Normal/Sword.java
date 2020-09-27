package com.github.matiasvergaras.finalreality.model.weapon.Normal;
import com.github.matiasvergaras.finalreality.model.weapon.AbstractWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.WeaponType;

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
        super(name, power, weight, WeaponType.SWORD);
    }
}
