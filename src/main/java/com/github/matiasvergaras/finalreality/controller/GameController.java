package com.github.matiasvergaras.finalreality.controller;

import com.github.matiasvergaras.finalreality.model.CPUPlayer;
import com.github.matiasvergaras.finalreality.model.UserPlayer;
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
import java.util.concurrent.BlockingQueue;

/**
 * Game Controller.
 * The controller will be an intermediary between the user and the objects of the model,
 * whose purpose will be to control all the messages that pass through it, manipulating
 * and redirecting those that are necessary.
 *
 * @since Homework 2
 * @author Matías Vergara Silva
 */
public class GameController {
    private PropertyChangeSupport endTurn = new PropertyChangeSupport(this);
    private EndTurnHandler endTurnHandler = new EndTurnHandler(this);
    private DeathPlayerToGCHandler deathPlayerToGCHandler = new DeathPlayerToGCHandler(this);
    private int numOfCharacters;
    private IPlayerCharacter selectedPlayerCharacter;
    private ICPUCharacter selectedCPUCharacter;
    private UserPlayer userPlayer;
    private CPUPlayer cpuPlayer;
    private BlockingQueue<ICharacter> turns;

    /**
     * Constructor for a new controller.
     *
     * @param numOfCharacters
     *        the number of characters that the player will have
     *
     */
    public GameController(int numOfCharacters, UserPlayer userPlayer, CPUPlayer cpuPlayer,
                          BlockingQueue<ICharacter> turns) {
        this.numOfCharacters = numOfCharacters;
        this.userPlayer = userPlayer;
        this.cpuPlayer = cpuPlayer;
        this.turns = turns;
    }

    public void addToInventory(IWeapon weapon) {
        this.userPlayer.addToInventory(weapon);
    }

    public void removeFromInventory( IWeapon weapon) {
        this.userPlayer.removeFromInventory(weapon);
    }

    public void addToPlayerParty(IPlayerCharacter character){
        if (this.userPlayer.getSizeOfParty() < numOfCharacters){
            this.userPlayer.addToParty(character);
        }
    }

    public void removeFromPlayerParty(IPlayerCharacter character){
        this.userPlayer.removeFromParty(character);
    }

    public void addToCPUParty(ICPUCharacter character){
        this.cpuPlayer.addToParty(character);
    }

    public void removeFromCPUParty(ICPUCharacter character){
        this.cpuPlayer.removeFromParty(character);
    }

    public ArrayList<IPlayerCharacter> getPlayerParty() {
        return this.userPlayer.getParty();
    }

    public ArrayList<IWeapon> getInventory() {
        return this.userPlayer.getInventory();
    }

    public ArrayList<ICPUCharacter> getCPUParty() {
        return this.cpuPlayer.getParty();
    }

    public

    /**
     * Equips a Weapon from the inventory to the currently selected Player Character.
     *
     * @param index
     *     the location of the weapon in the inventory.
     */
    public void equipWeapon(int index) {
        this.selectedPlayerCharacter.equipWeapon(this.getInventory().get(index));
    }

    public void addAxeToInventory(String name, int power, int weight){
        Axe axe = new Axe(name, power, weight);
        this.addToInventory(axe);
    }

    public void addBowToInventory(String name, int power, int weight){
        Bow bow = new Bow(name, power, weight);
        this.addToInventory(Bow);
    }
    public void addKnifeToInventory(String name, int power, int weight){
        Knife knife = new Knife(name, power, weight);
        this.addToInventory(knife);
    }
    public void addSwordToInventory(String name, int power, int weight){
        Sword sword = new Sword(name, power, weight);
        this.addToInventory(sword);
    }
    public void addStaffToInventory(String name, int power, int weight, int magicDamage){
        Staff staff = new Staff(name, power, weight, magicDamage);
        this.addToInventory(staff);
    }

    public void addEngineerToUserParty(String name, int HP, int DP){
        Engineer engineer = new Engineer(turns, name, HP, DP);
        this.addToPlayerParty(engineer);
    }

    public void addKnightToUserParty(String name, int HP, int DP){
        Knight knight = new Knight(turns, name, HP, DP);
        this.addToPlayerParty(knight);
    }

    public void addThiefToUserParty(String name, int HP, int DP){
        Thief thief = new Thief(turns, name, HP, DP);
        this.addToPlayerParty(thief);
    }

    public void addBlackMageToUserParty(String name, int HP, int DP, int mana){
        BlackMage blackMage = new BlackMage(turns, name, HP, DP, mana);
        this.addToPlayerParty(blackMage);
    }

    public void addWhiteMageToUserParty(String name, int HP, int DP, int mana){
        WhiteMage whiteMage = new WhiteMage(turns, name, HP, DP, mana);
        this.addToPlayerParty(whiteMage);
    }

    public void addEnemyToCPUParty(String name, int weight, int HP, int DP, int power){
        Enemy enemy = new Enemy(turns, name, weight, HP, DP, power);
        this.addToCPUParty(enemy);
    }

}

