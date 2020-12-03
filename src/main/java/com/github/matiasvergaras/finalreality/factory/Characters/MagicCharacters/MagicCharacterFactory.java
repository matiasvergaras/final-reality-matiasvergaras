package com.github.matiasvergaras.finalreality.factory.Characters.MagicCharacters;

import com.github.matiasvergaras.finalreality.factory.Characters.CharacterFactory;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;

import java.util.concurrent.LinkedBlockingQueue;

public abstract class MagicCharacterFactory extends CharacterFactory implements IMagicCharacterFactory{
    protected int mana;

    public MagicCharacterFactory(LinkedBlockingQueue<ICharacter> turns, int hp, int dp, int mana){
        super(turns, hp, dp);
        this.mana = mana;
    }

    public void setMana(int mana){
        this.mana = mana;
    }

}
