package com.github.matiasvergaras.finalreality.model.Mastermind;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
public abstract class AbstractMastermind implements IMastermind{

    /**
     * Adds a character to the Player's Party.
     * @param character     The ICharacter character to be added.
     */
    public void addPlayerCharacter(ICharacter character){
    }

    /**
     * Adds a character to the CPU's Party.
     * @param character     The ICharacter character to be added.
     */
    public void addCPUCharacter(ICharacter character){
    }

}
