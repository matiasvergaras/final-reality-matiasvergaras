package com.github.matiasvergaras.finalreality.model;

import com.github.matiasvergaras.finalreality.model.character.cpu.ICPUCharacter;


import java.util.ArrayList;

public class CPUPlayer {
    private ArrayList<ICPUCharacter> party = new ArrayList<>();

    public CPUPlayer(){

    }

    public ArrayList<ICPUCharacter> getParty(){
        return party;
    }

    public int getPartySize(){
        return party.size();
    }

    public void addToParty(ICPUCharacter character){
        this.party.add(character);
    }

    public void removeFromParty(ICPUCharacter character){
        this.party.remove(character);
    }
}
