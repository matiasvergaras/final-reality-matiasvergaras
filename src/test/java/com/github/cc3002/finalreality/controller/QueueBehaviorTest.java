package com.github.cc3002.finalreality.controller;

import com.github.matiasvergaras.finalreality.controller.GameController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A class to check some border cases of the queue behavior.
 */
public class QueueBehaviorTest {
    GameController gc;


    /**
     * Test what happens if a character is add to the queue
     * while there is an active turn.
     */
    @Test
    void addWhileInTurnTest() throws InterruptedException {
        gc = new GameController("Player", "CPU", 1);
        assertTrue(gc.isInitializing());
        gc.setSelectedCharacterFactory(0);
        //Adds an Engineer to the player party and selects him.
        gc.selectedCharacterFactoryProduce("Domingo Egg");
        gc.setSelectedCharacterFromPlayerParty(0);
        //Adds an Thief to the player party and set him as attack target.
        gc.setSelectedCharacterFactory(5);
        gc.setSelectedCharacterFactoryWeight(30);
        gc.addEnemyToCPU("Balbazak");
        //Sets bow factory to create the definitive bow with 10000 power.
        gc.setSelectedWeaponFactory(0);
        gc.setSelectedWeaponFactoryPower(10000);
        //Instantiate a definitive bow and add it to inventory. Select it.
        gc.selectedWeaponFactoryProduce("Strong Bow");
        gc.setSelectedWeapon(0);
        //Equip the definitive bow to Domingo the Engineer
        gc.equipSelectedWeaponToSelectedCharacter();
        gc.startGame();
        Thread.sleep(2000);
        //Checks that the active character is Domingo
        assertEquals(gc.getActiveCharacter().getName(), "Domingo Egg");
        //Check that Balbazak has not been added yet
        assertEquals(gc.getTurns().size(), 1);
        Thread.sleep(3000);
        //Checks that the Balbazak was added but the active character is still Domingo
        assertEquals(gc.getTurns().size(), 2);
        assertEquals(gc.getActiveCharacter().getName(), "Domingo Egg");
    }


    /**
     * Test what happens if a character is add to the queue
     * while there is an active turn.
     * <p> This also works as a good ''common game'' test, since here characters
     * do not die at the first hit. </p>
     */
    @Test
    void addWhileInTurnWithQueueNotEmptyTest() throws InterruptedException {
        gc = new GameController("Player", "CPU", 2);
        assertTrue(gc.isInitializing());
        //Adds an Engineer to the player party and selects him.
        gc.setSelectedCharacterFactory(0);
        gc.selectedCharacterFactoryProduce("Domingo Egg");
        gc.selectedCharacterFactoryProduce("Gort");
        //Adds an Thief to the player party and set him as attack target.
        gc.setSelectedCharacterFactory(5);
        gc.setSelectedCharacterFactoryWeight(40);
        gc.addEnemyToCPU("Balbazak");
        //Adds weapons for each character. Bow is faster than axe.
        gc.setSelectedWeaponFactory(0);
        gc.selectedWeaponFactoryProduce("Strong Bow");
        gc.setSelectedWeaponFactory(4);
        gc.selectedWeaponFactoryProduce("Strong Axe");
        //Equip the  bow to Domingo The Egg
        gc.setSelectedWeapon(0);
        gc.setSelectedCharacterFromPlayerParty(0);
        gc.equipSelectedWeaponToSelectedCharacter();
        //Equip the axe to Gort
        gc.setSelectedWeapon(1);
        gc.setSelectedCharacterFromPlayerParty(1);
        gc.equipSelectedWeaponToSelectedCharacter();
        gc.startGame();
        assertTrue(gc.isSettingNewTurn());
        Thread.sleep(1500);
        //Checks that the active character is Domingo
        assertEquals(gc.getActiveCharacter().getName(), "Domingo Egg");
        //Check that Gort and Balbazak have not been added yet
        assertEquals(gc.getTurns().size(), 1);
        Thread.sleep(2000);
        //Checks that the Gort was added but the active character is still Domingo
        assertEquals(gc.getTurns().size(), 2);
                    //System.out.println(gc.getTurns().poll().getName());
                    //System.out.println(gc.getTurns().poll().getName());
        assertEquals(gc.getActiveCharacter().getName(), "Domingo Egg");
        Thread.sleep(2000);
                    //System.out.println(gc.getTurns().poll().getName());
        //Checks that the Balbazak was added but the active character is still Domingo
        assertEquals(gc.getTurns().size(), 3);
        assertEquals(gc.getActiveCharacter().getName(), "Domingo Egg");
        //Init an attack
        gc.initAttackMove();
        assertTrue(gc.isSelectingAttackTarget());
        //Checks that if an attack is performed, then the next character in the queue
        //will be the next active character (should be Gort since he arrived the 2nd)
        gc.setSelectedCharacterFromCPUParty(0);
        gc.activeCharacterNormalAttackSelectedCharacter();
        assertEquals(gc.getActiveCharacter().getName(), "Gort");
        //Checks that if an attack is performed, then the next character in the game
        //will be the next active character (should be Balbazak since he arrived the 3rd)
        gc.initAttackMove();
        gc.activeCharacterNormalAttackSelectedCharacter();
        assertEquals(gc.getActiveCharacter().getName(), "Balbazak");
        //Make Balbazak attack in order to end his turn.
        gc.setSelectedCharacterFromPlayerParty(0);
        gc.activeCharacterNormalAttackSelectedCharacter();
        //Now the queue should be empty for some seconds. Let's check that.
        assertTrue(gc.getTurns().isEmpty());
        //Send the thread to sleep for a while, in order to see if a new turn starts
        //automatically (via observer of the queue).
        Thread.sleep(2000);
        //The first character to come back to the queue should be Domingo
        assertEquals(gc.getActiveCharacter().getName(), "Domingo Egg");
    }


}
