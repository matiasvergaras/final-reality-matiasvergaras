package com.github.matiasvergaras.finalreality.model.character.player.Magic;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.weapon.Magic.Staff;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * Class to represent a ''Black Wizard'' unit.
 * <p>
 *Black Wizards can can equip Staves (Staff) and Knives (Knife), and use Thunder and Fire Spells.
 *
 * @author Matías Vergara Silva
 *
 */

public class BlackMage extends AbstractMagicCharacter {
    /**
     * Creates a new Black Wizard Character.
     * @param turnsQueue
     *     the queue with the characters ready to play
     * @param name
     *     the character's name
     * @param HP
     *     the character's heal points
     * @param DP
     *     the character's defense points
     * @param mana
     *     the character's mana points
     */
    public BlackMage(@NotNull BlockingQueue<ICharacter> turnsQueue,
                     @NotNull String name,
                     int HP, int DP, int mana) {
        super(turnsQueue, name, "BLACK_MAGE", HP, DP, mana);
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlackMage)) return false;
        BlackMage that = (BlackMage) o;
        return this.getCharacterClass().equals(that.getCharacterClass()) &&
                this.getEquippedWeapon().equals(that.getEquippedWeapon()) &&
                this.getName().equals(that.getName()) &&
                this.getMana()==that.getMana() &&
                this.getHP()==that.getHP() &&
                this.getDP()==that.getDP();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getMana(), this.getEquippedWeapon(),this.getName(),
                this.getCharacterClass() , this.getDP(), this.getHP());
    }

}
