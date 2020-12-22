package com.github.matiasvergaras.finalreality.controller.phases;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;

/**
 * State of the game.
 * It can be "Initializing" if the user is configuring the game,
 * "Active" if the game is being played,
 * and "Finished" if the game already ended.
 * @author Matías Vergara Silva
 * @since Homework 2
 */

public class GameState implements IGameState {
    protected GameController gc;

    /**
     * Constructor for a new GameState
     * @param gc        The GameController that will
     *                              have this state.
     */
    public GameState(GameController gc){
        this.gc = gc;
    }


    /**
     * Returns true if the current state is Initializing.
     * @return  boolean isInitializing
     */
    @Override
    public boolean isInitializing(){
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCPUTurn() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlayerTurn() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPerformingAttack() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSelectingAttackTarget() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSettingNewTurn() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActive() {
        return false;
    }

    /**
     * Returns true if the current state is Finished.
     * @return  boolean isFinished
     */
    @Override
    public boolean isFinished(){
        return false;
    }

    /**
     * Changes the current state to Initializing.
     */
    @Override
    public void setInitializing(){}


    /**
     * {@inheritDoc}
     */
    @Override
    public void initAttack(){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelAttack(){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAttack(){}

    /**
     * Changes the current state to Finished.
     */
    @Override
    public void setFinished(){}

    /**
     * {@inheritDoc}
     */
    public void startGame() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public void startWaitTurns(){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void endTurn(){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToQueue(){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void startTurn(){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeDeadCharacter(ICharacter deadCharacter, int charactersAlive){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWinner(){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBlackMageToPlayer(String name){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void addWhiteMageToPlayer(String name){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEngineerToPlayer(String name){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void addThiefToPlayer(String name){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void addKnightToPlayer(String name){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEnemyToCPU(String name){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBowToInventory(){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBowToInventory(String name){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSwordToInventory(){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSwordToInventory(String name){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAxeToInventory(){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAxeToInventory(String name){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStaffToInventory(){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStaffToInventory(String name){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void addKnifeToInventory(){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void addKnifeToInventory(String name){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void equipSelectedWeaponToSelectedCharacter(){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void unequipSelectedCharacter(){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeSelectedCharacterFromItsParty(){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeSelectedWeaponFromInventory(){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void activeCharacterNormalAttackSelectedCharacter(){}

    /**
     * {@inheritDoc}
     * @param weight        The value to be set as the default weapon weight
     */
    @Override
    public void setSelectedWeaponFactoryWeight(int weight) {}

    /**
     * {@inheritDoc}
     * @param name        The value to be set as the default weapon name
     */
    @Override
    public void setSelectedWeaponFactoryName(String name) {}

    /**
     * {@inheritDoc}
     * @param power        The value to be set as the default weapon power
     */
    @Override
    public void setSelectedWeaponFactoryPower(int power) {}

    /**
     * {@inheritDoc}
     * @param magicPower       The value to be set as the default weapon magicPower of SelectedWeaponFactory
     */
    @Override
    public void setSelectedWeaponFactoryMagicPower(int magicPower) { }

    /**
     * {@inheritDoc}
     * @param hp       The value to be set as the default HP of selectedCharacterFactory
     */
    @Override
    public void setSelectedCharacterFactoryHP(int hp) {}

    /**
     * {@inheritDoc}
     * @param dp       The value to be set as the default DP of selectedCharacterFactory
     */
    @Override
    public void setSelectedCharacterFactoryDP(int dp) {}

    /**
     * {@inheritDoc}
     * @param mana      The value to be set as the default mana of selectedCharacterFactory
     */
    @Override
    public void setSelectedCharacterFactoryMana(int mana) {}

    /**
     * {@inheritDoc}
     * @param weight      The value to be set as the default weight of selectedCharacterFactory
     */
    @Override
    public void setSelectedCharacterFactoryWeight(int weight) { }

    /**
     * {@inheritDoc}
     * @param power      The value to be set as the default power of selectedCharacterFactory
     */
    @Override
    public void setSelectedCharacterFactoryPower(int power) { }

    /**
     * Sets the actual character factory.
     *
     * @param index The index of the new selected character factory in the
     *              character factories list.
     */
    @Override
    public void selectCharacterFactory(int index) {
    }

    /**
     * Sets the actual weapon factory.
     *
     * @param index The index of the new selected weapon factory in the
     *              weapon factories list.
     */
    @Override
    public void selectWeaponFactory(int index) {

    }


}
