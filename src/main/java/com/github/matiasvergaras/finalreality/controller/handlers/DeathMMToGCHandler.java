package com.github.matiasvergaras.finalreality.controller.handlers;


import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * PropertyChangeListener.
 * <p> This class implements a Handler of a Character Death. </p>
 * <p> His mission is to look for Mastermind's 'characterDead' message, and
 * notify it to the GameController. </p>
 * @author Matías Vergara Silva
 * @since Homework 2
 */
public class DeathMMToGCHandler implements PropertyChangeListener{
    private GameController controller;

    /**
     * Creates a new handler for Mastermind's character is dead message,
     * setting it associated GameController.
     *
     * @param controller
     *     The object to be notified of the change.
     */
    public DeathMMToGCHandler(GameController controller){
        this.controller = controller;
    }

    /**
     * Method to be called when a boundary property changes.
     * <p> When a Character dies, it will send a message to its Mastermind, which in time
     * will send a message to this Handler (because it will be his listener). Once the Handler
     * receives the message, it will call this method from GameController, which will
     * activate mechanism to remove characters from the queue and check for winners in the
     * GameController. </p>
     *
     * @param event
     *     A PropertyChangeEvent object describing the event source
     *     and the property that changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        controller.removeDeadCharacterFromQueue((ICharacter) event.getSource(), (Integer) event.getNewValue());
    }
}
