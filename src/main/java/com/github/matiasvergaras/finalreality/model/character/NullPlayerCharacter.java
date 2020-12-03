package com.github.matiasvergaras.finalreality.model.character;

import com.github.matiasvergaras.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class NullPlayerCharacter extends AbstractPlayerCharacter {

    /**
     * Constructor for a new Character.
     *
     * @param turnsQueue the queue with the characters ready to play
     *
     */
    public NullPlayerCharacter() {
        super(new LinkedBlockingQueue<>(), "Null", 0, 0);
    }

    public void waitTurn() {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor
                .schedule(super::addToQueue, 0, TimeUnit.SECONDS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void equipWeapon(IWeapon weapon) {
        if(this.isAlive()) {
            weapon.equipToNull(this);
        }
    }


}
