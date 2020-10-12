package com.github.matiasvergaras.finalreality.model.character.player.normal;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.AbstractPlayerCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * An abstract class that holds the common behaviour of all the Normal Characters in the game.
 * Maybe useful in the future.
 *
 * @author Matias Vergara Silva.
 */
public abstract class AbstractNormalCharacter extends AbstractPlayerCharacter {

    /**
     * Creates a new Normal Character.
     *
     * @param name       the character's name
     * @param turnsQueue the queue with the characters ready to play
     * @param HP         this character's heals points
     * @param DP         this character's defense points
     */
    public AbstractNormalCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                   @NotNull String name,
                                   int HP, int DP) {
        super(turnsQueue, name, HP, DP);
    }

}
