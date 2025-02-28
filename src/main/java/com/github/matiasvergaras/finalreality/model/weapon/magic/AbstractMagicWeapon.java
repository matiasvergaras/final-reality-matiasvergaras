package com.github.matiasvergaras.finalreality.model.weapon.magic;
import com.github.matiasvergaras.finalreality.model.AttributeSet.WeaponAttributeSet;
import com.github.matiasvergaras.finalreality.model.weapon.AbstractWeapon;


/**
 * An abstract class that holds the common behaviour of all  Magic Weapons in the game.
 * @since Homework 1
 * @author Matias Vergara Silva.
 * <p>
 * This class could serve us, for example, if we wanted to add properties of
 * '' additional mana '' or '' lifesteal '' to magic weapons.
 * </p>
 */
public abstract class AbstractMagicWeapon extends AbstractWeapon implements IMagicWeapon {
    private final int magicPower;

    /**
     * Creates a default Magic Weapon with a name, base damage, weight and magic damage.
     *
     * @param name        the weapon's name.
     * @param power       the weapon's base power.
     * @param weight      the weight of this weapon.
     * @param magicDamage the weapon's magic power.
     */
    public AbstractMagicWeapon(final String name, final int power, final int weight,
                               int magicDamage) {
        super(name, power, weight);
        this.magicPower = magicDamage;
    }

    /**
     * Get the magic damage of this weapon.
     *
     * @return this weapon's magic damage.
     */
    public int getMagicPower() {
        return magicPower;
    }

    /**
     * {@inheritDoc}
     * @return a WeaponAttributeSet with the attributes of this weapon.
     */
    @Override
    public WeaponAttributeSet getAttributes(){
        return new WeaponAttributeSet(this.getName(), this.getPower(), this.getWeight(),
                                        this.getMagicPower());
    }

}
