package com.github.matiasvergaras.finalreality.controller;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.weapon.magic.Staff;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Axe;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Bow;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Knife;
import com.github.matiasvergaras.finalreality.model.weapon.normal.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A class to test the add weapon to inventory methods of Game Controller.
 * @since Homework 2
 * @author Matías Vergara Silva
 */
public class InventoryAddTest {
    private GameController gameController;
    private LinkedBlockingQueue<ICharacter> turns;

    /**
     * Basic set-up. An empty turns queue to add the copy of the weapons,
     * and a gameController instance.
     */
    @BeforeEach
    void setUp(){
        turns = new LinkedBlockingQueue<>();
        gameController = new GameController("Greg", "Guardiana", 9);
    }

    /**
     * Test the addBowToInventory method.
     * <p> This test is for the method without parameters (without name),
     * that creates common weapons. </p>
     *
     */
    @Test
    void AddCommonBowTest(){
        gameController.addBowToInventory();
        Bow sameBow = new Bow("Common Bow", 90, 10);
        gameController.setSelectedWeapon(gameController.getInventorySize()-1);
        assertEquals(sameBow, gameController.getSelectedWeapon());
    }

    /**
     * Test the addSwordToInventory method.
     * <p> This test is for the method without parameters (without name),
     * that creates common weapons. </p>
     */
    @Test
    void AddCommonSwordTest(){
        gameController.addSwordToInventory();
        Sword sameSword = new Sword("Common Sword", 110, 11);
        gameController.setSelectedWeapon(gameController.getInventorySize()-1);
        assertEquals(sameSword, gameController.getSelectedWeapon());
    }

    /**
     * Test the addStaffToInventory method.
     * <p> This test is for the method without parameters (without name),
     * that creates common weapons. </p>
     */
    @Test
    void AddCommonStaffTest(){
        gameController.addStaffToInventory();
        Staff sameStaff = new Staff("Common Staff", 10, 11, 120);
        gameController.setSelectedWeapon(gameController.getInventorySize()-1);
        assertEquals(sameStaff, gameController.getSelectedWeapon());
    }

    /**
     * Test the addAxeToInventory method.
     * <p> This test is for the method without parameters (without name),
     * that creates common weapons. </p>
     */
    @Test
    void AddCommonAxeTest(){
        gameController.addAxeToInventory();
        Axe sameAxe = new Axe("Common Axe", 120, 13);
        gameController.setSelectedWeapon(gameController.getInventorySize()-1);
        assertEquals(sameAxe, gameController.getSelectedWeapon());
    }

    /**
     * Test the addKnifeToInventory method.
     * <p> This test is for the method without parameters (without name),
     * that creates common weapons. </p>
     *
     */
    @Test
    void AddCommonKnifeTest(){
        gameController.addKnifeToInventory();
        Knife sameKnife = new Knife("Common Knife", 100, 9);
        gameController.setSelectedWeapon(gameController.getInventorySize()-1);
        assertEquals(sameKnife, gameController.getSelectedWeapon());
    }

    /**
     * Test the addBowToInventory method.
     * <p> This test is for the method that receives a name as parameter. </p>
     */
    @Test
    void AddSpecialBowTest(){
        gameController.addBowToInventory("Buster Shot");
        Bow sameBow = new Bow("Buster Shot", 90, 10);
        gameController.setSelectedWeapon(gameController.getInventorySize()-1);
        assertEquals(sameBow, gameController.getSelectedWeapon());
    }

    /**
     * Test the addSwordToInventory method.
     * <p> This test is for the method that receives a name as parameter. </p>
     */
    @Test
    void AddSpecialSwordTest(){
        gameController.addSwordToInventory("Doom Blade");
        Sword sameSword = new Sword("Doom Blade", 110, 11);
        gameController.setSelectedWeapon(gameController.getInventorySize()-1);
        assertEquals(sameSword, gameController.getSelectedWeapon());
    }

    /**
     * Test the addAxeToInventory method.
     * <p> This test is for the method that receives a name as parameter. </p>
     */
    @Test
    void AddSpecialAxeTest(){
        gameController.addAxeToInventory("Atlas Axe");
        Axe sameAxe = new Axe("Atlas Axe", 120, 13);
        gameController.setSelectedWeapon(gameController.getInventorySize()-1);
        assertEquals(sameAxe, gameController.getSelectedWeapon());
    }


    /**
     * Test the addStaffToInventory method.
     * <p> This test is for the method that receives a name as parameter. </p>
     */
    @Test
    void AddSpecialStaffTest(){
        gameController.addStaffToInventory("Demon Rod");
        Staff sameStaff = new Staff("Demon Rod", 10, 11, 120);
        gameController.setSelectedWeapon(gameController.getInventorySize()-1);
        assertEquals(sameStaff, gameController.getSelectedWeapon());
    }

    /**
     * Test the addKnifeToInventory method.
     * <p> This test is for the method that receives a name as parameter. </p>
     */
    @Test
    void AddSpecialKnifeTest(){
        gameController.addKnifeToInventory("Chaos Breaker");
        Knife sameKnife = new Knife("Chaos Breaker", 100, 9);
        gameController.setSelectedWeapon(gameController.getInventorySize()-1);
        assertEquals(sameKnife, gameController.getSelectedWeapon());
    }

}


