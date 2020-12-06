package com.github.matiasvergaras.finalreality.controller;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.Mastermind.PlayerMastermind;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to test the Player Getters.
 * @since Homework 2
 * @author Matías Vergara Silva
 */
public class PlayerGetterTest {

    /**
     * Test of player getters:
     * <p> PlayerName, as String </p>
     * <p> CharantersQuantity of the player, as int. </p>
     * @since Homework 2
     * @author Matías Vergara Silva
     */
    @Test
    void playerGetterTest(){
        GameController gameController = new GameController("Chimuelo", 9);
        assertEquals(gameController.getPlayerName(), "Chimuelo");
        assertEquals(gameController.getCharactersQuantity(), 9);
    }
}
