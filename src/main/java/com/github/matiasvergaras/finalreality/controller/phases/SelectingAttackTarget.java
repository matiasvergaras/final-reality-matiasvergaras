package com.github.matiasvergaras.finalreality.controller.phases;

import com.github.matiasvergaras.finalreality.controller.GameController;

/**
 * A SelectingAttackTarget state of the game.
 * <p> SelectingAttackTarget is a kind of Active state. </p>
 * <p> It represents the state in which a Mastermind is choosing which
 * character to attack. </p>
 * <p> The decision is carried out via <Strong> setAttack</Strong> method.
 * If the player regrets sending an attack (he wants to be able to change
 * some equipment again) he can cancel his attack via  the
 * <Strong> cancelAttack</Strong> method.</p>
 * @author Matias Vergara Silva
 * @since Homework 3
 */
public class SelectingAttackTarget extends GameState {

    /**
     * Constructor for a new SelectingAttackTarget state.
     *
     * @param gameController gameController
     */
    public SelectingAttackTarget(GameController gameController) {
        super(gameController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelAttack(){
        gc.setState(new PlayerTurn(gc));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAttack(){
        gc.setState(new PerformingAttack(gc));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSelectingAttackTarget() {
        return true;
    }

    /**
     * In this state, the game is being played, so it is active.
     * @return  boolean isActive
     */
    @Override
    public boolean isActive(){ return true; }

}
