package com.github.matiasvergaras.finalreality.model.weapon.Magic;

/**
 * Class to represent a ''Stick'' weapon.
 * <p>
 *
 *
 * @author Matías Vergara Silva
 *
 */


public class Staff extends AbstractMagicWeapon{
    private final int magicDamage;

    /**
     * Creates a Staff object with a name, a base damage, weight and type.
     * @param name
     *              the Staff name
     * @param power
     *              the Staff power
     * @param weight
     *              the Staff weight
     * @param magicDamage
     *              the Staff magic Damage
     */
    public Staff(final String name, final int power, final int weight, final int magicDamage) {
        super(name, power, weight, "STAFF");
        this.magicDamage = magicDamage;
    }
}
