package com.github.matiasvergaras.finalreality.model.weapon.Magic;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;

import java.util.Objects;

/**
 * Class to represent a ''Staff'' weapon.
 * <p>
 * Staves (Staff) can be used only by Magic Characters, i.e. Mages.
 * @author Matías Vergara Silva
 *
 */
public class Staff extends AbstractMagicWeapon implements IMagicWeapon {

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
        super(name, power, weight, magicDamage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void equipToBlackMage(IPlayerCharacter character) {
        setWeaponFree();
        character.equip(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void equipToWhiteMage(IPlayerCharacter character) {
        setWeaponFree();
        character.equip(this);
    }

    /**
     * Check if this is equal to a given object o.
     * @param o The target object
     * @return True if are equals, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;
        Staff that = (Staff) o;
        return this.getName().equals(that.getName()) &&
                this.getPower() == that.getPower() &&
                this.getWeight() == that.getWeight() &&
                this.getMagicDamage() == that.getMagicDamage();
    }

    /**
     * Returns an integer value, generated by a hashing algorithm, distinct
     * for distinct objects. Required for the equals method.
     * @return Integer hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getPower(),
                this.getWeight(), this.getMagicDamage());
    }

}
