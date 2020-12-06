package com.github.matiasvergaras.finalreality.model.Mastermind;

import com.github.matiasvergaras.finalreality.model.CharacterAttributeSet;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * This represents a player of the game.
 * <p> To avoid name confusion with the human player's own classes, we'll call the players 'masterminds'.</p>
 * @since Homework 2
 * @author Matías Vergara Silva.
 */
public interface IMastermind {

    /**
     * Gets the attributes of the given character as an AttributeSet.
     * <p> AttributeSet is a new class that we created to keep the attributes of the different types of
     * characters. It has getters for every possible attribute.  If it is asked for an attribute that the character does
     * not have, it will return 0 (except for equippedWeapon, in which case it will return null). </p>
     * @param   character       The character whose attributes will be returned.
     * @return  an AttributeSet representing all the attributes of the character.
     */
    CharacterAttributeSet getCharacterAttributes(ICharacter character);

    /**
     * Gives the number of characters alive of this mastermind.
     * @return      int alive characters.
     */
    int getAliveCharacters();

    /**
     * Adds a character to the mastermind's Party.
     * @param character     The ICharacter character to be added.
     */
    void addToParty(ICharacter character);

    /**
     * Gives the number of characters in the mastermind's party.
     * @return  the number of characters in the userPlayer party.
     */
    int getPartySize();

    /**
     * Gives the party of the mastermind's.
     * @return  An ArrayList of ICharacter representing all the characters of the mastermind's together.
     */
    ArrayList<ICharacter> getParty();

    /**
     * Removes a character from the mastermind's party.
     * @param character     The ICharacter character to be removed.
     */
    void removeFromParty(ICharacter character);

    /**
     * Gives the character in the 'index' position at the party of this Mastermind.
     * @param index     The position of the desired character in the party.
     * @return          The ICharacter at the index position in the party.
     */
    ICharacter getCharacterFromParty(int index);

    /**
     * Makes an character performs an attack against another.
     * @param character     The character that will perform the attack.
     * @param target        The character that will receive the attack.
     */
    void makeNormalAttack(ICharacter character, ICharacter target);

    /**
     * Gives the actual player name.
     * @return  The name of the Mastermind.
     */
    public String getName();

    public void deadCharacter();

    public PropertyChangeSupport getDeadCharacter();
}
