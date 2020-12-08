package com.github.matiasvergaras.finalreality.factory.Characters;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.magic.BlackMage;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * BlackMageFactory.
 * <p> It produces units of BlackMage, using the constructor of its parent class CharacterFactory, following
 * the FactoryPattern. </p>
 * @since Homework 2
 * @author Matias Vergara Silva
 */
public class BlackMageFactory extends CharacterFactory {

    /**
     * Constructor for a new BlackMageFactory.
     * @param turns     The linkedBlockingQueue for the turns where the new characters will be added.
     * @param hp        The default Heal Points that the characters will have.
     * @param dp        The default Defense Points that the characters will have.
     * @param mana      The default Mana Points that the characters will have.
     */
    public BlackMageFactory(LinkedBlockingQueue<ICharacter> turns, int hp, int dp, int mana){
        super(turns, hp, dp);
        this.mana = mana;
    }

    /**
     * {@inheritDoc}
     * <p> We have decided to keep the parameter for the new character's name, since we want to have only
     * differentiable player characters, and the name is essential for that purpose. </p>
     * @param name  The name of the new BlackMage.
     * @return      A new BlackMage with the default values and the given name.
     */
    public BlackMage create(String name){
        return new BlackMage(turns, name, hp, dp, mana);
    }

}