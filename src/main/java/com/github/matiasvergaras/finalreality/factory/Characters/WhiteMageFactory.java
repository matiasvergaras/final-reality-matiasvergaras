package com.github.matiasvergaras.finalreality.factory.Characters;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.magic.WhiteMage;
import java.util.concurrent.LinkedBlockingQueue;

public class WhiteMageFactory extends CharacterFactory {

    public WhiteMageFactory(LinkedBlockingQueue<ICharacter> turns, int hp, int dp, int mana){
        super(turns, hp, dp);
        this.mana = mana;
    }

    public WhiteMage create(String name){
        return new WhiteMage(turns, name, hp, dp, mana);
    }

}