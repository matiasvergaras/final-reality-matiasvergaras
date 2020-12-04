package factory.character;

import com.github.matiasvergaras.finalreality.factory.Characters.WhiteMageFactory;
import com.github.matiasvergaras.finalreality.model.character.player.magic.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to tests the features of the WhiteMageFactory class.
 *
 * @author Matias Vergara Silva.
 * @since Homework 2
 */
public class WhiteMageFactoryTest extends CharacterFactoryTest {
    WhiteMage expectedCharacter;
    WhiteMageFactory factory;

    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        super.turnSetUp();
        this.expectedCharacter = new WhiteMage(turns, charactersName, hp, dp, mana);
        this.factory = new WhiteMageFactory(turns, hp, dp, mana);
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
