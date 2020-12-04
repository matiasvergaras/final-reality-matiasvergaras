package com.github.cc3002.finalreality.model.Mastermind;

import com.github.cc3002.finalreality.model.abstractModelTest;
import com.github.matiasvergaras.finalreality.factory.Characters.ICharacterFactory;
import com.github.matiasvergaras.finalreality.model.Mastermind.IMastermind;
import com.github.matiasvergaras.finalreality.model.Mastermind.PlayerMastermind;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.magic.IMagicCharacter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractMastermindTest extends abstractModelTest {
    IMastermind player;
    IMastermind cpu;

    /**
     * Basic Set-Up to check Masterminds behavior.
     */
    protected void setUp(){
        super.turnSetUp();
        this.player = new PlayerMastermind("Name", 2);

    }
    /**
     * Checks that the addToPlayerParty works properly from a PlayerMastermind.
     * <p> It tries to add a character at the end of the party, and checks if it worked. </p>
     * @param character        the ICharacter character to be added to the player Party.
     */
    protected void checkEffectiveAddToPlayerParty(ICharacter character) {
        player.addToPlayerParty(character);
        assertEquals(character, player.getCharacterFromParty(player.getPartySize()-1),
                "PlayerMastermind tried to add a character to its party but the last character there" +
                        "is different from the one that has been added");
    }

    /**
     * Checks that the addToPlayerParty works properly from a CPUMastermind.
     * <p> It tries to add a character at the end of the party, and checks that it did not have any effect. </p>
     * @param character        the ICharacter character to be added to the CPU Party.
     */
    protected void checkIneffectiveAddToPlayerParty(ICharacter character) {
        cpu.addToPlayerParty(character);
        assertEquals(0, cpu.getPartySize(), "CPUMastermind tried to add a character to " +
                "the player party, and he success.");
    }

    /**
     * Checks that the addToCPUParty works properly from a CPUMastermind.
     * <p> It tries to add a character at the end of the party, and checks if it worked. </p>
     * @param character        the ICharacter character to be added to the CPU Party.
     */
    protected void checkEffectiveAddToCPUParty(ICharacter character) {
        cpu.addToCPUParty(character);
        assertEquals(character, cpu.getCharacterFromParty(0),
                "CPUMastermind tried to add a character to its party but the last character there" +
                        "is different from the one that has been added");
    }

    /**
     * Checks that the addToCPUParty works properly from a PlayerMastermind.
     * <p> It tries to add a character at the end of the party, and checks that it did not have any effect </p>
     * @param character        the ICharacter character to be added to the CPU Party.
     */
    protected void checkIneffectiveAddToCPUParty(ICharacter character) {
        cpu.addToPlayerParty(character);
        assertEquals(0, cpu.getPartySize(), "PlayerMastermind tried to add a character to " +
                "the player party, and he success.");
    }

    /**
     * Checks that the getPartySize method works properly.
     * <p> It adds a given number of characters from a given factory to a given Mastermind party. </p>
     * @param n             Number of characters to add
     * @param factory       The factory that will be requested for the characters
     * @param mastermind    The mastermind whose party will be used to test.
     */
    protected void checkIneffectiveAddToCPUParty(int n, ICharacterFactory factory, IMastermind mastermind) {
        for (int i=0; i<=n; i++){
            factory.create("Character N" + n);
            mastermind.
        }
    }


}
