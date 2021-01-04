package com.github.matiasvergaras.finalreality.controller.handlers;

import com.github.matiasvergaras.finalreality.model.Mastermind.IMastermind;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * PropertyChangeListener.
 * <p> This class implements a Handler of a Character endTurn.. </p>
 * <p> His mission is to look for character's endTurn, and notify to their Mastermind. </p>
 * @author Matias Vergara Silva
 * @since Homework 2
 */
public class EndTurnCharacterToMMHandler implements PropertyChangeListener {
    private final IMastermind mastermind;

    /**
     * Creates a new handler for the end of a turn of a Mastermind character.
     *
     * @param mastermind
     *     The object to be notified of the change.
     */
    public EndTurnCharacterToMMHandler(IMastermind mastermind){
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
        mastermind.endTurn();
    }
}


