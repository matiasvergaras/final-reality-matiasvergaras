package com.github.matiasvergaras.finalreality.controller;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.cpu.Enemy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class to test the Controller methods to add characters to the CPUParty.
 */
public class CPUPartyTest {
    private GameController gameController;
    private LinkedBlockingQueue<ICharacter> turns;

    /**
     * Basic set-up: a turns queue and a Controller.
     */
    @BeforeEach
    void setUp() {
        turns = new LinkedBlockingQueue<>();
        gameController = new GameController();

    }

    /**
     * Test the addBlackMage method.
     * <p> Adds a new Enemy the CPU party and checks that it is correctly added
     * by checking the equality between a copy of the created character and the last character
     * added to the party, and by checking that the size of the party increased by 1. </p>
     */
    @Test
    void addBlackMageTest() {
        int initSize = gameController.getCPUPartySize();
        gameController.addEnemyToCPUParty("Darksol");
        gameController.setSelectedCharacterFromCPUParty(gameController.getCPUPartySize() - 1);
        Enemy sameCharacter = new Enemy(turns, "Darksol", 130, 100, 12, 100);
        assertEquals(gameController.getSelectedCharacter(), sameCharacter);
        assertEquals(initSize + 1, gameController.getCPUPartySize());
    }

    /**
     * Test that when adding characters to the CPU's party, nothing has been accidentally
     * added to the player party.
     */
    @AfterEach
    void testRivalPartySize(){
        assertEquals(gameController.getPlayerPartySize(), 0);
    }

}