package com.github.matiasvergaras.finalreality.controller;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.NullCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.NullWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * A class to cover the branches of GameController with an
 * if(index) when the index is out of bounds (Selects).
 * @author Matías Vergara Silva
 * @since Homework 2
 */
public class IndexOutOfBoundsTest {
    LinkedBlockingQueue<ICharacter> turns;
    GameController gameController;

    @BeforeEach
    void SetUp(){
        turns = new LinkedBlockingQueue<>();
        gameController = new GameController("The mismísimo Hackerman",
                "CPU", 2);
    }

    @Test
    void SelectCharacterTest(){
        assertEquals(gameController.getSelectedCharacter(), new NullCharacter());
        gameController.setSelectedCharacterFromPlayerParty(59423);
        assertEquals(gameController.getSelectedCharacter(), new NullCharacter());
        gameController.setSelectedCharacterFromCPUParty(0);
        assertEquals(gameController.getSelectedCharacter(), new NullCharacter());
    }

    @Test
    void SelectWeaponTest(){
        assertEquals(gameController.getSelectedWeapon(), new NullWeapon());
        gameController.setSelectedWeapon(123821);
        assertEquals(gameController.getSelectedWeapon(), new NullWeapon());
        gameController.setSelectedWeapon(0);
        assertEquals(gameController.getSelectedWeapon(), new NullWeapon());
    }

    @Test
    void SelectCharacterFactoryTest(){
        assertNull(gameController.getSelectedCharacterFactory());
        gameController.setSelectedCharacterFactory(12812831);
        assertNull(gameController.getSelectedCharacterFactory());
    }

    @Test
    void SelectWeaponFactoryTest(){
        assertNull(gameController.getSelectedWeaponFactory());
        gameController.setSelectedWeaponFactory(1231283);
        assertNull(gameController.getSelectedWeaponFactory());
    }
}
