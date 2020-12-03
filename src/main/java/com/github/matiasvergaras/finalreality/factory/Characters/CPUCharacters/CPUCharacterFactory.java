package com.github.matiasvergaras.finalreality.factory.Characters.CPUCharacters;

import com.github.matiasvergaras.finalreality.factory.Characters.CharacterFactory;

public abstract class CPUCharacterFactory extends CharacterFactory implements ICPUCharacterFactory{
    protected int weight = 10;
    protected int power = 100;

    public void setWeight(int weight){
        this.weight = weight;
    }

    public void setPower(int power){
        this.power = power;
    }
}
