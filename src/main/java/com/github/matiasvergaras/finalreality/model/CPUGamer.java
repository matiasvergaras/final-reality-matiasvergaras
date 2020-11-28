package com.github.matiasvergaras.finalreality.model;

import com.github.matiasvergaras.finalreality.model.character.cpu.ICPUCharacter;


import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * A class that holds all the information of a CPU Gamer.
 * A CPU Gamer has its own list of characters and its observed by the Game Controller
 * looking for an endTurn or the dead of a Character.
 * @since Homework 2
 * @author Matías Vergara Silva
 */
public class CPUGamer {
    private ArrayList<ICPUCharacter> party;
    private ICPUCharacter activeCharacter;
    private PropertyChangeSupport
            endTurn = new PropertyChangeSupport(this),
            deadCharacter = new PropertyChangeSupport(this);

    /**
     * Creates a new CPU Gamer
     *
     */
    public CPUGamer() {
        this.party = new ArrayList<>();
        this.activeCharacter = null;
    }


    /**
     * Returns the CPU gamer's active Character.
     * @return the active CPUCharacter.
     */
    public ICPUCharacter getActiveCharacter() {
        return this.activeCharacter;
    }

    /**
     * Sets the CPU gamer's active Character.
     *
     * @param aCharacter that will be activated.
     */
    public void setActiveCharacter(ICPUCharacter aCharacter){
        this.activeCharacter = aCharacter;
    }

    /** Gives the CPU gamer's party
     * @return the player's Characters list object.
     */
    public ArrayList<ICPUCharacter> getParty() {
        return this.party;
    }

    /**
     * Sets the CPU gamer's party
     * @param party gamer's ArrayList with CPUCharacters  that will be the new CPU party.
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
