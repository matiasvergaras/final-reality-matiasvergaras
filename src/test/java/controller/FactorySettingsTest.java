package controller;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.LinkedBlockingQueue;

public class FactorySettingsTest {
    private GameController gameController;
    private LinkedBlockingQueue<ICharacter> turns;

    @BeforeEach
    void setUp(){
        turns = new LinkedBlockingQueue<>();
        gameController = GameController.getInstance();
    }

    @Test
    void BlackMageFactorySettingTest(){
        gameController.setSelectedCharacterFactory(1);
        gameController.setSelectedCharacterFactoryHP(10);
        gameController.setSelectedCharacterFactoryDP(11);
        gameController.setSelectedCharacterFactoryMana(12);
        gameController.addBlackMageToPlayerParty("Azelf");
        gameController.setSelectedCharacterFromPlayerParty(gameController.getPlayerPartySize()-1);
        assertEquals(gameController.getSelectedCharacterCurrentHP(), 10);
        assertEquals(gameController.getSelectedCharacterMaxHP(), 10);
        assertEquals(gameController.getSelectedCharacterDP(), 11);
        assertEquals(gameController.getSelectedCharacterMaxMana(), 12);
        assertEquals(gameController.getSelectedCharacterCurrentMana(), 12);
    }
}
