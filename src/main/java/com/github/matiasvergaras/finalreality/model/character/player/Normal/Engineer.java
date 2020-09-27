package com.github.matiasvergaras.finalreality.model.character.player.Normal;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.CharacterClass;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Axe;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Bow;
import org.jetbrains.annotations.NotNull;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
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
     * Creates a new Engineer Character with an Axe.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     * @param weapon
     *     it's weapon.
     */
    public Engineer(@NotNull BlockingQueue<ICharacter> turnsQueue,
                       @NotNull String name,
                       final CharacterClass characterClass, Axe weapon) {
        super(turnsQueue, name, characterClass);
        this.equip(weapon);
    }

    /**
     * Creates a new Engineer Character with a Bow.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     * @param weapon
     *     it's weapon.
     */
    public Engineer(@NotNull BlockingQueue<ICharacter> turnsQueue,
                    @NotNull String name,
                    final CharacterClass characterClass, Bow weapon) {
        super(turnsQueue, name, characterClass);
        this.equip(weapon);
    }

    /**
     * Creates a new Engineer Character unarmed.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public Engineer(@NotNull BlockingQueue<ICharacter> turnsQueue,
                    @NotNull String name,
                    final CharacterClass characterClass) {
        super(turnsQueue, name, characterClass);
    }

}

