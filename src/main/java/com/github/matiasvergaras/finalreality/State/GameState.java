package com.github.matiasvergaras.finalreality.State;

import com.github.matiasvergaras.finalreality.controller.GameController;

/**
 * State of the game.
 * It can be "Initializing" if the user is setting the game,
 * "Active" if the game is being played,
 * and "Finished" if the game already ended.
 * @author Matías Vergara Silva
 * @since Homework 2
 */

public class GameState {
    protected GameController gameController;

    /**
     * Constructor for a new GameState
     * @param gameController        The GameController that will
     *                              have this state.
     */
    public GameState(GameController gameController){
        this.gameController = gameController;
    }

    /**
     * Returns true if the current state is Initializing.
     * @return  boolean isInitializing
     */
    public boolean isInitializing(){
        return false;
    }

    /**
     * Returns true if the current state is Active
     * @return  boolean isActive
     */
    public boolean isActive(){
        return false;
    }

    /**
     * Returns true if the current state is Finished.
     * @return  boolean isFinished
     */
    public boolean isFinished(){
        return false;
    }

    /**
     * Changes the current state to Initializing.
     */
    public void setInitializing(){
    }

    /**
     * Changes the current state to Active.
     */
    public void setActive(){
    }

    /**
     * Changes the current state to Finished.
     */
    public void setFinished(){
    }
}
