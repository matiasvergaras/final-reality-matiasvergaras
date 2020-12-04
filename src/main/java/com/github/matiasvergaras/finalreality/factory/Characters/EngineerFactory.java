package com.github.matiasvergaras.finalreality.factory.Characters;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Engineer;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * EngineerFactory.
 * <p> It produces units of Engineer, using the constructor of its parent class CharacterFactory, following
 * the FactoryPattern. </p>
 * @since Homework 2
 * @author Matias Vergara Silva
 */
public class EngineerFactory extends CharacterFactory {

    /**
     * Constructor for a new EngineerFactory.
     * @param turns     The LinkedBlockingQueue for the turns where the new characters created by this factory will be
     *                  added.
     * @param hp        The default Heal Points that the characters created by this factory will have.
     * @param dp        The default Defense Points that the characters created by this factory will have.
     */
    public EngineerFactory(LinkedBlockingQueue<ICharacter> turns, int hp, int dp){
        super(turns, hp, dp);
    }

    /**
     * {@inheritDoc}
     * <p> We have decided to keep the parameter for the new character's name, since we want to have only
     * differentiable characters, and the name is essential for that purpose. </p>
     * @param name  The name of the new Engineer.
     * @return      A new Enemy with the default values and the given name.
     */
    public Engineer create(String name){
        return new Engineer(turns, name, hp, dp);
    }

}
