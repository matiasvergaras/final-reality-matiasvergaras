package com.github.matiasvergaras.finalreality.factory.Characters;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Thief;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * ThiefFactory.
 * <p> It produces units of Thief, using the constructor of its parent class CharacterFactory, following
 * the FactoryPattern. </p>
 * @since Homework 2
 * @author Matias Vergara Silva
 */
public class ThiefFactory extends CharacterFactory {

    /**
     * Constructor for a new ThiefFactory.
     * @param turns     The LinkedBlockingQueue for the turns where the new characters created by this factory will be
     *                  added.
     * @param hp        The default Heal Points that the characters created by this factory will have.
     * @param dp        The default Defense Points that the characters created by this factory will have.
     */
    public ThiefFactory(LinkedBlockingQueue<ICharacter> turns, int hp, int dp){
        super(turns, hp, dp);
    }

    /**
     * {@inheritDoc}
     * <p> We have decided to keep the parameter for the new character's name, since we want to have only
     * differentiable characters, and the name is essential for that purpose. </p>
     * @param name  The name of the new Thief.
     * @return      A new Thief with the default values and the given name.
     */
    public Thief create(String name){
        return new Thief(turns, name, hp, dp);
    }

}