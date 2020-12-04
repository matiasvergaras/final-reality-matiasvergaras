package com.github.matiasvergaras.finalreality.model.Mastermind;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;

public interface IMastermind {
    void addPlayerCharacter(ICharacter character);

    void addCPUCharacter(ICharacter character);


}
