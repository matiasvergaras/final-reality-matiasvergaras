package com.github.matiasvergaras.finalreality.controller.handlers;

import com.github.matiasvergaras.finalreality.model.Mastermind.IMastermind;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * PropertyChangeListener.
 * <p> This class implements a Handler of a Character Death. </p>
 * <p> His mission is to look for character's death, and notify to their Mastermind. </p>
 * @author Matias Vergara Silva
 * @since Homework 2
 */
public class DeathCharacterToMMHandler implements PropertyChangeListener {
    private IMastermind mastermind;
    /**
     * Creates a new handler for Character's death.
     *
     * @param mastermind
     *     The object to be notified of the change.
     */
    public DeathCharacterToMMHandler(IMastermind mastermind){
        this.mastermind = mastermind;
    }

    /**
     * Method to be called when a boundary property changes.
     * <p> When a Character dies, it will send a message to the instance of this class in its
     * Mastermind, since it is his listener. Once the Handler receives the message, it will
     * call this method, starting the pass of the information to the gameController. </p>
     *
     * @param event
     *     A PropertyChangeEvent object describing the event source
     *     and the property that changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        mastermind.deadCharacter((ICharacter) event.getSource());
    }
}

