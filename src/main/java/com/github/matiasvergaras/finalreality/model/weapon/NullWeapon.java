package com.github.matiasvergaras.finalreality.model.weapon;

import com.github.matiasvergaras.finalreality.model.WeaponAttributeSet;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.magic.AbstractMagicWeapon;

import java.util.Objects;

/**
 * This class represents a Null Weapon, application of
 * NullPattern that we will use to limit the number of
 * null pointers in the game.
 * <p> A NullWeapon does not have a name, and its power,
 * weight and magicPower are 0. It can be equipped to any character. </p>
 */
public class NullWeapon extends AbstractMagicWeapon {

    /**
     * Constructor for a default item without any special behaviour.
     */

    public NullWeapon() {
        super("", 0, 0, 0);
    }

    @Override
    public WeaponAttributeSet getAttributes() {
        return null;
    }

    @Override
    public void equipToKnight(IPlayerCharacter character) {
        character.equip(this);
    }

    @Override
    public void equipToEngineer(IPlayerCharacter character) {
        character.equip(this);
    }

    @Override
    public void equipToThief(IPlayerCharacter character) {
        character.equip(this);
    }

    @Override
    public void equipToBlackMage(IPlayerCharacter character) {
        character.equip(this);
    }

    @Override
    public void equipToWhiteMage(IPlayerCharacter character) {
        character.equip(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NullWeapon)) return false;
        NullWeapon that = (NullWeapon) o;
        return this.getName().equals(that.getName()) &&
                this.getPower() == that.getPower() &&
                this.getWeight() == that.getWeight();
    }

    /**
     * Returns an integer value, generated by a hashing algorithm, distinct
     * for distinct objects. Required for the equals method.
     *
     * @return Integer hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getPower(),
                this.getWeight());
    }

}
