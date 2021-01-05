package com.github.matiasvergaras.finalreality.controller;

import com.github.matiasvergaras.finalreality.controller.phases.IGameState;
import com.github.matiasvergaras.finalreality.controller.phases.Initializing;
import com.github.matiasvergaras.finalreality.controller.handlers.AddQueueMMToGCHandler;
import com.github.matiasvergaras.finalreality.controller.handlers.DeathMMToGCHandler;
import com.github.matiasvergaras.finalreality.controller.handlers.EndTurnMMToGCHandler;
import com.github.matiasvergaras.finalreality.factory.Characters.ICharacterFactory;
import com.github.matiasvergaras.finalreality.factory.Weapons.*;
import com.github.matiasvergaras.finalreality.gui.FinalReality;
import com.github.matiasvergaras.finalreality.model.AttributeSet.CharacterAttributeSet;
import com.github.matiasvergaras.finalreality.model.Mastermind.CPUMastermind;
import com.github.matiasvergaras.finalreality.model.Mastermind.IMastermind;
import com.github.matiasvergaras.finalreality.model.Mastermind.PlayerMastermind;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.NullCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.NullWeapon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Game Controller.
 * The controller will be an intermediary between the user and the objects of the model,
 * whose purpose will be to control all the messages that pass through it, manipulating
 * and redirecting those that are necessary.
 * <p> User will communicate with GUI, GUI with Controller,  and Controller will do with the Masterminds
 * CPUMastermind and PlayerMasterminds, which in time will communicate with the rest of the model. </p>
 * <p> GameController will have a some phases (State Pattern). They can be divided in 3 main phases: "Initializing",
 * that defines the time when the user is setting-up the game, his characters, weapons, etc., "Active",
 * that is when the player is fighting against the CPU, and "Finished", that is when the user is
 * able to see the final state of the game and to know who wins. In finished mode the user is also
 * able to config a new game, turning back onto Initializing State. </p>
 * <p> There are 6 kinds of Active Phase: PlayerTurn, CPUTurn, SelectingAttackTarget, PerformingAttack, SettingNewTurn,
 * and ShowwingTurnResume. </p>
 * @since Homework 2
 * @author Matías Vergara Silva
 * @see IGameState
 */
public class GameController {
    private LinkedBlockingQueue<ICharacter> turns;
    private final PlayerMastermind player;
    private final CPUMastermind cpu;

    private ICharacter selectedCharacter;
    private IWeapon selectedWeapon;


    private ICharacter activeCharacter;
    private IMastermind winner;

    private ArrayList<ICharacterFactory> characterFactories;
    private ArrayList<IWeaponFactory> weaponFactories;

    private DeathMMToGCHandler deadCharacterHandler = new DeathMMToGCHandler(this);
    private EndTurnMMToGCHandler endTurnHandler = new EndTurnMMToGCHandler(this);
    private AddQueueMMToGCHandler addToQueueHandler = new AddQueueMMToGCHandler(this);
    private IGameState gameState;

    //Public to be able to change them from Initializing
    public ICharacterFactory selectedCharacterFactory;
    public IWeaponFactory selectedWeaponFactory;

    /**
     * Constructor of the GameController
     * @param playerName            A String representing the name that the player will have.
     * @param CPUName               A String representing the name that the CPU will have.
     * @param charactersQuantity    An int for the number of characters that the player will have.
     */
    public GameController(String playerName, String CPUName, int charactersQuantity){
        this.turns = new LinkedBlockingQueue<>();

        this.player = new PlayerMastermind(playerName, charactersQuantity);
        this.cpu = new CPUMastermind(CPUName);

        this.player.getDeadCharacter().addPropertyChangeListener(deadCharacterHandler);
        this.cpu.getDeadCharacter().addPropertyChangeListener(deadCharacterHandler);

        this.player.getEndTurn().addPropertyChangeListener(endTurnHandler);
        this.cpu.getEndTurn().addPropertyChangeListener(endTurnHandler);

        this.player.getAddQueue().addPropertyChangeListener(addToQueueHandler);
        this.cpu.getAddQueue().addPropertyChangeListener(addToQueueHandler);

        this.selectedCharacterFactory = null;
        this.selectedWeaponFactory = null;
        this.selectedCharacter = new NullCharacter();
        this.activeCharacter = new NullCharacter();
        this.selectedWeapon = new NullWeapon();
        this.winner = null;
        this.gameState = new Initializing(this);
    }


    /**
     * Returns the turns queue
     *
     */
    public LinkedBlockingQueue<ICharacter> getTurns(){
        return turns;
    }

    /**
     * Sets up a new active Character.
     * @param character     the new ICharacter active character.
     */
    public void setActiveCharacter(ICharacter character){
        activeCharacter = character;
    }

    /**
     * Changes the current state to the given one.
     * @param state     The new state.
     */
    public void setState(IGameState state){
        gameState = state;
    }

    /**
     * Returns true if the current state is Initializing.
     * @return  boolean isInitializing
     */
    public boolean isInitializing(){
        return gameState.isInitializing();
    }


    /**
     * Returns true if the current state is Finished.
     * <p> This method will be effective only in Active mode. </p>
     * @return  boolean isFinished
     */
    public boolean isFinished(){
        return gameState.isFinished();
    }

    /**
     * Returns true if the current phase is a CPUTurn.
     * @return   boolean current phase is CPUTurn
     */
    public boolean isCPUTurn() {
        return gameState.isCPUTurn();
    }

    /**
     * Returns true if the current phase is a PlayerTurn.
     * @return   boolean current phase is PlayerTurn
     */
    public boolean isPlayerTurn() {
        return gameState.isPlayerTurn();
    }

    /**
     * Returns true if the current phase is a SelectingAttackTarget.
     * @return   boolean current phase is SelectingAttackTarget
     */
    public boolean isSelectingAttackTarget() {
        return gameState.isSelectingAttackTarget();
    }

    /**
     * Returns true if the current phase is a SettingNewTurn.
     * @return   boolean current phase is SettingNewTurn
     */
    public boolean isSettingNewTurn() {
        return gameState.isSettingNewTurn();
    }

    /**
     * Returns true if the current phase is a PerformingAttack.
     * @return   boolean current phase is PerformingAttack
     */
    public boolean isPerformingAttack(){
        return gameState.isPerformingAttack();
    }

    /**
     * Returns true if the current phase is ShowingTurnResume
     * @return  boolean current phase is showingTurnResume
     */
    public boolean isShowingTurnResume() {
        return gameState.isShowingTurnResume();
    }

    /**
     * Returns true if the current phase is an Active subphase.
     * <p> Active subphases are CPUTurn, PlayerTurn, PerformingAttack,
     * SelectingAttackTarget, SettingNewTurn, showingTurnResume </p>
     * @return   boolean current phase is PlayerTurn
     */
    public boolean isActive(){
        return gameState.isActive();
    }

    /**
     * Changes the current state to Initializing.
     * <p> This method will be effective only in Finished mode. </p>
     */
    public void initializeGame(){
        gameState.setInitializing();
    }


    /**
     * Starts the game.
     * <p> Checks if the player has the correct number of characters
     * to play, and if that is the case, it changes the state of
     * the game to Active.</p>
     * <p> This method will be effective only in Initializing mode. </p>
     */
    public void startGame(){
        gameState.startGame();
    }


    /**
     * Method to be called by an EndTurnMMToGCHandler.
     * <p> "Starts the end" of a turn, by changing the state
     * to "showing turn resume". There will be an "OK" Button in GUI
     * that will send back the message of end the turn when fired. </p>
     * <p> In order to bypass the GUI interaction in tests, simulate the button by calling to gc.endTurn()</p>
     */
    public void showTurnResume() throws FileNotFoundException {
        gameState.showTurnResume();
    }

    /**
     * Starts a new turn.
     * <p> This method will only have effect in SettingNewTurn phase.</p>
     */
    public void startTurn() {
        gameState.startTurn();
    }

    /**
     * Starts an attack movement, by changing the current phase to
     * selectingAttackTarget.
     * <p> This method will be effective only in CPUTurn / PlayerTurn</p>
     */
    public void initAttackMove() {
        gameState.initAttack();
    }

    /**
     * Cancels an attack movement, by returning from the SelectingAttackTarget phase
     * to the PlayerTurn phase.
     * <p> It will only return to PlayerTurn phase because of that is, in fact, the only possibility,
     * since CPU will never regret of starting an attack (given that it decides automatically). </p>
     */
    public void cancelAttackMove() {
        gameState.cancelAttack();
    }

    /**
     * Method to be called by an AddQueueMMToGCHandler.
     * <p> Represents the fact of receiving the notification of a
     * character added to the turns queue. </p>
     * <p> Checks if the queue was empty before adding.
     * If so, sends the start new turn message.</p>
     * <p> This method will be effective only in Active mode. </p>
     */
    public void addToQueue(){
        gameState.addToQueue();
    }

    /**
     * Gives the activeCharacter.
     * @return ICharacter activeCharacter.
     */
    public ICharacter getActiveCharacter(){
        return activeCharacter;
    }

    /**
     * This method receives a dead character and the number of characters
     * remaining in his team after his death. It will remove him from
     * the turns queue and if the remaining
     * characters are 0, it will call to checkForWinner.
     * <p> This method will be effective only in Active mode. </p>
     * @param deadCharacter     The character to remove from queue
     * @param charactersAlive   The number of remaining characters in the dead character's team.
     */
    public void removeDeadCharacterFromQueue(ICharacter deadCharacter, int charactersAlive){
        gameState.removeDeadCharacter(deadCharacter, charactersAlive);
    }

    /**
     * <p> Check which team was left without alive characters after the last death and assign
     * the winner to the opposing player.</p>
     <p> This method will be effective only in Active mode. </p>
     */
    public void setWinner(IMastermind mastermind){
        winner = mastermind;
    }

    /**
     * Returns the Player Mastermind.
     * @return  PlayerMastermind player mastermind.
     */
    public PlayerMastermind getPlayer(){
        return player;
    }

    /**
     * Returns the CPU Mastermind.
     * @return  CPUMastermind cpu mastermind.
     */
    public CPUMastermind getCPU(){
        return cpu;
    }

    /**
     * Returns the winner of the game.
     * <p> 3 Possible values: null if the game has not ended yet,
     *     IMastermind player if player won, Imastermind cpu if cpu won.</p>
     * @return  IMastermind winner.
     */
    public IMastermind getWinner(){
        return winner;
    }

    /**
     * Returns the number of alive characters in the player party.
     * @return      int AliveCharacters from player.
     */
    public int getPlayerAliveNumber(){
        return player.getAliveCharacters();
    }

    /**
     * Returns the number of alive characters in the cpu party.
     * @return      int AliveCharacters from cpu.
     */
    public int getCPUAliveNumber(){
        return cpu.getAliveCharacters();
    }
    /**
     * Getter for Character Quantity of this GameController.
     * @return      int CharactersQuantity.
     */
    public int getCharactersQuantity(){
        return player.getCharacterQuantity();
    }

    /**
     * Getter for the name of the player of this GameController
     * @return      String playerName.
     */
    public String getPlayerName(){
        return player.getName();
    }

    /**
     * Gives the player party.
     */
    public ArrayList<ICharacter> getPlayerParty(){
        return player.getParty();
    }

    /**
     * Gives the CPU Name.
     * @return  String CPUName
     */
    public String getCPUName(){
        return cpu.getName();
    }
    /**
     * Gives the cpu party.
     */
    public ArrayList<ICharacter> getCPUParty(){
        return cpu.getParty();
    }

    /**
     * Gives the number of characters in the player party.
     * @return      An int representing the number of characters in the party.
     */
    public int getPlayerPartySize(){
        return player.getPartySize();
    }

    /**
     * Gives the number of characters in the cpu party.
     * @return      An int representing the number of characters in the party.
     */
    public int getCPUPartySize(){
        return cpu.getPartySize();
    }

    /**
     * Gives the inventory of the player.
     * @return An ArrayList of IWeapon representing the inventory of the player.
     */
    public ArrayList<IWeapon> getPlayerInventory(){
        return player.getInventory();
    }
    /**
     * Gives the player inventory size.
     * return       An int representing the number of elements in the player's inventory.
     */
    public int getInventorySize(){
        return player.getInventorySize();
    }

    /**
     * Orders to the selected character factory to produce a new character an add it to the
     * corresponding team.
     * <p> This will have effect only in initializing mode. </p>
     * @param name  The name of the character to be produced.
     */
    public void selectedCharacterFactoryProduce(String name){
        gameState.selectedCharacterFactoryProduce(name);
    }

    /**
     * Orders to the seleceted weapon factory to produce a new weapon and add it to
     * the player's inventory.
     * <p> This will have effect only in initializing mode. </p>
     * @param name
     */
    public void selectedWeaponFactoryProduce(String name) {
        gameState.selectedWeaponFactoryProduce(name);
    }


    /**
     * Sets the SelectedWeapon at a Weapon in the userPlayer Inventory, given an index representing its position
     * at the inventory.
     * <p> The method checks if the index is in the range (0, size of Inventory), in order to avoid IndexError's. </p>
     *
     * @param index     The position of the weapon that will be selected in the userPlayer Inventory.
     */
    public void setSelectedWeapon(int index){
        try{
            selectedWeapon = player.getInventory().get(index);
        }
        catch(IndexOutOfBoundsException ignored){
        }

    }

    /**
     * Sets the SelectedCharacter at a Character in the userPlayer party, given an index representing its position
     * at the party.
     * <p> In case of a bad index, the method will catch and ignore the error, and keep the
     * initial selectedCharacter. </p>
     * @param index     The position of the character that will be selected in the userPlayer Party.
     */
    public void setSelectedCharacterFromPlayerParty(int index){
        try{
            selectedCharacter = player.getCharacterFromParty(index);
        }
        catch(IndexOutOfBoundsException ignored){
        }
    }

    /**
     * Sets the SelectedCharacter at a Character in the cpuPlayer party, given an index representing its position
     * at the party.
     * <p> In case of a bad index, the method will catch and ignore the error, and keep the
     * initial selectedCharacter. </p>
     * @param index     The position of the character that will be selected in the cpuPlayer Party.
     */
    public void setSelectedCharacterFromCPUParty(int index){
        try{
            selectedCharacter = cpu.getParty().get(index);
        }
        catch(IndexOutOfBoundsException ignored){
        }
    }


    /**
     * Launch the equipping process of the selectedWeapon to the SelectedCharacter.
     * <p> In case of a bad index, the method will catch and ignore the error, and
     * no weapon will be equipped. </p>
     * <p> This method will be effective only in Initializing and Active mode. </p>
     */
    public void equipSelectedWeaponToSelectedCharacter(){
        gameState.equipSelectedWeaponToSelectedCharacter();
    }

    /**
     * Unequip the SelectedCharacter.
     * If the SelectedCharacter is not in the Player's party,
     * it will have no effect.
     * <p> This method will be effective only in Initializing and Active mode. </p>
     */
    public void unequipSelectedCharacter(){
        gameState.unequipSelectedCharacter();
    }

    /**
     * Removes the selectedCharacter from its party.
     * <p> Note that there will never have a character on both parties, as Controller does not have
     * methods to make wrong adds. </p>
     * <p> The method removes the character if it is present in any of the teams, and do nothing otherwise. </p>
     * <p> This method will be effective only in Initializing and Active mode. </p>
     */
    public void removeSelectedCharacterFromItsParty(){
        gameState.removeSelectedCharacterFromItsParty();
    }

    /**
     * Removes the selectedWeapon from the userPlayer inventory and sets the selectedWeapon as the NullWeapon.
     * <p> This method will be effective only in Initializing and Active mode. </p>
     */
    public void removeSelectedWeaponFromInventory(){
        gameState.removeSelectedWeaponFromInventory();
    }

    /**
     * This method makes the activeCharacter performs a normal attack against the selectedCharacter character.
     * <p> The method checks that any of the following cases are true:</p>
     * <p> activeCharacter is in userPlayer Party and selectedCharacter is in CPUPlayer Party, or </p>
     * <p> activeCharacter is in CPUPlayer Party and selectedCharacter in userPlayer Party.
     * Party. </p>
     * <p> If so, it sends the attack message in the corresponding direction. Otherwise, it has no effect.</p>
     * <p> In this way, the conditions fulfill a double function: they ensure that the attacking and receiving characters
     * are in the game (to avoid bugs) and at the same time they avoid attacks between the same team. </p>
     * <p> This method will be available only in Active - SelectingAttacKTarget mode. </p>
     */
    public void activeCharacterNormalAttackSelectedCharacter(){
        gameState.setAttack();
        gameState.activeCharacterNormalAttackSelectedCharacter();
    }

    /**
     * Gets the attributes of the SelectedCharacter as an AttributeSet.
     * <p> AttributeSet is a new class that we created to keep the attributes of the different types of
     * characters. It has getters for every possible attribute.  If it is asked for an attribute that the character does
     * not have, it will return 0 (except for equippedWeapon, in which case it will return null). </p>
     * @return  an AttributeSet representing all the attributes of the character.
     */
    public CharacterAttributeSet getSelectedCharacterAttributes(){
        return selectedCharacter.getAttributes();
    }

    /**
     * Change the current SelectedWeaponFactory to the one in weaponFactories at the given index.
     * <p> In case of a bad index, the method will catch and ignore the error, and keep the
     * initial selectedWeapon. </p>
     * <p> This method will be effective only in Initializing mode. </p>
     * @param index     The position of the new SelectedWeaponFactory in weaponFactories
     */
    public void setSelectedWeaponFactory(int index){
        try{
            gameState.selectWeaponFactory(index);
        }
        catch(IndexOutOfBoundsException ignored){
        }
    }

    /**
     * Change the current SelectedCharacterFactory to the one in CharacterFactories at the given index.
     * <p> In case of a bad index, the method will catch and ignore the error, and keep the
     * initial selectedCharacterFactory. </p>
     * @param index     The position of the new SelectedCharacterFactory in CharacterFactories
     */
    public void setSelectedCharacterFactory(int index){
        try{
            gameState.selectCharacterFactory(index);
        }
        catch(IndexOutOfBoundsException ignored){
        }
    }

    /**
     * Sets the selectedWeaponFactory default's weight value.
     * <p> This method will be effective only in Initializing mode. </p>
     * @param weight        The value to be set as the default weapon weight
     */
    public void setSelectedWeaponFactoryWeight(int weight){
        gameState.setSelectedWeaponFactoryWeight(weight);
    }


    /**
     * Sets the selectedWeaponFactory default's power value.
     * <p> This method will be effective only in Initializing mode. </p>
     * @param power        The value to be set as the default weapon power
     * @see ICharacterFactory
     */
    public void setSelectedWeaponFactoryPower(int power){
        gameState.setSelectedWeaponFactoryPower(power);
    }

    /**
     * Sets the selectedWeaponFactory default's magicPower value.
     * <p> This method will be effective only in Initializing mode. </p>
     * @param magicPower       The value to be set as the default weapon magicPower of SelectedWeaponFactory
     * @see ICharacterFactory
     */
    public void setSelectedWeaponFactoryMagicPower(int magicPower){
        gameState.setSelectedWeaponFactoryMagicPower(magicPower);
    }

    /**
     * Sets the selectedCharacterFactory default's HP
     * <p> This method will be effective only in Initializing mode. </p>
     * @param hp       The value to be set as the default HP of selectedCharacterFactory
     * @see ICharacterFactory
     */
    public void setSelectedCharacterFactoryHP(int hp){
        gameState.setSelectedCharacterFactoryHP(hp);
    }

    /**
     * Sets the selectedCharacterFactory default's DP
     * <p> This method will be effective only in Initializing mode. </p>
     * @param dp       The value to be set as the default DP of selectedCharacterFactory
     * @see ICharacterFactory
     */
    public void setSelectedCharacterFactoryDP(int dp){
        gameState.setSelectedCharacterFactoryDP(dp);
    }

    /**
     * Sets the selectedCharacterFactory default's Mana
     * <p> This method will be effective only in Initializing mode. </p>
     * @param mana      The value to be set as the default mana of selectedCharacterFactory
     * @see ICharacterFactory
     */
    public void setSelectedCharacterFactoryMana(int mana){
        gameState.setSelectedCharacterFactoryMana(mana);
    }

    /**
     * Sets the selectedCharacterFactory default's weight
     * <p> This method will be effective only in Initializing mode. </p>
     * @param weight      The value to be set as the default weight of selectedCharacterFactory
     * @see ICharacterFactory
     */
    public void setSelectedCharacterFactoryWeight(int weight){
        gameState.setSelectedCharacterFactoryWeight(weight);
    }

    /**
     * Sets the selectedCharacterFactory default's power
     * <p> This method will be effective only in Initializing mode. </p>
     * @param power      The value to be set as the default power of selectedCharacterFactory
     * @see ICharacterFactory
     */
    public void setSelectedCharacterFactoryPower(int power){
        gameState.setSelectedCharacterFactoryPower(power);
    }

    /**
     * Gives the SelectedCharacter ICharacter Object.
     * @return      The selectedCharacter ICharacter Object.
     */
    public ICharacter getSelectedCharacter(){
        return this.selectedCharacter;
    }


    /**
     * Gives the SelectedWeapon IWeapon Object.
     * @return      The selectedWeapon IWeapon Object.
     */
    public IWeapon getSelectedWeapon(){
        return selectedWeapon;
    }


    /**
     * Gives the SelectedWeaponFactory IWeaponFactory Object.
     * @return      The selectedWeaponFactory IWeaponFactory Object.
     */
    public IWeaponFactory getSelectedWeaponFactory(){
        return selectedWeaponFactory;
    }

    /**
     * Gives the SelectedCharacterFactory ICharacterFactory Object.
     * @return      The selectedCharacterFactory ICharacterFactory Object.
     */
    public ICharacterFactory getSelectedCharacterFactory(){
        return selectedCharacterFactory;
    }

    /**
     * Returns true if the given character is a Magic Character (Black or White Mage, this far).
     * @param character The character to ask for
     * @return boolean indicating if the character is magic or not.
     * <p> This method will be useful for the GUI, in order to be able to know if there should
     * exist a mana field for this character, of what kind of sprite use. </p>
     */
    public boolean characterIsMagic(ICharacter character) {
        return character.isMagic();
    }

    /**
     * Returns true if the given character is a BlackMage
     * @param character The character to ask for
     * @return boolean indicating if the character is BlackMage.
     * <p> This method will be useful for the GUI, in order to be able to know what kind of sprite use. </p>
     */
    public boolean characterIsBlackMage(ICharacter character){
        return character.isBlackMage();
    }

    /**
     * Returns true if the given character is a BlackMage
     * @param character The character to ask for
     * @return boolean indicating if the character is WhiteMage.
     * <p> This method will be useful for the GUI, in order to be able to know what kind of sprite use. </p>
     */
    public boolean characterIsWhiteMage(ICharacter character){
        return character.isWhiteMage();
    }

    /**
     * Returns true if the given character is a EngineerMage
     * @param character The character to ask for
     * @return boolean indicating if the character is Engineer.
     * <p> This method will be useful for the GUI, in order to be able to know what kind of sprite use. </p>
     */
    public boolean characterIsEngineer(ICharacter character){
        return character.isEngineer();
    }

    /**
     * Returns true if the given character is a Knight
     * @param character The character to ask for
     * @return boolean indicating if the character is Knight.
     * <p> This method will be useful for the GUI, in order to be able to know what kind of sprite use. </p>
     */
    public boolean characterIsKnight(ICharacter character){
        return character.isKnight();
    }

    /**
     * Returns true if the given character is a Thief
     * @param character The character to ask for
     * @return boolean indicating if the character is Thief.
     * <p> This method will be useful for the GUI, in order to be able to know what kind of sprite use. </p>
     */
    public boolean characterIsThief(ICharacter character){
        return character.isThief();
    }

    /**
     * Returns true if the given character is an Enemy.
     * @param character The character to ask for
     * @return boolean indicating if the character is Enemy.
     * <p> This method will be useful for the GUI, in order to be able to know what kind of sprite use. </p>
     */
    public boolean characterIsEnemy(ICharacter character){
        return character.isEnemy();
    }

    /**
     * Returns true if the given weapon is an Axe.
     * @param weapon The weapon to ask for
     * @return boolean indicating if the given weapon is an Axe.
     * <p> This method will be useful for the GUI, in order to be able to know what kind of sprite use. </p>
     */
    public boolean weaponIsAxe(IWeapon weapon){
        return weapon.isAxe();
    }

    /**
     * Returns true if the given weapon is a Sword.
     * @param weapon The weapon to ask for
     * @return boolean indicating if the given weapon is a Sword.
     * <p> This method will be useful for the GUI, in order to be able to know what kind of sprite use. </p>
     */
    public boolean weaponIsSword(IWeapon weapon){
        return weapon.isSword();
    }

    /**
     * Returns true if the given weapon is a Knife.
     * @param weapon The weapon to ask for
     * @return boolean indicating if the given weapon is a Knife.
     * <p> This method will be useful for the GUI, in order to be able to know what kind of sprite use. </p>
     */
    public boolean weaponIsKnife(IWeapon weapon){
        return weapon.isKnife();
    }

    /**
     * Returns true if the given weapon is a Bow.
     * @param weapon The weapon to ask for
     * @return boolean indicating if the given weapon is a Bow.
     * <p> This method will be useful for the GUI, in order to be able to know what kind of sprite use. </p>
     */
    public boolean weaponIsBow(IWeapon weapon){
        return weapon.isBow();
    }

    /**
     * Returns true if the given weapon is a Staff.
     * @param weapon The weapon to ask for
     * @return boolean indicating if the given weapon is a Staff.
     * <p> This method will be useful for the GUI, in order to be able to know what kind of sprite use. </p>
     */
    public boolean weaponIsStaff(IWeapon weapon){
        return weapon.isStaff();
    }

    /**
     * Returns true if the given weapon is Null
     * @param weapon The weapon to ask for
     * @return boolean indicating if the given weapon is Null
     * <p> This method will be useful for the GUI, in order to be able to know what kind of sprite use. </p>
     */
    public boolean weaponIsNull(IWeapon weapon){
        return weapon.isNull();
    }

    /**
     * Gives the name of a given weapon.
     * @param weapon        The weapon to ask for
     * @return              The name of the given weapon.
     */
    public String getWeaponName(IWeapon weapon){
        return weapon.getAttributes().getName();
    }

    /**
     * Gives the power of a given weapon.
     * @param weapon        The weapon to ask for
     * @return              The power of the given weapon.
     */
    public int getWeaponPower(IWeapon weapon){
        return weapon.getAttributes().getPower();
    }

    /**
     * Gives the weight of a given weapon.
     * @param weapon        The weapon to ask for
     * @return              The weight of the given weapon.
     */
    public int getWeaponWeight(IWeapon weapon){
        return weapon.getAttributes().getWeight();
    }

    /**
     * Gives the magicPower of a given weapon.
     * @param weapon        The weapon to ask for
     * @return              The magicPower of the given weapon.
     */
    public int getWeaponMagicPower(IWeapon weapon){
        return weapon.getAttributes().getMagicPower();
    }

    /**
     * Gives the owner's name of a given weapon.
     * @param weapon        The weapon to ask for
     * @return              The name of the owner of the given weapon.
     */
    public String getWeaponOwnerName(IWeapon weapon){
        if(weapon.getOwner()!=null){
            return weapon.getOwner().getAttributes().getName();
        }
        return "None";
    }

    /**
     * Gives the name of the given character.
     * @param character     The character to ask for.
     * @return              The name of the given character.
     */
    public String getCharacterName(ICharacter character){
        return character.getAttributes().getName();
    }

    /**
     * Gives the current HP of the given character.
     * @param character     The character to ask for.
     * @return              The currentHP of the given character.
     */
    public int getCharacterCurrentHP(ICharacter character){
        return character.getAttributes().getCurrentHP();
    }

    /**
     * Gives the maxHP of the given character.
     * @param character     The character to ask for.
     * @return              The maxHP of the given character.
     */
    public int getCharacterMaxHP(ICharacter character){
        return character.getAttributes().getMaxHP();
    }


    /**
     * Gives the DP of the given character.
     * @param character     The character to ask for.
     * @return              The DP of the given character.
     */
    public int getCharacterDP(ICharacter character){
        return character.getAttributes().getDP();
    }

    /**
     * Gives the equipped weapon of the given character.
     * @param character     The character to ask for.
     * @return              The equipped weapon of the given character.
     */
    public IWeapon getCharacterEquippedWeapon(ICharacter character){
        return character.getAttributes().getEquippedWeapon();
    }

    /**
     * Gives the current mana of the given character.
     * @param character     The character to ask for.
     * @return              The current mana of the given character.
     */
    public int getCharacterCurrentMana(ICharacter character){
        return character.getAttributes().getCurrentMana();
    }

    /**
     * Gives the max mana of the given character.
     * @param character     The character to ask for.
     * @return              The max mana of the given character.
     */
    public int getCharacterMaxMana(ICharacter character){
        return character.getAttributes().getMaxMana();
    }

    /**
     * Gives the weight of the given character.
     * @param character     The character to ask for.
     * @return              The weight of the given character.
     */
    public int getCharacterWeight(ICharacter character){
        return character.getAttributes().getWeight();
    }

    /**
     * Gives the power of the given character.
     * @param character     The character to ask for.
     * @return              The power of the given character.
     */
    public  int getCharacterPower(ICharacter character){
        return character.getAttributes().getPower();
    }

    /**
     * Finish the process of "end a turn", after showing results.
     * This method will be called from the GUI when the "OK" button of the turn summary is pressed,
     * or from the tests when simulating the GUI.
     * <p> This method will only have effect in ShowingTurnResume phase. </p>
     * <p> This method will fire a new turn, if possible.  </p>
     */
    public void endTurn() {
        gameState.endTurn();
    }

}

