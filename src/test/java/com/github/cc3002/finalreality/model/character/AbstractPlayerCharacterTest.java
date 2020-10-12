package com.github.cc3002.finalreality.model.character;

import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.Magic.Staff;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Axe;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Bow;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Knife;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Sword;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Abstract class containing the common tests for all the playable characters.
 *
 * @author Matias Vergara Silva.
 * @see IPlayerCharacter
 */
abstract public class AbstractPlayerCharacterTest extends AbstractCharacterTest {
    protected List<IWeapon> testWeapons;
    protected List<IPlayerCharacter> testPlayerCharacters;

    protected static final String BLACK_MAGE_NAME = "Airi";
    protected static final String ENGINEER_NAME = "Cid";
    protected static final String KNIGHT_NAME = "Adelbert";
    protected static final String THIEF_NAME = "Zidane";
    protected static final String WHITE_MAGE_NAME = "Eiko";

    protected static final Staff EXAMPLE_STAFF = new Staff("Example Staff", 5, 15, 250);
    protected static final Sword EXAMPLE_SWORD = new Sword("Example Sword", 20, 12);
    protected static final Axe EXAMPLE_AXE = new Axe("Example Axe", 35, 14);
    protected static final Knife EXAMPLE_KNIFE = new Knife("Example Knife", 30, 12);
    protected static final Bow EXAMPLE_BOW = new Bow("Example Bow", 20, 15);


    protected void playerSetUp(){
        super.basicSetUp();
        testWeapons = new ArrayList<>();
        testPlayerCharacters = new ArrayList<>();
    }

    /**
     *  Checks that the character starts without any weapon,
     *  and that he can equip the weapons corresponding to his type.
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
