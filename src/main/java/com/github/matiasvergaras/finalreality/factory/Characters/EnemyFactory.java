package com.github.matiasvergaras.finalreality.factory.Characters;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.cpu.Enemy;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * EnemyFactory.
 * <p> It produces units of Enemy, using the constructor of its parent class CharacterFactory, following
 * the FactoryPattern. </p>
 * @since Homework 2
 * @author Matias Vergara Silva
 */
public class EnemyFactory extends CharacterFactory {

    /**
     * Constructor for a new EnemyFactory.
     * @param turns     The LinkedBlockingQueue for the turns where the new characters created by this factory will be
     *                  added.
     * @param hp        The default Heal Points that the characters created by this factory will have.
     * @param dp        The default Defense Points that the characters created by this factory will have.
     * @param weight    The default Weight that the characters created by this factory  will have.
     * @param power     The default Power Points that the characters created by this factory  will have.
     */
    public EnemyFactory(LinkedBlockingQueue<ICharacter> turns, int hp, int dp, int weight, int power){
        super(turns, hp, dp);
        this.weight = weight;
        this.power = power;

    }

    /**
     * {@inheritDoc}
     * <p> We have decided to keep the parameter for the new character's name, since we want to have only
     * differentiable characters, and the name is essential for that purpose. </p>
     * @param name  The name of the new Enemy.
     * @return      A new Enemy with the default values and the given name.
     */
    public Enemy create(String name){
        return new Enemy(turns, name, weight, hp, dp, power);
    }

}
