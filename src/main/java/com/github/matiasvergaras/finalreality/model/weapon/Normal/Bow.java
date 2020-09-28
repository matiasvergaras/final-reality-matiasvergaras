package com.github.matiasvergaras.finalreality.model.weapon.Normal;
import com.github.matiasvergaras.finalreality.model.weapon.WeaponType;

/**
 * Class to represent a ''Bow'' weapon.
 * <p>
 *
 *
 * @author Matías Vergara Silva
 *
 */


public class Bow extends AbstractNormalWeapon{
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
        super(name, power, weight, WeaponType.BOW);
    }
}
