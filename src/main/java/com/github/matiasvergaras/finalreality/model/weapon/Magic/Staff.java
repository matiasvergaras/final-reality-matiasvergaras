package com.github.matiasvergaras.finalreality.model.weapon.Magic;
import com.github.matiasvergaras.finalreality.model.weapon.AbstractWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.WeaponType;

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
     */
    public Staff(final String name, final int power, final int weight) {
        super(name, power, weight, WeaponType.STAFF);
    }
}
