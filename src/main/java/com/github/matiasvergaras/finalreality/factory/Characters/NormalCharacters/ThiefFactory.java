package com.github.matiasvergaras.finalreality.factory.Characters.NormalCharacters;

import com.github.matiasvergaras.finalreality.factory.Characters.CharacterFactory;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Thief;

import java.util.concurrent.LinkedBlockingQueue;

public class ThiefFactory extends CharacterFactory {
    LinkedBlockingQueue<ICharacter> turns;

    public ThiefFactory(LinkedBlockingQueue<ICharacter> turns){
        this.turns = turns;
    }

    public Thief create(String name){
        return new Thief(turns, name, hp, dp);
    }

}