package com.github.matiasvergaras.finalreality.model.Mastermind;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A class to hold all the information of a PlayerMastermind.
 * <p> Since the party and the inventory are of the player and not of the Controller, we decided to create this class
 * to hold it. </p>
 * <p> PlayerMastermind does have a name and a maximum number of characters. </p>
 */
public class PlayerMastermind extends AbstractMastermind {
    private ArrayList<IWeapon> inventory;
    private final int characterQuantity;

    /**
     * Constructor for a new PlayerMastermind.
     * @param name                  The name of the Player in this game.
     * @param characterQuantity     The number of characters that the player will have.
     */
    public PlayerMastermind(String name, int characterQuantity){
        super(name);
        this.characterQuantity = characterQuantity;
        this.inventory = new ArrayList<>();
    }

    /**
     * Checks if it is enough space in the party. If so, it adds the given character.
     * <p> In case that the character to add is already equipped, we will also add its weapon
     * to the inventory. However, this should not be necessary in this version of the game,
     * since we have not implemented the in-game recruitment yet. </p>
     * @param character     The ICharacter character to be added.
     */
    @Override
    public void addToParty(ICharacter character){
        if(this.getPartySize()<this.characterQuantity){
            super.addToParty(character);
            IPlayerCharacter asPlayer = (IPlayerCharacter) character;
            if (asPlayer.isEquipped()){
                this.addToInventory(asPlayer.getEquippedWeapon());
            }

        }
    }

    /**
     * Gives the number of characters that this PlayerMastermind must have at the start of the game.
     * @return      The number of characters that this PlayerMastermind must have at the start of the game.
     */
    public int getCharacterQuantity(){
        return characterQuantity;
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
        this.getInventory().remove(weapon);
    }

    /**
     * Equips a given character with a given weapon.
     * <p> This method should work only if the weapon to equip is in the inventory and the
     * character in the party. So
     * we will add a condition for that. </p>
     * @param weapon        The IWeapon weapon to be equipped.
     * @param character     The IPlayerCharacter character that will equip the weapon.
     */
    public void equipCharacter(IWeapon weapon, IPlayerCharacter character){
        if(getParty().contains(character) & getInventory().contains(weapon)) {
            character.equipWeapon(weapon);
        }
    }

    /**
     * Unequips a given character, if it is equipped. Otherwise it has no effect.
     * <p> In order to unequip a character, it has to be in the playerParty. </p>
     * @param character     The character to be unequipped.
     */
    public void unequipCharacter(IPlayerCharacter character){
        if(getParty().contains(character)){
            character.getEquippedWeapon().setWeaponFree();
        }
    }

    /**
     * Gives the weapon in the inventory at the given index.
     * <p> Controller will be in charge of avoid passing an index greater than the size-1. </p>
     * <p> Therefore, this method will not verify it. </p>
     * @param index     The index of the weapon in the inventory.
     * @return  IWeapon weapon if the index is in correct range.
     */
    public IWeapon getWeaponFromInventory(int index){
        return getInventory().get(index);
    }

    /**
     * Check if this is equal to a given object o.
     *
     * @param o The target object
     * @return True if are equals, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerMastermind)) return false;
        PlayerMastermind that = (PlayerMastermind) o;
        return this.getName().equals(that.getName());
    }

    /**
     * Returns an integer value, generated by a hashing algorithm, distinct
     * for distinct objects. Required for the equals method.
     *
     * @return Integer hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }


}
