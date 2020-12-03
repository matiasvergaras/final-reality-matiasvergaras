package com.github.matiasvergaras.finalreality.factory.Characters.NormalCharacters;

import com.github.matiasvergaras.finalreality.factory.Characters.CharacterFactory;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Knight;

import java.util.concurrent.LinkedBlockingQueue;

public class KnightFactory extends CharacterFactory {
    LinkedBlockingQueue<ICharacter> turns;

    public KnightFactory(LinkedBlockingQueue<ICharacter> turns){
        this.turns = turns;
    }

    public Knight create(String name){
        return new Knight(turns, name, hp, dp);
    }

}
