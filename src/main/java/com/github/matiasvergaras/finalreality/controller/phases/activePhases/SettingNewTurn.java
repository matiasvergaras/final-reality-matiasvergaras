package com.github.matiasvergaras.finalreality.controller.phases.activePhases;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.controller.phases.Active;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;

public class SettingNewTurn extends Active {
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
                gc.setState(new PlayerTurn(gc));
            }
            else if(gc.getCPUParty().contains(character)){
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

}
