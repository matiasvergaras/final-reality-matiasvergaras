package com.github.matiasvergaras.finalreality.model.character.player.normal;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;


/**
 * Class to represent a ''Thief'' unit.
 * <p>
 * Thieves can equip Swords, Knives (Knife) and Bows, and cannot use any Spells.
 *
 * @author Matías Vergara Silva
 */
public class Thief extends AbstractPlayerCharacter {

    /**
     * Creates a new Thief Character.
     *
     * @param turnsQueue the queue with the characters ready to play
     * @param name       the character's name
     * @param HP         the character's heal points
     * @param DP         the character's defense points
     */
    public Thief(@NotNull BlockingQueue<ICharacter> turnsQueue,
                 @NotNull String name, int HP, int DP) {
        super(turnsQueue, name, HP, DP);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void equipWeapon(IWeapon weapon) {
        weapon.equipToThief(this);
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
        if (!(o instanceof Thief)) return false;
        Thief that = (Thief) o;
        return this.getName().equals(that.getName()) &&
                this.getMaxHP() == that.getMaxHP() &&
                this.getMaxDP() == that.getMaxDP() &&
                this.getCurrentDP() == that.getCurrentDP() &&
                this.getCurrentHP() == that.getCurrentHP();
    }

    /**
     * Returns an integer value, generated by a hashing algorithm, distinct
     * for distinct objects. Required for the equals method.
     *
     * @return Integer hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getName(),
                this.getMaxHP(), this.getMaxDP());
    }


}
