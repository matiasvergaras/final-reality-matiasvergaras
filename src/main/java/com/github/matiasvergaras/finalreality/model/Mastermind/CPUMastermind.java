package com.github.matiasvergaras.finalreality.model.Mastermind;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;


import java.util.ArrayList;

/**
 * A class to hold all the behavior of a CPUMastermind
 * <p> Since the party is of the CPU and not of the Controller, we decided to create this class to hold it. </p>
 * <p> CPUMastermind does not have a name. To instantiate it is enough with an empty constructor. </p>
 */
public class CPUMastermind extends AbstractMastermind {
    private ArrayList<ICharacter> party;

    /**
     * Constructor of a new CPUPMastermind.
     * It assigns a new empty party to this CPUMastermind.
     */
    public CPUMastermind(){
        this.party  = new ArrayList<>();
    }


}
