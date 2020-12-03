package com.github.matiasvergaras.finalreality.model;

import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;

import java.util.ArrayList;

public class UserPlayer {
    private ArrayList<IPlayerCharacter> party = new ArrayList<>();
    private ArrayList<IWeapon> inventory = new ArrayList<>();
    private int characterQuantity;

    public UserPlayer(int characterQuantity){
        this.characterQuantity = characterQuantity;
    }

    public ArrayList<IPlayerCharacter> getParty(){
        return party;
    }

    public ArrayList<IWeapon> getInventory(){
        return inventory;
    }

    public int getPartySize(){
        return party.size();
    }

    public int getInventorySize(){
        return inventory.size();
    }

    public void addToParty(IPlayerCharacter character) {
        if (this.getPartySize() < characterQuantity) {
            this.getParty().add(character);
        }
    }

    public void removeFromParty(IPlayerCharacter character){
        this.getParty().remove(character);
    }

    public void addToInventory(IWeapon weapon){
        this.inventory.add(weapon);
    }

    public void removeFromInventory(IWeapon weapon){
        this.inventory.remove(weapon);
    }

    public void unequipCharacter(IPlayerCharacter character){
        character.unequip();

    }
}
