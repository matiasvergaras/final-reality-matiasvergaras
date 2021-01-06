package com.github.cc3002.finalreality.controller;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A class to check that the removeWeaponFromInventory and
 * removeCharacterFromItsParty methods works properly.
 * @author Matías Vergara Silva
 * @since Homework 2
 */
public class RemoveTest {
    private GameController gc;
    private LinkedBlockingQueue<ICharacter> turns;

    /**
     * Basic set-up: a queue and a gameController
     */
    @BeforeEach
    void setUp(){
        turns = new LinkedBlockingQueue<>();
        gc = new GameController("Cuddy",
                "Tower of The Ancients",8);
    }

    /**
     * Test the remove weapon from inventory method of GameController.
     * It adds a new Weapon to the inventory via add{Weapon}toInventory method.
     * Then it checks that the inventory size is equal to 1.
     * Then it selects the added weapon, and send the remove message.
     * Finally, it checks that the inventory size returned to 0.
     */
    @Test
    void removeWeaponTest(){
        gc.setSelectedWeaponFactory(4);
        gc.selectedWeaponFactoryProduce("Common Axe");
        assertEquals(gc.getInventorySize(),1);
        gc.setSelectedWeapon(0);
        gc.removeSelectedWeaponFromInventory();
        assertEquals(gc.getInventorySize(), 0);
    }

    /**
     * Test the remove character from party method of GameController.
     * It adds a new Character to the playerParty via add{Character}toPlayerParty method.
     * Then it checks that the player party size is equal to 1.
     * Then it selects the added character, and send the remove message.
     * Finally, it checks that the player party size returned to 0.
     */
    @Test
    void removePlayerCharacterTest(){
        gc.setSelectedCharacterFactory(1);
        gc.selectedCharacterFactoryProduce("Torasu");
        assertEquals(gc.getPlayerPartySize(), 1);
        gc.setSelectedCharacterFromPlayerParty(0);
        gc.removeSelectedCharacterFromItsParty();
        assertEquals(gc.getPlayerPartySize(), 0);
    }

    /**
     * Test the remove character from party method of GameController.
     * It adds a new Character to the CPUParty via add{Character}toCPUParty method.
     * Then it checks that the cpu party size is equal to 1.
     * Then it selects the added character, and send the remove message.
     * Finally, it checks that the cpu party size returned to 0.
     */
    @Test
    void removeCPUCharacterTest(){
        gc.setSelectedCharacterFactory(5);
        gc.selectedCharacterFactoryProduce("Chimera");
        assertEquals(gc.getCPUPartySize(), 1);
        gc.setSelectedCharacterFromCPUParty(0);
        gc.removeSelectedCharacterFromItsParty();
        assertEquals(gc.getCPUPartySize(), 0);
    }
}
