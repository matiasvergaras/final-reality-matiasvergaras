package com.github.matiasvergaras.finalreality.controller;

import com.github.matiasvergaras.finalreality.model.CPUGamer;
import com.github.matiasvergaras.finalreality.model.PlayerGamer;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.cpu.Enemy;
import com.github.matiasvergaras.finalreality.model.character.cpu.ICPUCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.magic.BlackMage;
import com.github.matiasvergaras.finalreality.model.character.player.magic.WhiteMage;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Engineer;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Knight;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Thief;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.magic.Staff;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Axe;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Bow;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Knife;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Sword;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.BlockingQueue;

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
    private PropertyChangeSupport endTurn = new PropertyChangeSupport(this);
    private EndTurnHandler endTurnHandler = new EndTurnHandler(this);
    private DeathPlayerToGCHandler deathPlayerToGCHandler = new DeathPlayerToGCHandler(this);
    private int numOfCharacters;
    private PlayerGamer userPlayer;
    private CPUGamer cpuPlayer;
    private BlockingQueue<ICharacter> turns;

    /**
     * Constructor for a new controller.
     *
     * @param numOfCharacters
     *        the number of characters that the player will have
     *
     */
    public GameController(int numOfCharacters, PlayerGamer userPlayer, CPUGamer cpuPlayer,
                          BlockingQueue<ICharacter> turns) {
        this.numOfCharacters = numOfCharacters;
        this.userPlayer = userPlayer;
        this.cpuPlayer = cpuPlayer;
        this.turns = turns;
    }

    /**
     * Adds a Weapon object to the Player's inventory.
     * @param weapon    The weapon to be added.
     */
    public void addToInventory(IWeapon weapon) {
        this.userPlayer.addToInventory(weapon);
    }

    /**
     * Removes a Weapon object from the Player's inventory.
     * @param weapon    The weapon to be removed.
     */
    public void removeFromInventory( IWeapon weapon) {
        this.userPlayer.removeFromInventory(weapon);
    }

    /**
     * Adds a PlayerCharacter object to the Player's party
     * @param character The character to be added.
     */
    public void addToPlayerParty(IPlayerCharacter character){
        if (this.userPlayer.getSizeOfParty() < numOfCharacters){
            this.userPlayer.addToParty(character);
        }
    }

    /**
     * Removes a PlayerCharacter object from the Player's party
     * @param character The character to be removed.
     */
    public void removeFromPlayerParty(IPlayerCharacter character){
        this.userPlayer.removeFromParty(character);
    }

    /**
     * Adds a CPUCharacter object to the CPU's party
     * @param character The character to be added.
     */
    public void addToCPUParty(ICPUCharacter character){
        this.cpuPlayer.addToParty(character);
    }

    /**
     * Removes a CPUCharacter object from the CPU's party
     * @param character The character to be removed.
     */
    public void removeFromCPUParty(ICPUCharacter character){
        this.cpuPlayer.removeFromParty(character);
    }

    /**
     * Gives the list of the PlayerCharacter objects in the Player's party.
     * @return Player's party
     */
    public ArrayList<IPlayerCharacter> getPlayerParty() {
        return this.userPlayer.getParty();
    }

    /**
     * Creates a dictionary including all the attributes of the player's characters
     * together, in the following order:
     * <p> maxHP, currentHP, DP, maxMana (if applicable), currentMana (if applicable) </p>
     * Where the name of the character is the key.
     * @return a Dictionary with names as keys and lists of character's attributes as values.
     */
    public Hashtable<String, ArrayList<Integer>> getPlayerPartyAttributes(){
        Hashtable<String, ArrayList<Integer>> attributes = new Hashtable<String, ArrayList<Integer>>();
        for (IPlayerCharacter p: this.getPlayerParty()){
            ArrayList<Integer> attr = p.getAttributes();
            String name = p.getName();
            attributes.put(name, attr);
        }
        return attributes;
    }

    /**
     * Creates a dictionary including all the attributes of the CPU's characters
     * together, in the following order:
     * <p> maxHP, currentHP, DP, weight, power </p>
     * Where the name of the character is the key.
     * @return a Dictionary with names as keys and lists of character's attributes as values.
     */
    public Hashtable<String, ArrayList<Integer>> getCPUPartyAttributes(){
        Hashtable<String, ArrayList<Integer>> attributes = new Hashtable<String, ArrayList<Integer>>();
        for (ICPUCharacter p: this.getCPUParty()){
            ArrayList<Integer> attr = p.getAttributes();
            String name = p.getName();
            attributes.put(name, attr);
        }
        return attributes;
    }


    /**
     * Gives the list of Weapon object's in the Player's inventory
     * @return Player's inventory
     */
    public ArrayList<IWeapon> getInventory() {
        return this.userPlayer.getInventory();
    }

    /**
     * Gives the list of the CPUCharacters objects in the CPU's party.
     * @return CPU's party
     */
    public ArrayList<ICPUCharacter> getCPUParty() {
        return this.cpuPlayer.getParty();
    }

    /**
     * Equips a Weapon from the inventory to a Player Character.
     *
     * @param index      The index of the weapon in the inventory.
     * @param character  The character to be equipped.
     */
    public void equipWeapon(int index, IPlayerCharacter character) {
        character.equipWeapon(this.getInventory().get(index));
    }

    /**
     * Makes a PlayerCharacter attacks a CPUCharacter
     * <p>
     *     We decided to separate the attacks in the two directions to remove the user
     *     from generating attacks between characters on the same side.
     * </p>
     *
     * @param playerCharacter
     *                       The player character that will performs the attack.
     * @param cpuCharacter
     *                       The CPU character that will receive the attack.
     */
    public void playerAttacksCPU(IPlayerCharacter playerCharacter, ICPUCharacter cpuCharacter){
        playerCharacter.normalAttack(cpuCharacter);
    }

    /**
     * Makes a CPUCharacter attacks a PlayerCharacter
     * <p>
     *     We decided to separate the attacks in the two directions to remove the user
     *     from generating attacks between characters on the same side.
     * </p>
     *
     * @param cpuCharacter
     *                       The CPU character that will performs the attack.
     * @param playerCharacter
     *                       The player character that will receive the attack.
     */
    public void CPUAttacksPlayer(ICPUCharacter cpuCharacter, IPlayerCharacter playerCharacter){
        cpuCharacter.normalAttack(playerCharacter);
    }

    /**
     * Adds an Axe to the Player's inventory.
     * @param name  The name of the weapon.
     * @param power The normal power of the weapon.
     * @param weight    The weight of the weapon.
     */
    public void addAxeToInventory(String name, int power, int weight){
        Axe axe = new Axe(name, power, weight);
        this.addToInventory(axe);
    }

    /**
     * Adds a Bow to the Player's inventory.
     * @param name  The name of the weapon.
     * @param power The normal power of the weapon.
     * @param weight    The weight of the weapon.
     */
    public void addBowToInventory(String name, int power, int weight){
        Bow bow = new Bow(name, power, weight);
        this.addToInventory(bow);
    }

    /**
     * Adds a Knife to the Player's inventory.
     * @param name  The name of the weapon.
     * @param power The normal power of the weapon.
     * @param weight    The weight of the weapon.
     */
    public void addKnifeToInventory(String name, int power, int weight){
        Knife knife = new Knife(name, power, weight);
        this.addToInventory(knife);
    }

    /**
     * Adds a Sword to the Player's inventory.
     * @param name  The name of the weapon.
     * @param power The normal power of the weapon.
     * @param weight    The weight of the weapon.
     */
    public void addSwordToInventory(String name, int power, int weight){
        Sword sword = new Sword(name, power, weight);
        this.addToInventory(sword);
    }

    /**
     * Adds a Staff to the Player's inventory.
     * @param name  The name of the weapon.
     * @param power The normal power of the weapon.
     * @param weight    The weight of the weapon.
     */
    public void addStaffToInventory(String name, int power, int weight, int magicDamage){
        Staff staff = new Staff(name, power, weight, magicDamage);
        this.addToInventory(staff);
    }

    /**
     * Adds an Engineer to the Player's party.
     * @param name  The name of the character.
     * @param HP The HP of the character.
     * @param DP    The DP of the character.
     */
    public void addEngineerToUserParty(String name, int HP, int DP){
        Engineer engineer = new Engineer(turns, name, HP, DP);
        this.addToPlayerParty(engineer);
    }

    /**
     * Adds a Knight to the Player's party.
     * @param name  The name of the character.
     * @param HP The HP of the character.
     * @param DP    The DP of the character.
     */
    public void addKnightToUserParty(String name, int HP, int DP){
        Knight knight = new Knight(turns, name, HP, DP);
        this.addToPlayerParty(knight);
    }

    /**
     * Adds a Thief to the Player's party.
     * @param name  The name of the character.
     * @param HP The HP of the character.
     * @param DP    The DP of the character.
     */
    public void addThiefToUserParty(String name, int HP, int DP){
        Thief thief = new Thief(turns, name, HP, DP);
        this.addToPlayerParty(thief);
    }

    /**
     * Adds a BlackMage to the Player's party.
     * @param name  The name of the character.
     * @param HP The HP of the character.
     * @param DP    The DP of the character.
     * @param mana The mana of the character.
     */
    public void addBlackMageToUserParty(String name, int HP, int DP, int mana){
        BlackMage blackMage = new BlackMage(turns, name, HP, DP, mana);
        this.addToPlayerParty(blackMage);
    }

    /**
     * Adds a WhiteMage to the Player's party.
     * @param name  The name of the character.
     * @param HP The HP of the character.
     * @param DP    The DP of the character.
     * @param mana The mana of the character.
     */
    public void addWhiteMageToUserParty(String name, int HP, int DP, int mana){
        WhiteMage whiteMage = new WhiteMage(turns, name, HP, DP, mana);
        this.addToPlayerParty(whiteMage);
    }

    /**
     * Adds an Enemy to the CPU's party.
     * @param name  The name of the character.
     * @param weight The weight of the character.
     * @param HP The HP of the character.
     * @param DP    The DP of the character.
     * @param power The power of the character.

     */
    public void addEnemyToCPUParty(String name, int weight, int HP, int DP, int power){
        Enemy enemy = new Enemy(turns, name, weight, HP, DP, power);
        this.addToCPUParty(enemy);
    }


}

