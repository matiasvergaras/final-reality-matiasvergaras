package com.github.matiasvergaras.finalreality.model.spell;

/**
 * This represents a Spell from the game.
 * A Spell can be used by a magic character (implies only player characters).
 * and it has a cost in mana and a associated type.
 * @see SpellType
 * @author Matias Vergara Silva.
 */
public interface ISpell {

    /**
     * Returns this spell name.
     */
    String getName();

    /**
     * Returns this spell cost.
     */
    int getCost();

    /**
     * Returns this spell type.
     */
    String getType();

}
