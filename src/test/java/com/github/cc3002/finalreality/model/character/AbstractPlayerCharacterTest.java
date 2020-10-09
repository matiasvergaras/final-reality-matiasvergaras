package com.github.cc3002.finalreality.model.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


import com.github.matiasvergaras.finalreality.model.character.player.AbstractPlayerCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.Magic.Staff;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Set of tests for the {@code GameCharacter} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Matias Vergara Silva.
 * @see AbstractPlayerCharacter
 */
public abstract class AbstractPlayerCharacterTest extends AbstractCharacterTest {
    protected List<IPlayerCharacter> testPlayerCharacters;
    protected IWeapon testWeapon;

    /**
     * Method to try to equip a Weapon to a Character inside of the waitTurn Test
     * @param character
     *                  The character to try to equip
     */
    void tryToEquip(IPlayerCharacter character) {
        character.equip(testWeapon);
    }

    /**
     * Checks that the class' equip method works properly.
     */
    void equipWeaponTest() {
        for (var character :
                testPlayerCharacters) {
            assertNull(character.getEquippedWeapon());
            character.equip(testWeapon);
            assertEquals(testWeapon, character.getEquippedWeapon());
        }
    }

    protected void basicSetUp() {
        turns = new LinkedBlockingQueue<>();
        testCharacters = new ArrayList<>();
    }


}
