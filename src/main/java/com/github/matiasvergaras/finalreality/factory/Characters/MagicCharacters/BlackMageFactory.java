package com.github.matiasvergaras.finalreality.factory.Characters.MagicCharacters;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.magic.BlackMage;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class BlackMageFactory extends MagicCharacterFactory {
    LinkedBlockingQueue<ICharacter> turns;

    public BlackMageFactory(LinkedBlockingQueue<ICharacter> turns){
        this.turns = turns;
    }

    public BlackMage create(String name){
        return new BlackMage(turns, name, hp, dp, mana);
    }

}