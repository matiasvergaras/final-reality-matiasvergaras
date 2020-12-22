package com.github.matiasvergaras.finalreality.model.Mastermind;

import com.github.matiasvergaras.finalreality.controller.handlers.DeathCharacterToMMHandler;
import com.github.matiasvergaras.finalreality.controller.handlers.EndTurnCharacterToMMHandler;
import com.github.matiasvergaras.finalreality.controller.handlers.AddQueueCharacterToMMHandler;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * Class to hold the common behavior of both CPUMastermind and PlayerMastermind.
 * @since Homework 2
 * @author Matías Vergara Silva.
 */
public abstract class AbstractMastermind implements  IMastermind{
    protected ArrayList<ICharacter> party;
    protected String name;
    protected PropertyChangeSupport deadCharacter = new PropertyChangeSupport(this);
    protected PropertyChangeSupport endTurn = new PropertyChangeSupport(this);
    protected PropertyChangeSupport addQueue = new PropertyChangeSupport(this);
    protected DeathCharacterToMMHandler deadCharacterHandler = new DeathCharacterToMMHandler(this);
    protected EndTurnCharacterToMMHandler endTurnHandler = new EndTurnCharacterToMMHandler(this);
    protected AddQueueCharacterToMMHandler addQueueHandler = new AddQueueCharacterToMMHandler(this);
    protected int aliveCharacters;

    /**
     * Basic constructor for a Mastermind
     */
    public AbstractMastermind(String name){
        this.name = name;
        this.party = new ArrayList<>();
    }

    /**
     * Adds a character to the Mastermind's Party and calls to
     * his waitTurn method.
     * @param character     The ICharacter character to be added.
     */
    @Override
    public void addToParty(ICharacter character){
        this.aliveCharacters++;
        this.getParty().add(character);
        character.getDeadCharacter().addPropertyChangeListener(deadCharacterHandler);
        character.getEndTurn().addPropertyChangeListener(endTurnHandler);
        character.getAddQueue().addPropertyChangeListener(addQueueHandler);

    }

    /**
     * {@inheritDoc}
     * @return  The number of alive characters of this mastermind.
     */
    @Override
    public int getAliveCharacters(){
        return this.aliveCharacters;
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

    /**
     *{@inheritDoc}
     * @return  The name of the PlayerMastermind.
     */
    @Override
    public String getName(){
        return name;
    }

    /**
     * Alerts the gameController that a character died.
     */
    public void deadCharacter(ICharacter character){
        this.aliveCharacters--;
        deadCharacter.firePropertyChange(new PropertyChangeEvent(character, "aliveCharacters Changed",
                aliveCharacters+1, aliveCharacters));
    }


    /**
     * {@inheritDoc}
     */
    public void endTurn(){
        endTurn.firePropertyChange(new PropertyChangeEvent( this, "Active character ended his turn",
                "Active", "Inactive"));
    }


    /**
     *
     * @return propertyChangeSupport of Character's death, in order to be able to use it outside of this class.
     */
    public PropertyChangeSupport getDeadCharacter(){
        return deadCharacter;
    }



    /**
     *
     * @return propertyChangeSupport of Character's death, in order to be able to use it outside of this class.
     */
    public PropertyChangeSupport getEndTurn(){
        return endTurn;
    }


    /**
     *
     * @return propertyChangeSupport of Character added to queue,
     * in order to be able to use it outside of this class.
     */
    public PropertyChangeSupport getAddQueue() { return addQueue;}

    /**
     * {@inheritDoc}
     */
    public void characterAddedToQueue(){
        addQueue.firePropertyChange(new PropertyChangeEvent(this, "Character added",
                "Out of queue", "In queue"));
    }
}
