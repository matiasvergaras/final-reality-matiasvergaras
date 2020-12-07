package com.github.matiasvergaras.finalreality.model.character;

import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Abstract class containing the common tests for all playable characters.
 * @since Homework 1
 * @author Matias Vergara Silva.
 * @see IPlayerCharacter
 */
abstract public class AbstractPlayerCharacterTest extends AbstractCharacterTest {


    protected void playerSetUp() {
        super.basicSetUp();
        testWeapons = new ArrayList<>();
        testPlayerCharacters = new ArrayList<>();
    }

    /**
     * Checks that the character starts without any weapon,
     * and that he can equip the weapons corresponding to his type.
     */
    protected void checkEquipWeapon() {
        for (var character :
                testPlayerCharacters) {
            assertNull(character.getEquippedWeapon(), "Character started equipped, but that is not right.");
            for (var weapon : testWeapons) {
                character.equip(weapon);
                assertEquals(weapon, character.getEquippedWeapon(), "Weapon was not equipped successfully.");
            }
        }
    }

    /**
     * Checks that the AttackPower attribute is calculated correctly:
     * It has to start at 0 if the playerCharacter does not have a weapon,
     * and be equal to the weapon power if the character is equipped.
     * @param character         The character to test
     * @param weapon            The weapon to equip
     */
    protected void checkGetAttackPower(IPlayerCharacter character, IWeapon weapon){
        assertEquals(character.getAttackPower(), 0);
        character.equipWeapon(weapon);
        assertEquals(character.getAttackPower(), weapon.getPower());
    }




}
