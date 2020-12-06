package com.github.matiasvergaras.finalreality.controller;

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
    private static GameController uniqueInstance;
    private LinkedBlockingQueue<ICharacter> turns;
    private int charactersQuantity;
    private String playerName;
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
    private ArrayList<ICharacterFactory> characterFactories = new ArrayList<>();
    private ArrayList<IWeaponFactory> weaponFactories = new ArrayList<>();


    /**
     *
     * Real constructor of the GameController.
     * Private to prevent access bypassing the UniqueInstance.
     */
    public GameController(){
        this.turns = new LinkedBlockingQueue<>();
        this.charactersQuantity = 7;
        this.playerName = "Player 1";
        this.player = new PlayerMastermind(playerName, charactersQuantity);
        this.cpu = new CPUMastermind("CPU");
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
        this.selectedWeapon = null;
        this.attackTarget = null;
    }

    /**
     * Gives the player party.
     */
    public ArrayList<ICharacter> getPlayerParty(){
        return player.getParty();
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
     * A method to overwrite the default name and character number that the player will have.
     * <p> Has to be available only at the start of the game, since it will overwrite the player with a
     * completely new one. </p>
     * <p> Defaults values are: </p>
     * <p> Name: Player 1 </p>
     * <p> Characters quantity: 7 </p>
     * @param playerName            The new name that the player will have.
     * @param newQuantity           The number of characters the player will play with.
     */
    public void setNewPlayerValues(String playerName, int newQuantity){
        this.playerName = playerName;
        this.charactersQuantity = newQuantity;
        this.player = new PlayerMastermind(playerName, newQuantity);
    }

    /**
     * Getter to the actual player.
     * @return      PlayerMastermind player.
     */
    public PlayerMastermind getPlayer(){
        return this.player;
    }

    /**
     * Request a new BlackMage character to the corresponding factory and add it to the User's party if there is still
     * space left.
     * <p> the character will have the default parameters, which can be modified using the set methods </p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    public void addBlackMageToPlayerParty(String name){
        player.addToParty(blackMageFactory.create(name));
    }

    /**
     * Request a new WhiteMage character to the corresponding factory and add it to the User's party if there is still
     * space left.
     * <p> the character will have the default parameters, which can be modified using the set methods. </p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    public void addWhiteMageToPlayerParty(String name){
        player.addToParty(whiteMageFactory.create(name));
    }

    /**
     * Request a new Engineer character to the corresponding factory and add it to the User's party if there is still
     * space left.
     * <p> the character will have the default parameters, which can be modified using the set methods. </p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    public void addEngineerToPlayerParty(String name){
        player.addToParty(engineerFactory.create(name));
    }

    /**
     * Request a new Thief character to the corresponding factory and add it to the User's party if there is still
     * space left.
     * <p> the character will have the default parameters, which can be modified using the set methods. </p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    public void addThiefToPlayerParty(String name){
        player.addToParty(thiefFactory.create(name));
    }

    /**
     * Request a new Knight character to the corresponding factory and add it to the User's party if there is still
     * space left.
     * <p> the character will have the default parameters, which can be modified using the set methods. </p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    public void addKnightToPlayerParty(String name){
        player.addToParty(knightFactory.create(name));
    }

    /**
     * Request a new CPU character to the corresponding factory and add it to the CPU's party.
     * <p> the character will have the default parameters, which can be modified using the set methods. </p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    public void addEnemyToCPUParty(String name){
        cpu.addToParty(enemyFactory.create(name));
    }

    /**
     * Request a new Bow weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * @see IWeaponFactory
     */
    public void addBowToInventory(){
        player.addToInventory(bowFactory.create());
    }

    /**
     * Request a new Bow weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * @param name      The name of the weapon to create.
     * @see IWeaponFactory
     */
    public void addBowToInventory(String name){
        player.addToInventory(bowFactory.create(name));
    }

    /**
     * Request a new Sword weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * @see IWeaponFactory
     */
    public void addSwordToInventory(){
        player.addToInventory(swordFactory.create());
    }

    /**
     * Request a new Bow weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * <p> This add method allow the user to give the name of the weapon, in order to have some special weapons. </p>
     * @param name      The name of the weapon to create.
     * @see IWeaponFactory
     */
    public void addSwordToInventory(String name){
        player.addToInventory(swordFactory.create(name));
    }

    /**
     * Request a new Axe weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * @see IWeaponFactory
     */
    public void addAxeToInventory(){
        player.addToInventory(axeFactory.create());
    }

    /**
     * Request a new Bow weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * <p> This add method allow the user to give the name of the weapon, in order to have some special weapons. </p>
     * @param name      The name of the weapon to create.
     * @see IWeaponFactory
     */
    public void addAxeToInventory(String name){
        player.addToInventory(axeFactory.create(name));
    }

    /**
     * Request a new Staff weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * @see IWeaponFactory
     */
    public void addStaffToInventory(){
        player.addToInventory(staffFactory.create());
    }

    /**
     * Request a new Bow weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * <p> This add method allow the user to give the name of the weapon, in order to have some special weapons. </p>
     * @param name      The name of the weapon to create.
     * @see IWeaponFactory
     */
    public void addStaffToInventory(String name){
        player.addToInventory(staffFactory.create(name));
    }

    /**
     * Request a new Knife weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * @see IWeaponFactory
     */
    public void addKnifeToInventory(){
        player.addToInventory(knifeFactory.create());
    }

    /**
     * Request a new Knife weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> The weapon will have the default parameters, which can be modified using the set methods. </p>
     * <p> This add method allow the user to give the name of the weapon, in order to have some special weapons. </p>
     * @param name      The name of the weapon to create.
     * @see IWeaponFactory
     */
    public void addKnifeToInventory(String name){
        player.addToInventory(knifeFactory.create(name));
    }

    /**
     * Sets the SelectedWeapon at a Weapon in the userPlayer Inventory, given an index representing its position
     * at the inventory.
     * <p> The method checks if the index is in the range (0, size of Inventory), in order to avoid IndexError's. </p>
     *
     * @param index     The position of the weapon that will be selected in the userPlayer Inventory.
     */
    public void setSelectedWeapon(int index){
        if(index < player.getInventorySize()) {
            this.selectedWeapon = player.getInventory().get(index);
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
        if(index < player.getPartySize()){
            this.selectedCharacter = player.getCharacterFromParty(index);
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
        if(index < cpu.getPartySize()){
            this.selectedCharacter = cpu.getParty().get(index);
        }
    }

    /**
     * Sets the attackTarget at a Character in the userPlayer party, given an index representing its position
     * at the party.
     * <p> The method checks if the index is in the range (0, size of user Player Party), in order to avoid
     * IndexError's. </p>
     * @param index     The position of the character that will be targeted in the userPlayer Party.
     */
    public void setAttackTargetFromPlayerParty(int index){
        if(index < player.getPartySize()){
            this.attackTarget= player.getCharacterFromParty(index);
        }
    }

    /**
     * Sets the attackTarget at a Character in the cpuPlayer party, given an index representing its position
     * at the party.
     * <p> The method checks if the index is in the range (0, size of cpu Player Party), in order to avoid
     * IndexError's. </p>
     * @param index     The position of the character that will be selected in the cpuPlayer Party.
     */
    public void setAttackTargetFromCPUParty(int index){
        if(index < cpu.getPartySize()){
            this.attackTarget = cpu.getParty().get(index);
        }
    }

    /**
     * Launch the equipping process of the selectedWeapon to the SelectedCharacter.
     * <p> The method checks if the SelectedCharacter is in the PlayerParty since if not it will be an CPUCharacter,
     * who cannot equip weapons. </p>
     */
    public void equipSelectedWeaponToSelectedCharacter(){
        if(player.getParty().contains(selectedCharacter)){
            IPlayerCharacter character = (IPlayerCharacter)this.selectedCharacter;
            player.equipCharacter(selectedWeapon, character);
        }
    }

    /**
     * Unequip the SelectedCharacter.
     * <p> The method checks if the SelectedCharacter is in the PlayerParty since if not it will be an CPUCharacter,
     * who cannot equip weapons. </p>
     */
    public void unequipSelectedCharacter(){
        if (player.getParty().contains(selectedCharacter)) {
            player.unequipCharacter((IPlayerCharacter)selectedCharacter);
        }
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
     * This method receives a target, and makes the selectedCharacter performs a normal attack against him.
     * <p> The method checks that any of the following cases are true:</p>
     * <p> selectedCharacter is in userPlayer party and target is in CPUPlayer Party, or </p>
     * <p> selectedCharacter is in CPUPlayer Party and selectedCharacter in userPlayer
     * Party. </p>
     * <p> If so, it sends the attack message in the corresponding direction. Otherwise, it has no effect.</p>
     * <p> In this way, the if's fulfill a double function: they ensure that the attacking and receiving characters
     * are in the teams (to avoid bugs) and at the same time they avoid attacks between the same team. </p>
     * @param target        The ICharacter that is going to be attacked.
     */
    public void selectedCharacterNormalAttack(ICharacter target){
        if(player.getParty().contains(selectedCharacter) & cpu.getParty().contains(target)){
            player.makeNormalAttack(selectedCharacter, target);
        }
        if(cpu.getParty().contains(selectedCharacter) & player.getParty().contains(target)){
            cpu.makeNormalAttack(selectedCharacter, target);
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
     * <p> The methods checks if the index is in the range of the weaponFactories list, to avoid indexError's. </p>
     * @param index     The position of the new SelectedWeaponFactory in weaponFactories
     */
    public void setSelectedWeaponFactory(int index){
        if(index < weaponFactories.size()){
            this.selectedWeaponFactory = weaponFactories.get(index);
        }
    }

    /**
     * Change the current SelectedCharacterFactory to the one in CharacterFactories at the given index.
     * <p> The methods checks if the index is in the range of the weaponFactories list, to avoid indexError's. </p>
     * @param index     The position of the new SelectedCharacterFactory in CharacterFactories
     */
    public void setSelectedCharacterFactory(int index){
        if(index < characterFactories.size()){
            this.selectedCharacterFactory = characterFactories.get(index);
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
     * Sets the selectedCharacterFactory default's pwoer
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
        return this.attackTarget;
    }


    /**
     * Gives the SelectedWeapon IWeapon Object.
     * @return      The selectedWeapon IWeapon Object.
     */
    public IWeapon getSelectedWeapon(){
        return this.selectedWeapon;
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
        return this.selectedWeaponFactory;
    }

    /**
     * Gives the SelectedCharacterFactory ICharacterFactory Object.
     * @return      The selectedCharacterFactory ICharacterFactory Object.
     */
    public ICharacterFactory getSelectedCharacterFactory(){
        return this.selectedCharacterFactory;
    }


}

