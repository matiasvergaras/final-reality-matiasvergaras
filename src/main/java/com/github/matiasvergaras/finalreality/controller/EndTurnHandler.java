package com.github.matiasvergaras.finalreality.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EndTurnHandler implements PropertyChangeListener {
    private GameController controller;

    /**
     * Creates a new handler for an end turn
     *
     * @param controller
     *     The Game Controller to be notified of the change.
     */
    public EndTurnHandler(GameController controller){
        this.controller = controller;
    }

    /**
     * Method that will be called when a property changes.
     * @param evt
     *           A PropertyChangeEvent object describing the event source
     *           and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        //controller.endTurn();
    }
}