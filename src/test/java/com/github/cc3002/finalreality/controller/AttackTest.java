package com.github.cc3002.finalreality.controller;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.weapon.NullWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to check that the attack order of GameController works properly.
 * It also checks equip and unequip methods.
 * <p> Controller is in charge of avoiding friendly-fire, and we will check that.</p>
 * @since Homework 2
 * @author Matías Vergara Silva
 */

public class AttackTest {
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
        gc.setSelectedCharacterFactory(0);
        //Adds an Engineer to the player party
        gc.selectedCharacterFactoryProduce("Domingo Egg");
        //Adds an Enemy to the CPU party.
        gc.addEnemyToCPU("Chaos");
        //Sets bow factory to create the definitive bow with 10000 power.
        gc.setSelectedWeaponFactory(0);
        gc.setSelectedWeaponFactoryPower(10000);
        //To make sure that the engineer will get the turn at first.
        gc.setSelectedWeaponFactoryWeight(0);
        //Instantiate a definitive bow and add it to inventory. Select it.
        gc.selectedWeaponFactoryProduce("Common Bow");
        gc.setSelectedWeapon(gc.getInventorySize()-1);
        //Sets Domingo Egg as selectedCharacter
        gc.setSelectedCharacterFromPlayerParty(0);
        //Equips the bow to Domingo
        gc.equipSelectedWeaponToSelectedCharacter();
        //Make sure that the enemy is alive before getting attacked
        assertTrue(gc.getSelectedCharacter().isAlive());
        //Starts the game
        gc.startGame();
        assertTrue(gc.isPlayerTurn());
        //Give some time to make sure that there will be characters in
        //the queue (as in ActiveCharacter variable).
        Thread.sleep(2000);
        gc.unequipSelectedCharacter();
        gc.equipSelectedWeaponToSelectedCharacter();;
        gc.setSelectedCharacterFromCPUParty(0);
        assertEquals(gc.getActiveCharacter().getName(), "Domingo Egg");
        //And to be sure that the selectedCharacter will not be overwritten
        Thread.sleep(2000);
        assertEquals(gc.getActiveCharacter().getName(), "Domingo Egg");
        gc.initAttackMove();
        assertTrue(gc.isSelectingAttackTarget());
        gc.activeCharacterNormalAttackSelectedCharacter();
        //Check that the enemy died.
        assertFalse(gc.getSelectedCharacter().isAlive());
        //Check that the game ended.
        assertTrue(gc.isFinished());
        //Tries to unequip Domingo. Since the game is in Finished state, it should not work.
        gc.setSelectedCharacterFromPlayerParty(0);
        gc.unequipSelectedCharacter();
        assertEquals(gc.getWinner().getName(), gc.getPlayerName());
        assertNotEquals(gc.getCharacterEquippedWeapon(gc.getSelectedCharacter()), new NullWeapon());
        gc.initializeGame();
        gc.unequipSelectedCharacter();
        assertEquals(gc.getCharacterEquippedWeapon(gc.getSelectedCharacter()), new NullWeapon());

    }

    /**
     * Test a ineffective attack from player to player.
     */
    @Test
    void IneffectivePlayerToPlayerAttackTest() throws InterruptedException {
        assertTrue(gc.isInitializing());
        gc.setSelectedCharacterFactory(0);
        //Adds an Engineer to the player party and selects him.
        gc.selectedCharacterFactoryProduce("Domingo Egg");
        gc.setSelectedCharacterFromPlayerParty(0);
        //Adds an Thief to the player party and set him as attack target.
        gc.setSelectedCharacterFactory(3);
        gc.selectedCharacterFactoryProduce("Lowe");
        //Sets bow factory to create the definitive bow with 10000 power.
        gc.setSelectedWeaponFactory(0);
        gc.setSelectedWeaponFactoryPower(10000);
        //Instantiate a definitive bow and add it to inventory. Select it.
        gc.selectedWeaponFactoryProduce("Common Bow");
        gc.setSelectedWeapon(0);
        //Equip the definitive bow to Domingo the Engineer
        gc.equipSelectedWeaponToSelectedCharacter();
        gc.setSelectedCharacterFromPlayerParty(1);
        gc.selectedWeaponFactoryProduce("Common Bow");
        gc.setSelectedWeapon(1);
        gc.equipSelectedWeaponToSelectedCharacter();
        gc.setSelectedCharacterFromPlayerParty(0);
        //First we make sure that the  thief is alive before getting attacked
        assertTrue(gc.getSelectedCharacter().isAlive());
        gc.startGame();
        Thread.sleep(2000);
        //Send attack message
        assertEquals(gc.getActiveCharacter().getName(), "Domingo Egg");
        gc.activeCharacterNormalAttackSelectedCharacter();
        //Check that the thief is still alive (if he was attacked, he would be already dead).
        assertTrue(gc.getSelectedCharacter().isAlive());
        //Send the unequip message and check that it is correctly done.
        gc.unequipSelectedCharacter();
        assertEquals(gc.getCharacterEquippedWeapon(gc.getSelectedCharacter()), new NullWeapon());
    }

    /**
     * Checks an effective attack from cpu to player.
     */
    @RepeatedTest(4)
    void EffectiveCPUToPlayerAttackTest() throws InterruptedException {
        assertTrue(gc.isInitializing());
        gc.setSelectedCharacterFactory(0);
        //Adds an Engineer to the player party.
        gc.selectedCharacterFactoryProduce("Domingo Egg");
        //Adds a weapon to inventory and equips it to Domingo, in order to
        //make him able to enter to the queue.
        gc.setSelectedWeaponFactory(4);
        gc.selectedWeaponFactoryProduce("Common Axe");
        gc.setSelectedWeapon(0);
        gc.setSelectedCharacterFromPlayerParty(0);
        gc.equipSelectedWeaponToSelectedCharacter();
        //Sets the enemy factories to create mega powerful enemies.
        gc.setSelectedCharacterFactory(5);
        gc.setSelectedCharacterFactoryPower(1000);
        gc.setSelectedCharacterFactoryWeight(32);
        //Adds an Enemy to the CPU party.
        gc.addEnemyToCPU("Elliot");
        //Starts the game
        gc.startGame();
        gc.unequipSelectedCharacter();
        gc.equipSelectedWeaponToSelectedCharacter();
        assertTrue(gc.isSettingNewTurn());
        //Give some time to make sure that there will be characters in
        //the queue (as in ActiveCharacter variable).
        Thread.sleep(3000);
        //Since Domingo weapon has weight 13 (default Axe), he will take the turn the first.
        //Well check it and make him attack Elliot.
        assertEquals(gc.getActiveCharacter(), gc.getPlayerParty().get(0));
        //Start an attack move, changing to selectingAttackTarget phase
        gc.initAttackMove();
        assertTrue(gc.isSelectingAttackTarget());
        gc.setSelectedCharacterFromCPUParty(0);
        gc.activeCharacterNormalAttackSelectedCharacter();
        //Once player has attacked, his turn should end, and an automatic turn of enemy should start.
        //Since there is just Domingo Egg in the player side, Elliot will attack him, and since Elliot has
        //power 1000, it will end with him. So there should be a winner. We will wait for 500ms in order
        //to give the time to do those things.
        Thread.sleep(500);
        assertEquals(gc.getWinner().getName(), "Runefaust");
        //Elliot HP should have changed when received the attack (Elliot DP:100, Bow Power:110).
        assertNotEquals(gc.getCharacterMaxHP(gc.getSelectedCharacter()), gc.getCharacterCurrentHP(gc.getSelectedCharacter()));
        assertTrue(gc.isFinished());
        assertEquals(gc.getWinner().getName(), "Runefaust");
    }

}
