package com.github.matiasvergaras.finalreality.model.character.player.magic;

import com.github.matiasvergaras.finalreality.model.AttributeSet.CharacterAttributeSet;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.AbstractPlayerCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * An abstract class that holds the common behaviour of all Magic Characters in the game.
 * @since Homework 1
 * @author Matias Vergara Silva.
 */
public abstract class AbstractMagicCharacter extends AbstractPlayerCharacter implements IMagicCharacter {
    private final int maxMana;
    private int currentMana;

    /**
     * Creates a new Magic Character.
     *
     * @param name       the character's name
     * @param turnsQueue the queue with the characters ready to play
     * @param HP         this character heal points
     * @param DP         this character defense points
     * @param mana       this character mana points
     */
    protected AbstractMagicCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                     @NotNull String name,
                                     int HP, int DP, int mana) {
        super(turnsQueue, name, HP, DP);
        this.maxMana = mana;
        this.currentMana = mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    /**
     * To get the attributes of this character,
     * adding the maxMana and the currentMana to those of
     * the parent class.
     * @return An ArrayList of Integer whit the attributes in the following
     * order: maxHP, currentHP, DP, maxMana, currentMana
     */
    @Override
    public CharacterAttributeSet getAttributes(){
        return new CharacterAttributeSet(this.getName(), this.getMaxHP(), this.getCurrentHP(), this.getDP(), this.getMaxMana(),
                this.getCurrentMana(), this.getEquippedWeapon());
    }

    /**
     * {@inheritDoc}
     *
     */
    public boolean isMagic(){
        return true;
    }


}
