package com.github.matiasvergaras.finalreality.model.spell;

import org.jetbrains.annotations.NotNull;

/**
 * Class to represent a ''Poison'' Magic Spell.
 * <p>
 * Poison Spell is a White Magic Type Spell.
 * It applies ''Poisoned'' state to an opponent.
 * Default cost: 40 mana
 *
 * @author Matías Vergara Silva
 *
 */
public class Poison extends AbstractSpell{
    /**
     * Creates a Spell with a name, cost and type.
     * @param name
     *     the spell name
     * @param cost
     *     the cost of this spell (in mana)
     */
    protected Poison(@NotNull String name,
                   final int cost) {
        super(name, cost, "POISON");
    }

    /**
     * Check if this is equal to a given object o.
     * @param o The target object
     * @return True if are equals, false otherwise
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Poison)) return false;
        Poison that = (Poison) o;
        return this.getName().equals(that.getName()) &&
                this.getCost() == that.getCost() &&
                this.getType() == that.getType();
    }
}
