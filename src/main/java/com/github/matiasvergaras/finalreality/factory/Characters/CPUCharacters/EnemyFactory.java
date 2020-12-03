package com.github.matiasvergaras.finalreality.factory.Characters.CPUCharacters;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.cpu.Enemy;

import java.util.concurrent.LinkedBlockingQueue;

public class EnemyFactory extends CPUCharacterFactory {
    LinkedBlockingQueue<ICharacter> turns;

    public EnemyFactory(LinkedBlockingQueue<ICharacter> turns){
        this.turns = turns;
    }

    public Enemy create(String name){
        return new Enemy(turns, name, weight, hp, dp, power);
    }

}
