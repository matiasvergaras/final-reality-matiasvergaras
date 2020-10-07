package com.github.matiasvergaras.finalreality.model.character.player.Magic;
import com.github.matiasvergaras.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.BlockingQueue;

/**
 * An abstract class that holds the common behaviour of all the Magic Characters in the game.
 *
 * @author Matias Vergara Silva.
 */
public abstract class AbstractMagicCharacter extends AbstractPlayerCharacter{
    private final int mana;

    /**
     * Creates a new Magic Character.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters ready to play
     * @param characterClass
     *     the class of this character
     */
    protected AbstractMagicCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                     @NotNull String name,
                                     final String characterClass,
                                     int HP, int DP, int mana) {
        super(turnsQueue, name, characterClass, HP, DP);
        this.mana=mana;
    }

    public int getMana() {
        return this.mana;
    }

}
