package com.github.matiasvergaras.finalreality.controller.phases;

import com.github.matiasvergaras.finalreality.controller.GameController;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A CPUTurn state of the game.
 * <p> CPUTurn is a kind of Active state. </p>
 * <p> It represents the state in which a CPU Mastermind is 'choosing'
 * what to do, but actually, it is only an intermediate phase between
 * the automatic actions of the CPU.</p>
 * @author Matias Vergara Silva
 * @since Homework 3
 */
public class CPUTurn extends GameState{

    /**
     * Constructor for a new CPUTurn state.
     *
     * @param gameController gameController
     */
    public CPUTurn(GameController gameController) {
        super(gameController);
    }

    /**
     * {@inheritDoc}
     */
    public void initAttack(){
        gc.setState(new SelectingAttackTarget(gc));
        attackRandom();
    }

    /**
     * Selects a random character from the player party, checks if it is alive,
     * and if that is the case, it sends the message to perform an attack.
     * <p> In case the chosen character is not alive, then it will iterate. </p>
     */
    public void attackRandom(){
        boolean targetFound = false;
        while(!targetFound){
            int randomIndex = ThreadLocalRandom.current().nextInt(0, gc.getPlayerPartySize());
            if(gc.getPlayerParty().get(randomIndex).isAlive()){
                gc.setSelectedCharacterFromPlayerParty(randomIndex);
                gc.activeCharacterNormalAttackSelectedCharacter();
                targetFound = true;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCPUTurn() {
        return true;
    }

    /**
     * In this state, the game is being played, so it is active.
     * @return  boolean isActive
     */
    @Override
    public boolean isActive(){ return true; }
}
