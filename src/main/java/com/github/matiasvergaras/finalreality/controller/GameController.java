package com.github.matiasvergaras.finalreality.controller;

import com.github.matiasvergaras.finalreality.State.GameState;
import com.github.matiasvergaras.finalreality.State.Initializing;
import com.github.matiasvergaras.finalreality.factory.Characters.EnemyFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.ICharacterFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.BlackMageFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.WhiteMageFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.EngineerFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.KnightFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.ThiefFactory;
import com.github.matiasvergaras.finalreality.factory.Weapons.*;
import com.github.matiasvergaras.finalreality.model.CharacterAttributeSet;
import com.github.matiasvergaras.finalreality.model.Mastermind.CPUMastermind;
import com.github.matiasvergaras.finalreality.model.Mastermind.IMastermind;
import com.github.matiasvergaras.finalreality.model.Mastermind.PlayerMastermind;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Game Controller.
 * The controller will be an intermediary between the user and the objects of the model,
 * whose purpose will be to control all the messages that pass through it, manipulating
 * and redirecting those that are necessary.
 * <p> User will communicate with Controller, and Controller will do with
 * userPlayer/cpuPlayer. </p>
 * @since Homework 2
 * @author Matías Vergara Silva
 */
public class GameController {
    private LinkedBlockingQueue<ICharacter> turns;
    private AxeFactory axeFactory = new AxeFactory("Common Axe", 120, 13);
    private BowFactory bowFactory = new BowFactory("Common Bow", 90, 10);
    private KnifeFactory knifeFactory = new KnifeFactory("Common Knife",100, 9);
    private StaffFactory staffFactory = new StaffFactory("Common Staff", 10, 11, 120);
    private SwordFactory swordFactory = new SwordFactory("Common Sword", 110, 11);

    private EngineerFactory engineerFactory = new EngineerFactory(turns, 125, 70);
    private KnightFactory knightFactory = new KnightFactory(turns, 180, 100);
    private ThiefFactory thiefFactory = new ThiefFactory(turns,90, 50);
    private BlackMageFactory blackMageFactory = new BlackMageFactory(turns, 120, 40, 200);
    private WhiteMageFactory whiteMageFactory = new WhiteMageFactory(turns, 120, 30, 200);
    private EnemyFactory enemyFactory = new EnemyFactory(turns, 130, 100, 12, 100);

    private PlayerMastermind player;
    private CPUMastermind cpu;

    private ICharacter selectedCharacter;
    private ICharacter attackTarget;
    private IWeapon selectedWeapon;
    private ICharacterFactory selectedCharacterFactory;
    private IWeaponFactory selectedWeaponFactory;

    private ICharacter activeCharacter;
    private IMastermind winner;

    private ArrayList<ICharacterFactory> characterFactories = new ArrayList<>();
    private ArrayList<IWeaponFactory> weaponFactories = new ArrayList<>();

    private DeathMMToGCHandler deadCharacterHandler = new DeathMMToGCHandler(this);
    private EndTurnMMToGCHandler endTurnHandler = new EndTurnMMToGCHandler(this);
    private GameState gameState;

    /**
     *
     * Real constructor of the GameController.
     * Private to prevent access bypassing the UniqueInstance.
     */
    public GameController(String playerName, String CPUName, int charactersQuantity){
        this.turns = new LinkedBlockingQueue<>();
        this.player = new PlayerMastermind(playerName, charactersQuantity);
        this.cpu = new CPUMastermind(CPUName);

        this.player.getDeadCharacter().addPropertyChangeListener(deadCharacterHandler);
        this.cpu.getDeadCharacter().addPropertyChangeListener(deadCharacterHandler);

        this.player.getEndTurn().addPropertyChangeListener(endTurnHandler);
        this.cpu.getEndTurn().addPropertyChangeListener(endTurnHandler);

        characterFactories.add(engineerFactory);
        characterFactories.add(blackMageFactory);
        characterFactories.add(whiteMageFactory);
        characterFactories.add(thiefFactory);
        characterFactories.add(knightFactory);
        characterFactories.add(enemyFactory);

        weaponFactories.add(bowFactory);
        weaponFactories.add(staffFactory);
        weaponFactories.add(knifeFactory);
        weaponFactories.add(swordFactory);
        weaponFactories.add(axeFactory);

        this.selectedCharacterFactory = null;
        this.selectedWeaponFactory = null;
        this.selectedCharacter = null;
        this.activeCharacter = null;
        this.selectedWeapon = null;
        this.attackTarget = null;
        this.winner = null;
        this.gameState = new Initializing(this);
    }

    /**
     * Changes the current state to the given one.
     * @param state     The new state.
     */
    public void setState(GameState state){
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
     * Returns true if the current state is Active
     * @return  boolean isActive
     */
    public boolean isActive(){
        return gameState.isActive();
    }

    /**
     * Returns true if the current state is Finished.
     * @return  boolean isFinished
     */
    public boolean isFinished(){
        return gameState.isFinished();
    }

    /**
     * Changes the current state to Initializing.
     */
    public void initializeGame(){
        gameState.setInitializing();
    }


    /**
     * Starts the game.
     * This method will be called only in Initializing state. Otherwise it will have
     * no effect.
     * <p> Checks if the player has the correct number of characters
     * to play, and if that is the case, it changes the state of
     * the game to Active.</p>
     * <p> This method has to be marked as ''throws InterrumpedException'' since it calls to
     *      startTurn, which does throws said exception. </p>
     */
    public void startGame() throws InterruptedException {
        if(getPlayerPartySize() == getCharactersQuantity()){
            gameState.setActive();
            startWaitTurns();
            startTurn();
        }
    }
    /**
     * Ends this game by changing the state of the game to Finished.
     * <p> This method has to be called while standing in Active state.
     * Otherwise it will have no effect. </p>
     */
    public void endGame(){
        gameState.setFinished();
    }

    /**
     * Sends to every character in both parties the message to start their wait to
     * entry to the turns queue.
     * <p> We will assume that the time between iterations is negligible compared
     * to the time that the characters must wait to entry to the queue, so it does not
     * really affect the game behavior. </p>
     * <p> This method should to be called only by the StartGame Controller's method.
     * We will set it has private to avoid as much as we can another calls. </p>
     */
    private void startWaitTurns(){
        for(ICharacter c: getPlayerParty()){
            c.waitTurn();
        }
        for(ICharacter c: getCPUParty()){
            c.waitTurn();
        }
    }

    /**
     * Method to be called by an EndTurnHandler.
     * <p> Sends to the character that just ended his turn the wait for re-entry order. </p>
     * <p> Calls to StartTurn, in order to start a new Turn. </p>
     * <p> A character will wait for its turn only if he is alive (new feature in waitTurn). </p>
     * <p> This method has to be marked as ''throws InterrumpedException'' since it calls to
     *      startTurn, which does throws said exception. </p>
     */
    public void endTurn() throws InterruptedException {
        if(isActive()) {
            activeCharacter.waitTurn();
            startTurn();
        }
    }

    /**
     * Gets the next characters in the queue and set him as activeCharacter
     * (without removing it from the queue).
     * <p> If the queue is empty, the take will send the thread to sleep until
     * an element becomes available. </p>
     * <p> This method throws InterruptedException hen the interruption occurs
     * at time of waiting for an element to become available if queue is empty.</p>
     * @throws InterruptedException
     */
    public void startTurn() throws InterruptedException {
        if(isActive()) {
            activeCharacter = turns.poll();
        }
    }

    /**
     * This method receives a dead character and the number of characters
     * remaining in his team after his death. If the dead character is
     * in the turns queue, it will remove him, and if the remaining
     * characters are 0, it calls to checkForWinner.
     * @param deadCharacter     The character to remove from queue
     * @param charactersAlive   The number of remaining characters in the dead character's team.
     */
    public void removeDeadCharacter(ICharacter deadCharacter, int charactersAlive){
        if(isActive()) {
            turns.remove(deadCharacter);
            if (charactersAlive == 0) setWinner();
        }
    }

    /**
     * <p> Check which team was left without alive characters after the last death and assign
     * the winner to the opposing player.</p>>
     * <p> It will be private in order to make sure that it will be called only
     * when some team has every member dead, condition trapped by removeCharacterFromQueue. </p>
     */
    private void setWinner(){
        if(isActive()) {
            winner = ((0 == getCPUAliveNumber()) ? player : cpu);
            endGame();
        }
    }

    /**
     * Returns the winner of the game.
     * <p> 3 Possible values: null if the game is still in progress,
     * player, cpu.</p>
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
     * Gives the player inventory size.
     * return       An int representing the number of elements in the player's inventory.
     */
    public int getInventorySize(){
        return player.getInventorySize();
    }

    /**
     * Request a new BlackMage character to the corresponding factory and tries to add it to the User's party
     * by calling to addToParty method.
     * <p> the character will have the default parameters, which can be modified using the set methods </p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    public void addBlackMageToPlayerParty(String name){
        if(isInitializing()) {
            player.addToParty(blackMageFactory.create(name));
        }
    }

    /**
     * Request a new WhiteMage character to the corresponding factory and tries to add it to the User's party
     * by calling to addToParty method.
     * <p> the character will have the default parameters, which can be modified using the set methods. </p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    public void addWhiteMageToPlayerParty(String name){
        if(isInitializing()) {
            player.addToParty(whiteMageFactory.create(name));
        }
    }

    /**
     * Request a new Engineer character to the corresponding factory and tries to add it to the User's party
     * by calling to addToParty method.
     * <p> the character will have the default parameters, which can be modified using the set methods. </p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    public void addEngineerToPlayerParty(String name){
        if(isInitializing()) {
            player.addToParty(engineerFactory.create(name));
        }
    }

    /**
     * Request a new Thief character to the corresponding factory and tries to add it to the User's party
     * by calling to addToParty method.
     * <p> the character will have the default parameters, which can be modified using the set methods. </p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    public void addThiefToPlayerParty(String name){
        if(isInitializing()) {
            player.addToParty(thiefFactory.create(name));
        }
    }

    /**
     * Request a new Knight character to the corresponding factory and and tries to add it to the User's party
     * by calling to addToParty method.
     * <p> the character will have the default parameters, which can be modified using the set methods. </p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    public void addKnightToPlayerParty(String name){
        if(isInitializing()) {
            player.addToParty(knightFactory.create(name));
        }
    }

    /**
     * Request a new CPU character to the corresponding factory and add it to the CPU's party.
     * <p> the character will have the default parameters, which can be modified using the set methods. </p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    public void addEnemyToCPUParty(String name){
        if(isInitializing()) {
            cpu.addToParty(enemyFactory.create(name));
        }
    }

    /**
     * Request a new Bow weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * @see IWeaponFactory
     */
    public void addBowToInventory(){
        if(isInitializing()){
            player.addToInventory(bowFactory.create());
        }
    }

    /**
     * Request a new Bow weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * @param name      The name of the weapon to create.
     * @see IWeaponFactory
     */
    public void addBowToInventory(String name){
        if(isInitializing()) {
            player.addToInventory(bowFactory.create(name));
        }
    }

    /**
     * Request a new Sword weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * @see IWeaponFactory
     */
    public void addSwordToInventory(){
        if(isInitializing()) {
            player.addToInventory(swordFactory.create());
        }
    }

    /**
     * Request a new Bow weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * <p> This add method allow the user to give the name of the weapon, in order to have some special weapons. </p>
     * @param name      The name of the weapon to create.
     * @see IWeaponFactory
     */
    public void addSwordToInventory(String name){
        if(isInitializing()) {
            player.addToInventory(swordFactory.create(name));
        }
    }

    /**
     * Request a new Axe weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * @see IWeaponFactory
     */
    public void addAxeToInventory(){
        if(isInitializing()) {
            player.addToInventory(axeFactory.create());
        }
    }

    /**
     * Request a new Bow weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * <p> This add method allow the user to give the name of the weapon, in order to have some special weapons. </p>
     * @param name      The name of the weapon to create.
     * @see IWeaponFactory
     */
    public void addAxeToInventory(String name){
        if(isInitializing()) {
            player.addToInventory(axeFactory.create(name));
        }
    }

    /**
     * Request a new Staff weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * @see IWeaponFactory
     */
    public void addStaffToInventory(){
        if(isInitializing()) {
            player.addToInventory(staffFactory.create());
        }
    }

    /**
     * Request a new Bow weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * <p> This add method allow the user to give the name of the weapon, in order to have some special weapons. </p>
     * @param name      The name of the weapon to create.
     * @see IWeaponFactory
     */
    public void addStaffToInventory(String name){
        if(isInitializing()) {
            player.addToInventory(staffFactory.create(name));
        }
    }

    /**
     * Request a new Knife weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * @see IWeaponFactory
     */
    public void addKnifeToInventory(){
        if(isInitializing()) {
            player.addToInventory(knifeFactory.create());
        }
    }

    /**
     * Request a new Knife weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> The weapon will have the default parameters, which can be modified using the set methods. </p>
     * <p> This add method allow the user to give the name of the weapon, in order to have some special weapons. </p>
     * @param name      The name of the weapon to create.
     * @see IWeaponFactory
     */
    public void addKnifeToInventory(String name){
        if(isInitializing()) {
            player.addToInventory(knifeFactory.create(name));
        }
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
     * Sets the attackTarget at a Character in the userPlayer party, given an index representing its position
     * at the party.
     * <p> In case of a bad index, the method will catch and ignore the error, and keep the
     * initial attackTarget. </p>
     * @param index     The position of the character that will be targeted in the userPlayer Party.
     */
    public void setAttackTargetFromPlayerParty(int index){
        try{
            attackTarget= player.getCharacterFromParty(index);
        }
        catch(IndexOutOfBoundsException ignored){
        }
    }

    /**
     * Sets the attackTarget at a Character in the cpuPlayer party, given an index representing its position
     * at the party.
     * <p> In case of a bad index, the method will catch and ignore the error, and keep the
     * initial attackTarget. </p>
     * @param index     The position of the character that will be selected in the cpuPlayer Party.
     */
    public void setAttackTargetFromCPUParty(int index){
        try{
            attackTarget = cpu.getParty().get(index);
        }
        catch(IndexOutOfBoundsException ignored){
        }
    }

    /**
     * Launch the equipping process of the selectedWeapon to the SelectedCharacter.
     * <p> In case of a bad index, the method will catch and ignore the error, and
     * no weapon will be equipped. </p>
     */
    public void equipSelectedWeaponToSelectedCharacter(){
                IPlayerCharacter character = (IPlayerCharacter)this.selectedCharacter;
                player.equipCharacter(selectedWeapon, character);
    }

    /**
     * Unequip the SelectedCharacter.
     * If the SelectedCharacter is not in the Player's party,
     * it will have no effect.
     */
    public void unequipSelectedCharacter(){
        player.unequipCharacter((IPlayerCharacter)selectedCharacter);

    }

    /**
     * Removes the selectedCharacter from its party.
     * <p> Note that there will never have a character on both parties, as they receive different types. </p>
     * <p> The method removes the character if it is present in any of the teams, and do nothing otherwise. </p>
     */
    public void removeSelectedCharacterFromItsParty(){
        player.removeFromParty(selectedCharacter);
        cpu.removeFromParty(selectedCharacter);
        selectedCharacter = null;
    }

    /**
     * Removes the selectedWeapon from the userPlayer inventory and sets the selectedWeapon as the NullWeapon.
     */
    public void removeSelectedWeaponFromInventory(){
        player.removeFromInventory(selectedWeapon);
        selectedWeapon = null;
    }

    /**
     * This method makes the selectedCharacter performs a normal attack against the attackTarget character.
     * <p> The method checks that any of the following cases are true:</p>
     * <p> selectedCharacter is in userPlayer Party and attackTarget is in CPUPlayer Party, or </p>
     * <p> selectedCharacter is in CPUPlayer Party and attackTarget in userPlayer Party.
     * Party. </p>
     * <p> If so, it sends the attack message in the corresponding direction. Otherwise, it has no effect.</p>
     * <p> In this way, the if's fulfill a double function: they ensure that the attacking and receiving characters
     * are in the game (to avoid bugs) and at the same time they avoid attacks between the same team. </p>
     */
    public void selectedCharacterNormalAttackTarget(){
        if(isActive()){
            if(player.getParty().contains(selectedCharacter) && cpu.getParty().contains(attackTarget)){
                player.makeNormalAttack(selectedCharacter, attackTarget);
            }
            if(cpu.getParty().contains(selectedCharacter) && player.getParty().contains(attackTarget)){
                cpu.makeNormalAttack(selectedCharacter, attackTarget);
            }
        }
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
     * Gets the selectedCharacter name.
     * @return      the name of the selectedCharacter, as a String.
     */
    public String getSelectedCharacterName(){
        return getSelectedCharacterAttributes().getName();
    }

    /**
     * Gets the selectedCharacter current HP.
     * @return      the current HP of the selectedCharacter, as an int.
     */
    public int getSelectedCharacterCurrentHP(){
        return getSelectedCharacterAttributes().getCurrentHP();
    }

    /**
     * Gets the selectedCharacter maxHP.
     * @return      the maxHP of the selectedCharacter, as an int.
     */
    public int getSelectedCharacterMaxHP(){
        return getSelectedCharacterAttributes().getMaxHP();
    }


    /**
     * Gets the selectedCharacter DP.
     * @return      the DP of the selectedCharacter, as an int.
     */
    public int getSelectedCharacterDP(){
        return getSelectedCharacterAttributes().getDP();
    }

    /**
     * Gets the selectedCharacter equippedWeapon.
     * <p> If the character does not have the attribute, method returns null. </p>
     * @return      the selectedCharacter equipped Weapon, as IWeapon.
     */
    public IWeapon getSelectedCharacterEquippedWeapon(){
        return getSelectedCharacterAttributes().getEquippedWeapon();
    }

    /**
     * Gets the selectedCharacter CurrentMana.
     * <p> If the character does not have the attribute, method returns null. </p>
     * @return      the CurrentMana of the selectedCharacter, as an int.
     */
    public int getSelectedCharacterCurrentMana(){
        return getSelectedCharacterAttributes().getCurrentMana();
    }

    /**
     * Gets the selectedCharacter MaxMana.
     * <p> If the character does not have the attribute, method returns null. </p>
     * @return      the MaxMana of the selectedCharacter, as an int.
     */
    public int getSelectedCharacterMaxMana(){
        return getSelectedCharacterAttributes().getMaxMana();
    }

    /**
     * Gets the selectedCharacter weight.
     * <p> If the character does not have the attribute, method returns null. </p>
     * @return      the weight of the selectedCharacter, as an int.
     */
    public int getSelectedCharacterWeight(){
        return getSelectedCharacterAttributes().getWeight();
    }

    /**
     * Gets the selectedCharacter power.
     * <p> If the character does not have the attribute, method returns null. </p>
     * @return      the power of the selectedCharacter, as an int.
     */
    public  int getSelectedCharacterPower(){
        return getSelectedCharacterAttributes().getPower();
    }

    /**
     * Change the current SelectedWeaponFactory to the one in weaponFactories at the given index.
     * <p> In case of a bad index, the method will catch and ignore the error, and keep the
     * initial selectedWeapon. </p>
     * @param index     The position of the new SelectedWeaponFactory in weaponFactories
     */
    public void setSelectedWeaponFactory(int index){
        try{
            this.selectedWeaponFactory = weaponFactories.get(index);
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
            this.selectedCharacterFactory = characterFactories.get(index);
        }
        catch(IndexOutOfBoundsException ignored){
        }
    }

    /**
     * Sets the selectedWeaponFactory default's weight value.
     * @param weight        The value to be set as the default weapon weight
     */
    public void setSelectedWeaponFactoryWeight(int weight){
        selectedWeaponFactory.setWeight(weight);
    }


    /**
     * Sets the selectedWeaponFactory default's name value.
     * @param name        The value to be set as the default weapon name
     */
    public void setSelectedWeaponFactoryName(String name){
        selectedWeaponFactory.setName(name);
    }

    /**
     * Sets the selectedWeaponFactory default's power value.
     * @param power        The value to be set as the default weapon power
     * @see ICharacterFactory
     */
    public void setSelectedWeaponFactoryPower(int power){
        selectedWeaponFactory.setPower(power);
    }

    /**
     * Sets the selectedWeaponFactory default's magicPower value.
     * @param magicPower       The value to be set as the default weapon magicPower of SelectedWeaponFactory
     * @see ICharacterFactory
     */
    public void setSelectedWeaponFactoryMagicPower(int magicPower){
        selectedWeaponFactory.setMagicPower(magicPower);
    }

    /**
     * Sets the selectedCharacterFactory default's HP
     * @param hp       The value to be set as the default HP of selectedCharacterFactory
     * @see ICharacterFactory
     */
    public void setSelectedCharacterFactoryHP(int hp){
        selectedCharacterFactory.setHP(hp);
    }

    /**
     * Sets the selectedCharacterFactory default's DP
     * @param dp       The value to be set as the default DP of selectedCharacterFactory
     * @see ICharacterFactory
     */
    public void setSelectedCharacterFactoryDP(int dp){
        selectedCharacterFactory.setDP(dp);
    }

    /**
     * Sets the selectedCharacterFactory default's Mana
     * @param mana      The value to be set as the default mana of selectedCharacterFactory
     * @see ICharacterFactory
     */
    public void setSelectedCharacterFactoryMana(int mana){
        selectedCharacterFactory.setMana(mana);
    }

    /**
     * Sets the selectedCharacterFactory default's weight
     * @param weight      The value to be set as the default weight of selectedCharacterFactory
     * @see ICharacterFactory
     */
    public void setSelectedCharacterFactoryWeight(int weight){
        selectedCharacterFactory.setWeight(weight);
    }

    /**
     * Sets the selectedCharacterFactory default's power
     * @param power      The value to be set as the default power of selectedCharacterFactory
     * @see ICharacterFactory
     */
    public void setSelectedCharacterFactoryPower(int power){
        selectedCharacterFactory.setPower(power);
    }

    /**
     * Gives the SelectedCharacter ICharacter Object.
     * @return      The selectedCharacter ICharacter Object.
     */
    public ICharacter getSelectedCharacter(){
        return this.selectedCharacter;
    }

    /**
     * Gives the attackTarget ICharacter Object.
     * @return      The attackTarget ICharacter Object.
     */
    public ICharacter getAttackTargetCharacter(){
        return attackTarget;
    }


    /**
     * Gives the SelectedWeapon IWeapon Object.
     * @return      The selectedWeapon IWeapon Object.
     */
    public IWeapon getSelectedWeapon(){
        return selectedWeapon;
    }

    /**
     * Gives the SelectedWeapon name.
     * @return      The selectedWeapon name String.
     */
    public String getSelectedWeaponName(){
        return getSelectedWeapon().getAttributes().getName();
    }

    /**
     * Gives the SelectedWeapon power.
     * @return      The selectedWeapon power.
     */
    public int getSelectedWeaponPower(){
        return getSelectedWeapon().getAttributes().getPower();
    }

    /**
     * Gives the SelectedWeapon weight.
     * @return      The selectedWeapon weight.
     */
    public int getSelectedWeaponWeight(){
        return getSelectedWeapon().getAttributes().getWeight();
    }

    /**
     * Gives the SelectedWeapon magicPower.
     * @return      The selectedWeapon magicPower.
     */
    public int getSelectedWeaponMagicPower(){
        return getSelectedWeapon().getAttributes().getMagicPower();
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
}

