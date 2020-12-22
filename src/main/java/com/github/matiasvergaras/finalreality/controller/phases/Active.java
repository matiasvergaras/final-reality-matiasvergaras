package com.github.matiasvergaras.finalreality.controller.phases;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.Mastermind.IMastermind;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.NullWeapon;

/**
 * An Active State of the game.
 * <p> It represents the state in which the game is being played.
 * There exists 5 active phases: CPUTurn, PlayerTurn, PerformingAttack,
 * SelectingAttackTarget and SettingNewTurn. </p>
 * @author Matias Vergara Silva
 * @since Homework 2
 */
public abstract class Active extends GameState {

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

}
