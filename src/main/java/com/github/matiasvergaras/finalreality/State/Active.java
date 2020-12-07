package com.github.matiasvergaras.finalreality.State;

import com.github.matiasvergaras.finalreality.controller.GameController;

/**
 * A Finished State of the game.
 * <p> It represents the state in which a game as already been played,
 * there is a Winner and a party full of dead characters. </p>
 * @author Matias Vergara Silva
 * @since Homework 2
 */
public class Active extends GameState {

    /**
     * Constructor for a new Active state.
     * @param gameController        gameController
     */
    public Active(GameController gameController){
        super(gameController);
    }

    /**
     * Returns true if the current state is Active
     * @return  boolean isActive
     */
    public boolean isActive(){
        return true;
    }

    /**
     * Changes the current state to Finished.
     */
    public void setFinished(){
        gameController.setState(new Finished(gameController));
    }
}
