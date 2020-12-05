package com.github.matiasvergaras.finalreality.controller;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.Mastermind.PlayerMastermind;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to test the Player Setter.
 * <p> Player Setter must be available just at the start of the game, in order to let the
 * player to choose his name and number of characters. There must not be another opportunity
 * to use it, since it will overwrite the current player status. </p>
 * @since Homework 2
 * @author Matías Vergara Silva
 */
public class PlayerSetterTest {

    /**
     * Test of player setter, with his new name and character quantity.ç
     * @since Homework 2
     * @author Matías Vergara Silva
     */
    @Test
    void playerSetterTest(){
        GameController gameController = new GameController();
        gameController.setNewPlayerValues("Bleu", 12);
        assertEquals(gameController.getPlayer(), new PlayerMastermind("Bleu", 12));
    }
}
