package com.github.matiasvergaras.finalreality.model.character;

import com.github.matiasvergaras.finalreality.model.AttributeSet.CharacterAttributeSet;

import java.beans.PropertyChangeSupport;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 * @since Homework 1
 * @author Ignacio Slater Muñoz.
 * @author Matías Vergara Silva.
 */
public interface ICharacter {

    /**
     * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
     * seconds before adding the character to the queue.
     */
    void waitTurn();

    /**
     * {@inheritDoc}
     * @param character         The character that will receive the attack.
     */
    void normalAttack(ICharacter character);

    /**
     * Receive a non-magic attack
     *
     * @param character         The ICharacter that is performing the attack.
     */
    void receiveNormalAttack(ICharacter character);

    /**
     * Returns the attack power of this unit calculated based on its type:
     * <p> With the power for the cpuCharacters </p>
     * <p> With the weapon for the playerCharacters </p>
     */
    int getAttackPower();

    /**
     * A method to get the name of this character.
     *
     * @return this character's name.
     */
    String getName();

    /**
     * A method to get the current HP of this character.
     *
     * @return this character's current HP.
     */
    int getCurrentHP();

    /**
     * A method to get the max HP of this character.
     *
     * @return this character's max HP.
     */
    int getMaxHP();

    /**
     * A method to get the Defense Points of this character.
     *
     * @return this character's max DP.
     */
    int getDP();

    /**
     * Evaluation of the statement "This character is alive".
     *
     * @return Boolean True if character is alive, False otherwise.
     */
    boolean isAlive();

    /**
     * modify the HP of this character.
     *
     * @param diff a Integer to rest to the Character HP.
     */
    void reduceHP(double diff);

    /**
     * To get all the attributes of this character together.
     * @return An ArrayList of Integer whit the attributes in the following
     * order: maxHP, currentHP, DP, maxMana (if applicable), currentMana (if applicable)
     */
    CharacterAttributeSet getAttributes();

    /**
     * Gives the PropertyChangeSupport of Character's death,
     * in order to be able to assign listeners to it outside of this class.
     * @return propertyChangeSupport deadCharacter.
     */
    public PropertyChangeSupport getDeadCharacter();


    /**
     * Gives the PropertyChangeSupport of the end of character's turn,
     * in order to be able to assign listeners to it outside of this class.
     * @return propertyChangeSupport endTurn
     */
    public PropertyChangeSupport getEndTurn();


    /**
     * Gives the PropertyChangeSupport of the add of a character to the turns queue,
     * in order to be able to assign listeners to it outside of this class.
     * @return propertyChangeSupport addQueue
     */
    public PropertyChangeSupport getAddQueue();

    /**
     * Returns true if this character is magic (i.e. a black mage or white mage).
     * @return  boolean isMagic
     */
    boolean isMagic();

    /**
     * Indicates if this unit is a BlackMage
     * @return  True if this unit is a BlackMage.
     */
    boolean isBlackMage();

    /**
     * Indicates if this unit is a WhiteMage
     * @return  True if this unit is a WhiteMage.
     */
    boolean isWhiteMage();

    /**
     * Indicates if this unit is an Engineer.
     * @return  True if this unit is an Engineer.
     */
    boolean isEngineer();

    /**
     * Indicates if this unit is a Knight.
     * @return  True if this unit is a Knight.
     */
    boolean isKnight();

    /**
     * Indicates if this unit is a Thief
     * @return  True if this unit is a Thief.
     */
    boolean isThief();

    /**
     * Indicates if this unit is an Enemy
     * @return  True if this unit is a Enemy.
     */
    boolean isEnemy();
}





