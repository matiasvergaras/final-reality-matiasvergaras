package com.github.matiasvergaras.finalreality.controller.handlers;


import com.github.matiasvergaras.finalreality.model.Mastermind.IMastermind;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * PropertyChangeListener.
 * <p> This class implements a Handler of the addition of a character to the turns queue. </p>
 * <p> His mission is to look for character's addQueue, and notify to their Mastermind. </p>
 * @author Matias Vergara Silva
 * @since Homework 2
 */
public class AddQueueCharacterToMMHandler implements PropertyChangeListener {
    IMastermind mastermind;

    /**
     * Creates a new handler for the end of a turn of a Mastermind character.
     *
     * @param mastermind
     *     The object to be notified of the change.
     */
    public AddQueueCharacterToMMHandler(IMastermind mastermind){
        this.mastermind = mastermind;
    }

    /**
     * This method gets called when a turn ends.
     *
     * @param event
     *     A PropertyChangeEvent object describing the event source
     *     and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        mastermind.characterAddedToQueue();
    }
}