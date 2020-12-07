package com.github.matiasvergaras.finalreality.controller;

import com.github.matiasvergaras.finalreality.model.Mastermind.IMastermind;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EndTurnCharacterToMMHandler implements PropertyChangeListener {
    IMastermind mastermind;

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


