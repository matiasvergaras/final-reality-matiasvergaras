package com.github.matiasvergaras.finalreality.model.character.player.Magic;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.CharacterClass;
import com.github.matiasvergaras.finalreality.model.weapon.Magic.Staff;
import org.jetbrains.annotations.NotNull;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import java.util.concurrent.BlockingQueue;

/**
 * Class to represent a ''White Wizard'' unit.
 * <p>
 *White Wizards can can equip only Staves (Staff), and use Heal, Poison and Paralysis Spells.
 *
 * @author Matías Vergara Silva
 *
 */

public class WhiteWizard extends AbstractMagicCharacter {

    /**
     * Creates a new Black Wizard Character with a Staff.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     * @param characterClass
     *      the class of this character
     * @param weapon
     *     it's weapon.
     */
    public WhiteWizard(@NotNull BlockingQueue<ICharacter> turnsQueue,
                       @NotNull String name,
                       final CharacterClass characterClass, Staff weapon) {
        super(turnsQueue, name, characterClass);
        this.equippedWeapon = weapon;

    }

    /**
     * Creates a new unarmed Black Wizard Character.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     * @param characterClass
     *      the class of this character
     */
    public WhiteWizard(@NotNull BlockingQueue<ICharacter> turnsQueue,
                       @NotNull String name,
                       final CharacterClass characterClass) {
        super(turnsQueue, name, characterClass);

    }


}
