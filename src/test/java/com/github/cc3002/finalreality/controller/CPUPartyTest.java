package com.github.cc3002.finalreality.controller;

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
 * @author Matías Vergara Silva
 * @since Homework 2
 */
public class CPUPartyTest {
    private GameController gc;
    private LinkedBlockingQueue<ICharacter> turns;

    /**
     * Basic set-up: a turns queue and a Controller.
     */
    @BeforeEach
    void setUp() {
        turns = new LinkedBlockingQueue<>();
        gc = new GameController("Chase", "Alterone", 5);

    }

    /**
     * Test the addBlackMage method.
     * <p> Adds a new Enemy the CPU party and checks that it is correctly added
     * by checking the equality between a copy of the created character and the last character
     * added to the party, and by checking that the size of the party increased by 1. </p>
     */
    @Test
    void addEnemyTest() {
        int initSize = gc.getCPUPartySize();
        gc.setSelectedCharacterFactory(5);
        gc.selectedCharacterFactoryProduce("Darksol");
        gc.setSelectedCharacterFromCPUParty(gc.getCPUPartySize() - 1);
        Enemy sameCharacter = new Enemy(turns, "Darksol", 130, 100, 12, 100);
        assertEquals(gc.getSelectedCharacter(), sameCharacter);
        assertEquals(initSize + 1, gc.getCPUPartySize());
    }


    /**
     * Test that when adding characters to the CPU's party, nothing has been accidentally
     * added to the player party.
     */
    @AfterEach
    void testRivalPartySize(){
        assertEquals(gc.getPlayerPartySize(), 0);
    }

}