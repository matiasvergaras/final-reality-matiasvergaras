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

/**
 * Class to test the Controller methods to add characters to the playerParty.
 */
public class PlayerPartyTest {
    private GameController gameController;
    private LinkedBlockingQueue<ICharacter> turns;
    private int rivalInitPartySize;

    /**
     * Basic set-up: a turns queue and a Controller.
     */
    @BeforeEach
    void setUp(){
        turns = new LinkedBlockingQueue<>();
        gameController = new GameController();
        rivalInitPartySize = gameController.getCPUPartySize();

    }

    /**
     * Test the addBlackMage method.
     * <p> Adds a new blackMage to the player party and checks that it is correctly added
     * by checking the equality between a copy of the created character and the last character
     * added to the party, and by checking that the size of the party increased by 1. </p>
     */
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

    /**
     * Test the addWhiteMage method.
     * <p> Adds a new whiteMage to the player party and checks that it is correctly added
     * by checking the equality between a copy of the created character and the last character
     * added to the party, and by checking that the size of the party increased by 1. </p>
     */
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

    /**
     * Test the addEngineer method.
     * <p> Adds a new Engineer to the player party and checks that it is correctly added
     * by checking the equality between a copy of the created character and the last character
     * added to the party, and by checking that the size of the party increased by 1. </p>
     */
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

    /**
     * Test the addKnight method.
     * <p> Adds a new Knight to the player party and checks that it is correctly added
     * by checking the equality between a copy of the created character and the last character
     * added to the party, and by checking that the size of the party increased by 1. </p>
     */
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

    /**
     * Test the addThief method.
     * <p> Adds a new Thief to the player party and checks that it is correctly added
     * by checking the equality between a copy of the created character and the last character
     * added to the party, and by checking that the size of the party increased by 1. </p>
     */
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

    /**
     * Test that when adding characters to the player's party, nothing has been accidentally
     * added to the cpu party.
     */
    @Test
    void testRivalPartySize(){
        assertEquals(gameController.getCPUPartySize(), rivalInitPartySize);
    }




}
