package com.github.matiasvergaras.finalreality.controller;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to check that the death notifications between
 * Characters, Masterminds and Controller works well.
 * @author Matias Vergara Silva
 * @since Homework 2
 */
public class deathNotificationSystemTest {
    GameController gameController;
    LinkedBlockingQueue<ICharacter> turns;

    @BeforeEach
    void setUp() {
        gameController = new GameController("Narsha",
                "Bustoke", 4);
    }

    /**
     * Test the case where the cpu wins.
     */
    @Test
    void cpuWinsTest(){
        //Create the player party
        gameController.addThiefToPlayerParty("Lyle");
        gameController.addEngineerToPlayerParty("Kokichi");
        gameController.addKnightToPlayerParty("Gong");
        gameController.addBlackMageToPlayerParty("Nova");
        //Overflow the party. Just to check again that it is working.
        gameController.addWhiteMageToPlayerParty("Khris");
        //Sets the Enemy factory to create powerful characters
        gameController.setSelectedCharacterFactory(5);
        gameController.setSelectedCharacterFactoryPower(600);
        gameController.addEnemyToCPUParty("Mishaela");
        gameController.addEnemyToCPUParty("Ramladu");
        assertNull(gameController.getWinner());
        assertEquals(gameController.getPlayerAliveNumber(), 4);
        //Force to make some attacks with the first enemy
        gameController.setSelectedCharacterFromCPUParty(0);
        gameController.setAttackTargetFromPlayerParty(0);
        gameController.selectedCharacterNormalAttackTarget();
        assertNull(gameController.getWinner());
        assertEquals(gameController.getPlayerAliveNumber(), 3);
        //Change to second enemy. Force him to make some attacks.
        gameController.setSelectedCharacterFromCPUParty(1);
        gameController.setAttackTargetFromPlayerParty(1);
        gameController.selectedCharacterNormalAttackTarget();
        assertNull(gameController.getWinner());
        assertEquals(gameController.getPlayerAliveNumber(), 2);
        gameController.setAttackTargetFromPlayerParty(2);
        gameController.selectedCharacterNormalAttackTarget();
        assertNull(gameController.getWinner());
        assertEquals(gameController.getPlayerAliveNumber(), 1);
        gameController.setAttackTargetFromPlayerParty(3);
        gameController.selectedCharacterNormalAttackTarget();
        assertEquals(gameController.getPlayerAliveNumber(), 0);
        //Every player character should be dead by now and the controller
        //should know who is the winner. We ask for it.
        assertEquals(gameController.getWinner().getName(), gameController.getCPUName());
        assertTrue(gameController.getGameEnded());
    }

    /**
     * Test the case where the player wins.
     */
    @Test
    void playerWinsTest(){
        //Create the player party
        gameController.addThiefToPlayerParty("Lyle");
        gameController.addEngineerToPlayerParty("Kokichi");
        //Sets the bow factory to create powerful bows
        gameController.setSelectedWeaponFactory(0);
        gameController.setSelectedWeaponFactoryPower(700);
        //Equips Lyle
        gameController.setSelectedCharacterFromPlayerParty(0);
        gameController.addBowToInventory("Bow of Lyle");
        gameController.setSelectedWeapon(0);
        gameController.equipSelectedWeaponToSelectedCharacter();
        //Sets Axe factory to create powerful axes
        gameController.setSelectedWeaponFactory(4);
        gameController.setSelectedWeaponFactoryPower(700);
        //Equips Kokichi
        gameController.addAxeToInventory("Axe of Kokichi");
        gameController.setSelectedCharacterFromPlayerParty(1);
        gameController.setSelectedWeapon(1);
        gameController.equipSelectedWeaponToSelectedCharacter();
        //Create enemy party
        gameController.addEnemyToCPUParty("Elliot");
        gameController.addEnemyToCPUParty("Kane");
        gameController.addEnemyToCPUParty("Darksol");
        gameController.addEnemyToCPUParty("Chaos");
        gameController.addEnemyToCPUParty("Dark Dragon");
        gameController.addEnemyToCPUParty("Balbazak");
        //Force attacks
        gameController.setSelectedCharacterFromPlayerParty(0);
        gameController.setAttackTargetFromCPUParty(0);
        assertNull(gameController.getWinner());
        assertEquals(gameController.getCPUAliveNumber(), 6);
        gameController.selectedCharacterNormalAttackTarget();
        assertNull(gameController.getWinner());
        assertEquals(gameController.getCPUAliveNumber(), 5);
        gameController.setAttackTargetFromCPUParty(1);
        gameController.selectedCharacterNormalAttackTarget();
        assertNull(gameController.getWinner());
        assertEquals(gameController.getCPUAliveNumber(), 4);
        gameController.setAttackTargetFromCPUParty(2);
        gameController.selectedCharacterNormalAttackTarget();
        assertNull(gameController.getWinner());
        assertEquals(gameController.getCPUAliveNumber(), 3);
        gameController.setAttackTargetFromCPUParty(3);
        //change attacker
        gameController.setSelectedCharacterFromPlayerParty(1);
        gameController.selectedCharacterNormalAttackTarget();
        assertNull(gameController.getWinner());
        assertEquals(gameController.getCPUAliveNumber(), 2);
        gameController.setAttackTargetFromCPUParty(4);
        gameController.selectedCharacterNormalAttackTarget();
        assertNull(gameController.getWinner());
        assertEquals(gameController.getCPUAliveNumber(), 1);
        gameController.setAttackTargetFromCPUParty(5);
        gameController.selectedCharacterNormalAttackTarget();
        assertEquals(gameController.getCPUAliveNumber(), 0);
        //Every player character should be dead by now and the controller
        //should know who is the winner. We ask for it.
        assertEquals(gameController.getWinner().getName(), gameController.getPlayerName());
        assertTrue(gameController.getGameEnded());
    }


 }
