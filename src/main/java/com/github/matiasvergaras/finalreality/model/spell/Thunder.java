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
        super(name, cost, SpellType.THUNDER);
    }
}
