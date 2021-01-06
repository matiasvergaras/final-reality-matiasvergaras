package com.github.matiasvergaras.finalreality.controller.phases;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;

/**
 * A PlayerTurn state of the game.
 * <p> PlayerTurn is a kind of Active state. </p>
 * <p> It represents the state in which a Player Mastermind is 'choosing'
 * what to do. It will be able to equip/unequip weapons and to initialize
 * an attack. </p>
 * @author Matias Vergara Silva
 * @since Homework 3
 */
public class PlayerTurn extends GameState {

    /**
     * Constructor for a new PlayerTurn state.
     *
     * @param gameController gameController
     */
    public PlayerTurn(GameController gameController) {
        super(gameController);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void equipSelectedWeaponToSelectedCharacter(){
        IPlayerCharacter character = (IPlayerCharacter) gc.getSelectedCharacter();
        gc.getPlayer().equipCharacter(gc.getSelectedWeapon(), character);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void unequipSelectedCharacter(){
        gc.getPlayer().unequipCharacter((IPlayerCharacter) gc.getSelectedCharacter());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initAttack(){
        System.out.println("STARTING ATTACK");
        gc.setState(new SelectingAttackTarget(gc));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlayerTurn() {
        return true;
    }

    /**
     * In this state, the game is being played, so it is active.
     * @return  boolean isActive
     */
    @Override
    public boolean isActive(){ return true; }

}
