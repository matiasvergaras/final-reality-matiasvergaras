package com.github.cc3002.finalreality.model.character;

import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.magic.Staff;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Axe;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Bow;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Knife;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Sword;

import java.util.ArrayList;
import java.util.List;

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
            assertNull(character.getEquippedWeapon());
            for (var weapon : testWeapons) {
                character.equip(weapon);
                assertEquals(weapon, character.getEquippedWeapon());
            }
        }
    }
}
