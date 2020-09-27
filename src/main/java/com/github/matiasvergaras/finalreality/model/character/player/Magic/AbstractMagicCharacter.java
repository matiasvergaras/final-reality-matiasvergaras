package com.github.matiasvergaras.finalreality.model.character.player.Magic;
import com.github.matiasvergaras.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.CharacterClass;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.BlockingQueue;

/**
 * An abstract class that holds the common behaviour of all the Magic Characters in the game.
 *
 * @author Matias Vergara Silva.
 */
public abstract class AbstractMagicCharacter extends AbstractPlayerCharacter{
    private int mana = 100; //Default mana


    /**
     * Creates a new Magic Character.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     * @param characterClass
     *     the class of this character
     */
    protected AbstractMagicCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                     @NotNull String name,
                                     final CharacterClass characterClass) {
        super(turnsQueue, name, characterClass);
    }


}
