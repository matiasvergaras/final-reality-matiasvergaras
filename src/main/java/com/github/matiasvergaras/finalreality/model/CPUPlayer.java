package com.github.matiasvergaras.finalreality.model;

import com.github.matiasvergaras.finalreality.model.character.cpu.ICPUCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class CPUPlayer {
    private ArrayList<ICPUCharacter> party;
    private IPlayerCharacter activeCharacter;
    private IWeapon selectedWeapon;
    private PropertyChangeSupport
            endTurn = new PropertyChangeSupport(this),
            deadCharacter = new PropertyChangeSupport(this);

    /**
     * Creates a new CPU Player
     *
     */
    public CPUPlayer() {
        this.party = new ArrayList<>();
        this.activeCharacter = null;
        this.selectedWeapon = null;
    }


    /**
     * Returns the CPU player's active Character.
     * @return the active PlayerCharacter.
     */
    public IPlayerCharacter getActiveCharacter() {
        return this.activeCharacter;
    }

    /**
     * Sets the CPU player's active Character.
     *
     * @param aCharacter that will be activated.
     */
    public void setActiveCharacter(IPlayerCharacter aCharacter){
        this.activeCharacter = aCharacter;
    }

    /** Gives the CPU player's party
     * @return the player's Characters list object.
     */
    public ArrayList<ICPUCharacter> getParty() {
        return this.party;
    }

    /**
     * Sets the CPU player's party
     * @param party player's ArrayList with weapon objects.
     */
    public void setParty(ArrayList<ICPUCharacter> party) {
        this.party = party;
    }

    public int getSizeOfParty(){
        return this.party.size();
    }


    /**
     * Adds a character to the CPU player's party
     * @param character ICPUCharacter object to be added to the party.
     */
    public void addToParty(ICPUCharacter character) {
        if (character.isAlive()){
            this.party.add(character);
        }
    }

    /**
     * Removes a character from the players' party
     * @param character ICPUCharacter object to be removed from the party.
     */
    public void removeFromParty(ICPUCharacter character) {
        this.party.remove(character);
    }

}
