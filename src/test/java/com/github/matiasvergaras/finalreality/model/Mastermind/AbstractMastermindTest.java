package com.github.matiasvergaras.finalreality.model.Mastermind;

import com.github.matiasvergaras.finalreality.model.abstractModelTest;
import com.github.matiasvergaras.finalreality.factory.Characters.ICharacterFactory;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to hold the common test for a Mastermind.
 * <p> Masterminds represents the 'user player' and 'cpu player' in the game. </p>
 * @author Matías Vergara Silva
 * @since Homework 2
 */
public abstract class AbstractMastermindTest extends abstractModelTest {
    IMastermind player;
    IMastermind cpu;
    protected String playerName = "Player Name";
    protected int characterQuantity = 5;

    /**
     * Basic Set-Up to check Masterminds behavior.
     */
    protected void setUp(){
        super.turnSetUp();
        this.player = new PlayerMastermind(playerName, characterQuantity);
        this.cpu = new CPUMastermind("CPU");

    }

    /**
     * Constructor test
     * <p> Checks the equal and hashcode methods</p>
     *
     */
    protected void checkEquals(IMastermind toCheck, IMastermind sameMastermind, IMastermind anotherMastermind){
        assertEquals(toCheck, toCheck);
        assertEquals(toCheck, sameMastermind);
        assertNotEquals(toCheck, anotherMastermind);
        assertEquals(toCheck.hashCode(), sameMastermind.hashCode());
        assertNotEquals(toCheck.hashCode(), anotherMastermind.hashCode());
    }

    /**
     * Checks that the addToParty works properly.
     * <p> It tries to add a character at the end of the party, and checks if it worked. </p>
     * <p> Since the Controller will be in charge of verify the type of the characters to add,
     * at this point it is normal to be able to add any kind of characters to both masterminds. </p>
     * @param mastermind       The IMastermind mastermind to be tested.
     * @param character        The ICharacter character to be added to the player Party.
     */
    protected void checkAddToParty(IMastermind mastermind, ICharacter character) {
        mastermind.addToParty(character);
        assertEquals(character, mastermind.getCharacterFromParty(mastermind.getPartySize()-1),
                "PlayerMastermind tried to add a character to its party but the last character there" +
                        "is different from the one that has been added");
    }


    /**
     * Checks that the getPartySize and getParty methods works properly.
     * <p> It adds a given number of characters from a given factory to a given Mastermind party,
     * and checks that the party is equal to the list of the added characters. </p>
     * @param n             Number of characters to add.
     *                      If used with PlayerMastermind, n has to be lower or equal to charactersQuantity.
     * @param factory       The factory that will be requested for the characters
     * @param mastermind    The mastermind whose party will be used to test.
     */
    protected void checkPartyGetters(int n, ICharacterFactory factory, IMastermind mastermind) {
        ArrayList<ICharacter> partyCopy = new ArrayList<>();
        for (int i=0; i<=n; i++){
            ICharacter character =factory.create("Character N" + n);
            mastermind.addToParty(character);
            partyCopy.add(character);
            assertEquals(mastermind.getPartySize(), i+1);
        }
        assertEquals(mastermind.getParty(), partyCopy);
    }

    /**
     * Checks that the removeFromParty method works properly.
     * <p> It adds a character to the party, and checks that it has been
     * correctly added. Then it removes it and checks that the character is not in the party. </p>
     * @param character         The character to be added.
     * @param mastermind        The mastermind to be tested.
     */
    protected void checkRemoveFromParty(ICharacter character, IMastermind mastermind){
        mastermind.addToParty(character);
        assertTrue(mastermind.getParty().contains(character));
        mastermind.removeFromParty(character);
        assertFalse(mastermind.getParty().contains(character));
    }

    /**
     * Checks that the getCharacterFromParty method works properly.
     * <p> It checks that the party starts empty, adds a character, checks that it has been
     * correctly added and then it ask for the character in the index 0. It check that it
     * is equal to the added character, and finally it checks that the party did not loose the
     * character when asked for it. </p>
     * @param character         The character to be added.
     * @param mastermind        The mastermind to be tested.
     */
    protected void checkGetCharacterFromParty(ICharacter character, IMastermind mastermind){
        assertEquals(mastermind.getPartySize(), 0);
        mastermind.addToParty(character);
        assertTrue(mastermind.getParty().contains(character));
        assertEquals(character, mastermind.getCharacterFromParty(0));
        assertTrue(mastermind.getParty().contains(character));
    }

    /**
     * Checks that the makeNormalAttack method works properly.
     * <p> Since the Controller will be in charge of prevent the friendly-fire, in this
     * tests is normal to have that feature. </p>
     * <p> The attacker attackPower has to be grater than the receptor DP. </p>
     * @param attacker      The character that is performing the attack.
     * @param receptor      The character that will receive the attack.
     */
    protected void checkUnarmoredEffectiveAttack(ICharacter attacker, ICharacter receptor, IMastermind mastermind){
        mastermind.addToParty(attacker);
        int initialHP = receptor.getCurrentHP();
        mastermind.makeNormalAttack(attacker, receptor);
        assertEquals(Integer.max(initialHP-(attacker.getAttackPower()-receptor.getDP()),0)
                , receptor.getCurrentHP());
    }

    /**
     * Checks that the makeNormalAttack method works properly.
     * <p> Specifically, it checks that if the attacker is not in the party, no attack is performed. </p>
     * <p> Since the Controller will be in charge of prevent the friendly-fire, in this
     * tests is normal to have that feature. </p>
     * <p> The attacker attackPower has to be grater than the receptor DP. </p>
     * @param attacker      The character that is performing the attack.
     * @param receptor      The character that will receive the attack.
     */
    protected void checkUnarmoredIneffectiveAttack(ICharacter attacker, ICharacter receptor, IMastermind mastermind){
        int initialHP = receptor.getCurrentHP();
        mastermind.makeNormalAttack(attacker, receptor);
        assertEquals(initialHP, receptor.getCurrentHP());
    }

}
