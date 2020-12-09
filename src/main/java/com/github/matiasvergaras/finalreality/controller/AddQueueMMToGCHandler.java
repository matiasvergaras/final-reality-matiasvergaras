package com.github.matiasvergaras.finalreality.controller;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * PropertyChangeListener.
 * <p> This class implements a Handler of the addition of a character to the turns queue. </p>
 * <p> His mission is to look for Mastermind's 'addToQueue' message, and
 * notify it to the GameController. </p>
 * @author Matías Vergara Silva
 * @since Homework 2
 */
public class AddQueueMMToGCHandler implements PropertyChangeListener{
    private GameController controller;

    /**
     * Creates a new handler for Mastermind's character is dead message,
     * setting it associated GameController.
     *
     * @param controller
     *     The object to be notified of the change.
     */
    public AddQueueMMToGCHandler(GameController controller){
        this.controller = controller;
    }

    /**
     * Method to be called when a boundary property changes.
     * <p> When a Character adds himself to the queue, it will send a message to its Mastermind,
     * which in time will send a message to this Handler (because it will be his listener).
     * Once the Handler receives the message, it will call this method from GameController,
     * which will activate a mechanism to check if the queue is empty, and, if that is the case,
     * start a new turn. </p>
     *
     * @param event
     *     A PropertyChangeEvent object describing the event source
     *     and the property that changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        controller.addToQueue();
    }
}
