package com.github.matiasvergaras.finalreality.State;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.factory.Characters.ICharacterFactory;
import com.github.matiasvergaras.finalreality.factory.Weapons.IWeaponFactory;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;

import java.util.ArrayList;

/**
 * State of the game.
 * It can be "Initializing" if the user is configuring the game,
 * "Active" if the game is being played,
 * and "Finished" if the game already ended.
 * @author Matías Vergara Silva
 * @since Homework 2
 */

public abstract class AbstractGameState implements IGameState {
    protected GameController gc;

    /**
     * Constructor for a new GameState
     * @param gc        The GameController that will
     *                              have this state.
     */
    public AbstractGameState(GameController gc){
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
     * Returns true if the current state is Active
     * @return  boolean isActive
     */
    @Override
    public boolean isActive(){
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
    public void setInitializing(){
    }

    /**
     * Changes the current state to Active.
     */
    @Override
    public void setActive(){
    }

    /**
     * Changes the current state to Finished.
     */
    @Override
    public void setFinished(){
    }

    /**
     * {@inheritDoc}
     */
    public void startGame() throws InterruptedException {

    }

    /**
     * {@inheritDoc}
     */
    public void initializeGame(){

    }
    /**
     * {@inheritDoc}
     */
    public void getWinner(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startWaitTurns() throws InterruptedException {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endTurn() throws InterruptedException {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startTurn() throws InterruptedException {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeDeadCharacter(ICharacter deadCharacter, int charactersAlive){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWinner(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBlackMageToPlayer(String name){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addWhiteMageToPlayer(String name){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEngineerToPlayer(String name){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addThiefToPlayer(String name){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addKnightToPlayer(String name){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEnemyToCPU(String name){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBowToInventory(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBowToInventory(String name){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSwordToInventory(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSwordToInventory(String name){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAxeToInventory(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAxeToInventory(String name){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStaffToInventory(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStaffToInventory(String name){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addKnifeToInventory(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addKnifeToInventory(String name){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void equipSelectedWeaponToSelectedCharacter(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unequipSelectedCharacter(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeSelectedCharacterFromItsParty(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeSelectedWeaponFromInventory(){

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activeCharacterNormalAttackSelectedCharacter(){
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public ArrayList<ICharacterFactory> getCharacterFactories(){
        return null;
    }

    /**
     * Gives the list with all the weapon factories of this gameController.
     * @return      ArrayList<IWeaponFactory> weapon factories.
     */
    public ArrayList<IWeaponFactory> getWeaponFactories(){
        return null;
    }

}
