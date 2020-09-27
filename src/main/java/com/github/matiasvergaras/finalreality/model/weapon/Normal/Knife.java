package com.github.matiasvergaras.finalreality.model.weapon.Normal;
import com.github.matiasvergaras.finalreality.model.weapon.AbstractWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.WeaponType;

/**
 * Class to represent a ''Knife'' weapon.
 * <p>
 *
 *
 * @author Matías Vergara Silva
 *
 */

public class Knife extends AbstractNormalWeapon {
    /**
     * Creates a Knife object with a name, a base damage, weight and type.
     * @param name
     *              the Knife name
     * @param power
     *              the Knife power
     * @param weight
     *              the Knife weight
     */
    public Knife(final String name, final int power, final int weight) {
        super(name, power, weight, WeaponType.KNIFE);
    }
}
