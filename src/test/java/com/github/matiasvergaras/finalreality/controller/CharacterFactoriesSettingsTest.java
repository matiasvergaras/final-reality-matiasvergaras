package com.github.matiasvergaras.finalreality.controller;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Class to test that the SetSelectedFactory{Attribute} methods works properly.
 * <p> First we set a new value for each variable of the selectedCharacterFactory.
 * Then we add a new character from that factory to its correspondent party, we select it
 * and we test its values to be equal to the given by the new factory settings.</p>
 * @since Homework 2
 * @author Matías Vergara Silva
 */
public class CharacterFactoriesSettingsTest {
    private GameController gameController;



    /**
     * Sets a new configuration for the SelectedCharacterFactory.
     * @param index     The index of the factory to configure in the GameController CharacterFactories
     *                  list.
     * @param seed      An int that will be used to set new values.
     */
    void setFactoryForTesting(int index, int seed){
        gameController.setSelectedCharacterFactory(index);
        gameController.setSelectedCharacterFactoryHP(seed*10);
        gameController.setSelectedCharacterFactoryDP(seed*70);
        gameController.setSelectedCharacterFactoryMana(310/seed);
        gameController.setSelectedCharacterFactoryWeight(seed);
        gameController.setSelectedCharacterFactoryPower(seed*5);
    }

    /**
     * Tests that the SelectedCharacters has the correct attribute values,
     * given by the seed that was used to configure its factory.
     * <p> This test is specific to the Magic Characters. </p>
     * @param seed      The seed used to configure the factory. Has to be exactly the same.
     */
    void testFactorySettingForMagic(int seed){
        assertEquals(gameController.getSelectedCharacterCurrentHP(), seed*10);
        assertEquals(gameController.getSelectedCharacterMaxHP(), seed*10);
        assertEquals(gameController.getSelectedCharacterDP(), seed*70);
        assertEquals(gameController.getSelectedCharacterMaxMana(), 310/seed);
        assertEquals(gameController.getSelectedCharacterCurrentMana(), 310/seed);
        assertEquals(gameController.getSelectedCharacterWeight(), 0);
        assertEquals(gameController.getSelectedCharacterPower(), 0);
        assertNull(gameController.getSelectedCharacterEquippedWeapon());
    }

    /**
     * Tests that the SelectedCharacters has the correct attribute values,
     * given by the seed that was used to configure its factory.
     * <p> This test is specific to the Normal Characters. </p>
     * @param seed      The seed used to configure the factory. Has to be exactly the same.
     */
    void testFactorySettingForNormal(int seed){
        assertEquals(gameController.getSelectedCharacterCurrentHP(), seed*10);
        assertEquals(gameController.getSelectedCharacterMaxHP(), seed*10);
        assertEquals(gameController.getSelectedCharacterDP(), seed*70);
        assertEquals(gameController.getSelectedCharacterMaxMana(), 0);
        assertEquals(gameController.getSelectedCharacterCurrentMana(), 0);
        assertEquals(gameController.getSelectedCharacterWeight(), 0);
        assertEquals(gameController.getSelectedCharacterPower(), 0);
        assertNull(gameController.getSelectedCharacterEquippedWeapon());
    }

    /**
     * Tests that the SelectedCharacters has the correct attribute values,
     * given by the seed that was used to configure its factory.
     * <p> This test is specific to the CPU Characters. </p>
     * @param seed      The seed used to configure the factory. Has to be exactly the same.
     */
    void testFactorySettingForCPU(int seed){
        assertEquals(gameController.getSelectedCharacterCurrentHP(), seed*10);
        assertEquals(gameController.getSelectedCharacterMaxHP(), seed*10);
        assertEquals(gameController.getSelectedCharacterDP(), seed* 70);
        assertEquals(gameController.getSelectedCharacterMaxMana(), 0);
        assertEquals(gameController.getSelectedCharacterCurrentMana(), 0);
        assertEquals(gameController.getSelectedCharacterWeight(), seed);
        assertEquals(gameController.getSelectedCharacterPower(), seed*5);
        assertNull(gameController.getSelectedCharacterEquippedWeapon());
    }

    /**
     * Basic set-up: a GameController instance.
     */
    @BeforeEach
    void setUp(){
        gameController = new GameController("Cuddy", 4);
    }

    /**
     * Test for an EngineerFactory.
     */
    @Test
    void EngineerFactorySettingTest(){
        setFactoryForTesting(0, 3);
        gameController.addEngineerToPlayerParty("Vankar");
        gameController.setSelectedCharacterFromPlayerParty(gameController.getPlayerPartySize()-1);
        testFactorySettingForNormal(3);
    }

    /**
     * Test for a BlackMage Factory.
     */
    @Test
    void BlackMageFactorySettingTest(){
        setFactoryForTesting(1,6);
        gameController.addBlackMageToPlayerParty("Azelf");
        gameController.setSelectedCharacterFromPlayerParty(gameController.getPlayerPartySize()-1);
        testFactorySettingForMagic(6);
    }

    /**
     * Test for a WhiteMage Factory.
     */
    @Test
    void WhiteMageFactorySettingTest(){
        setFactoryForTesting(2, 7);
        gameController.addWhiteMageToPlayerParty("Tao");
        gameController.setSelectedCharacterFromPlayerParty(gameController.getPlayerPartySize()-1);
        testFactorySettingForMagic(7);
    }

    /**
     * Test for a Thief Factory.
     */
    @Test
    void ThiefFactorySettingTest(){
        setFactoryForTesting(3, 2);
        gameController.addThiefToPlayerParty("Hanzou");
        gameController.setSelectedCharacterFromPlayerParty(gameController.getPlayerPartySize()-1);
        testFactorySettingForNormal(2);
    }

    /**
     * Test for a Knight Factory.
     */
    @Test
    void KnightFactorySettingTest(){
        setFactoryForTesting(4,12);
        gameController.addKnightToPlayerParty("Gort");
        gameController.setSelectedCharacterFromPlayerParty(gameController.getPlayerPartySize()-1);
        testFactorySettingForNormal(12);
    }

    /**
     * Test for an Enemy Factory.
     */
    @Test
    void EnemyFactorySettingTest(){
        setFactoryForTesting(5, 9);
        gameController.addEnemyToCPUParty("Darksol");
        gameController.setSelectedCharacterFromCPUParty(gameController.getCPUPartySize()-1);
        testFactorySettingForCPU(9);
    }

    /**
     * Test the equal method of WeaponAttributeSet
     */
    @Test
    void equalHashCodeAttributeTest(){
        setFactoryForTesting(1, 5);
        ICharacter character = gameController.getSelectedCharacterFactory().create("Zylo");
        ICharacter copy = gameController.getSelectedCharacterFactory().create("Zylo");
        assertEquals(character.getAttributes(), copy.getAttributes());
        assertEquals(character.getAttributes().hashCode(), copy.getAttributes().hashCode());

    }



}
