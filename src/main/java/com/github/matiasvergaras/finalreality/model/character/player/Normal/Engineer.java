package com.github.matiasvergaras.finalreality.model.character.player.Normal;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.Magic.BlackMage;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Axe;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Bow;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * Class to represent a ''Engineer'' unit.
 * <p>
 *Engineers can equip Axes and Bows, and no magic spells.
 *
 * @author Matías Vergara Silva
 *
 */

public class Engineer extends AbstractNormalCharacter {
    /**
     * Creates a new Engineer Character.
     * @param turnsQueue
     *     the queue with the characters ready to play
     * @param name
     *     the character's name
     * @param HP
     *     the character's heal points
     * @param DP
     *     the character's defense points
     */
    public Engineer(@NotNull BlockingQueue<ICharacter> turnsQueue,
                    @NotNull String name, int HP, int DP) {
        super(turnsQueue, name, "ENGINEER", HP, DP);
    }

    /**
     * Check if this is equal to a given object o.
     * @param o The target object
     * @return True if are equals, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Engineer)) return false;
        Engineer that = (Engineer) o;
        return this.getCharacterClass().equals(that.getCharacterClass()) &&
                this.getEquippedWeapon().equals(that.getEquippedWeapon()) &&
                this.getName().equals(that.getName()) &&
                this.getHP()==that.getHP() &&
                this.getDP()==that.getDP();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getEquippedWeapon(),this.getName(),
                this.getCharacterClass() , this.getDP(), this.getHP());
    }


}

