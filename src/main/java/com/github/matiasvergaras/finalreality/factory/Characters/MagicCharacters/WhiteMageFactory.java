package com.github.matiasvergaras.finalreality.factory.Characters.MagicCharacters;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.magic.WhiteMage;
import java.util.concurrent.LinkedBlockingQueue;

public class WhiteMageFactory extends MagicCharacterFactory {
    LinkedBlockingQueue<ICharacter> turns;

    public WhiteMageFactory(LinkedBlockingQueue<ICharacter> turns){
        this.turns = turns;
    }

    public WhiteMage create(String name){
        return new WhiteMage(turns, name, hp, dp, mana);
    }

}