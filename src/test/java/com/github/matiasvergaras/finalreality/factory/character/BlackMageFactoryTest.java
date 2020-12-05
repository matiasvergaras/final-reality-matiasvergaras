package com.github.matiasvergaras.finalreality.factory.character;


import com.github.matiasvergaras.finalreality.factory.Characters.BlackMageFactory;
import com.github.matiasvergaras.finalreality.model.character.player.magic.BlackMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to tests the features of the BlackMageFactory class.
 *
 * @author Matias Vergara Silva.
 * @since Homework 2
 */
public class BlackMageFactoryTest extends CharacterFactoryTest {
    BlackMage expectedCharacter;
    BlackMageFactory factory;

    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        super.turnSetUp();
        this.expectedCharacter = new BlackMage(turns, charactersName, hp, dp, mana);
        this.factory = new BlackMageFactory(turns, hp, dp, mana);
    }


    /**
     * Checks that the factory create methods works properly.
     */
    @Test
    void factoryCreateTest() {
        checkFactoryCreate(this.factory, this.expectedCharacter);
    }

    /**
     * Checks that the factory HP setter method works properly.
     * <p> The first call to checkFactorySet is to set a new value. </p>
     * <p> The second one is to test what happens if the new value is the same as the default. </p>
     */
    @Test
    void factorySetHPTest() {
        checkFactorySetHP(this.factory, 120);
        checkFactorySetHP(this.factory, 120);
    }

    /**
     * Checks that the factory DP setter method works properly.
     * <p> The first call to checkFactorySet is to set a new value. </p>
     * <p> The second one is to test what happens if the new value is the same as the default. </p>
     */
    @Test
    void factorySetDPTest() {
        checkFactorySetDP(this.factory, 50);
        checkFactorySetDP(this.factory, 50);
    }

    /**
     * Checks that the factory Mana setter method works properly.
     * <p> The first call to checkFactorySet is to set a new value. </p>
     * <p> The second one is to test what happens if the new value is the same as the default. </p>
     */
    @Test
    void factorySetManaTest() {
        checkFactorySetMana(this.factory, 115);
        checkFactorySetMana(this.factory, 115);
    }

    /**
     * Checks that the methods that should not influence this factory productions works
     * properly.
     */
    @Test
    void factoryIneffectiveSetsTest() {
        checkIneffectiveSetPower(this.factory, 1123);
        checkIneffectiveSetWeight(this.factory, 1212);
    }


}
