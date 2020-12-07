package com.github.matiasvergaras.finalreality.State;

import com.github.matiasvergaras.finalreality.controller.GameController;

/**
 * A Finished State of the game.
 * <p> It represents the state in which a game as already been played,
 * there is a Winner and a party full of dead characters. </p>
 * @author Matias Vergara Silva
 * @since Homework 2
 */
public class Finished extends GameState {
    /**
     * Constructor for a new Finished state.
     * @param gameController        gameController
     */
    public Finished(GameController gameController){
        super(gameController);
    }

    /**
     * Returns true if the current state is Finished.
     * @return  boolean isFinished
     */
    public boolean isFinished(){
        return true;
    }

    /**
     * Changes the current state to Initializing.
     */
    public void setInitializing(){
        gameController.setState(new Initializing(gameController));
    }
}
