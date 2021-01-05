package com.github.cc3002.finalreality.controller;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to check that the death notifications between
 * Characters, Masterminds and Controller works well.
 * @author Matias Vergara Silva
 * @since Homework 2
 */
public class DeathNotificationSystemTest {
    GameController gc;
    LinkedBlockingQueue<ICharacter> turns;

    @BeforeEach
    void setUp() {
        gc = new GameController("Narsha",
                "Bustoke", 4);
    }

    /**
     * Test the case where the cpu wins.
     */
    @RepeatedTest(2)
    void cpuWinsTest() throws InterruptedException {
        //Create the player party an add a thief
        gc.setSelectedCharacterFactory(3);
        gc.selectedCharacterFactoryProduce("Lyle");
        //add an engineer
        gc.setSelectedCharacterFactory(0);
        gc.selectedCharacterFactoryProduce("Kokichi");
        //add a knight
        gc.setSelectedCharacterFactory(4);
        gc.selectedCharacterFactoryProduce("Gong");
        //add a blackmage
        gc.setSelectedCharacterFactory(1);
        gc.selectedCharacterFactoryProduce("Nova");
        //Overflow the party. Just to check again that it is working.
        //add a white mage
        gc.setSelectedCharacterFactory(3);
        gc.selectedCharacterFactoryProduce("Khris");
        //Sets the Enemy factory to create powerful characters
        assertEquals(gc.getCharactersQuantity(), gc.getPlayerPartySize());
        assertEquals(gc.getPlayerParty().get(gc.getPlayerPartySize()-1).getName(), "Nova");
        //To make sure that enemies will have only deadly attacks.
        gc.setSelectedCharacterFactory(5);
        gc.setSelectedCharacterFactoryPower(600);
        //To make sure that enemies will attack the first.
        gc.setSelectedCharacterFactoryWeight(1);
        gc.selectedCharacterFactoryProduce("Mishaela");
        gc.selectedCharacterFactoryProduce("Ramladu");
        gc.selectedCharacterFactoryProduce("Balbazak");
        gc.selectedCharacterFactoryProduce("Hindel");
        gc.selectedCharacterFactoryProduce("Pyro");
        assertNull(gc.getWinner());
        //Check that the aliveNumber of each masterminds corresponds with the number of
        //added characters.
        assertEquals(gc.getPlayerAliveNumber(), 4);
        assertEquals(gc.getCPUAliveNumber(), 5);
        //Starts the game
        gc.startGame();
        //Since enemies are the first to attack, and their attacks are automatic,
        // there should be no need to do anything else more than press ''ok'' as many times as
        // battle turns are needed to kill every player character (4 but in the last it is not necessary to press
        // anything).
        // We simulate it :
        Thread.sleep(400);

        assertTrue(gc.isShowingTurnResume());
        gc.endTurn();

        assertTrue(gc.isShowingTurnResume());
        gc.endTurn();

        assertTrue(gc.isShowingTurnResume());
        gc.endTurn();

        // Now the game should have finished and the winner should be the CPU.
        assertEquals(gc.getWinner().getName(), gc.getCPUName());
        Thread.sleep(800);
        assertTrue(gc.isFinished());
    }

    /**
     * Test the case where the player wins.
     */
    @RepeatedTest(5)
    void playerWinsTest() throws InterruptedException {
        //Create the player party
        gc.setSelectedCharacterFactory(3);
        gc.selectedCharacterFactoryProduce("Lyle");
        gc.setSelectedCharacterFactory(0);
        gc.selectedCharacterFactoryProduce("Kokichi");
        gc.selectedCharacterFactoryProduce("Bleu");
        gc.selectedCharacterFactoryProduce("Jogurt");
        //Sets the bow factory to create rapid and powerful bows
        gc.setSelectedWeaponFactory(0);
        gc.setSelectedWeaponFactoryPower(700);
        gc.setSelectedWeaponFactoryWeight(0);
        //Equips Lyle
        gc.selectedWeaponFactoryProduce("Bow of Lyle");
        gc.setSelectedCharacterFromPlayerParty(0);
        gc.setSelectedWeapon(0);
        gc.equipSelectedWeaponToSelectedCharacter();
        //Equips Kokichi
        gc.selectedWeaponFactoryProduce("Bow of Kokichi");
        gc.setSelectedCharacterFromPlayerParty(1);
        gc.setSelectedWeapon(1);
        gc.equipSelectedWeaponToSelectedCharacter();
        //Equips Bleu
        gc.selectedWeaponFactoryProduce("Bow of Bleu");
        gc.setSelectedCharacterFromPlayerParty(2);
        gc.setSelectedWeapon(2);
        gc.equipSelectedWeaponToSelectedCharacter();
        //Equips Jogurt
        gc.selectedWeaponFactoryProduce("Bow of Jogurt");
        gc.setSelectedCharacterFromPlayerParty(3);
        gc.setSelectedWeapon(3);
        gc.equipSelectedWeaponToSelectedCharacter();
        //Sets up enemy Team.
        //Config the factory to produce weighted characters, so we can be
        //sure that they wont play before the player characters.
        gc.setSelectedCharacterFactory(5);
        gc.setSelectedWeaponFactoryWeight(0);
        gc.selectedCharacterFactoryProduce("Elliot");
        gc.selectedCharacterFactoryProduce("Ramladu");
        //Start game
        gc.startGame();
        //Give some time to make sure that there will be characters in
        //the queue (as in ActiveCharacter variable).
        Thread.sleep(2000);
        gc.initAttackMove();
        //Kill Elliot
        gc.setSelectedCharacterFromCPUParty(0);
        gc.activeCharacterNormalAttackSelectedCharacter();
        //Check that game is waiting for user to press 'ok' and simulate it.
        assertTrue(gc.isShowingTurnResume());
        gc.endTurn();
        //Kill Ramladu
        gc.initAttackMove();
        gc.setSelectedCharacterFromCPUParty(1);
        gc.activeCharacterNormalAttackSelectedCharacter();
        //Now every enemy is dead so the game should have finished
        //Check for game finished status and winner
        Thread.sleep(300);
        assertTrue(gc.isFinished());
        assertEquals(gc.getWinner().getName(), gc.getPlayerName());
    }


 }
