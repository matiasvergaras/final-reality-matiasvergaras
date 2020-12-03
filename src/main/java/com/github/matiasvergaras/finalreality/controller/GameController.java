package com.github.matiasvergaras.finalreality.controller;

import com.github.matiasvergaras.finalreality.factory.Characters.CPUCharacters.EnemyFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.CPUCharacters.ICPUCharacterFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.ICharacterFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.MagicCharacters.BlackMageFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.MagicCharacters.IMagicCharacterFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.MagicCharacters.WhiteMageFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.NormalCharacters.EngineerFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.NormalCharacters.KnightFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.NormalCharacters.ThiefFactory;
import com.github.matiasvergaras.finalreality.factory.Weapons.*;
import com.github.matiasvergaras.finalreality.model.CPUPlayer;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.UserPlayer;
import com.github.matiasvergaras.finalreality.model.character.cpu.ICPUCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.NullWeapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Game Controller.
 * The controller will be an intermediary between the user and the objects of the model,
 * whose purpose will be to control all the messages that pass through it, manipulating
 * and redirecting those that are necessary.
 * <p> User will communicate with Controller, and Controller will do with
 * userPlayer/cpuPlayer. </p>
 * <p> We will use the Singleton Pattern with Game Controller, since there should not be more than 1 instance
 * of controller at the same time. </p>
 * @since Homework 2
 * @author Matías Vergara Silva
 */
public class GameController {
    private static GameController uniqueInstance;
    private LinkedBlockingQueue<ICharacter> turns;
    private AxeFactory axeFactory = new AxeFactory(120, 13);
    private BowFactory bowFactory = new BowFactory(90, 10);
    private KnifeFactory knifeFactory = new KnifeFactory(100, 9);
    private StaffFactory staffFactory = new StaffFactory(10, 11, 120);
    private SwordFactory swordFactory = new SwordFactory(110, 11);
    private EngineerFactory engineerFactory = new EngineerFactory(turns, 125, 70);
    private KnightFactory knightFactory = new KnightFactory(turns, 180, 100);
    private ThiefFactory thiefFactory = new ThiefFactory(turns,90, 50);
    private BlackMageFactory blackMageFactory = new BlackMageFactory(turns, 120, 40, 200);
    private WhiteMageFactory whiteMageFactory = new WhiteMageFactory(turns, 120, 30, 200);
    private EnemyFactory enemyFactory = new EnemyFactory(turns, 130, 100, 12, 100);
    private List<ICPUCharacterFactory> cpuCharacterFactories = new ArrayList<>();
    private List<IMagicCharacterFactory> magicCharacterFactories = new ArrayList<>();
    private UserPlayer userPlayer;
    private CPUPlayer cpuPlayer;
    private ICharacter selectedCharacter;
    private IWeapon selectedWeapon;
    private ICharacterFactory selectedCharacterFactory;
    private IWeaponFactory selectedWeaponFactory;

    /**
     *
     * Real constructor of the GameController.
     * Private to prevent access bypassing the UniqueInstance
     * @param charactersQuantity    the number of characters that the user will have.
     * @param turns                 a linked blocking queue for keeping the turns.
     */
    private GameController(int charactersQuantity, LinkedBlockingQueue<ICharacter> turns){
        this.userPlayer = new UserPlayer(charactersQuantity);
        this.cpuPlayer = new CPUPlayer();
        cpuCharacterFactories.add(enemyFactory);
        magicCharacterFactories.add(whiteMageFactory);
        magicCharacterFactories.add(blackMageFactory);
    }

    /**
     * Calls to the constructor if the Singleton Pattern condition is met.
     * @param charactersQuantity    the number of characters that the user will have.
     * @param turns                 a linked blocking queue for keeping the turns.
     * @return an instance of GameController if it has not been instantiated before. Null otherwise.
     */
    public static GameController uniqueInstance(int charactersQuantity, LinkedBlockingQueue<ICharacter> turns){
        if(uniqueInstance == null){
            uniqueInstance = new GameController(charactersQuantity,  turns);
        }
        return uniqueInstance;
    }

    /**
     * Request a new BlackMage character to the corresponding factory and add it to the User's party if there is still
     * space left.
     * <p> the character will have the default parameters, which can be modified using the set methods. /p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     * @see IMagicCharacterFactory
     */
    public void addBlackMageToPlayerParty(String name){
        userPlayer.addToParty(blackMageFactory.create(name));
    }

    /**
     * Request a new WhiteMage character to the corresponding factory and add it to the User's party if there is still
     * space left.
     * <p> the character will have the default parameters, which can be modified using the set methods. /p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     * @see IMagicCharacterFactory
     */
    public void addWhiteMageToPlayerParty(String name){
        userPlayer.addToParty(whiteMageFactory.create(name));
    }

    /**
     * Request a new Engineer character to the corresponding factory and add it to the User's party if there is still
     * space left.
     * <p> the character will have the default parameters, which can be modified using the set methods. /p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    public void addEngineerToPlayerParty(String name){
        userPlayer.addToParty(engineerFactory.create(name));
    }

    /**
     * Request a new Thief character to the corresponding factory and add it to the User's party if there is still
     * space left.
     * <p> the character will have the default parameters, which can be modified using the set methods. /p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    public void addThiefToPlayerParty(String name){
        userPlayer.addToParty(thiefFactory.create(name));
    }

    /**
     * Request a new Knight character to the corresponding factory and add it to the User's party if there is still
     * space left.
     * <p> the character will have the default parameters, which can be modified using the set methods. /p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    public void addKnightToPlayerParty(String name){
        userPlayer.addToParty(knightFactory.create(name));
    }

    /**
     * Request a new CPU character to the corresponding factory and add it to the CPU's party.
     * <p> the character will have the default parameters, which can be modified using the set methods. /p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     * @see ICPUCharacterFactory
     */
    public void addEnemyToCPUParty(String name){
        cpuPlayer.addToParty(enemyFactory.create(name));
    }

    /**
     * Request a new Bow weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. /p>
     * @see IWeaponFactory
     */
    public void addBowToInventory(){
        userPlayer.addToInventory(bowFactory.create());
    }

    /**
     * Request a new Bow weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. /p>
     * @param name      The name of the weapon to create.
     * @see IWeaponFactory
     */
    public void addBowToInventory(String name){
        userPlayer.addToInventory(bowFactory.create(name));
    }

    /**
     * Request a new Sword weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. /p>
     * @see IWeaponFactory
     */
    public void addSwordToInventory(){
        userPlayer.addToInventory(swordFactory.create());
    }

    /**
     * Request a new Bow weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. /p>
     * <p> This add method allow the user to give the name of the weapon, in order to have some special weapons. </p>
     * @param name      The name of the weapon to create.
     * @see IWeaponFactory
     */
    public void addSwordToInventory(String name){
        userPlayer.addToInventory(swordFactory.create(name));
    }

    /**
     * Request a new Axe weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. /p>
     * @see IWeaponFactory
     */
    public void addAxeToInventory(){
        userPlayer.addToInventory(axeFactory.create());
    }

    /**
     * Request a new Bow weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. /p>
     * <p> This add method allow the user to give the name of the weapon, in order to have some special weapons. </p>
     * @param name      The name of the weapon to create.
     * @see IWeaponFactory
     */
    public void addAxeToInventory(String name){
        userPlayer.addToInventory(axeFactory.create(name));
    }

    /**
     * Request a new Staff weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. /p>
     * @see IWeaponFactory
     */
    public void addStaffToInventory(){
        userPlayer.addToInventory(staffFactory.create());
    }

    /**
     * Request a new Bow weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. /p>
     * <p> This add method allow the user to give the name of the weapon, in order to have some special weapons. </p>
     * @param name      The name of the weapon to create.
     * @see IWeaponFactory
     */
    public void addStaffToInventory(String name){
        userPlayer.addToInventory(staffFactory.create(name));
    }

    /**
     * Request a new Knife weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. /p>
     * @see IWeaponFactory
     */
    public void addKnifeToInventory(){
        userPlayer.addToInventory(knifeFactory.create());
    }

    /**
     * Request a new Knife weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> The weapon will have the default parameters, which can be modified using the set methods. /p>
     * <p> This add method allow the user to give the name of the weapon, in order to have some special weapons. </p>
     * @param name      The name of the weapon to create.
     * @see IWeaponFactory
     */
    public void addKnifeToInventory(String name){
        userPlayer.addToInventory(knifeFactory.create(name));
    }

    /**
     * Sets the SelectedWeapon at a Weapon in the userPlayer Inventory, given an index representing its position
     * at the inventory.
     * <p> The method checks if the index is in the range (0, size of Inventory), in order to avoid IndexError's. </p>
     *
     * @param index     The position of the weapon that will be selected in the userPlayer Inventory.
     */
    public void setSelectedWeapon(int index){
        if(index < userPlayer.getInventorySize()) {
            this.selectedWeapon = userPlayer.getInventory().get(index);
        }
    }

    /**
     * Sets the SelectedCharacter at a Character in the userPlayer party, given an index representing its position
     * at the party.
     * <p> The method checks if the index is in the range (0, size of user Player Party), in order to avoid
     * IndexError's. </p>
     * @param index     The position of the character that will be selected in the userPlayer Party.
     */
    public void setSelectedCharacterFromPlayerParty(int index){
        if(index < userPlayer.getPartySize()){
            this.selectedCharacter = userPlayer.getParty().get(index);
        }
    }

    /**
     * Sets the SelectedCharacter at a Character in the cpuPlayer party, given an index representing its position
     * at the party.
     * <p> The method checks if the index is in the range (0, size of cpu Player Party), in order to avoid
     * IndexError's. </p>
     * @param index     The position of the character that will be selected in the cpuPlayer Party.
     */
    public void setSelectedCharacterFromCPUParty(int index){
        if(index < cpuPlayer.getPartySize()){
            this.selectedCharacter = userPlayer.getParty().get(index);
        }
    }

    /**
     * Launch the equipping process of the selectedWeapon to the SelectedCharacter.
     * <p> The method checks if the SelectedCharacter is in the PlayerParty since if not it will be an CPUCharacter,
     * who cannot equip weapons. </p>
     */
    public void equipSelectedWeaponToSelectedCharacter(){
        if(userPlayer.getParty().contains(selectedCharacter)){
            IPlayerCharacter character = (IPlayerCharacter)this.selectedCharacter;
            character.equipWeapon(selectedWeapon);
        }
    }

    /**
     * Unequip the SelectedCharacter.
     * <p> The method checks if the SelectedCharacter is in the PlayerParty since if not it will be an CPUCharacter,
     * who cannot equip weapons. </p>
     */
    public void unequipSelectedCharacter(){
        if (userPlayer.getParty().contains(selectedCharacter)) {
            userPlayer.unequipCharacter((IPlayerCharacter)selectedCharacter);
        }
    }

    /**
     * Removes the selectedCharacter from its party.
     * <p> Note that there will never have a character on both parties, as they receive different types. </p>
     * <p> The method removes the character if it is present in any of the teams, and do nothing otherwise. </p>
     */
    public void removeSelectedCharacterFromItsParty(){
        userPlayer.removeFromParty((IPlayerCharacter)selectedCharacter);
        cpuPlayer.removeFromParty((ICPUCharacter)selectedCharacter);
    }

    /**
     * Removes the selectedWeapon from the userPlayer inventory and sets the selectedWeapon as the NullWeapon.
     */
    public void removeSelectedWeaponFromInventory(){
        userPlayer.removeFromInventory(selectedWeapon);
        selectedWeapon = new NullWeapon();
    }

    /**
     * This method receives a target, and makes the selectedCharacter performs a normall attack against him.
     * <p> The method checks that any of the following cases are true:</p>
     * <p> selectedCharacter is in userPlayer party and target is in CPUPlayer Party, or </p>
     * <p> selectedCharacter is in CPUPlayer Party and selectedCharacter in userPlayer
     * Party. </p>
     * <p> If so, it sends the attack message in the corresponding direction. Otherwise, it has no effect.</p>
     * @param target        The ICharacter that is going to be attacked.
     */
    public void selectedCharacterNormalAttack(ICharacter target){
        if(userPlayer.getParty().contains(selectedCharacter) & cpuPlayer.getParty().contains(target)){
            IPlayerCharacter character = (IPlayerCharacter)selectedCharacter;
            ICPUCharacter cpuCharacter = (ICPUCharacter)target;
            character.normalAttack(cpuCharacter);
        }
        if(cpuPlayer.getParty().contains(selectedCharacter) & userPlayer.getParty().contains(target)){
            IPlayerCharacter character = (IPlayerCharacter)target;
            ICPUCharacter cpuCharacter = (ICPUCharacter)selectedCharacter;
            cpuCharacter.normalAttack(character);
        }
    }

    /**
     * Gets the attributes of the SelectedCharacter as a Map where the keys are the attribute description as String
     * (i.e. maxHP, DP, maxMana, weight, equippedWeapon, etc) and the values are the attributes.
     * <p> Since it will return values as Object, we decided to keep this method as private as well we created
     * specific methods that call this one but returns the values in their right type. </p>
     * @return  a Map<String, Object> representing the attributes of the selectedCharacter.
     */
    private Map<String, Object> getSelectedCharacterAttributes(){
        return selectedCharacter.getAttributes();
    }

    /**
     * Gets the selectedCharacter name.
     * @return      the name of the selectedCharacter, as a String.
     */
    String getSelectedCharacterName(){
        return (String)selectedCharacter.getAttributes().get("name");
    }

    /**
     * Gets the selectedCharacter current HP.
     * @return      the current HP of the selectedCharacter, as an int.
     */
    int getSelectedCharacterCurrentHP(){
        return (Integer)selectedCharacter.getAttributes().get("currentHP");
    }

    /**
     * Gets the selectedCharacter maxHP.
     * @return      the maxHP of the selectedCharacter, as an int.
     */
    int getSelectedCharacterMaxHP(){
        return (Integer)selectedCharacter.getAttributes().get("maxHP");
    }


    /**
     * Gets the selectedCharacter DP.
     * @return      the DP of the selectedCharacter, as an int.
     */
    int getSelectedCharacterDP(){
        return (Integer)selectedCharacter.getAttributes().get("DP");
    }

    /**
     * Gets the selectedCharacter equippedWeapon.
     * <p> If the character does not have the attribute, method returns null. </p>
     * @return      the selectedCharacter equipped Weapon, as IWeapon.
     */
    IWeapon getSelectedCharacterCurrentWeapon(){
        return (IWeapon)selectedCharacter.getAttributes().get("equippedWeapon");
    }

    /**
     * Gets the selectedCharacter CurrentMana.
     * <p> If the character does not have the attribute, method returns null. </p>
     * @return      the CurrentMana of the selectedCharacter, as an int.
     */
    int getSelectedCharacterCurrentMana(){
        return (int)selectedCharacter.getAttributes().get("currentMana");
    }

    /**
     * Gets the selectedCharacter MaxMana.
     * <p> If the character does not have the attribute, method returns null. </p>
     * @return      the MaxMana of the selectedCharacter, as an int.
     */
    int getSelectedCharacterMaxMana(){
        return (int)selectedCharacter.getAttributes().get("maxMana");
    }

    /**
     * Gets the selectedCharacter weight.
     * <p> If the character does not have the attribute, method returns null. </p>
     * @return      the weight of the selectedCharacter, as an int.
     */
    int getSelectedCharacterWeight(){
        return (int)selectedCharacter.getAttributes().get("weight");
    }

    /**
     * Gets the selectedCharacter power.
     * <p> If the character does not have the attribute, method returns null. </p>
     * @return      the power of the selectedCharacter, as an int.
     */
    int getSelectedCharacterPower(){
        return (int)selectedCharacter.getAttributes().get("Power");
    }

    /**
     *
     * @param weight
     */
    public void setSelectedWeaponFactoryWeight(int weight){
        selectedWeaponFactory.setWeight(weight);
    }

    public void setSelectedWeaponFactoryPower(int power){
        selectedWeaponFactory.setPower(power);
    }


    public void setSelectedWeaponFactoryMagicPower(int magicPower){
        selectedWeaponFactory.setMagicPower(magicPower);
    }

    public void setSelectedCharacterFactoryHP(int hp){
        selectedCharacterFactory.setHP(hp);
    }

    public void setSelectedCharacterFactoryDP(int dp){
        selectedCharacterFactory.setDP(dp);
    }

    public void setSelectedCharacterFactoryMana(int mana){
        if(magicCharacterFactories.contains(selectedCharacterFactory)){
            IMagicCharacterFactory selectedCharacterFactory = (IMagicCharacterFactory)this.selectedCharacterFactory;
            selectedCharacterFactory.setMana(mana);
        }
    }

    public void setDefaultEnemiesWeight(int weight){
        if(cpuCharacterFactories.contains(selectedCharacterFactory)){
            ICPUCharacterFactory selectedCharacterFactory = (ICPUCharacterFactory)this.selectedCharacterFactory;
            selectedCharacterFactory.setWeight(weight);
        }
    }

    public void setDefaultEnemiesPower(int power){
        if(cpuCharacterFactories.contains(selectedCharacterFactory)){
            ICPUCharacterFactory selectedCharacterFactory = (ICPUCharacterFactory)this.selectedCharacterFactory;
            selectedCharacterFactory.setWeight(power);
        }
    }

}

