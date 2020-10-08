package com.github.matiasvergaras.finalreality.model.spell;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Class to represent a ''Paralysis'' Magic Spell.
 * <p>
 * Paralysis Spell is a White Magic Type Spell.
 * It applies ''Paralyzed'' state to an opponent.
 * Default cost: 25 mana
 *
 * @author Matías Vergara Silva
 *
 */
public class Paralysis extends AbstractSpell{
    /**
     * Creates a Paralysis Spell with a name, cost and type.
     * @param name
     *     the spell name
     * @param cost
     *     the cost of this spell (in mana)
     */
    protected Paralysis(@NotNull String name,
                   final int cost) {
        super(name, cost, "PARALYSIS");
    }

    /**
     * Check if this is equal to a given object o.
     * @param o The target object
     * @return True if are equals, false otherwise
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paralysis)) return false;
        Paralysis that = (Paralysis) o;
        return this.getName().equals(that.getName()) &&
                this.getCost() == that.getCost() &&
                this.getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(),this.getCost(),
                this.getType());
    }

}
