package com.github.matiasvergaras.finalreality.factory.Characters;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;

/**
 * Interface to represent a CharacterFactory class and all its child classes.
 * @since  Homework 2
 * @author Matías Vergara Silva
 */
public interface ICharacterFactory {

    /**
     * Set default Heal Points of this Factory.
     * @param hp    The new default Heal Points.
     */
    void setHP(int hp);

    /**
     * Set default Defense Points of this Factory.
     * @param dp    The new default Defense Points.
     */
    void setDP(int dp);

    /**
     * Set default Mana Points of this Factory.
     * @param mana    The new default Mana Points.
     */
    void setMana(int mana);

    /**
     * Set default Weight Points of this Factory.
     * @param weight    The new default Weight Points.
     */
    void setWeight(int weight);

    /**
     * Set default Power Points of this Factory.
     * @param power    The new default Power Points.
     */
    void setPower(int power);

    /**
     * Produces a new Character of this Factory.
     * @param name      The name of the Character to be created.
     * @return          A new Character, whose type depend of the factory that receives the message.
     */
    ICharacter create(String name);
}
