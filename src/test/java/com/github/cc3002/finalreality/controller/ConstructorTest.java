package com.github.cc3002.finalreality.controller;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.character.player.NullCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to test the constructor of the GameController.
 * It checks that the values of a gameCharacter starts as they should do.
 */
public class ConstructorTest {
    GameController gc;
    String playerName = "Guardiana";
    String CPUName = "Runefaust";
    int charactersQuantity = 5;

    /**
     * Basic set-up: a gameController
     */
    @BeforeEach
    void setUp(){
        gc = new GameController(playerName, CPUName, charactersQuantity);
    }

    /**
     * Test that the constructor parameters are assigned correctly
     */
    @Test
    void testParameters(){
        assertEquals(gc.getPlayerName(), playerName);
        assertEquals(gc.getCPUName(), CPUName);
        assertEquals(gc.getCharactersQuantity(), charactersQuantity);
    }

    /**
     * Test that the parties and the inventory starts empties.
     */
    @Test
    void testEmpties(){
        assertEquals(gc.getPlayerPartySize(), 0);
        assertEquals(gc.getCPUPartySize(), 0);
        assertEquals(gc.getInventorySize(), 0);
        assertEquals(gc.getPlayer().getInventory().size(), gc.getInventorySize());
        assertEquals(gc.getPlayer().getParty().size(), gc.getPlayerPartySize());
        assertEquals(gc.getCPU().getParty().size(), gc.getCPUPartySize());
    }

    /**
     * Test that the turnsQueue starts empty.
     */
    @Test
    void testTurnsQueue(){
        assertEquals(gc.getTurns().size(), 0);
    }

    /**
     * Test that the selected(Weapon/Character/Factories) starts
     * at their null values, and the same for the winner.
     */
    @Test
    void testSelected(){
        assertNull(gc.getSelectedWeaponFactory());
        assertNull(gc.getSelectedCharacterFactory());
        assertEquals(gc.getSelectedCharacter(), new NullCharacter());
        assertEquals(gc.getActiveCharacter(), new NullCharacter());
        assertNull(gc.getWinner());
    }

    /**
     * Test that the initial state is Initializing.
     */
    @Test
    void testInitState(){
        assertTrue(gc.isInitializing());
    }
}
