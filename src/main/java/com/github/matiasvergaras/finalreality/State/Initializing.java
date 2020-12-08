package com.github.matiasvergaras.finalreality.State;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.factory.Characters.*;
import com.github.matiasvergaras.finalreality.factory.Weapons.*;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * An Initializing State of the game.
 * <p> It represents the state in which the user is setting up
 * the game. </p>
 * @author Matias Vergara Silva
 * @since Homework 2
 */
public class Initializing extends AbstractGameState {
    private LinkedBlockingQueue<ICharacter> turns = gc.getTurns();
    private AxeFactory axeFactory = new AxeFactory("Common Axe", 120, 20);
    private BowFactory bowFactory = new BowFactory("Common Bow", 110, 10);
    private KnifeFactory knifeFactory = new KnifeFactory("Common Knife",100, 9);
    private StaffFactory staffFactory = new StaffFactory("Common Staff", 10, 11, 120);
    private SwordFactory swordFactory = new SwordFactory("Common Sword", 110, 11);

    private EngineerFactory engineerFactory = new EngineerFactory(turns, 125, 70);
    private KnightFactory knightFactory = new KnightFactory(turns, 180, 100);
    private ThiefFactory thiefFactory = new ThiefFactory(turns,90, 50);
    private BlackMageFactory blackMageFactory = new BlackMageFactory(turns, 120, 40, 200);
    private WhiteMageFactory whiteMageFactory = new WhiteMageFactory(turns, 120, 30, 200);
    private EnemyFactory enemyFactory = new EnemyFactory(turns, 130, 100, 12, 100);


    /**
     * Constructor for a new Initialize state.
     * @param gameController        gameController
     */
    public Initializing(GameController gameController){
        super(gameController);
    }

    /**
     * Returns true if the current state is Initializing.
     * @return  boolean isInitializing
     */
    public boolean isInitializing(){
        return true;
    }

    /**
     * Changes the current state to Active.
     */
    public void setActive(){
        gc.setState(new Active(gc));
    }

    /**
     * Gives the list with all the character factories of this gameController.
     * @return      ArrayList<ICharacterFactory> character's factories.
     */
    public ArrayList<ICharacterFactory> getCharacterFactories(){
        ArrayList<ICharacterFactory> factories = new ArrayList<>();
        factories.add(engineerFactory);
        factories.add(blackMageFactory);
        factories.add(whiteMageFactory);
        factories.add(thiefFactory);
        factories.add(knightFactory);
        factories.add(enemyFactory);
        return factories;
    }

    /**
     * Gives the list with all the weapon factories of this gameController.
     * @return      ArrayList<IWeaponFactory> weapon factories.
     */
    public ArrayList<IWeaponFactory> getWeaponFactories(){
        ArrayList<IWeaponFactory> factories = new ArrayList<>();
        factories.add(bowFactory);
        factories.add(staffFactory);
        factories.add(knifeFactory);
        factories.add(swordFactory);
        factories.add(axeFactory);
        return factories;
    }

    /**
     * {@inheritDoc}
     * @throws InterruptedException
     */
    public void startGame() throws InterruptedException {
        if(gc.getPlayerPartySize() == gc.getCharactersQuantity()){
            gc.activateTurns();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBlackMageToPlayer(String name){
        gc.getPlayer().addToParty(blackMageFactory.create(name));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addWhiteMageToPlayer(String name){
        gc.getPlayer().addToParty(whiteMageFactory.create(name));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEngineerToPlayer(String name){
        gc.getPlayer().addToParty(engineerFactory.create(name));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addThiefToPlayer(String name){
        gc.getPlayer().addToParty(thiefFactory.create(name));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addKnightToPlayer(String name){
        gc.getPlayer().addToParty(knightFactory.create(name));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEnemyToCPU(String name){
        gc.getCPU().addToParty(enemyFactory.create(name));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBowToInventory(){
        gc.getPlayer().addToInventory(bowFactory.create());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBowToInventory(String name){
        gc.getPlayer().addToInventory(bowFactory.create(name));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSwordToInventory(){
        gc.getPlayer().addToInventory(swordFactory.create());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSwordToInventory(String name){
        gc.getPlayer().addToInventory(swordFactory.create(name));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAxeToInventory(){
        gc.getPlayer().addToInventory(axeFactory.create());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAxeToInventory(String name){
        gc.getPlayer().addToInventory(axeFactory.create(name));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStaffToInventory(){
        gc.getPlayer().addToInventory(staffFactory.create());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStaffToInventory(String name){
        gc.getPlayer().addToInventory(staffFactory.create(name));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addKnifeToInventory(){
        gc.getPlayer().addToInventory(knifeFactory.create());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addKnifeToInventory(String name){
        gc.getPlayer().addToInventory(knifeFactory.create(name));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeSelectedCharacterFromItsParty(){
        gc.getPlayer().removeFromParty(gc.getSelectedCharacter());
        gc.getCPU().removeFromParty(gc.getSelectedCharacter());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeSelectedWeaponFromInventory(){
        gc.getPlayer().removeFromInventory(gc.getSelectedWeapon());
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

}
