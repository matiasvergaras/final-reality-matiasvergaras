package com.github.matiasvergaras.finalreality.model.character.player.Normal;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.CharacterClass;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Axe;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Knife;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Sword;
import org.jetbrains.annotations.NotNull;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import java.util.concurrent.BlockingQueue;

/**
 * Class to represent a ''Knight'' unit.
 * <p>
 *Knights can equip Swords, Axes and Knives, and no magic spells.
 *
 * @author Matías Vergara Silva
 *
 */
public class Knight extends AbstractNormalCharacter{

    /**

     * Creates a new Knight Character with a Sword.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     * @param weapon
     *      it's weapon
     */
    public Knight(@NotNull BlockingQueue<ICharacter> turnsQueue,
                    @NotNull String name,
                    final CharacterClass characterClass, Sword weapon) {
        super(turnsQueue, name, characterClass);
        this.equip(weapon);
    }
    /**

     * Creates a new Knight Character with an Axe.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     * @param weapon
     *      it's weapon
     */
    public Knight(@NotNull BlockingQueue<ICharacter> turnsQueue,
                  @NotNull String name,
                  final CharacterClass characterClass, Axe weapon) {
        super(turnsQueue, name, characterClass);
        this.equip(weapon);
    }
    /**
     * Creates a new Knight Character with an Knife
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     * @param weapon
     *      it's weapon
     */
    public Knight(@NotNull BlockingQueue<ICharacter> turnsQueue,
                  @NotNull String name,
                  final CharacterClass characterClass, Knife weapon) {
        super(turnsQueue, name, characterClass);
        this.equip(weapon);
    }
    /**

     * Creates a new unarmed Knight Character
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public Knight(@NotNull BlockingQueue<ICharacter> turnsQueue,
                  @NotNull String name,
                  final CharacterClass characterClass) {
        super(turnsQueue, name, characterClass);
    }


}
