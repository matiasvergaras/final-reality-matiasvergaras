package com.github.matiasvergaras.finalreality.model.character.player.Magic;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.CharacterClass;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.BlockingQueue;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;

/**
 * Class to represent a ''Black Wizard'' unit.
 * <p>
 *Black Wizards can can equip Staves (Staff) and Knives (Knife), and use Thunder and Fire Spells.
 *
 * @author Matías Vergara Silva
 *
 */

public class BlackWizard extends AbstractMagicCharacter {

    /**
     * Creates a new Black Wizard Character.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     * @param characterClass
     *     the class of this character
     */
    public BlackWizard(@NotNull BlockingQueue<ICharacter> turnsQueue,
                       @NotNull String name,
                       final CharacterClass characterClass, int mana, IWeapon weapon) {
        super(turnsQueue, name, characterClass, mana,  weapon);

    }

}
