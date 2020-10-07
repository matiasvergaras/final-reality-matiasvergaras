package com.github.matiasvergaras.finalreality.model.spell;
import com.github.matiasvergaras.finalreality.model.character.player.Normal.Engineer;
import org.jetbrains.annotations.NotNull;


/**
 * Class to represent a ''Fire'' Magic Spell.
 * <p>
 * Fire Spell is a Black Magic Type Spell.
 * It reduces the opponent's health by magicDamage and has 20% chance of inducing ''Burned'' state.
 * Default cost: 15 mana
 *
 * @author Matías Vergara Silva
 *
 */

public class Fire extends AbstractSpell {
    /**
     * Creates a Fire Spell with a name, cost and type.
     * @param name
     *     the spell name
     * @param cost
     *     the cost of this spell (in mana)
     */
    protected Fire(@NotNull String name,
                             final int cost) {
        super(name, cost, "FIRE");
    }

    /**
     * Check if this is equal to a given object o.
     * @param o The target object
     * @return True if are equals, false otherwise
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fire)) return false;
        Fire that = (Fire) o;
        return this.getName().equals(that.getName()) &&
                this.getCost() == that.getCost() &&
                this.getType() == that.getType();
    }

}
