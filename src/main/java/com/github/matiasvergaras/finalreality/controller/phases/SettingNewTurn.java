package com.github.matiasvergaras.finalreality.controller.phases;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;

public class SettingNewTurn extends GameState {
    /**
     * Constructor for a new SettingNewTurn state.
     *
     * @param gameController gameController
     */
    public SettingNewTurn(GameController gameController) {
        super(gameController);
    }


    /**
     * {@inheritDoc}
     */
    public void addToQueue(){
        if(gc.getTurns().size()>0){
            startTurn();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void startTurn(){
        if(!gc.getTurns().isEmpty()) {
            ICharacter character = gc.getTurns().peek();
            gc.setActiveCharacter(character);
            if( gc.getPlayerParty().contains(character)){
                System.out.println("SETTING PLAYER TURN");
                gc.setState(new PlayerTurn(gc));
            }
            else if(gc.getCPUParty().contains(character)){
                System.out.println("SETTING CPU TURN");
                gc.setState(new CPUTurn(gc));
                gc.initAttackMove();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSettingNewTurn() {
        return true;
    }

    /**
     * In this state, the game is being played, so it is active.
     * @return  boolean isActive
     */
    @Override
    public boolean isActive(){ return true; }


}
