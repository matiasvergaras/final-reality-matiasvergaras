package com.github.matiasvergaras.finalreality.model.character.player.Normal;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.AbstractPlayerCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * An abstract class that holds the common behaviour of all the Normal Characters in the game.
 *
 * @author Matias Vergara Silva.
 */
public abstract class AbstractNormalCharacter extends AbstractPlayerCharacter {

    /**
     * Creates a new Normal Character.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     * @param characterClass
     *     the class of this character
     */
    public AbstractNormalCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                   @NotNull String name,
                                   final String characterClass) {
        super(turnsQueue, name, characterClass);
    }

}
