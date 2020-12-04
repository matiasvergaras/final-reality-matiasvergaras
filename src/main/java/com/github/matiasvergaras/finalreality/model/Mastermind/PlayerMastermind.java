package com.github.matiasvergaras.finalreality.model.Mastermind;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;

import java.util.ArrayList;

/**
 * A class to hold all the information of a PlayerMastermind.
 * <p> Since the party and the inventory are of the player and not of the Controller, we decided to create this class
 * to hold it. </p>
 * <p> PlayerMastermind does have a name and a maximum number of characters. </p>
 */
public class PlayerMastermind extends AbstractMastermind {
    private ArrayList<IPlayerCharacter> party;
    private ArrayList<IWeapon> inventory;
    private final int characterQuantity;
    private final String name;

    /**
     * Constructor for a new PlayerMastermind.
     * @param name                  The name of the Player in this game.
     * @param characterQuantity     The number of characters that the player will have.
     */
    public PlayerMastermind(String name, int characterQuantity){
        this.characterQuantity = characterQuantity;
        this.party = new ArrayList<>();
        this.inventory = new ArrayList<>();
        this.name = name;
    }

    /**
     * Gives the actual player name.
         * @return  The name of the PlayerMastermind.
     */
    public String getPlayerName(){
        return name;
    }

    /**
     * Gives the number of characters that this PlayerMastermind must have at the start of the game.
     * @return      The number of characters that this PlayerMastermind must have at the start of the game.
     */
    public int getCharacterQuantity(){
        return characterQuantity;
    }


    /**
     * Adds a character to the party of the PlayerMastermind if there is space.
     * @param character     The IPlayerCharacter character to be added to the PlayerMastermind party.
     */
    public void addToPlayerParty(ICharacter character) {
        if (this.getPartySize() < characterQuantity) {
            this.getParty().add(character);
        }
    }

    /**
     * Returns the number of elements in the PlayerMastermind inventory.
     * @return      The number of elements in the PlayerMastermind inventory.
     */
    public int getInventorySize(){
        return inventory.size();
    }

    /**
     * Gives the inventory of this PlayerMastermind
     * @return      An ArrayList of IWeapon representing all the weapons in the inventory of the PlayerMastermind
     * together.
     */
    public ArrayList<IWeapon> getInventory(){
        return inventory;
    }

    /**
     * Adds a given weapon to the inventory of this PlayerMastermind.
     * @param weapon        The IWeapon  weapon to be added to the inventory.
     */
    public void addToInventory(IWeapon weapon){
        this.inventory.add(weapon);
    }

    /**
     * Removes a given weapon from the PlayerMastermind inventory.
     * @param weapon        The IWeapon weapon to be removed from the inventory.
     */
    public void removeFromInventory(IWeapon weapon){
        this.inventory.remove(weapon);
    }

    /**
     * Equips a given character with a given weapon.
     * @param weapon        The IWeapon weapon to be equipped.
     * @param character     The IPlayerCharacter character that will equip the weapon.
     */
    public void equipCharacter(IWeapon weapon, IPlayerCharacter character){
        character.equipWeapon(weapon);
    }

    /**
     * Unequips a given character, if it is equipped. Otherwise it has no effect.
     * @param character     The character to be unequipped.
     */
    public void unequipCharacter(IPlayerCharacter character){
        character.unequip();
    }


}
