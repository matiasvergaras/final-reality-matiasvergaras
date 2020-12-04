package com.github.matiasvergaras.finalreality.factory.Characters;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Knight;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * KnightFactory.
 * <p> It produces units of Knight, using the constructor of its parent class CharacterFactory, following
 * the FactoryPattern. </p>
 * @since Homework 2
 * @author Matias Vergara Silva
 */
public class KnightFactory extends CharacterFactory {

    /**
     * Constructor for a new KnightFactory.
     * @param turns     The LinkedBlockingQueue for the turns where the new characters created by this factory will be
     *                  added.
     * @param hp        The default Heal Points that the characters created by this factory will have.
     * @param dp        The default Defense Points that the characters created by this factory will have.
     */
    public KnightFactory(LinkedBlockingQueue<ICharacter> turns, int hp, int dp){
        super(turns, hp, dp);
    }

    /**
     * {@inheritDoc}
     * <p> We have decided to keep the parameter for the new character's name, since we want to have only
     * differentiable characters, and the name is essential for that purpose. </p>
     * @param name  The name of the new Knight.
     * @return      A new Knight with the default values and the given name.
     */
    public Knight create(String name){
        return new Knight(turns, name, hp, dp);
    }

}
