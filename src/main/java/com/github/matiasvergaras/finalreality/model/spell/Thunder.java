package com.github.matiasvergaras.finalreality.model.spell;

import org.jetbrains.annotations.NotNull;

/**
 * Class to represent a ''Thunder'' Magic Spell.
 * <p>
 * Thunder Spell is a White Magic Type Spell.
 * Reduces the opponent's health by magicDamage and has a 30% chances of inducing ''Paralyzed'' state.
 * Default cost: 40 mana
 *
 * @author Matías Vergara Silva
 *
 */
public class Thunder extends AbstractSpell{
    /**
     * Creates a Spell with a name, cost and type.
     * @param name
     *     the spell name
     * @param cost
     *     the cost of this spell (in mana)
     */
    protected Thunder(@NotNull String name,
                   final int cost) {
        super(name, cost, "THUNDER");
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
