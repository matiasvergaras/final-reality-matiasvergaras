package com.github.matiasvergaras.finalreality.controller.phases;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.Mastermind.IMastermind;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;

/**
 * A PerformingAttack state of the game.
 * <p> PerformingAttack is a kind of Active state. </p>
 * <p> It represents the state in which a Mastermind has just sent the
 * attack message, and the attack and its consequences are being carried out.</p>
 * @author Matias Vergara Silva
 * @since Homework 3
 */
public class PerformingAttack extends GameState {

    /**
     * Constructor for a new performingAttack state.
     *
     * @param gameController gameController
     */
    public PerformingAttack(GameController gameController) {
        super(gameController);
    }

    /**
     * {@inheritDoc}
     */
    public void activeCharacterNormalAttackSelectedCharacter() {
        if (gc.getPlayerParty().contains(gc.getActiveCharacter()) && gc.getCPUParty().contains(gc.getSelectedCharacter())) {
            gc.getPlayer().makeNormalAttack(gc.getActiveCharacter(), gc.getSelectedCharacter());
        }
        else if (gc.getCPUParty().contains(gc.getActiveCharacter()) && gc.getPlayerParty().contains(gc.getSelectedCharacter())) {
            gc.getCPU().makeNormalAttack(gc.getActiveCharacter(), gc.getSelectedCharacter());
        }
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
    public void showTurnResume(){
        gc.setState(new ShowingTurnResume(gc));
    }

    /**
     * {@inheritDoc}
     */
    public void endTurn(){
        gc.getTurns().poll();
        gc.getActiveCharacter().waitTurn();
        setNewTurn();
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
     * Changes to the SettingNewTurn phase, and sends the message of start
     * a new turn.
     */
    public void setNewTurn(){
        gc.setState(new SettingNewTurn(gc));
        gc.startTurn();

    }

    /**
     * Changes the current state to Finished.
     */
    public void setFinished() {
        gc.setState(new Finished(gc));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPerformingAttack() {
        return true;
    }

    /**
     * In this state, the game is being played, so it is active.
     * @return  boolean isActive
     */
    @Override
    public boolean isActive(){ return true; }

}
