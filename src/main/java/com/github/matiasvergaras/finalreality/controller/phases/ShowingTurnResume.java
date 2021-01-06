package com.github.matiasvergaras.finalreality.controller.phases;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.Mastermind.IMastermind;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;

import java.io.FileNotFoundException;

/**
 * @author Matias Vergara Silva
 * @since Homework 3
 */
public class ShowingTurnResume extends GameState {

    /**
     * Constructor for a new performingAttack state.
     *
     * @param gameController gameController
     */
    public ShowingTurnResume(GameController gameController) {
        super(gameController);
    }


    /**
     * {@inheritDoc}
     */
    public void endTurn(){
        gc.getTurns().poll();
        gc.getActiveCharacter().waitTurn();
        System.out.println("SETTING NEW TURN");
        System.out.println(gc.getCPUAliveNumber());
        System.out.println(gc.getPlayerAliveNumber());
        if(gc.getCPUAliveNumber()>0 && gc.getPlayerAliveNumber()>0){
            setNewTurn();
        }
    }

    /**
     * Changes to the SettingNewTurn phase, and sends the message of start
     * a new turn.
     */
    public void setNewTurn(){
        gc.setState(new SettingNewTurn(gc));
        gc.startTurn();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isShowingTurnResume() {
        return true;
    }

    /**
     * In this state, the game is being played, so it is active.
     * @return  boolean isActive
     */
    @Override
    public boolean isActive(){ return true; }

}