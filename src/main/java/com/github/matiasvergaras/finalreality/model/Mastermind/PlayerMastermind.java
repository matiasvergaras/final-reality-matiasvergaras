package com.github.matiasvergaras.finalreality.model.Mastermind;

import com.github.matiasvergaras.finalreality.model.character.cpu.ICPUCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;

import java.util.ArrayList;

/**
 * A class to hold all the information of a userPlayer.
 * <p> Since the party and the inventory are of the player and not of the Controller, we decided to create this class
 * to hold it. </p>
 * <p> User Player does have a name and a maximum number of characters. </p>
 */
public class PlayerMastermind implements AbstractMastermind {
    private ArrayList<IPlayerCharacter> party;
    private ArrayList<IWeapon> inventory;
    private final int characterQuantity;
    private final String name;

    /**
     * Constructor for a new UserPlayer
     * @param name                  The name of the Player in this game.
     * @param characterQuantity     The number of characters that the player will have.
     */
    public UserPlayer(String name, int characterQuantity){
        this.characterQuantity = characterQuantity;
        this.party = new ArrayList<>();
        this.inventory = new ArrayList<>();
        this.name = name;
    }

    /**
     * Gives the actual player name.
     * @return  The name of the UserPlayer.
     */
    public String getPlayerName(){
        return name;
    }

    /**
     * Gives the number of characters that this userPlayer must have at the start of the game.
     * @return      The number of characters that this userPlayer must have at the start of the game.
     */
    public int getCharacterQuantity(){
        return characterQuantity;
    }

    /**
     * Gives the number of characters in the userPlayer party.
     * @return  the number of characters in the userPlayer party.
     */
    public int getPartySize(){
        return party.size();
    }

    /**
     * Gives the party of the userPlayer.
     * @return  An ArrayList of IPlayerCharacter representing all the characters of the userPlayer together.
     */
    public ArrayList<IPlayerCharacter> getParty(){
        return party;
    }

    /**
     * Adds a character to the party of the userPlayer if there is space.
     * @param character     The IPlayerCharacter character to be added to the userPlayer party.
     */
    public void addToParty(IPlayerCharacter character) {
        if (this.getPartySize() < characterQuantity) {
            this.getParty().add(character);
        }
    }

    /**
     * Removes a character from the UserPlayer party.
     * @param character     The IPlayerCharacter character to be removed.
     */
    public void removeFromParty(IPlayerCharacter character){
        this.getParty().remove(character);
    }

    /**
     * Returns the number of elements in the userPlayer inventory.
     * @return      The number of elements in the userPlayer inventory.
     */
    public int getInventorySize(){
        return inventory.size();
    }

    /**
     * Gives the inventory of this userPlayer.
     * @return      An ArrayList of IWeapon representing all the weapons in the inventory of the userPlayer together.
     */
    public ArrayList<IWeapon> getInventory(){
        return inventory;
    }

    /**
     * Adds a given weapon to the inventory of this userPlayer.
     * @param weapon        The IWeapon  weapon to be added to the inventory.
     */
    public void addToInventory(IWeapon weapon){
        this.inventory.add(weapon);
    }

    /**
     * Removes a given weapon from the userPlyer inventory.
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

    /**
     * Makes a character performs a normal attack against an enemy.
     * @param character     The IPlayerCharacter character that will perform the attack.
     * @param target        The ICPUCharacter character that will receive the attack.
     */
    public void makeNormalAttack(IPlayerCharacter character, ICPUCharacter target){
        character.normalAttack(target);
    }


}
