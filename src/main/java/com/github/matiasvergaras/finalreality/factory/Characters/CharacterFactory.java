package com.github.matiasvergaras.finalreality.factory.Characters;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;

import java.util.concurrent.LinkedBlockingQueue;

public abstract class CharacterFactory implements ICharacterFactory{
    protected LinkedBlockingQueue<ICharacter> turns;
    protected int hp;
    protected int dp;

    public CharacterFactory(LinkedBlockingQueue<ICharacter> turns, int hp, int dp){
        this.turns = turns;
        this.hp = hp;
        this.dp = dp;
    }
    public void setHP(int hp){
        this.hp = hp;
    }

    public void setDP(int dp){
        this.dp = dp;
    }

}
