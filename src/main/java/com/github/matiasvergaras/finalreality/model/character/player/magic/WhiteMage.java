package com.github.matiasvergaras.finalreality.model.character.player.magic;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.cpu.ICPUCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * Class to represent a ''White Wizard'' unit.
 * <p>
 * White Wizards can can equip only Staves (Staff), and use Heal, Poison and Paralysis Spells.
 *
 * @author Matías Vergara Silva
 */

public class WhiteMage extends AbstractMagicCharacter {

    /**
     * Creates a new White Wizard Character.
     *
     * @param turnsQueue the queue with the characters ready to play.
     * @param name       the character's name
     * @param HP         the character's heal points
     * @param DP         the character's defense points
     * @param mana       the character's mana points
     */
    public WhiteMage(@NotNull BlockingQueue<ICharacter> turnsQueue,
                     @NotNull String name, int HP, int DP, int mana) {
        super(turnsQueue, name, HP, DP, mana);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void equipWeapon(IWeapon weapon) {
        weapon.equipToWhiteMage(this);
    }

    /**
     * Check if this is equal to a given object o.
     *
     * @param o The target object
     * @return True if are equals, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WhiteMage)) return false;
        WhiteMage that = (WhiteMage) o;
        return this.getName().equals(that.getName()) &&
                this.getMaxHP() == that.getMaxHP() &&
                this.getDP() == that.getDP() &&
                this.getMaxMana() == that.getMaxMana();
    }

    /**
     * Returns an integer value, generated by a hashing algorithm, distinct
     * for distinct objects. Required for the equals method.
     *
     * @return Integer hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getMaxHP(), this.getDP(),
                +this.getMaxMana());
    }


}
