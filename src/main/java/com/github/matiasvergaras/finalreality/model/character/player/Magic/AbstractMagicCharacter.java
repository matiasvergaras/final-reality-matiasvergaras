package com.github.matiasvergaras.finalreality.model.character.player.magic;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.cpu.IEnemyCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * An abstract class that holds the common behaviour of all the Magic Characters in the game.
 *
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

    public void reduceMana(int diff) {
        currentMana -= diff;
    }

    public void useThunderSpell(IEnemyCharacter enemy) {
    }

    public void useFireSpell(IEnemyCharacter enemy) {
    }

    public void useParalysisSpell(IEnemyCharacter enemy) {
    }

    public void usePoisonSpell(IEnemyCharacter enemy) {
    }

    public void useHealSpell(IPlayerCharacter ally) {
    }


}
