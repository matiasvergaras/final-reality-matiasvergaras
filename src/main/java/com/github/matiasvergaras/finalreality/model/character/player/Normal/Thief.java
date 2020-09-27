package com.github.matiasvergaras.finalreality.model.character.player.Normal;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.CharacterClass;
import org.jetbrains.annotations.NotNull;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import java.util.concurrent.BlockingQueue;

/**
 * Class to represent a ''Thief'' unit.
 * <p>
 *Thieves can equip Swords, Staves (Staff) and Bows, and cannot use any Spells.
 *
 * @author Matías Vergara Silva
 *
 */
public class Thief extends AbstractNormalCharacter{
    /**
     * Creates a new Thief Character.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public Thief(@NotNull BlockingQueue<ICharacter> turnsQueue,
                 @NotNull String name,
                 final CharacterClass characterClass, IWeapon weapon) {
        super(turnsQueue, name, characterClass, weapon);
    }
}
