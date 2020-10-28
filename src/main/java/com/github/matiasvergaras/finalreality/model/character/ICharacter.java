package com.github.matiasvergaras.finalreality.model.character;

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


}





