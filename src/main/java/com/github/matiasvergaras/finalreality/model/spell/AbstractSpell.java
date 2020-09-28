package com.github.matiasvergaras.finalreality.model.spell;

import org.jetbrains.annotations.NotNull;


/**
 * An abstract class that holds the common behaviour of all  Spells in the game.
 *
 *
 * @author Matias Vergara Silva.
 */

 public abstract class AbstractSpell implements ISpell {
    private final String name;
    private final int cost;
    private final String type;
    /**
     * Creates a Spell with a name, cost and type
     * @param name
     *     the spell name
     * @param cost
     *     the cost of this spell (in mana)
     * @param type
     *     the type of this spell
     *  * @author Matías Vergara Silva.
     */
    protected AbstractSpell (@NotNull String name,
                             final int cost, final String type) {
        this.name = name;
        this.cost = cost;
        this.type = type;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public int getCost() {
        return cost;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getType() {
        return type;
    }
}
