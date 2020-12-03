package com.github.matiasvergaras.finalreality.factory.Characters;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.magic.BlackMage;

import java.util.concurrent.LinkedBlockingQueue;

public class BlackMageFactory extends CharacterFactory {

    public BlackMageFactory(LinkedBlockingQueue<ICharacter> turns, int hp, int dp, int mana){
        super(turns, hp, dp);
        this.mana = mana;
    }

    public BlackMage create(String name){
        return new BlackMage(turns, name, hp, dp, mana);
    }

}