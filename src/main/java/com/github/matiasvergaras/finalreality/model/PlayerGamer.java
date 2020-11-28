package com.github.matiasvergaras.finalreality.model;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent a Player.
 * <p>
 * User with communicate with controller, and controller with player
 * @since Homework 2
 * @author Matías Vergara Silva
 */
public class PlayerGamer {
    private ArrayList<IPlayerCharacter> party;
    private ArrayList<IWeapon> inventory;
    private IPlayerCharacter selectedCharacter;
    private IPlayerCharacter activeCharacter;
    private IWeapon selectedWeapon;
    private PropertyChangeSupport
            endTurn = new PropertyChangeSupport(this),
            deadCharacter = new PropertyChangeSupport(this);

    /**
     * Creates a new PlayerGamer
     *
     */
    public PlayerGamer() {
        this.party = new ArrayList<>();
        this.inventory = new ArrayList<>();
        this.activeCharacter = null;
        this.selectedCharacter = null;
        this.selectedWeapon = null;
    }


    /**
     * Returns the player's active Character.
     * @return the active PlayerCharacter.
     */
    public IPlayerCharacter getActiveCharacter() {
        return this.activeCharacter;
    }

    /**
     * Sets the player's active Character.
     *
     * @param aCharacter that will be activated.
     */
    public void setActiveCharacter(IPlayerCharacter aCharacter){
        this.activeCharacter = aCharacter;
    }

    /**
     * Returns the player's selected Character.
     * @return the selected PlayerCharacter.
     */
    public IPlayerCharacter getSelectedCharacter() {
        return this.selectedCharacter;
    }

    /**
     * Sets the player's selected Character.
     *
     * @param aCharacter that will be activated.
     */
    public void setSelectedCharacter(IPlayerCharacter aCharacter){
        this.selectedCharacter = aCharacter;
    }

    /** Gives the player's party
     * @return the player's Characters list object.
     */
    public ArrayList<IPlayerCharacter> getParty() {
        return this.party;
    }

    /**
     * Sets the player's party
     * @param party player's ArrayList with the PlayerCharacter's that will be the new player's party.
     */
    public void setParty(ArrayList<IPlayerCharacter> party) {
        this.party = party;
    }

    /** Gives the player's inventory
     * @return the player's inventory object.
     */
    public ArrayList<IWeapon> getInventory() {
        return this.inventory;
    }

    public int getSizeOfParty(){
        return this.party.size();
    }

    public int getSizeOfInventory(){
        return this.inventory.size();
    }

    /**
     * Sets the player's inventory
     * @param inventory player's ArrayList with weapon objects.
     */
    public void setInventory(ArrayList<IWeapon> inventory) {
        this.inventory = inventory;
    }

    /**
     * Adds a weapon to the player's inventory
     * @param weapon IWeapon object to be added to the inventory.
     */
    public void addToInventory(IWeapon weapon) {
        this.inventory.add(weapon);
    }

    /**
     * Adds a character to the players' party
     * @param character IPlayerCharacter object to be added to the party.
     */
    public void addToParty(IPlayerCharacter character) {
        if (character.isAlive()){
            this.party.add(character);
        }
    }

    /**
     * Removes a character from the players' party
     * @param character IPlayerCharacter object to be removed from the party.
     */
    public void removeFromParty(IPlayerCharacter character) {
        this.party.remove(character);
    }

    /**
     * Removes a weapon from the players' Inventory
     * @param weapon IWeapon object to be removed from the inventory.
     */
    public void removeFromInventory(IWeapon weapon) {
        this.inventory.remove(weapon);
    }

}
