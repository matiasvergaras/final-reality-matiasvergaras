package com.github.cc3002.finalreality.controller;

import com.github.matiasvergaras.finalreality.controller.GameController;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.weapon.NullWeapon;
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
    private GameController gc;



    /**
     * Sets a new configuration for the SelectedCharacterFactory.
     * @param index     The index of the factory to configure in the GameController CharacterFactories
     *                  list.
     * @param seed      An int that will be used to set new values.
     */
    void setFactoryForTesting(int index, int seed){
        gc.setSelectedCharacterFactory(index);
        gc.setSelectedCharacterFactoryHP(seed*10);
        gc.setSelectedCharacterFactoryDP(seed*70);
        gc.setSelectedCharacterFactoryMana(310/seed);
        gc.setSelectedCharacterFactoryWeight(seed);
        gc.setSelectedCharacterFactoryPower(seed*5);
    }

    /**
     * Tests that the SelectedCharacters has the correct attribute values,
     * given by the seed that was used to configure its factory.
     * <p> This test is specific to the Magic Characters. </p>
     * @param seed      The seed used to configure the factory. Has to be exactly the same.
     */
    void testFactorySettingForMagic(int seed){
        assertEquals(gc.getCharacterCurrentHP(gc.getSelectedCharacter()), seed*10);
        assertEquals(gc.getCharacterMaxHP(gc.getSelectedCharacter()), seed*10);
        assertEquals(gc.getCharacterDP(gc.getSelectedCharacter()), seed*70);
        assertEquals(gc.getCharacterMaxMana(gc.getSelectedCharacter()), 310/seed);
        assertEquals(gc.getCharacterCurrentMana(gc.getSelectedCharacter()), 310/seed);
        assertEquals(gc.getCharacterWeight(gc.getSelectedCharacter()), 0);
        assertEquals(gc.getCharacterPower(gc.getSelectedCharacter()), 0);
        assertEquals(gc.getCharacterEquippedWeapon(gc.getSelectedCharacter()), new NullWeapon());
    }

    /**
     * Tests that the SelectedCharacters has the correct attribute values,
     * given by the seed that was used to configure its factory.
     * <p> This test is specific to the Normal Characters. </p>
     * @param seed      The seed used to configure the factory. Has to be exactly the same.
     */
    void testFactorySettingForNormal(int seed){
        assertEquals(gc.getCharacterCurrentHP(gc.getSelectedCharacter()), seed*10);
        assertEquals(gc.getCharacterMaxHP(gc.getSelectedCharacter()), seed*10);
        assertEquals(gc.getCharacterDP(gc.getSelectedCharacter()), seed*70);
        assertEquals(gc.getCharacterMaxMana(gc.getSelectedCharacter()), 0);
        assertEquals(gc.getCharacterCurrentMana(gc.getSelectedCharacter()), 0);
        assertEquals(gc.getCharacterWeight(gc.getSelectedCharacter()), 0);
        assertEquals(gc.getCharacterPower(gc.getSelectedCharacter()), 0);
        assertEquals(gc.getCharacterEquippedWeapon(gc.getSelectedCharacter()), new NullWeapon());
    }

    /**
     * Tests that the SelectedCharacters has the correct attribute values,
     * given by the seed that was used to configure its factory.
     * <p> This test is specific to the CPU Characters. </p>
     * @param seed      The seed used to configure the factory. Has to be exactly the same.
     */
    void testFactorySettingForCPU(int seed){
        assertEquals(gc.getCharacterCurrentHP(gc.getSelectedCharacter()), seed*10);
        assertEquals(gc.getCharacterMaxHP(gc.getSelectedCharacter()), seed*10);
        assertEquals(gc.getCharacterDP(gc.getSelectedCharacter()), seed* 70);
        assertEquals(gc.getCharacterMaxMana(gc.getSelectedCharacter()), 0);
        assertEquals(gc.getCharacterCurrentMana(gc.getSelectedCharacter()), 0);
        assertEquals(gc.getCharacterWeight(gc.getSelectedCharacter()), seed);
        assertEquals(gc.getCharacterPower(gc.getSelectedCharacter()), seed*5);
        assertEquals(gc.getCharacterEquippedWeapon(gc.getSelectedCharacter()), new NullWeapon());
    }

    /**
     * Basic set-up: a GameController instance.
     */
    @BeforeEach
    void setUp(){
        gc = new GameController("Cuddy", "Metapha", 4);
    }

    /**
     * Test for an EngineerFactory.
     */
    @Test
    void EngineerFactorySettingTest(){
        setFactoryForTesting(0, 3);
        gc.setSelectedCharacterFactory(0);
        gc.selectedCharacterFactoryProduce("Vankar");
        gc.setSelectedCharacterFromPlayerParty(gc.getPlayerPartySize()-1);
        testFactorySettingForNormal(3);
    }

    /**
     * Test for a BlackMage Factory.
     */
    @Test
    void BlackMageFactorySettingTest(){
        setFactoryForTesting(1,6);
        gc.setSelectedCharacterFactory(1);
        gc.selectedCharacterFactoryProduce("Azelf");
        gc.setSelectedCharacterFromPlayerParty(gc.getPlayerPartySize()-1);
        testFactorySettingForMagic(6);
    }

    /**
     * Test for a WhiteMage Factory.
     */
    @Test
    void WhiteMageFactorySettingTest(){
        setFactoryForTesting(2, 7);
        gc.setSelectedCharacterFactory(2);
        gc.selectedCharacterFactoryProduce("Tao");
        gc.setSelectedCharacterFromPlayerParty(gc.getPlayerPartySize()-1);
        testFactorySettingForMagic(7);
    }

    /**
     * Test for a Thief Factory.
     */
    @Test
    void ThiefFactorySettingTest(){
        setFactoryForTesting(3, 2);
        gc.setSelectedCharacterFactory(3);
        gc.selectedCharacterFactoryProduce("Hanzou");
        gc.setSelectedCharacterFromPlayerParty(gc.getPlayerPartySize()-1);
        testFactorySettingForNormal(2);
    }

    /**
     * Test for a Knight Factory.
     */
    @Test
    void KnightFactorySettingTest(){
        setFactoryForTesting(4,12);
        gc.setSelectedCharacterFactory(4);
        gc.selectedCharacterFactoryProduce("Gort");
        gc.setSelectedCharacterFromPlayerParty(gc.getPlayerPartySize()-1);
        testFactorySettingForNormal(12);
    }

    /**
     * Test for an Enemy Factory.
     */
    @Test
    void EnemyFactorySettingTest(){
        setFactoryForTesting(5, 9);
        gc.addEnemyToCPU("Darksol");
        gc.setSelectedCharacterFromCPUParty(gc.getCPUPartySize()-1);
        testFactorySettingForCPU(9);
    }

    /**
     * Test the equal method of WeaponAttributeSet
     */
    @Test
    void equalHashCodeAttributeTest(){
        setFactoryForTesting(1, 5);
        ICharacter character = gc.getSelectedCharacterFactory().create("Zylo");
        ICharacter copy = gc.getSelectedCharacterFactory().create("Zylo");
        assertEquals(character.getAttributes(), copy.getAttributes());
        assertEquals(character.getAttributes().hashCode(), copy.getAttributes().hashCode());

    }



}
