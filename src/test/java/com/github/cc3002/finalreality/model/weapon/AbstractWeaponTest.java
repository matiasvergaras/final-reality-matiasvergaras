package com.github.cc3002.finalreality.model.weapon;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.Magic.BlackMage;
import com.github.matiasvergaras.finalreality.model.character.player.Magic.WhiteMage;
import com.github.matiasvergaras.finalreality.model.character.player.Normal.Engineer;
import com.github.matiasvergaras.finalreality.model.character.player.Normal.Knight;
import com.github.matiasvergaras.finalreality.model.character.player.Normal.Thief;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.Magic.Staff;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Axe;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Bow;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Knife;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Sword;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public abstract class AbstractWeaponTest {
    protected BlockingQueue<ICharacter> turns;
    protected static final String AXE_NAME = "Test Axe";
    protected static final String STAFF_NAME = "Test Staff";
    protected static final String SWORD_NAME = "Test Sword";
    protected static final String BOW_NAME = "Test Bow";
    protected static final String KNIFE_NAME = "Test Knife";
    protected static final int DAMAGE = 15;
    protected static final int WEIGHT = 10;
    protected static final int MAGIC_DAMAGE = 30;
    Axe expectedAxe = new Axe(AXE_NAME, DAMAGE, WEIGHT);
    BlackMage exampleBlackMage = new BlackMage(turns, "Example Black Mage", 200, 200, 200);
    WhiteMage exampleWhiteMage = new WhiteMage(turns, "Example Black Mage", 200, 200, 200);
    Engineer exampleEngineer = new Engineer(turns, "Example Engineer", 200, 200);
    Knight exampleKnight = new Knight(turns, "Example Knight", 200, 200);
    Thief exampleThief = new Thief(turns, "Example Thief", 200, 200);

    Staff expectedStaff = new Staff(STAFF_NAME, DAMAGE, WEIGHT, MAGIC_DAMAGE);
    Sword expectedSword = new Sword(SWORD_NAME, DAMAGE, WEIGHT);
    Bow expectedBow = new Bow(BOW_NAME, DAMAGE, WEIGHT);
    Knife expectedKnife = new Knife(KNIFE_NAME, DAMAGE, WEIGHT);




    /**
     * Sets up the basics to test a weapon type against the others.
     */
    void basicSetUp(){
        turns = new LinkedBlockingQueue<>();
    }

    /**
     * Checks that the class' constructor and hashcode, equals methods works properly.
     */
    protected void checkConstruction(final IWeapon expectedWeapon,
                                     final IWeapon testEqualWeapon,
                                     final IWeapon sameClassDifferentWeapon,
                                     final IWeapon differentWeapon){
        assertEquals(expectedWeapon, testEqualWeapon);
        assertNotEquals(sameClassDifferentWeapon, testEqualWeapon);
        assertNotEquals(testEqualWeapon, differentWeapon);
        assertEquals(expectedWeapon.hashCode(), testEqualWeapon.hashCode());
        assertNotEquals(expectedWeapon.hashCode(), differentWeapon.hashCode());

    }
}
