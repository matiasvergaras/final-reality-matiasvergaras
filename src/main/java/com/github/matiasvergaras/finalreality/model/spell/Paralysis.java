package com.github.matiasvergaras.finalreality.model.spell;

import org.jetbrains.annotations.NotNull;

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
}
