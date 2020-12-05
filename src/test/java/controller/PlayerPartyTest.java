package controller;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.Mastermind.IMastermind;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.magic.BlackMage;
import com.github.matiasvergaras.finalreality.model.character.player.magic.WhiteMage;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Engineer;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Knight;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerPartyTest {
    private GameController gameController;
    private LinkedBlockingQueue<ICharacter> turns;

    @BeforeEach
    void setUp(){
        turns = new LinkedBlockingQueue<>();
        gameController = GameController.getInstance();
    }

    @Test
    void addBlackMageTest(){
        IMastermind player = gameController.getPlayer();
        int initSize = gameController.getPlayerPartySize();
        gameController.addBlackMageToPlayerParty("Alef");
        gameController.setSelectedCharacterFromPlayerParty(gameController.getPlayerPartySize()-1);
        BlackMage sameCharacter = new BlackMage(turns, "Alef", 120, 40, 200);
        assertEquals(gameController.getSelectedCharacter(), sameCharacter);
        assertEquals(initSize+1, gameController.getPlayerPartySize());
    }

    @Test
    void addWhiteMageTest(){
        IMastermind player = gameController.getPlayer();
        int initSize = gameController.getPlayerPartySize();
        gameController.addWhiteMageToPlayerParty("Tao");
        gameController.setSelectedCharacterFromPlayerParty(gameController.getPlayerPartySize()-1);
        WhiteMage sameCharacter = new WhiteMage(turns, "Tao", 120, 40, 200);
        assertEquals(gameController.getSelectedCharacter(), sameCharacter);
        assertEquals(initSize+1, gameController.getPlayerPartySize());

    }

    @Test
    void addEngineerTest(){
        IMastermind player = gameController.getPlayer();
        int initSize = gameController.getPlayerPartySize();
        gameController.addEngineerToPlayerParty("Vankar");
        gameController.setSelectedCharacterFromPlayerParty(gameController.getPlayerPartySize()-1);
        Engineer sameCharacter = new Engineer(turns, "Vankar", 100, 100);
        assertEquals(gameController.getSelectedCharacter(), sameCharacter);
        assertEquals(initSize+1, gameController.getPlayerPartySize());

    }

    @Test
    void addKnightTest(){
        IMastermind player = gameController.getPlayer();
        int initSize = gameController.getPlayerPartySize();
        gameController.addKnightToPlayerParty("Gort");
        gameController.setSelectedCharacterFromPlayerParty(gameController.getPlayerPartySize()-1);
        Knight sameCharacter = new Knight(turns, "Gort", 200, 12);
        assertEquals(gameController.getSelectedCharacter(), sameCharacter);
        assertEquals(initSize+1, gameController.getPlayerPartySize());

    }


    @Test
    void addThiefTest(){
        IMastermind player = gameController.getPlayer();
        int initSize = gameController.getPlayerPartySize();
        gameController.addThiefToPlayerParty("Hanzou");
        gameController.setSelectedCharacterFromPlayerParty(gameController.getPlayerPartySize()-1);
        Thief sameCharacter = new Thief(turns, "Hanzou", 200, 12);
        assertEquals(gameController.getSelectedCharacter(), sameCharacter);
        System.out.println(gameController.getPlayerPartySize());
        assertEquals(initSize+1, gameController.getPlayerPartySize());

    }

    @Test
    void testPartySizes(){
        assertEquals(gameController.getCPUPartySize(), 0);
    }




}
