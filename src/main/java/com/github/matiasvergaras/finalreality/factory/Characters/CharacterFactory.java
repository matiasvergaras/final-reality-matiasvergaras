package com.github.matiasvergaras.finalreality.factory.Characters;

public abstract class CharacterFactory implements ICharacterFactory{
    protected int hp = 120;
    protected int dp = 60;

    public void setHP(int hp){
        this.hp = hp;
    }

    public void setDP(int dp){
        this.dp = dp;
    }

}
