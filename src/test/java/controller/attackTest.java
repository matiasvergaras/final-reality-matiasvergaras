package controller;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A class to check that the attack order of GameController works properly.
 * <p> Controller is in charge of avoiding friendly-fire, and we will check that.</p>
 */
public class attackTest {
    private GameController gameController;
    private LinkedBlockingQueue<ICharacter> turns;

    /**
     * Basic set-up: a turns queue and a Controller.
     */
    @BeforeEach
    void setUp() {
        turns = new LinkedBlockingQueue<>();
        gameController = new GameController();
    }

    /**
     * Test an effective attack from player to cpu.
     */
    @Test
    void EffectivePlayerToCPUAttackTest(){
        //Adds an Engineer to the player party and selects him.
        gameController.addEngineerToPlayerParty("Domingo Egg");
        gameController.setSelectedCharacterFromPlayerParty(gameController.getPlayerPartySize()-1);
        //Adds an Enemy to the CPU party and set him as attack target.
        gameController.addEnemyToCPUParty("Chaos");
        gameController.setAttackTargetFromCPUParty(gameController.getCPUPartySize()-1);
        //Sets bow factory to create the definitive bow with 10000 power.
        gameController.setSelectedWeaponFactory(0);
        gameController.setSelectedWeaponFactoryPower(10000);
        //Instantiate a definitive bow and add it to inventory. Select it.
        gameController.addBowToInventory();
        gameController.setSelectedWeapon(gameController.getInventorySize()-1);
        //Equip the definitive bow to the engineer that we added before
        gameController.equipSelectedWeaponToSelectedCharacter();
        //First we make sure that the enemy is alive before getting attacked
        assertTrue(gameController.getAttackTargetCharacter().isAlive());
        //Send attack message
        gameController.selectedCharacterNormalAttack(gameController.getAttackTargetCharacter());
        //Check that the enemy died.
        assertFalse(gameController.getAttackTargetCharacter().isAlive());
    }

    /**
     * Test a ineffective attack from player to player.
     */
    @Test
    void IneffectivePlayerToPlayerAttackTest(){
        //Adds an Engineer to the player party and selects him.
        gameController.addEngineerToPlayerParty("Domingo Egg");
        gameController.setSelectedCharacterFromPlayerParty(gameController.getPlayerPartySize()-1);
        //Adds an Thief to the player party and set him as attack target.
        gameController.addThiefToPlayerParty("Lowe");
        gameController.setAttackTargetFromPlayerParty(gameController.getPlayerPartySize()-1);
        //Sets bow factory to create the definitive bow with 10000 power.
        gameController.setSelectedWeaponFactory(0);
        gameController.setSelectedWeaponFactoryPower(10000);
        //Instantiate a definitive bow and add it to inventory. Select it.
        gameController.addBowToInventory();
        gameController.setSelectedWeapon(gameController.getInventorySize()-1);
        //Equip the definitive bow to the engineer that we added before
        gameController.equipSelectedWeaponToSelectedCharacter();
        //First we make sure that the enemy is alive before getting attacked
        assertTrue(gameController.getAttackTargetCharacter().isAlive());
        //Send attack message
        gameController.selectedCharacterNormalAttack(gameController.getAttackTargetCharacter());
        //Check that the thief is still alive (if he was attacked, he would be already dead).
        assertTrue(gameController.getAttackTargetCharacter().isAlive());
    }

    /**
     * Checks an effective attack from cpu to player.
     */
    @Test
    void EffectiveCPUToPlayerAttackTest(){
        //Adds an Engineer to the player party and selects him as target.
        gameController.addEngineerToPlayerParty("Domingo Egg");
        gameController.setAttackTargetFromPlayerParty(gameController.getPlayerPartySize()-1);
        //Sets the enemy factories to create mega powerful enemies.
        gameController.setSelectedCharacterFactory(5);
        gameController.setSelectedCharacterFactoryPower(10000);
        //Adds an Enemy to the CPU party and set him as selectedCharacter.
        gameController.addEnemyToCPUParty("Elliot");
        gameController.setSelectedCharacterFromCPUParty(gameController.getCPUPartySize()-1);
        //First we make sure that the engineer is alive before getting attacked
        assertTrue(gameController.getAttackTargetCharacter().isAlive());
        //Send attack message
        gameController.selectedCharacterNormalAttack(gameController.getAttackTargetCharacter());
        //Check that the engineer received the mega powerful attack (i.e. he died).
        assertFalse(gameController.getAttackTargetCharacter().isAlive());
    }

    /**
     * Checks an ineffective attack from cpu to player.
     */
    @Test
    void IneffectiveCPUToCPUAttackTest(){
        //Sets the enemy factories to create mega powerful enemies.
        gameController.setSelectedCharacterFactory(5);
        gameController.setSelectedCharacterFactoryPower(10000);
        //Adds an Enemy to the CPU party and set him as selectedCharacter.
        gameController.addEnemyToCPUParty("Elliot");
        gameController.setSelectedCharacterFromCPUParty(gameController.getCPUPartySize()-1);
        //Adds a second instance of the super Enemy to the CPU party. Select him as target.
        gameController.addEnemyToCPUParty("Kane");
        gameController.setAttackTargetFromCPUParty(gameController.getCPUPartySize()-1);
        //First we make sure that the engineer is alive before getting attacked
        assertTrue(gameController.getAttackTargetCharacter().isAlive());
        //Send attack message
        gameController.selectedCharacterNormalAttack(gameController.getAttackTargetCharacter());
        //Check that the second enemy did not receive the attack (i.e. he did not died).
        assertTrue(gameController.getAttackTargetCharacter().isAlive());
    }
}
