package com.github.matiasvergaras.finalreality.model.character.player.Normal;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.CharacterClass;
import com.github.matiasvergaras.finalreality.model.weapon.Magic.Staff;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Bow;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Sword;
import org.jetbrains.annotations.NotNull;
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
     * Creates a new Thief Character with a Sword.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     * @param weapon
     *      it's weapon
     */
    public Thief(@NotNull BlockingQueue<ICharacter> turnsQueue,
                 @NotNull String name,
                 final CharacterClass characterClass, Sword weapon) {
        super(turnsQueue, name, characterClass);
    }
    /**
     * Creates a new Thief Character with a Staff.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     * @param weapon
     *      it's weapon
     */
    public Thief(@NotNull BlockingQueue<ICharacter> turnsQueue,
                 @NotNull String name,
                 final CharacterClass characterClass, Staff weapon) {
        super(turnsQueue, name, characterClass);
    }

     /**
      * Creates a new Thief Character with a Bow.
      *
      * @param name
      *     the character's name
      * @param turnsQueue
      *     the queue with the characters waiting for their turn
      * @param weapon
      *      it's weapon
      */
     public Thief(@NotNull BlockingQueue<ICharacter> turnsQueue,
                  @NotNull String name,
                  final CharacterClass characterClass, Bow weapon) {
         super(turnsQueue, name, characterClass);
     }


}
