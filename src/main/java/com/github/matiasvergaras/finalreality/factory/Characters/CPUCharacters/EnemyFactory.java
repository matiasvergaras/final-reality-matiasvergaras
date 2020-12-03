package com.github.matiasvergaras.finalreality.factory.Characters.CPUCharacters;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.cpu.Enemy;

import java.util.concurrent.LinkedBlockingQueue;

public class EnemyFactory extends CPUCharacterFactory {

    public EnemyFactory(LinkedBlockingQueue<ICharacter> turns, int hp, int dp, int weight, int power){
        super(turns, hp, dp, weight, power);
    }

    public Enemy create(String name){
        return new Enemy(turns, name, weight, hp, dp, power);
    }

}
