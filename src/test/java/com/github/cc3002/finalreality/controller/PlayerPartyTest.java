package com.github.cc3002.finalreality.controller;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.cpu.Enemy;
import com.github.matiasvergaras.finalreality.model.character.player.magic.BlackMage;
import com.github.matiasvergaras.finalreality.model.character.player.magic.WhiteMage;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Engineer;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Knight;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to test the Controller methods to add characters to the playerParty
 * @since Homework 2
 * @author Matías Vergara Silva
 */
public class PlayerPartyTest {
    private GameController gc;
    private LinkedBlockingQueue<ICharacter> turns;

    /**
     * Basic set-up: a turns queue and a Controller.
     */
    @BeforeEach
    void setUp(){
        turns = new LinkedBlockingQueue<>();
        gc = new GameController("Max", "Waral",9);
    }

    /**
     * Test the addBlackMage method.
     * <p> Adds a new blackMage to the player party and checks that it is correctly added
     * by checking the equality between a copy of the created character and the last character
     * added to the party, and by checking that the size of the party increased by 1. </p>
     */
    @Test
    void addBlackMageTest(){
        int initSize = gc.getPlayerPartySize();
        gc.setSelectedCharacterFactory(1);
        gc.selectedCharacterFactoryProduce("Alef");
        gc.setSelectedCharacterFromPlayerParty(gc.getPlayerPartySize()-1);
        BlackMage sameCharacter = new BlackMage(turns, "Alef", 120, 40, 200);
        assertEquals(gc.getSelectedCharacterName(), "Alef");
        assertEquals(gc.getSelectedCharacterAttributes(),sameCharacter.getAttributes());
        assertEquals(gc.getSelectedCharacter(), sameCharacter);
        assertEquals(initSize+1, gc.getPlayerPartySize());
    }

    /**
     * Test the addWhiteMage method.
     * <p> Adds a new whiteMage to the player party and checks that it is correctly added
     * by checking the equality between a copy of the created character and the last character
     * added to the party, and by checking that the size of the party increased by 1. </p>
     */
    @Test
    void addWhiteMageTest(){
        int initSize = gc.getPlayerPartySize();
        gc.setSelectedCharacterFactory(2);
        gc.selectedCharacterFactoryProduce("Tao");
        gc.setSelectedCharacterFromPlayerParty(gc.getPlayerPartySize()-1);
        WhiteMage sameCharacter = new WhiteMage(turns, "Tao", 120, 40, 200);
        assertEquals(gc.getSelectedCharacter(), sameCharacter);
        assertEquals(initSize+1, gc.getPlayerPartySize());

    }

    /**
     * Test the addEngineer method.
     * <p> Adds a new Engineer to the player party and checks that it is correctly added
     * by checking the equality between a copy of the created character and the last character
     * added to the party, and by checking that the size of the party increased by 1. </p>
     */
    @Test
    void addEngineerTest(){
        int initSize = gc.getPlayerPartySize();
        gc.setSelectedCharacterFactory(0);
        gc.selectedCharacterFactoryProduce("Vankar");
        gc.setSelectedCharacterFromPlayerParty(gc.getPlayerPartySize()-1);
        Engineer sameCharacter = new Engineer(turns, "Vankar", 100, 100);
        assertEquals(gc.getSelectedCharacter(), sameCharacter);
        assertEquals(initSize+1, gc.getPlayerPartySize());

    }

    /**
     * Test the addKnight method.
     * <p> Adds a new Knight to the player party and checks that it is correctly added
     * by checking the equality between a copy of the created character and the last character
     * added to the party, and by checking that the size of the party increased by 1. </p>
     */
    @Test
    void addKnightTest(){
        int initSize = gc.getPlayerPartySize();
        gc.setSelectedCharacterFactory(4);
        gc.selectedCharacterFactoryProduce("Gort");
        gc.setSelectedCharacterFromPlayerParty(gc.getPlayerPartySize()-1);
        Knight sameCharacter = new Knight(turns, "Gort", 200, 12);
        assertEquals(gc.getSelectedCharacter(), sameCharacter);
        assertEquals(initSize+1, gc.getPlayerPartySize());

    }

    /**
     * Test the addThief method.
     * <p> Adds a new Thief to the player party and checks that it is correctly added
     * by checking the equality between a copy of the created character and the last character
     * added to the party, and by checking that the size of the party increased by 1. </p>
     */
    @Test
    void addThiefTest(){
        int initSize = gc.getPlayerPartySize();
        gc.setSelectedCharacterFactory(3);
        gc.selectedCharacterFactoryProduce("Hanzou");
        gc.setSelectedCharacterFromPlayerParty(gc.getPlayerPartySize()-1);
        Thief sameCharacter = new Thief(turns, "Hanzou", 200, 12);
        assertEquals(gc.getSelectedCharacter(), sameCharacter);
        System.out.println(gc.getPlayerPartySize());
        assertEquals(initSize+1, gc.getPlayerPartySize());

    }

    /**
     * Test that when adding characters to the player's party, nothing has been accidentally
     * added to the cpu party.
     */
    @Test
    void testRivalPartySize(){
        assertEquals(gc.getCPUPartySize(), 0);
    }

    /**
     * Test that the getPlayerParty method works properly.
     * <p> It add some characters to the playerParty and to a copyList, and then
     * it checks that the list are equals. </p>
     */
    @Test
    void getPlayerPartyTest(){
        ArrayList<ICharacter> copy = new ArrayList<>();
        gc.setSelectedCharacterFactory(1);
        gc.selectedCharacterFactoryProduce("Azelf");
        gc.setSelectedCharacterFactory(2);
        gc.selectedCharacterFactoryProduce("Lowe");
        gc.setSelectedCharacterFactory(4);
        gc.selectedCharacterFactoryProduce("Gort");
        gc.setSelectedCharacterFactory(0);
        gc.selectedCharacterFactoryProduce("Balbaroy");
        gc.setSelectedCharacterFactory(3);
        gc.selectedCharacterFactoryProduce("Arthur");
        copy.add(new BlackMage(turns, "Azelf", 120, 40, 200));
        copy.add(new WhiteMage(turns, "Lowe", 120, 30, 200));
        copy.add(new Knight(turns, "Gort", 180, 100));
        copy.add(new Engineer(turns, "Balbaroy", 125, 70));
        copy.add(new Thief(turns, "Arthur", 90, 50));
        assertEquals(gc.getPlayerParty(), copy);
    }

    /**
     * Test that the getCPUParty method works properly.
     * <p> It add some characters to the CPUParty and a copy of them to a copyList, and then
     * it checks that the list are equals. </p>
     */
    @Test
    void getCPUPartyTest(){
        ArrayList<ICharacter> copy = new ArrayList<>();
        gc.addEnemyToCPU("Azelf");
        gc.addEnemyToCPU("Lowe");
        gc.addEnemyToCPU("Gort");
        copy.add(new Enemy(turns, "Azelf", 180, 100, 12, 100));
        copy.add(new Enemy(turns, "Lowe", 180, 100, 12, 100));
        copy.add(new Enemy(turns, "Gort", 180, 100, 12, 100));
        assertEquals(gc.getCPUParty(), copy);
    }




}
