package com.github.cc3002.finalreality.controller;

import com.github.matiasvergaras.finalreality.controller.GameController;
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
     * <p> CharactersQuantity of the player, as int. </p>
     * @since Homework 2
     * @author Matías Vergara Silva
     */
    @Test
    void playerGetterTest(){
        GameController gc = new GameController("Chimuelo", "Dragonia",
                9);
        assertEquals(gc.getMastermindName(gc.getPlayer()), "Chimuelo");
        assertEquals(gc.getMastermindName(gc.getCPU()), "Dragonia");
        assertEquals(gc.getCharactersQuantity(), 9);
    }
}
