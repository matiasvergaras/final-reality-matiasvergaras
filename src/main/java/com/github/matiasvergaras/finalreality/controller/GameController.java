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
 * <p> User will communicate with Controller, and Controller will do with the Masterminds
 * CPUMastermind and PlayerMasterminds, which in time will communicate with the rest of the model. </p>
 * <p> GameController will have a 3-States State Pattern. They are "Initializing", that defines
 * the time when the user is setting-up the game, his characters, weapons, etc., "Active",
 * that is when the player is fighting against the CPU, and "Finished", that is when the user is
 * able to see the final state of the game and to know who wins. In finished mode the user is also
 * able to config a new game, turning back onto Initializing State. </p>
 * @since Homework 2
 * @author Matías Vergara Silva
 */
public class GameController {
    private FinalReality gui;
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
     * Sets-up the GUI of this gameController.
     * @param GUI       The GUI of the game.
     */
    public void setGUI(FinalReality GUI){
        this.gui = GUI;
    }

    /**
     * Returns the GUI associated to this gameController.
     * <p> Necessary to be able to test GC without the GUI. </p>
     * @return  GUI
     */
    public FinalReality getGUI(){
        return gui;
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
     * Returns true if the current phase is an Active subphase.
     * <p> Active subphases are CPUTurn, PlayerTurn, PerformingAttack,
     * SelectingAttackTarget, SettingNewTurn </p>
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
     * <p> "Starts the end" of a turn, by sending to the GUI the order to show
     * the turn resume. Then the GUI will send back the message of end the turn
     * and start a new one. </p>
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

    public void selectedCharacterFactoryProduce(String name){
        gameState.selectedCharacterFactoryProduce(name);
    }

    public void selectedWeaponFactoryProduce(String name) {
        gameState.selectedWeaponFactoryProduce(name);
    }

    /**
     * Request a new CPU character to the corresponding factory and add it to the CPU's party.
     * <p> the character will have the default parameters, which can be modified using the set methods. </p>
     * <p> This method will be effective only in Initializing mode. </p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    public void addEnemyToCPU(String name){
        gameState.addEnemyToCPU(name);
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

    public boolean characterIsMagic(ICharacter character) {
        return character.isMagic();
    }

    public boolean characterIsBlackMage(ICharacter character){
        return character.isBlackMage();
    }

    public boolean characterIsWhiteMage(ICharacter character){
        return character.isWhiteMage();
    }

    public boolean characterIsEngineer(ICharacter character){
        return character.isEngineer();
    }

    public boolean characterIsKnight(ICharacter character){
        return character.isKnight();
    }

    public boolean characterIsThief(ICharacter character){
        return character.isThief();
    }

    public boolean characterIsEnemy(ICharacter character){
        return character.isEnemy();
    }

    public boolean weaponIsAxe(IWeapon weapon){
        return weapon.isAxe();
    }

    public boolean weaponIsSword(IWeapon weapon){
        return weapon.isSword();
    }

    public boolean weaponIsKnife(IWeapon weapon){
        return weapon.isKnife();
    }

    public boolean weaponIsBow(IWeapon weapon){
        return weapon.isBow();
    }

    public boolean weaponIsStaff(IWeapon weapon){
        return weapon.isStaff();
    }

    public boolean weaponIsNull(IWeapon weapon){
        return weapon.isNull();
    }

    public String getWeaponName(IWeapon weapon){
        return weapon.getAttributes().getName();
    }

    public int getWeaponPower(IWeapon weapon){
        return weapon.getAttributes().getPower();
    }

    public int getWeaponWeight(IWeapon weapon){
        return weapon.getAttributes().getWeight();
    }

    public int getWeaponMagicPower(IWeapon weapon){
        return weapon.getAttributes().getMagicPower();
    }

    public String getWeaponOwnerName(IWeapon weapon){
        if(weapon.getOwner()!=null){
            return weapon.getOwner().getAttributes().getName();
        }
        return "None";
    }

    public String getCharacterName(ICharacter character){
        return character.getAttributes().getName();
    }


    public int getCharacterCurrentHP(ICharacter character){
        return character.getAttributes().getCurrentHP();
    }


    public int getCharacterMaxHP(ICharacter character){
        return character.getAttributes().getMaxHP();
    }



    public int getCharacterDP(ICharacter character){
        return character.getAttributes().getDP();
    }


    public IWeapon getCharacterEquippedWeapon(ICharacter character){
        return character.getAttributes().getEquippedWeapon();
    }


    public int getCharacterCurrentMana(ICharacter character){
        return character.getAttributes().getCurrentMana();
    }


    public int getCharacterMaxMana(ICharacter character){
        return character.getAttributes().getMaxMana();
    }


    public int getCharacterWeight(ICharacter character){
        return character.getAttributes().getWeight();
    }


    public  int getCharacterPower(ICharacter character){
        return character.getAttributes().getPower();
    }


    public void setNewTurn() {
        gameState.setNewTurn();
    }

    public void endTurn() {
        gameState.endTurn();
    }

    public boolean isShowingTurnResume() {
        return gameState.isShowingTurnResume();
    }
}

