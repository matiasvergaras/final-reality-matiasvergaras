package com.github.matiasvergaras.finalreality.model.Mastermind;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.cpu.ICPUCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;


import java.util.ArrayList;

/**
 * A class to hold all the information of a CPUPlayer.
 * <p> Since the party is of the CPU and not of the Controller, we decided to create this class to hold it. </p>
 * <p> CPU Player does not have a name. To instantiate it is enough with an empty constructor. </p>
 */
public class CPUMastermind implements AbstractMastermind {
    private ArrayList<ICharacter> party;

    /**
     * Constructor of a new CPUPMastermind.
     * It assigns a new empty party to this CPUMastermind.
     */
    public CPUMastermind(){
        this.party  = new ArrayList<>();
    }

    /**
     * Adds a new character to this CPUPlayer party's.
     * @param character     The ICPUCharacter to be added.
     */
    public void addToCPUParty(ICharacter character){
        this.party.add(character);
    }
    
    /**
     * Makes an character performs a normal attack against an enemy (from the UserPlayer).
     * @param character     The ICPUCharacter that will perform the attack.
     * @param target        The IPlayerCharacter that will receive the attack.
     */
    public void makeNormalAttack(ICPUCharacter character, IPlayerCharacter target){
        character.normalAttack(target);
    }
}
