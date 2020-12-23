package com.github.matiasvergaras.finalreality.controller.phases;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.controller.phases.activePhases.SettingNewTurn;
import com.github.matiasvergaras.finalreality.factory.Characters.*;
import com.github.matiasvergaras.finalreality.factory.Weapons.*;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.NullWeapon;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * An Initializing State of the game.
 * <p> It represents the state in which the user is setting up
 * the game. </p>
 * @author Matias Vergara Silva
 * @since Homework 2
 */
public class Initializing extends GameState {
    private LinkedBlockingQueue<ICharacter> turns = gc.getTurns();
    private AxeFactory axeFactory = new AxeFactory(120, 20);
    private BowFactory bowFactory = new BowFactory(110, 10);
    private KnifeFactory knifeFactory = new KnifeFactory(100, 9);
    private StaffFactory staffFactory = new StaffFactory(10, 11, 120);
    private SwordFactory swordFactory = new SwordFactory(110, 11);

    private EngineerFactory engineerFactory = new EngineerFactory(turns, 125, 70);
    private KnightFactory knightFactory = new KnightFactory(turns, 180, 100);
    private ThiefFactory thiefFactory = new ThiefFactory(turns,90, 50);
    private BlackMageFactory blackMageFactory = new BlackMageFactory(turns, 120, 40, 200);
    private WhiteMageFactory whiteMageFactory = new WhiteMageFactory(turns, 120, 30, 200);
    private EnemyFactory enemyFactory = new EnemyFactory(turns, 130, 100, 12, 100);

    private ArrayList<IWeaponFactory> weaponFactories = new ArrayList<>();
    private ArrayList<ICharacterFactory> characterFactories = new ArrayList<>();

    /**
     * Constructor for a new Initialize state.
     * @param gameController        gameController
     */
    public Initializing(GameController gameController){
        super(gameController);
        weaponFactories.add(bowFactory);
        weaponFactories.add(staffFactory);
        weaponFactories.add(knifeFactory);
        weaponFactories.add(swordFactory);
        weaponFactories.add(axeFactory);
        characterFactories.add(engineerFactory);
        characterFactories.add(blackMageFactory);
        characterFactories.add(whiteMageFactory);
        characterFactories.add(thiefFactory);
        characterFactories.add(knightFactory);
        characterFactories.add(enemyFactory);
    }

    /**
     * Returns true if the current state is Initializing.
     * @return  boolean isInitializing
     */
    public boolean isInitializing(){
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void startGame() {
        if(gc.getPlayerPartySize() == gc.getCharactersQuantity()){
            startWaitTurns();
            setNewTurn();
            gc.startTurn();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void startWaitTurns(){
        for(ICharacter c: gc.getPlayerParty()){
            if(!c.getAttributes().getEquippedWeapon().equals(new NullWeapon())){
                c.waitTurn();
            }

        }
        for(ICharacter c: gc.getCPUParty()){
            c.waitTurn();
        }

    }

    /**
     * Changes the current state to Active.
     */
    public void setNewTurn(){
        gc.setState(new SettingNewTurn(gc));
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
    public void addBowToInventory(String name){
        gc.getPlayer().addToInventory(bowFactory.create(name));

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
    public void addAxeToInventory(String name){
        gc.getPlayer().addToInventory(axeFactory.create(name));

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


    /**
     * {@inheritDoc}
     * @param weight        The value to be set as the default weapon weight
     */
    @Override
    public void setSelectedWeaponFactoryWeight(int weight) {
        gc.getSelectedWeaponFactory().setWeight(weight);
    }

    /**
     * {@inheritDoc}
     * @param power        The value to be set as the default weapon power
     */
    @Override
    public void setSelectedWeaponFactoryPower(int power) {
        gc.getSelectedWeaponFactory().setPower(power);
    }

    /**
     * {@inheritDoc}
     * @param magicPower       The value to be set as the default weapon magicPower of SelectedWeaponFactory
     */
    @Override
    public void setSelectedWeaponFactoryMagicPower(int magicPower) {
        gc.getSelectedWeaponFactory().setMagicPower(magicPower);
    }

    /**
     * {@inheritDoc}
     * @param hp       The value to be set as the default HP of selectedCharacterFactory
     */
    @Override
    public void setSelectedCharacterFactoryHP(int hp) {
        gc.getSelectedCharacterFactory().setHP(hp);
    }

    /**
     * {@inheritDoc}
     * @param dp       The value to be set as the default DP of selectedCharacterFactory
     */
    @Override
    public void setSelectedCharacterFactoryDP(int dp) {
        gc.getSelectedCharacterFactory().setDP(dp);
    }

    /**
     * {@inheritDoc}
     * @param mana      The value to be set as the default mana of selectedCharacterFactory
     */
    @Override
    public void setSelectedCharacterFactoryMana(int mana) {
        gc.getSelectedCharacterFactory().setMana(mana);
    }

    /**
     * {@inheritDoc}
     * @param weight      The value to be set as the default weight of selectedCharacterFactory
     */
    @Override
    public void setSelectedCharacterFactoryWeight(int weight) {
        gc.getSelectedCharacterFactory().setWeight(weight);
    }

    /**
     * {@inheritDoc}
     * @param power      The value to be set as the default power of selectedCharacterFactory
     */
    @Override
    public void setSelectedCharacterFactoryPower(int power) {
        gc.getSelectedCharacterFactory().setPower(power);
    }

    /**
     * Sets the actual character factory.
     *
     * @param index The index of the new selected character factory in the
     *              character factories list.
     */
    @Override
    public void selectCharacterFactory(int index) {
        gc.selectedCharacterFactory = characterFactories.get(index);
    }

    /**
     * Sets the actual character factory.
     *
     * @param index The index of the new selected character factory in the
     *              character factories list.
     */
    @Override
    public void selectWeaponFactory(int index) {
        gc.selectedWeaponFactory = weaponFactories.get(index);
    }


    @Override
    public void selectedCharacterFactoryProduce(String name) {
        gc.getPlayer().addToParty(gc.getSelectedCharacterFactory().create(name));
    }

    @Override
    public void selectedWeaponFactoryProduce(String name) {
        gc.getPlayerInventory().add(gc.getSelectedWeaponFactory().create(name));
    }
}
