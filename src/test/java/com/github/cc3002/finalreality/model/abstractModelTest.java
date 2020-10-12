package com.github.cc3002.finalreality.model;

import com.github.matiasvergaras.finalreality.model.character.CPU.Enemy;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.Magic.BlackMage;
import com.github.matiasvergaras.finalreality.model.character.player.Magic.WhiteMage;
import com.github.matiasvergaras.finalreality.model.character.player.Normal.Engineer;
import com.github.matiasvergaras.finalreality.model.character.player.Normal.Knight;
import com.github.matiasvergaras.finalreality.model.character.player.Normal.Thief;
import com.github.matiasvergaras.finalreality.model.weapon.Magic.Staff;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Axe;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Bow;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Knife;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Sword;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class abstractModelTest {
    protected BlockingQueue<ICharacter> turns;

    protected static final String AXE_NAME = "Test Axe";
    protected static final String STAFF_NAME = "Test Staff";
    protected static final String SWORD_NAME = "Test Sword";
    protected static final String BOW_NAME = "Test Bow";
    protected static final String KNIFE_NAME = "Test Knife";

    protected BlackMage exampleBlackMage = new BlackMage(turns, "Example Black Mage", 100, 200, 250);
    protected WhiteMage exampleWhiteMage = new WhiteMage(turns, "Example White Mage", 200, 100, 200);
    protected Engineer exampleEngineer = new Engineer(turns, "Example Engineer", 320, 210);
    protected Knight exampleKnight = new Knight(turns, "Example Knight", 120, 400);
    protected Thief exampleThief = new Thief(turns, "Example Thief", 100, 80);
    protected Enemy exampleEnemy = new Enemy(turns, "Example Enemy", 12, 100, 120, 100, 15);

    protected static final int DAMAGE = 15;
    protected static final int WEIGHT = 10;
    protected static final int MAGIC_DAMAGE = 30;

    protected Axe exampleAxe = new Axe(AXE_NAME, DAMAGE, WEIGHT);
    protected Staff exampleStaff = new Staff(STAFF_NAME, DAMAGE, WEIGHT, MAGIC_DAMAGE);
    protected Sword exampleSword = new Sword(SWORD_NAME, DAMAGE, WEIGHT);
    protected Bow exampleBow = new Bow(BOW_NAME, DAMAGE, WEIGHT);
    protected Knife exampleKnife = new Knife(KNIFE_NAME, DAMAGE, WEIGHT);

    protected void turnSetUp(){
        turns = new LinkedBlockingQueue<>();
    }
}
