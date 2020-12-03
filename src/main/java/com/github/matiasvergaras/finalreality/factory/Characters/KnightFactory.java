package com.github.matiasvergaras.finalreality.factory.Characters;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Knight;

import java.util.concurrent.LinkedBlockingQueue;

public class KnightFactory extends CharacterFactory {

    public KnightFactory(LinkedBlockingQueue<ICharacter> turns, int hp, int dp){
        super(turns, hp, dp);
    }

    public Knight create(String name){
        return new Knight(turns, name, hp, dp);
    }

}
