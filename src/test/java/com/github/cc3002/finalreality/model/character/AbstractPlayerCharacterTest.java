package com.github.cc3002.finalreality.model.character;

import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.Magic.Staff;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Axe;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Bow;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Knife;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Sword;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

abstract public class AbstractPlayerCharacterTest extends AbstractCharacterTest {
    protected List<IPlayerCharacter> testPlayerCharacters;
    protected List<IWeapon> testWeapons;
    protected static final String BLACK_MAGE_NAME = "Airi";
    protected static final String ENGINEER_NAME = "Cid";
    protected static final String KNIGHT_NAME = "Adelbert";
    protected static final String THIEF_NAME = "Zidane";
    protected static final String WHITE_MAGE_NAME = "Eiko";

    protected static final Staff EXAMPLE_STAFF = new Staff("Example Staff", 200, 15, 250);
    protected static final Sword EXAMPLE_SWORD = new Sword("Example Sword", 200, 12);
    protected static final Axe EXAMPLE_AXE = new Axe("Example Axe", 200, 14);
    protected static final Knife EXAMPLE_KNIFE = new Knife("Example Knife", 200, 12);
    protected static final Bow EXAMPLE_BOW = new Bow("Example Bow", 200, 15);


    void playerSetUp(){
        testPlayerCharacters = new ArrayList<>();
        testWeapons = new ArrayList<>();
    }

    /**
     *  Checks that the character starts without any weapon,
     *  and that he can equip the weapons corresponding to his type.
     */
    void checkEquipWeapon() {
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
