package com.github.matiasvergaras.finalreality.factory.Characters;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.magic.WhiteMage;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * WhiteMageFactory.
 * <p> It produces units of WhiteMage, using the constructor of its parent class CharacterFactory, following
 * the FactoryPattern. </p>
 * @since Homework 2
 * @author Matias Vergara Silva
 */
public class WhiteMageFactory extends CharacterFactory {

    /**
     * Constructor for a new WhiteMageFactory.
     * @param turns     The LinkedBlockingQueue for the turns where the new characters created by this factory will be
     *                  added.
     * @param hp        The default Heal Points that the characters created by this factory will have.
     * @param dp        The default Defense Points that the characters created by this factory will have.
     * @param mana      The default Mana Points that the characters created by this factory will have.
     */
    public WhiteMageFactory(LinkedBlockingQueue<ICharacter> turns, int hp, int dp, int mana){
        super(turns, hp, dp);
        this.mana = mana;
    }

    /**
     * {@inheritDoc}
     * <p> We have decided to keep the parameter for the new character's name, since we want to have only
     * differentiable characters, and the name is essential for that purpose. </p>
     * @param name  The name of the new WhiteMage.
     * @return      A new WhiteMage with the default values and the given name.
     */
    public WhiteMage create(String name){
        return new WhiteMage(turns, name, hp, dp, mana);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMagicFactory(){
        return true;
    }
}