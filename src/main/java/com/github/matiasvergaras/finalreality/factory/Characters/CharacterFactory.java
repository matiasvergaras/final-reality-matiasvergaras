package com.github.matiasvergaras.finalreality.factory.Characters;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Character Factory.
 * <p> An Abstract Character Factory that holds the common methods related to the production of every kind of character.
 * </p>
 * <p> To avoid overcomplicating the code at a stage when it is not necessary yet, all specific character factories will
 * inherit the setWeight, setPower and setMana methods from this class, even if their creations do not use them. This
 * will also allow us to avoid using downcast at the Controller. </p>
 * <p> Factories will allow us to avoid tight coupling between the controller and the concrete products. This way,
 * Controller will request characters from the factories, who will make the corresponding calls to the model
 * constructors. </p>
 * <p> We will use the Singleton Pattern with each concrete Factory, since there should not be more than 1 instance
 * of each of them at the same time (given the methods to configure them without need to create another one). </p>
 * @since Homework 2
 * @author Matías Vergara Silva
 */
public abstract class CharacterFactory implements ICharacterFactory{
    protected LinkedBlockingQueue<ICharacter> turns;
    protected int hp;
    protected int dp;
    protected int weight;
    protected int power;
    protected int mana;

    /**
     * Basic constructor for a character Factory, including the turns queue, HP and DP.
     * @param turns     The LinkedBlockingQueue with the characters ready to play.
     * @param hp        The HP that will be set as default for every character produced by this factory.
     * @param dp        The DP that will be set as default for every character produced by this factory.
     */
    public CharacterFactory(LinkedBlockingQueue<ICharacter> turns, int hp, int dp){
        this.turns = turns;
        this.hp = hp;
        this.dp = dp;
    }


    /**
     * Sets the HP default value to another one, given as parameter.
     * @param hp    New HP default value
     */
    public void setHP(int hp){
        this.hp = hp;
    }

    /**
     * Sets the DP default value to another one, given as parameter.
     * @param dp    New DP default value
     */
    public void setDP(int dp){
        this.dp = dp;
    }

    /**
     * Sets the weight default value to another one, given as parameter.
     * @param weight    New weight default value
     */
    public void setWeight(int weight){
        this.weight = weight;
    }

    /**
     * Sets the power default value to another one, given as parameter.
     * @param power    New power default value
     */
    public void setPower(int power){
        this.power = power;
    }

    /**
     * Sets the Mana default value to another one, given as parameter.
     * @param mana    New Mana default value
     */
    public void setMana(int mana){
        this.mana = mana;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public boolean isMagicFactory(){
        return false;
    }

}
