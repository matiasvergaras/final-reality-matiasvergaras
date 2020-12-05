package controller;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.Mastermind.PlayerMastermind;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerSetterTest {
    @Test
    void playerSetterTest(){
        GameController gameController = GameController.getInstance();
        gameController.setNewPlayerValues("Bleu", 12);
        assertEquals(gameController.getPlayer(), new PlayerMastermind("Bleu", 12));
    }
}
