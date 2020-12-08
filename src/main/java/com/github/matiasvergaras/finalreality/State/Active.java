package com.github.matiasvergaras.finalreality.State;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.Mastermind.IMastermind;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;

/**
 * A Finished State of the game.
 * <p> It represents the state in which a game as already been played,
 * there is a Winner and a party full of dead characters. </p>
 * @author Matias Vergara Silva
 * @since Homework 2
 */
public class Active extends AbstractGameState {

    /**
     * Constructor for a new Active state.
     *
     * @param gameController gameController
     */
    public Active(GameController gameController) {
        super(gameController);
    }

    /**
     * Returns true if the current state is Active
     *
     * @return boolean isActive
     */
    public boolean isActive() {
        return true;
    }

    /**
     * Changes the current state to Finished.
     */
    public void setFinished() {
        gc.setState(new Finished(gc));
    }

    /**
     * {@inheritDoc}
     * @throws InterruptedException
     */
    public void endTurn() throws InterruptedException {
        gc.getActiveCharacter().waitTurn();
        startTurn();
    }

    /**
     * {@inheritDoc}
     * @throws InterruptedException
     */
    public void startTurn() throws InterruptedException {
        gc.setActiveCharacter(gc.getTurns().take());
        /*
         * for the next homeworks:
         * if cpuParty contains activeCharacter : ( ... random attack ... )
         * if playerParty contains activeCharacter : ( ... wait for action ... )
         */
    }

    /**
     * {@inheritDoc}
     */
    public void removeDeadCharacter(ICharacter deadCharacter, int charactersAlive) {
        gc.getTurns().remove(deadCharacter);
        if (charactersAlive == 0) setWinner();
    }

    /**
     * {@inheritDoc}
     */
    public void setWinner() {
        IMastermind winner = ((0 == gc.getCPUAliveNumber()) ? gc.getPlayer() : gc.getCPU());
        gc.setWinner(winner);
        setFinished();
    }

    /**
     * {@inheritDoc}
     */
    public void activeCharacterNormalAttackSelectedCharacter() {
        if (gc.getPlayerParty().contains(gc.getActiveCharacter()) &&
                gc.getCPUParty().contains(gc.getSelectedCharacter())) {
            gc.getPlayer().makeNormalAttack(gc.getActiveCharacter(), gc.getSelectedCharacter());
        }
        if (gc.getCPUParty().contains(gc.getActiveCharacter()) &&
                gc.getPlayerParty().contains(gc.getSelectedCharacter())) {
            gc.getCPU().makeNormalAttack(gc.getActiveCharacter(), gc.getSelectedCharacter());
        }
    }
}
