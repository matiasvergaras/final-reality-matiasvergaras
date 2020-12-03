package com.github.matiasvergaras.finalreality.factory.Characters;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;

public interface ICharacterFactory {

    void setHP(int hp);

    void setDP(int dp);

    void setMana(int mana);

    void setWeight(int weight);

    void setPower(int power);

    ICharacter create(String name);
}
