package com.github.cc3002.finalreality.controller;

import com.github.matiasvergaras.finalreality.controller.GameController;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to check that game will be only in one state at a time.
 * @since Homework 2
 * @author Matías Vergara Silva
 */
public class StateTest {

    /**
     * Same as the test of an effective attack from player to cpu but
     * here the focus is to check if the game is in only one state
     * at a time.
     * Test an effective attack from player to cpu.
     */
    @Test
    void EffectivePlayerToCPUAttackTest() throws InterruptedException {
        GameController gc = new GameController("Player", "CPU", 1);
        //Check that the game is only in initializing mode.
        assertTrue(gc.isInitializing());
        assertFalse(gc.isFinished());
        assertFalse(gc.isActive());
        //Adds an Engineer to the player party.
        gc.addEngineerToPlayer("Domingo Egg");
        //Adds an Enemy to the CPU party.
        gc.addEnemyToCPU("Chaos");
        //Sets bow factory to create the definitive bow with 10000 power.
        gc.setSelectedWeaponFactory(0);
        gc.setSelectedWeaponFactoryPower(10000);
        //To make sure that the engineer will get the turn at first.
        gc.setSelectedWeaponFactoryWeight(1);
        //Instantiate a definitive bow and add it to inventory. Select it.
        gc.addBowToInventory();
        gc.setSelectedWeapon(0);
        //Sets Domingo Egg as selectedCharacter
        gc.setSelectedCharacterFromPlayerParty(0);
        //Equips the bow to Domingo
        gc.equipSelectedWeaponToSelectedCharacter();
        //Starts the game
        gc.startGame();
        //Check that the gameController is only in active mode.
        assertTrue(gc.isActive());
        assertFalse(gc.isFinished());
        assertFalse(gc.isInitializing());
        //In start mode, the user should not be able to get the factories.
        //Select Chaos as SelectedCharacter
        gc.setSelectedCharacterFromCPUParty(0);
        //Send attack message
        //Give some time to make sure that there will be characters in
        //the queue (as in ActiveCharacter variable).
        Thread.sleep(2000);
        assertEquals(gc.getActiveCharacter().getName(), "Domingo Egg");
        gc.activeCharacterNormalAttackSelectedCharacter();
        //Check that the enemy died.
        assertFalse(gc.getSelectedCharacter().isAlive());
        //Check that the game ended and is only in finished mode.
        assertTrue(gc.isFinished());
        assertFalse(gc.isInitializing());
        assertFalse(gc.isActive());
    }

}
