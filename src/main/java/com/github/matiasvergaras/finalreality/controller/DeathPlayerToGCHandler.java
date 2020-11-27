package com.github.matiasvergaras.finalreality.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Class to handle the eventual character death
 * Communicates from Player to Game Controller
 * <p>
 * Controller will look after Player,
 * and Player will look after PlayerCharacters.
 * @since Homework 2
 * @author Matías Vergara Silva
 */
public class DeathPlayerToGCHandler implements PropertyChangeListener {
    private GameController controller;
    /**
     * Creates a new handler for character's dead of Player
     *
     * @param controller
     *        The Game Controller to be notified of the change.
     */
    public DeathPlayerToGCHandler(GameController controller){
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
        //controller.removeCharacter(evt.getPropertyName());
    }
}



