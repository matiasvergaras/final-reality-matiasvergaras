package factory.character;

import com.github.matiasvergaras.finalreality.factory.Characters.EnemyFactory;
import com.github.matiasvergaras.finalreality.model.character.cpu.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to tests the features of the EnemyFactory class.
 *
 * @author Matias Vergara Silva.
 * @since Homework 2
 */
public class EnemyFactoryTest extends CharacterFactoryTest {
    Enemy expectedCharacter;
    EnemyFactory factory;

    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        super.turnSetUp();
        this.expectedCharacter = new Enemy(turns, charactersName, hp, dp, weight, power);
        this.factory = new EnemyFactory(turns, hp, dp, weight, power);
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
     * Checks that the factory Weight setter method works properly.
     * <p> The first call to checkFactorySet is to set a new value. </p>
     * <p> The second one is to test what happens if the new value is the same as the default. </p>
     */
    @Test
    void factorySetWeightTest() {
        checkFactorySetWeight(this.factory, 12);
        checkFactorySetWeight(this.factory, 12);

    }

    /**
     * Checks that the factory Power setter method works properly.
     * <p> The first call to checkFactorySet is to set a new value. </p>
     * <p> The second one is to test what happens if the new value is the same as the default. </p>
     */
    @Test
    void factorySetPowerTest() {
        checkFactorySetPower(this.factory, 158);
        checkFactorySetPower(this.factory, 158);
    }

    /**
     * Checks that the methods that should not influence this factory productions works
     * properly.
     */
    @Test
    void factoryIneffectiveSetsTest() {
        checkIneffectiveSetMana(this.factory, 1123);
    }

}

