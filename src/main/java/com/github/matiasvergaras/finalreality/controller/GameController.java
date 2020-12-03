package com.github.matiasvergaras.finalreality.controller;

import com.github.matiasvergaras.finalreality.factory.Characters.CPUCharacters.EnemyFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.CPUCharacters.ICPUCharacterFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.ICharacterFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.MagicCharacters.BlackMageFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.MagicCharacters.IMagicCharacterFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.MagicCharacters.WhiteMageFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.NormalCharacters.EngineerFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.NormalCharacters.KnightFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.NormalCharacters.ThiefFactory;
import com.github.matiasvergaras.finalreality.factory.Weapons.*;
import com.github.matiasvergaras.finalreality.model.CPUPlayer;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.UserPlayer;
import com.github.matiasvergaras.finalreality.model.character.NullCharacter;
import com.github.matiasvergaras.finalreality.model.character.cpu.ICPUCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.NullWeapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Game Controller.
 * The controller will be an intermediary between the user and the objects of the model,
 * whose purpose will be to control all the messages that pass through it, manipulating
 * and redirecting those that are necessary.
 * <p> User will communicate with Controller, and Controller will do with
 * userPlayer/cpuPlayer. </p>
 * @since Homework 2
 * @author Matías Vergara Silva
 */
public class GameController {
    private static GameController uniqueInstance;
    private LinkedBlockingQueue<ICharacter> turns = new LinkedBlockingQueue<ICharacter>();
    private AxeFactory axeFactory = new AxeFactory();
    private BowFactory bowFactory = new BowFactory();
    private KnifeFactory knifeFactory = new KnifeFactory();
    private StaffFactory staffFactory = new StaffFactory();
    private SwordFactory swordFactory = new SwordFactory();
    private EngineerFactory engineerFactory = new EngineerFactory(turns);
    private KnightFactory knightFactory = new KnightFactory(turns);
    private ThiefFactory thiefFactory = new ThiefFactory(turns);
    private BlackMageFactory blackMageFactory = new BlackMageFactory(turns);
    private WhiteMageFactory whiteMageFactory = new WhiteMageFactory(turns);
    private EnemyFactory enemyFactory = new EnemyFactory(turns);
    private List<IWeaponFactory> weaponFactories = new ArrayList<>();
    private List<ICharacterFactory> characterFactories = new ArrayList<>();
    private List<ICPUCharacterFactory> cpuCharacterFactories = new ArrayList<>();
    private List<IMagicCharacterFactory> magicCharacterFactories = new ArrayList<>();
    private UserPlayer userPlayer;
    private CPUPlayer cpuPlayer;
    private ICharacter selectedCharacter = new NullCharacter(turns);
    private IWeapon selectedWeapon = new NullWeapon();

    public GameController(int charactersQuantity){
        this.userPlayer = new UserPlayer(charactersQuantity);

        weaponFactories.add(axeFactory);
        weaponFactories.add(bowFactory);
        weaponFactories.add(knifeFactory);
        weaponFactories.add(staffFactory);
        weaponFactories.add(swordFactory);

        characterFactories.add(engineerFactory);
        characterFactories.add(thiefFactory);
        characterFactories.add(knightFactory);
        characterFactories.add(blackMageFactory);
        characterFactories.add(whiteMageFactory);
        characterFactories.add(enemyFactory);

        cpuCharacterFactories.add(enemyFactory);

        magicCharacterFactories.add(whiteMageFactory);
        magicCharacterFactories.add(blackMageFactory);

    }

    public static GameController uniqueInstance(int charactersQuantity){
        if(uniqueInstance == null){
            uniqueInstance = new GameController(charactersQuantity);
        }
        return uniqueInstance;
    }

    public void addBlackMageToPlayerParty(String name){
        userPlayer.addToParty(blackMageFactory.create(name));
    }

    public void addWhiteMageToPlayerParty(String name){
        userPlayer.addToParty(whiteMageFactory.create(name));
    }

    public void addEngineerToPlayerParty(String name){
        userPlayer.addToParty(engineerFactory.create(name));
    }

    public void addThiefToPlayerParty(String name){
        userPlayer.addToParty(thiefFactory.create(name));
    }

    public void addKnightToPlayerParty(String name){
        userPlayer.addToParty(knightFactory.create(name));
    }

    public void addEnemyToCPUParty(String name){
        cpuPlayer.addToParty(enemyFactory.create(name));
    }

    public void addBowToInventory(){
        userPlayer.addToInventory(bowFactory.create());
    }

    public void addBowToInventory(String name){
        userPlayer.addToInventory(bowFactory.create(name));
    }

    public void addSwordToInventory(){
        userPlayer.addToInventory(swordFactory.create());
    }

    public void addSwordToInventory(String name){
        userPlayer.addToInventory(swordFactory.create(name));
    }

    public void addAxeToInventory(){
        userPlayer.addToInventory(axeFactory.create());
    }

    public void addAxeToInventory(String name){
        userPlayer.addToInventory(axeFactory.create(name));
    }

    public void addStaffToInventory(){
        userPlayer.addToInventory(staffFactory.create());
    }

    public void addStaffToInventory(String name){
        userPlayer.addToInventory(staffFactory.create(name));
    }

    public void addKnifeToInventory(){
        userPlayer.addToInventory(knifeFactory.create());
    }

    public void addKnifeToInventory(String name){
        userPlayer.addToInventory(knifeFactory.create(name));
    }

    public void setSelectedWeapon(int index){
        if(index < userPlayer.getInventorySize()) {
            this.selectedWeapon = userPlayer.getInventory().get(index);
        }
    }

    public void setSelectedCharacter(int index){
        if(index < userPlayer.getPartySize()){
            this.selectedCharacter = userPlayer.getParty().get(index);
        }
    }

    public void equipSelectedWeaponToSelectedCharacter(){
        if(userPlayer.getParty().contains(selectedCharacter)){
            IPlayerCharacter character = (IPlayerCharacter)this.selectedCharacter;
            character.equipWeapon(selectedWeapon);
        }
    }

    public void unequipSelectedCharacter(){
        if (userPlayer.getParty().contains(selectedCharacter)) {
            userPlayer.unequipCharacter((IPlayerCharacter)selectedCharacter);
        }
    }

    public void removeSelectedCharacterFromItsParty(){
        if(userPlayer.getParty().contains(selectedCharacter)){
            userPlayer.removeFromParty((IPlayerCharacter)selectedCharacter);
        }
        if(cpuPlayer.getParty().contains(selectedCharacter)){
            cpuPlayer.removeFromParty((ICPUCharacter)selectedCharacter);
        }
    }

    public void removeSelectedWeaponFromInventory(){
        userPlayer.removeFromInventory(selectedWeapon);
        selectedWeapon = new NullWeapon();
    }

    public void selectedCharacterNormalAttack(ICharacter target){
        if(userPlayer.getParty().contains(selectedCharacter) & cpuPlayer.getParty().contains(target)){
            IPlayerCharacter character = (IPlayerCharacter)selectedCharacter;
            ICPUCharacter cpuCharacter = (ICPUCharacter)target;
            character.normalAttack(cpuCharacter);
        }
        if(cpuPlayer.getParty().contains(selectedCharacter) & userPlayer.getParty().contains(target)){
            IPlayerCharacter character = (IPlayerCharacter)target;
            ICPUCharacter cpuCharacter = (ICPUCharacter)selectedCharacter;
            cpuCharacter.normalAttack(character);
        }
    }

    Map<String, Object> getSelectedCharacterAttributes(){
        return selectedCharacter.getAttributes();
    }

    String getSelectedCharacterName(){
        return (String)selectedCharacter.getAttributes().get("name");
    }

    int getSelectedCharacterCurrentHP(){
        return (Integer)selectedCharacter.getAttributes().get("currentHP");
    }

    int getSelectedCharacterMaxHP(){
        return (Integer)selectedCharacter.getAttributes().get("maxHP");
    }

    int getSelectedCharacterDP(){
        return (Integer)selectedCharacter.getAttributes().get("DP");
    }

    IWeapon getSelectedCharacterCurrentWeapon(){
        return (IWeapon)selectedCharacter.getAttributes().get("equippedWeapon");
    }

    int getSelectedCharacterCurrentMana(){
        return (int)selectedCharacter.getAttributes().get("currentMana");
    }

    int getSelectedCharacterMaxMana(){
        return (int)selectedCharacter.getAttributes().get("maxMana");
    }

    int getSelectedCharacterWeight(){
        return (int)selectedCharacter.getAttributes().get("weight");
    }

    int getSelectedCharacterPower(){
        return (int)selectedCharacter.getAttributes().get("Power");
    }


    public void setDefaultWeaponWeight(int weight){
        for(IWeaponFactory factory: weaponFactories){
            factory.setWeight(weight);
        }
    }

    public void setDefaultWeaponPower(int power){
        for(IWeaponFactory factory: weaponFactories){
            factory.setPower(power);
        }
    }

    public void setDefaultWeaponMagicPower(int magicPower){
        for(IWeaponFactory factory: weaponFactories){
            factory.setMagicPower(magicPower);
        }
    }

    public void setDefaultCharactersHP(int hp){
        for(ICharacterFactory factory: characterFactories){
            factory.setHP(hp);
        }
    }

    public void setDefaultCharactersDP(int dp){
        for(ICharacterFactory factory: characterFactories){
            factory.setHP(dp);
        }
    }

    public void setDefaultCharactersMana(int mana){
        for(IMagicCharacterFactory factory: magicCharacterFactories){
            factory.setMana(mana);
        }
    }

    public void setDefaultEnemiesWeight(int weight){
        for(ICPUCharacterFactory factory: cpuCharacterFactories){
            factory.setWeight(weight);
        }
    }

    public void setDefaultEnemiesPower(int power){
        for(ICPUCharacterFactory factory: cpuCharacterFactories){
            factory.setWeight(power);
        }
    }




}

