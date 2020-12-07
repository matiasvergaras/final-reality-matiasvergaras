package com.github.matiasvergaras.finalreality.State;

import com.github.matiasvergaras.finalreality.controller.GameController;

/**
 * An Initializing State of the game.
 * <p> It represents the state in which the user is setting up
 * the game. </p>
 * @author Matias Vergara Silva
 * @since Homework 2
 */
public class Initializing extends GameState {

    /**
     * Constructor for a new Initialize state.
     * @param gameController        gameController
     */
    public Initializing(GameController gameController){
        super(gameController);
    }

    /**
     * Returns true if the current state is Initializing.
     * @return  boolean isInitializing
     */
    public boolean isInitializing(){
        return true;
    }

    /**
     * Changes the current state to Active.
     */
    public void setActive(){
        gameController.setState(new Active(gameController));
    }

}
