package com.github.matiasvergaras.finalreality.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Class to handle the eventual character death.
 * Communicates to Game Controller.
 * @since Homework 2
 * @author Matías Vergara Silva
 */
public class DeathPlayerCharacterHandler implements PropertyChangeListener {
    private GameController controller;
    /**
     * Creates a new handler for the death of characters
     *
     * @param controller
     *        The Game Controller to be notified of the change.
     */
    public DeathPlayerCharacterHandler(GameController controller){
        this.controller = controller;
    }

    /**
     * Method that will be called when a character dies.
     * @param evt   A PropertyChangeEvent object describing the event source
     *              and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        controller.DeathCharacterHandler();
    }
}



