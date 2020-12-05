package com.github.matiasvergaras.finalreality.model;
import com.github.matiasvergaras.finalreality.factory.Characters.EnemyFactory;
import com.github.matiasvergaras.finalreality.factory.Characters.KnightFactory;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.cpu.Enemy;
import com.github.matiasvergaras.finalreality.model.character.player.magic.BlackMage;
import com.github.matiasvergaras.finalreality.model.character.player.magic.WhiteMage;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Engineer;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Knight;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Thief;
import com.github.matiasvergaras.finalreality.model.weapon.magic.Staff;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Axe;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Bow;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Knife;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Sword;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class to test all model features.
 * Set ups the basics for every model tests.
 * @author Matias Vergara Silva.
 */
public abstract class abstractModelTest {

    //--------------------------------------SET-UP OF ATTRIBUTES VARIABLES---------------------------------------------//
    protected LinkedBlockingQueue<ICharacter> turns;
    protected int DAMAGE = 240;
    protected int POWERFUL_DAMAGE=310;
    protected int WEAK_DAMAGE= 5;
    protected int WEIGHT = 10;
    protected int MAGIC_DAMAGE = 300;
    protected int LOW_DP = 30;
    protected int MANA = 150;
    protected int HP = 100;
    protected int DP = 150;
    protected String AXE_NAME="Some Axe";
    protected String BOW_NAME="Some Bow";
    protected String KNIFE_NAME="Some Knife";
    protected String STAFF_NAME="Some Staff";
    protected String SWORD_NAME="Some Sword";

    //--------------------------------------SET-UP OF SOME POWERFUL WEAPONS-------------------------------------------//
    protected Axe powerfulAxe = new Axe("Powerful Axe", POWERFUL_DAMAGE, WEIGHT);
    protected Staff powerfulStaff = new Staff("Powerful Staff",POWERFUL_DAMAGE, WEIGHT, MAGIC_DAMAGE);
    protected Sword powerfulSword = new Sword("Powerful Sword", POWERFUL_DAMAGE, WEIGHT);
    protected Bow powerfulBow = new Bow("Powerful Bow", POWERFUL_DAMAGE, WEIGHT);
    protected Knife powerfulKnife = new Knife("Powerful Knife", POWERFUL_DAMAGE, WEIGHT);

    //--------------------------------------SET-UP OF SOME WEAK WEAPONS-----------------------------------------------//
    protected Axe weakAxe = new Axe("Weak Axe", WEAK_DAMAGE, WEIGHT);
    protected Staff weakStaff = new Staff("Weak Staff", WEAK_DAMAGE, WEIGHT, MAGIC_DAMAGE);
    protected Sword weakSword = new Sword("Weak Sword", WEAK_DAMAGE, WEIGHT);
    protected Bow weakBow = new Bow("Weak Bow", WEAK_DAMAGE, WEIGHT);
    protected Knife weakKnife = new Knife("Weak Knife", WEAK_DAMAGE, WEIGHT);

    //--------------------------------------SET-UP OF A LOW-DAMAGE-POWER ENEMY----------------------------------------//
    protected Enemy weakEnemy = new Enemy(turns, "Weak Enemy", 12, 100, 120, WEAK_DAMAGE);

    //--------------------------------------SET-UP OF SOME EXAMPLE COMMON CHARACTERS----------------------------------//
    protected BlackMage exampleBlackMage = new BlackMage(turns, "Example Black Mage", HP, DP, MANA);
    protected WhiteMage exampleWhiteMage = new WhiteMage(turns, "Example White Mage", HP, DP, MANA);
    protected Engineer exampleEngineer = new Engineer(turns, "Example Engineer", HP, DP);
    protected Knight exampleKnight = new Knight(turns, "Example Knight", HP, DP);
    protected Thief exampleThief = new Thief(turns, "Example Thief", HP, DP);

    //-------------------------------------SET-UP OF A POWERFUL EXAMPLE ENEMY-----------------------------------------#
    protected Enemy exampleEnemy = new Enemy(turns, "Example Enemy", WEIGHT, HP, DP, POWERFUL_DAMAGE);

    //--------------------------------------SET-UP OF SOME LOW-DP CHARACTERS------------------------------------------//
    protected BlackMage weakBlackMage = new BlackMage(turns, "Weak Black Mage", 100, LOW_DP, 250);
    protected WhiteMage weakWhiteMage = new WhiteMage(turns, "Weak White Mage", 200, LOW_DP, 200);
    protected Engineer weakEngineer = new Engineer(turns, "Weak Engineer", 320, LOW_DP);
    protected Knight weakKnight = new Knight(turns, "Weak Knight", 120, LOW_DP);
    protected Thief weakThief = new Thief(turns, "Weak Thief", 100, LOW_DP);

    //--------------------------------------SET-UP OF SOME COMMON WEAPONS---------------------------------------------//
    protected Axe exampleAxe = new Axe(AXE_NAME, DAMAGE, WEIGHT);
    protected Staff exampleStaff = new Staff(STAFF_NAME, DAMAGE, WEIGHT, MAGIC_DAMAGE);
    protected Sword exampleSword = new Sword(SWORD_NAME, DAMAGE, WEIGHT);
    protected Bow exampleBow = new Bow(BOW_NAME, DAMAGE, WEIGHT);
    protected Knife exampleKnife = new Knife(KNIFE_NAME, DAMAGE, WEIGHT);

    //--------------------------------------SET-UP OF SOME DEAD CHARACTERS--------------------------------------------//
    protected Engineer deadEngineer = new Engineer(turns, "Zombie Engineer", 0, 100);
    protected BlackMage deadBlackMage = new BlackMage(turns, "Zombie Black Mage", 0, 80, 100);
    protected WhiteMage deadWhiteMage = new WhiteMage(turns, "Zombie White Mage", 0, 100, 120);
    protected Thief deadThief = new Thief(turns, "Zombie Thief", 0, 60);
    protected Knight deadKnight = new Knight(turns, "Zombie Knight", 0, 200);
    protected Enemy deadEnemy= new Enemy(turns, "Zombie Enemy", 0, 0, 100, 100);

    //--------------------------------------SET-UP OF SOME FACTORIES--------------------------------------------//
    protected KnightFactory knightFactory = new KnightFactory(turns, 200, 120);
    protected EnemyFactory enemyFactory = new EnemyFactory(turns, 200, 120, 12, 114);
    /*
     * Basic set-up : A turns queue
     */
    protected void turnSetUp() {
        turns = new LinkedBlockingQueue<>();
    }
}
