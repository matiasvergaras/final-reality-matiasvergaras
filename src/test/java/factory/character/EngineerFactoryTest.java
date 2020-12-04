package factory.character;

import com.github.matiasvergaras.finalreality.factory.Characters.EngineerFactory;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Engineer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to tests the features of the EngineerFactory class.
 *
 * @author Matias Vergara Silva.
 * @since Homework 2
 */
public class EngineerFactoryTest extends CharacterFactoryTest {
    Engineer expectedCharacter;
    EngineerFactory factory;

    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        super.turnSetUp();
        this.expectedCharacter = new Engineer(turns, charactersName, hp, dp);
        this.factory = new EngineerFactory(turns, hp, dp);
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
     * Checks that the methods that should not influence this factory productions works
     * properly.
     */
    @Test
    void factoryIneffectiveSetsTest() {
        checkIneffectiveSetMana(this.factory, 120);
        checkIneffectiveSetPower(this.factory, 1123);
        checkIneffectiveSetWeight(this.factory, 1212);
    }
}
