package com.github.matiasvergaras.finalreality.controller.handlers;

import com.github.matiasvergaras.finalreality.controller.GameController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * PropertyChangeListener.
 * <p> This class implements a Handler of a Character endTurn. </p>
 * <p> His mission is to look for Mastermind's 'endTurn' message, and
 * notify it to the GameController. </p>
 * @author Matías Vergara Silva
 * @since Homework 2
 */
public class EndTurnMMToGCHandler implements PropertyChangeListener {
    private GameController controller;


    /**
     * Creates a new handler for the end of a turn of a Mastermind character.
     *
     * @param controller
     *     The object to be notified of the change.
     */
    public EndTurnMMToGCHandler(GameController controller){
        this.controller = controller;
    }

    /**
     * This method gets called when a turn ends.
     *
     * @param evt
     *     A PropertyChangeEvent object describing the event source
     *     and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        controller.endTurn();
    }
}


