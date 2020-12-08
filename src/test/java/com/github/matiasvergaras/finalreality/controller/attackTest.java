package com.github.matiasvergaras.finalreality.controller;

import com.github.matiasvergaras.finalreality.model.weapon.NullWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to check that the attack order of GameController works properly.
 * It also checks equip and unequip methods.
 * <p> Controller is in charge of avoiding friendly-fire, and we will check that.</p>
 * @since Homework 2
 * @author Matías Vergara Silva
 */

public class attackTest {
    private GameController gc;

    /**
     * Basic set-up: a Controller.
     */
    @BeforeEach
    void setUp() {
        gc = new GameController("Lisa", "Runefaust", 1);
    }

    /**
     * Test an effective attack from player to cpu.
     */
    @Test
    void EffectivePlayerToCPUAttackTest() throws InterruptedException {
        assertTrue(gc.isInitializing());
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
        gc.setSelectedWeapon(gc.getInventorySize()-1);
        //Sets Domingo Egg as selectedCharacter
        gc.setSelectedCharacterFromPlayerParty(0);
        //Equips the bow to Domingo
        gc.equipSelectedWeaponToSelectedCharacter();
        //Make sure that the enemy is alive before getting attacked
        assertTrue(gc.getSelectedCharacter().isAlive());
        //Starts the game
        gc.startGame();
        assertFalse(gc.isInitializing());
        //Select Chaos as SelectedCharacter
        gc.setSelectedCharacterFromCPUParty(0);
        //Send attack message
        assertTrue(gc.isActive());
        assertEquals(gc.getActiveCharacter().getName(), "Domingo Egg");
        gc.activeCharacterNormalAttackSelectedCharacter();
        //Check that the enemy died.
        assertFalse(gc.getSelectedCharacter().isAlive());
        //Check that the game ended.
        assertTrue(gc.isFinished());
        //Tries to unequip Domingo. Since the game is in Finished state, it should not work.
        gc.setSelectedCharacterFromPlayerParty(0);
        gc.unequipSelectedCharacter();
        assertEquals(gc.getWinner().getName(), gc.getPlayerName());
        assertNotEquals(gc.getSelectedCharacterEquippedWeapon(), new NullWeapon());
        gc.initializeGame();
        gc.unequipSelectedCharacter();
        assertEquals(gc.getSelectedCharacterEquippedWeapon(), new NullWeapon());

    }

    /**
     * Test a ineffective attack from player to player.
     */
    @Test
    void IneffectivePlayerToPlayerAttackTest() throws InterruptedException {
        assertTrue(gc.isInitializing());
        //Adds an Engineer to the player party and selects him.
        gc.addEngineerToPlayer("Domingo Egg");
        gc.setSelectedCharacterFromPlayerParty(gc.getPlayerPartySize()-1);
        //Adds an Thief to the player party and set him as attack target.
        gc.addThiefToPlayer("Lowe");
        //Sets bow factory to create the definitive bow with 10000 power.
        gc.setSelectedWeaponFactory(0);
        gc.setSelectedWeaponFactoryPower(10000);
        //Instantiate a definitive bow and add it to inventory. Select it.
        gc.addBowToInventory();
        gc.setSelectedWeapon(gc.getInventorySize()-1);
        //Equip the definitive bow to Domingo the Engineer
        gc.equipSelectedWeaponToSelectedCharacter();
        gc.setSelectedCharacterFromCPUParty(1);
        //First we make sure that the  thief is alive before getting attacked
        assertTrue(gc.getSelectedCharacter().isAlive());
        gc.startGame();
        //Send attack message
        gc.activeCharacterNormalAttackSelectedCharacter();
        //Check that the thief is still alive (if he was attacked, he would be already dead).
        assertTrue(gc.getSelectedCharacter().isAlive());
        //Send the unequip message and check that it is correctly done.
        gc.unequipSelectedCharacter();
        assertEquals(gc.getSelectedCharacterEquippedWeapon(), new NullWeapon());
    }

    /**
     * Checks an effective attack from cpu to player.
     */
    @Test
    void EffectiveCPUToPlayerAttackTest() throws InterruptedException {
        assertTrue(gc.isInitializing());
        //Adds an Engineer to the player party.
        gc.addEngineerToPlayer("Domingo Egg");
        //Adds a weapon to inventory and equips it to Domingo, in order to
        //make him able to enter to the queue.
        gc.addAxeToInventory();
        gc.setSelectedWeapon(0);
        gc.setSelectedCharacterFromPlayerParty(0);
        gc.equipSelectedWeaponToSelectedCharacter();
        //Sets the enemy factories to create mega powerful enemies.
        gc.setSelectedCharacterFactory(5);
        gc.setSelectedCharacterFactoryPower(1000);
        gc.setSelectedCharacterFactoryWeight(14);
        //Adds an Enemy to the CPU party.
        gc.addEnemyToCPU("Elliot");
        //Starts the game
        gc.startGame();
        //Since Domingo weapon has weight 13 (default Axe), he will take the turn the first.
        //Well check it and make him attack Elliot.
        assertEquals(gc.getActiveCharacter(), gc.getPlayerParty().get(0));
        gc.setSelectedCharacterFromCPUParty(0);
        gc.activeCharacterNormalAttackSelectedCharacter();
        //Winner should not been assigned yet
        assertNull(gc.getWinner());
        //Elliot HP should have changed when received the attack (Elliot DP:100, Bow Power:110).
        assertNotEquals(gc.getSelectedCharacterMaxHP(), gc.getSelectedCharacterCurrentHP());
        //Now is turn of Elliot to attack.
        //We set Domingo as selectedCharacter and make sure that he is alive before getting attacked
        gc.setSelectedCharacterFromPlayerParty(0);
        assertTrue(gc.getSelectedCharacter().isAlive());
        //Now active character has to be Elliot, since he's the next in the queue.
        assertEquals(gc.getActiveCharacter(), gc.getCPUParty().get(0));
        //Send attack message
        gc.activeCharacterNormalAttackSelectedCharacter();
        //Check that Domingo received the mega powerful attack (i.e. he died).
        assertFalse(gc.getSelectedCharacter().isAlive());
        //Domingo was the only player character, so once he died, the game should have ended,
        //and the winner should be Runefaust.
        assertTrue(gc.isFinished());
        assertEquals(gc.getWinner().getName(), "Runefaust");
    }

    /**
     * Checks an ineffective attack from cpu to player.
     */
    @Test
    void IneffectiveCPUToCPUAttackTest() throws InterruptedException {
        assertTrue(gc.isInitializing());
        //Sets the enemy factories to create mega powerful enemies.
        gc.setSelectedCharacterFactory(5);
        gc.setSelectedCharacterFactoryPower(10000);
        //To make sure that this enemy will be the first in attack.
        gc.setSelectedCharacterFactoryWeight(1);
        //Adds an Enemy to the CPU party and set him as selectedCharacter.
        gc.addEnemyToCPU("Elliot");
        gc.setSelectedCharacterFromCPUParty(gc.getCPUPartySize()-1);
        //Adds a second instance of the super Enemy to the CPU party.
        // Makes sure that this second instance will not take the first turn.
        gc.setSelectedCharacterFactoryWeight(30);
        gc.addEnemyToCPU("Kane");
        //Make sure that Kane is alive before getting attacked
        assertTrue(gc.getSelectedCharacter().isAlive());
        //Try to start the game. Since the condition of charactersNumber for player has
        //not been reached yet, it should not work.
        gc.startGame();
        assertFalse(gc.isActive());
        //Adds a character to reach the condition and retry.
        gc.addKnightToPlayer("Vyrun");
        gc.startGame();
        assertTrue(gc.isActive());
        // Select Kane as target.
        gc.setSelectedCharacterFromCPUParty(gc.getCPUPartySize()-1);
        // Checks that the turn belongs to Elliot.
        assertEquals(gc.getActiveCharacter().getName(), "Elliot");
        //Send attack message
        gc.activeCharacterNormalAttackSelectedCharacter();
        //Check that the second enemy did not receive the attack (i.e. he did not died).
        assertTrue(gc.getSelectedCharacter().isAlive());
        //The game should be still active.
        assertTrue(gc.isActive());
    }

}
