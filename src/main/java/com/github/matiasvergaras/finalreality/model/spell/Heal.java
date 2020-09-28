package com.github.matiasvergaras.finalreality.model.spell;

import org.jetbrains.annotations.NotNull;

/**
 * Class to represent a ''Heal'' Magic Spell.
 * <p>
 * Heal Spell is a WhiteMagic Type Spell.
 * It heals an ally for 30% of their maximum heals points (HP).
 * Default cost: 15 mana
 *
 * @author Matías Vergara Silva
 *
 */
public class Heal extends AbstractSpell{
    /**
     * Creates a Heal Spell with a name, cost and type.
     * @param name
     *     the spell name
     * @param cost
     *     the cost of this spell (in mana)
     */
    protected Heal(@NotNull String name,
                   final int cost) {
        super(name, cost, "HEAL");
    }
}
