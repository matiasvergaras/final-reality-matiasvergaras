package com.github.matiasvergaras.finalreality.factory.Characters.NormalCharacters;

import com.github.matiasvergaras.finalreality.factory.Characters.CharacterFactory;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Engineer;

import java.util.concurrent.LinkedBlockingQueue;

public class EngineerFactory extends CharacterFactory {

    public EngineerFactory(LinkedBlockingQueue<ICharacter> turns, int hp, int dp){
        super(turns, hp, dp);
    }

    public Engineer create(String name){
        return new Engineer(turns, name, hp, dp);
    }

}
