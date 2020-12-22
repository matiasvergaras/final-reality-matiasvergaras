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
    @Test
    void cpuWinsTest() throws InterruptedException {
        //Create the player party
        gc.addThiefToPlayer("Lyle");
        gc.addEngineerToPlayer("Kokichi");
        gc.addKnightToPlayer("Gong");
        gc.addBlackMageToPlayer("Nova");
        //Overflow the party. Just to check again that it is working.
        gc.addWhiteMageToPlayer("Khris");
        //Sets the Enemy factory to create powerful characters
        assertEquals(gc.getCharactersQuantity(), gc.getPlayerPartySize());
        assertEquals(gc.getPlayerParty().get(gc.getPlayerPartySize()-1).getName(), "Nova");
        //To make sure that enemies will have only deadly attacks.
        gc.setSelectedCharacterFactory(5);
        gc.setSelectedCharacterFactoryPower(600);
        //To make sure that enemies will attack the first.
        gc.setSelectedCharacterFactoryWeight(0);
        gc.addEnemyToCPU("Mishaela");
        gc.addEnemyToCPU("Ramladu");
        gc.addEnemyToCPU("Balbazak");
        gc.addEnemyToCPU("Hindel");
        gc.addEnemyToCPU("Pyro");
        assertNull(gc.getWinner());
        //Check that the aliveNumber of each masterminds corresponds with the number of
        //added characters.
        assertEquals(gc.getPlayerAliveNumber(), 4);
        assertEquals(gc.getCPUAliveNumber(), 5);
        //Starts the game
        gc.startGame();
        //Wait for characters in the queue
        Thread.sleep(3000);
        //Since enemies are the first to attack, and their attacks are automatic,
        // there should be no need to do anything else. The game should end and the winner should be the CPU.
        assertEquals(gc.getWinner().getName(), gc.getCPUName());
        assertTrue(gc.isFinished());
    }

    /**
     * Test the case where the player wins.
     */
    @Test
    void playerWinsTest() throws InterruptedException {
        //Create the player party
        gc.addThiefToPlayer("Lyle");
        gc.addEngineerToPlayer("Kokichi");
        gc.addEngineerToPlayer("Bleu");
        gc.addEngineerToPlayer("Jogurt");
        //Sets the bow factory to create powerful bows
        gc.setSelectedWeaponFactory(0);
        gc.setSelectedWeaponFactoryPower(700);
        //Equips Lyle
        gc.addBowToInventory("Bow of Lyle");
        gc.setSelectedCharacterFromPlayerParty(0);
        gc.setSelectedWeapon(0);
        gc.equipSelectedWeaponToSelectedCharacter();
        //Equips Kokichi
        gc.addBowToInventory("Bow of Kokichi");
        gc.setSelectedCharacterFromPlayerParty(1);
        gc.setSelectedWeapon(1);
        gc.equipSelectedWeaponToSelectedCharacter();
        //Equips Bleu
        gc.addBowToInventory("Bow of Bleu");
        gc.setSelectedCharacterFromPlayerParty(2);
        gc.setSelectedWeapon(2);
        gc.equipSelectedWeaponToSelectedCharacter();
        //Equips Jogurt
        gc.addBowToInventory("Bow of Jogurt");
        gc.setSelectedCharacterFromPlayerParty(3);
        gc.setSelectedWeapon(3);
        gc.equipSelectedWeaponToSelectedCharacter();
        //Sets up enemy Team.
        //Config the factory to produce weighted characters, so we can be
        //sure that they wont play before the player characters.
        gc.setSelectedCharacterFactory(5);
        gc.setSelectedWeaponFactoryWeight(0);
        gc.addEnemyToCPU("Elliot");
        gc.addEnemyToCPU("Ramladu");
        //Start game
        gc.startGame();
        //Give some time to make sure that there will be characters in
        //the queue (as in ActiveCharacter variable).
        Thread.sleep(2000);
        gc.initAttackMove();
        //Kill Elliot
        gc.setSelectedCharacterFromCPUParty(0);
        gc.activeCharacterNormalAttackSelectedCharacter();
        //Kill Ramladu
        gc.initAttackMove();
        gc.setSelectedCharacterFromCPUParty(1);
        gc.activeCharacterNormalAttackSelectedCharacter();
        //Check for game finished status and winner
        assertTrue(gc.isFinished());
        assertEquals(gc.getWinner().getName(), gc.getPlayerName());
    }


 }
