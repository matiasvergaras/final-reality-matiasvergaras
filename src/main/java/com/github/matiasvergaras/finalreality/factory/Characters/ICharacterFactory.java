package com.github.matiasvergaras.finalreality.factory.Characters;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;

public interface ICharacterFactory {
    void setHP(int hp);

    void setDP(int dp);

    ICharacter create(String name);
}
