package com.github.matiasvergaras.finalreality.factory.Characters.CPUCharacters;

import com.github.matiasvergaras.finalreality.factory.Characters.CharacterFactory;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;

import java.util.concurrent.LinkedBlockingQueue;

public abstract class CPUCharacterFactory extends CharacterFactory implements ICPUCharacterFactory{
    protected int weight;
    protected int power;

    public CPUCharacterFactory(LinkedBlockingQueue<ICharacter> turns, int hp, int dp, int weight, int power){
        super(turns, hp, dp);
        this.weight = weight;
        this.power = power;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }

    public void setPower(int power){
        this.power = power;
    }
}
