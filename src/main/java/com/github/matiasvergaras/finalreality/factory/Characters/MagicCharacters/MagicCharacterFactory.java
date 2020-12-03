package com.github.matiasvergaras.finalreality.factory.Characters.MagicCharacters;

import com.github.matiasvergaras.finalreality.factory.Characters.CharacterFactory;

public abstract class MagicCharacterFactory extends CharacterFactory implements IMagicCharacterFactory{
    protected int mana;

    public void setMana(int mana){
        this.mana = mana;
    }

}
