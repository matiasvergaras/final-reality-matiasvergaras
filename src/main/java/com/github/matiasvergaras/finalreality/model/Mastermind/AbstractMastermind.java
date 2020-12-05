package com.github.matiasvergaras.finalreality.model.Mastermind;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;

import java.util.ArrayList;
import java.util.Map;

/**
 * Class to hold the common behavior of both CPUMastermind and PlayerMastermind.
 * @since Homework 2
 * @author Matías Vergara Silva.
 */
public abstract class AbstractMastermind implements  IMastermind{
    protected ArrayList<ICharacter> party;

    /**
     * Adds a character to the Player's Party.
     * @param character     The ICharacter character to be added.
     */
    @Override
    public void addToParty(ICharacter character){
        this.getParty().add(character);
    }

    /**
     * Gives the number of characters in the mastermind's party.
     * @return  the number of characters in the userPlayer party.
     */
    @Override
    public int getPartySize(){
        return party.size();
    }

    /**
     * Gives the party of the mastermind's.
     * @return  An ArrayList of ICharacter representing all the characters of the mastermind's together.
     */
    @Override
    public ArrayList<ICharacter> getParty(){
        return party;
    }

    /**
     * Removes a character from the mastermind's party.
     * @param character     The ICharacter character to be removed.
     */
    @Override
    public void removeFromParty(ICharacter character){
        this.getParty().remove(character);
    }

    /**
     * {@inheritDoc}
     * <p> Controller will be in charge of avoid passing an index grater than the size-1. </p>
     * <p> Therefore, this method will not verify it. </p>
     * @param index     The position of the character to be returned in the party.
     * @return          The character in the 'index' position of the party.
     */
    @Override
    public ICharacter getCharacterFromParty(int index){
        return getParty().get(index);
    }

    /**
     * Makes a character performs a normal attack against another.
     * <p> Controller will be in charge of check that the characters are in different teams.
     * Mastermind will only check that the attacker character is in his party. </p>
     * @param character     The character that will perform the attack.
     * @param target        The character that will receive the attack.
     */
    @Override
    public void makeNormalAttack(ICharacter character, ICharacter target){
        if(this.getParty().contains(character)){
            character.normalAttack(target);
        }
    }

}