package com.github.matiasvergaras.finalreality.model.character;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NullCharacter extends AbstractCharacter{

    /**
     * Constructor for a new Character.
     *
     * @param turnsQueue the queue with the characters ready to play
     *
     */
    public NullCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(turnsQueue, "Null", 0, 0);
    }

    public void waitTurn() {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor
                .schedule(super::addToQueue, 0, TimeUnit.SECONDS);
    }

}
