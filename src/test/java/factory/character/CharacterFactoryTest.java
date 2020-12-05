package factory.character;

import com.github.matiasvergaras.finalreality.factory.Characters.ICharacterFactory;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.cpu.ICPUCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.magic.IMagicCharacter;

import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class containing the common tests for all types of  Character Factories.
 *
 * @author Matias Vergara Silva.
 * @see ICharacterFactory
 * @since Homework 2
 */
public abstract class CharacterFactoryTest {
    protected LinkedBlockingQueue<ICharacter> turns;
    protected int hp = 100;
    protected int dp = 80;
    protected int mana = 120;
    protected int weight = 12;
    protected int power = 130;

    protected String charactersName = "FACTORY TEST SUBJECT";

    /**
     * Basic set-up : A turns queue
     */
    protected void turnSetUp() {
        turns = new LinkedBlockingQueue<>();
    }

    /**
     * A method to check if the factory produces the expected character.
     */
    protected void checkFactoryCreate(ICharacterFactory testFactory, ICharacter expectedCharacter){
        assertEquals(testFactory.create(charactersName), expectedCharacter, "CharacterFactory created " +
                "something different from the given expected character.");
    }

    /**
     * A method to check that the FactorySetHP method sets the new default HP correctly.
     */
    protected void checkFactorySetHP(ICharacterFactory testFactory, int newHP){
        ICharacter initialProduction = testFactory.create(charactersName);
        testFactory.setHP(newHP);
        ICharacter finalProduction = testFactory.create(charactersName);
        if(initialProduction.getMaxHP()!=newHP) {
            assertNotEquals(initialProduction.getMaxHP(), finalProduction.getMaxHP(), "The factory products " +
                    "did not change after using setHP");
        }
        else{
            assertEquals(initialProduction.getMaxHP(), finalProduction.getMaxHP(),
                    "Factory products did change, but the new value was the same.");
        }
        assertEquals(newHP, finalProduction.getMaxHP(), "The factory new default HP value was not set" +
                "up properly.");

    }

    /**
     * A method to check that the FactorySetDP method sets the new default DP correctly.
     */
    protected void checkFactorySetDP(ICharacterFactory testFactory, int newDP) {
        ICharacter initialProduction = testFactory.create(charactersName);
        testFactory.setDP(newDP);
        ICharacter finalProduction = testFactory.create(charactersName);
        if (initialProduction.getDP() != newDP) {
            assertNotEquals(initialProduction.getDP(), finalProduction.getDP(), "The factory products " +
                    "did not change after using setHP");
        } else {
            assertEquals(initialProduction.getDP(), finalProduction.getDP(),
                    "Factory products did change, but the new value was the same.");
        }
        assertEquals(newDP, finalProduction.getDP(), "The factory new default DP value was not set" +
                "up properly.");

    }

    /**
     * A method to check that the FactorySetMana method sets the new default Mana correctly.
     * It has to be used with a magic player character factory.
     */
    protected void checkFactorySetMana(ICharacterFactory testFactory, int newMana) {
        IMagicCharacter initialProduction = (IMagicCharacter) testFactory.create(charactersName);
        testFactory.setMana(newMana);
        IMagicCharacter finalProduction = (IMagicCharacter) testFactory.create(charactersName);
        if (initialProduction.getMaxMana() != newMana) {
            assertNotEquals(initialProduction.getMaxMana(), finalProduction.getMaxMana(), "The factory products " +
                    "did not change after using setHP");
        } else {
            assertEquals(initialProduction.getMaxMana(), finalProduction.getMaxMana(),
                    "Factory products did change, but the new value was the same.");
        }
        assertEquals(newMana, finalProduction.getMaxMana(), "The factory new default Mana value was not set" +
                "up properly.");
    }

    /**
     * A method to check that the FactorySetWeight method sets the new default Weight correctly.
     * It has to be used with a cpu character's factory.
     */
    protected void checkFactorySetWeight(ICharacterFactory testFactory, int newWeight){
        ICPUCharacter initialProduction = (ICPUCharacter) testFactory.create(charactersName);
        testFactory.setWeight(newWeight);
        ICPUCharacter finalProduction = (ICPUCharacter) testFactory.create(charactersName);
        if (initialProduction.getWeight() != newWeight) {
            assertNotEquals(initialProduction.getWeight(), finalProduction.getWeight(), "The factory products " +
                    "did not change after using setHP");
        } else {
            assertEquals(initialProduction.getWeight(), finalProduction.getWeight(),
                    "Factory products did change, but the new value was the same.");
        }
        assertEquals(newWeight, finalProduction.getWeight(), "The factory new default Mana value was not set" +
                "up properly.");
    }

    /**
     * A method to check that the FactorySetPower method sets the new default Power correctly.
     * It has to be used with a cpu character's factory.
     */
    protected void checkFactorySetPower(ICharacterFactory testFactory, int newPower){
        ICPUCharacter initialProduction = (ICPUCharacter) testFactory.create(charactersName);
        testFactory.setPower(newPower);
        ICPUCharacter finalProduction = (ICPUCharacter) testFactory.create(charactersName);
        if (initialProduction.getPower() != newPower) {
            assertNotEquals(initialProduction.getPower(), finalProduction.getPower(), "The factory products " +
                    "did not change after using setHP");
        } else {
            assertEquals(initialProduction.getPower(), finalProduction.getPower(),
                    "Factory products did change, but the new value was the same.");
        }
        assertEquals(newPower, finalProduction.getPower(), "The factory new default Mana value was not set" +
                "up properly.");
    }

    /**
     * A method to check that the FactorySetPower method does not interfere whit the creations of the Factory if they
     * dont use the Power attribute
     * <p> It has to be used with a no-cpu characters factory. </p>
     */
    protected void checkIneffectiveSetPower(ICharacterFactory testFactory, int newPower){
        ICharacter initialProduction = testFactory.create(charactersName);
        testFactory.setPower(newPower);
        ICharacter finalProduction = testFactory.create(charactersName);
        assertEquals(initialProduction.getAttributes(), finalProduction.getAttributes(), "Changed a parameter" +
                "that were not supposed to influence this production, but it did.");
    }

    /**
     * A method to check that the FactorySetWeight method does not interfere whit the creations of the Factory if they
     * dont use the Weight attribute.
     * <p> It has to be used with a no-cpu characters factory. </p>
     */
    protected void checkIneffectiveSetWeight(ICharacterFactory testFactory, int newWeight){
        ICharacter initialProduction = testFactory.create(charactersName);
        testFactory.setWeight(newWeight);
        ICharacter finalProduction = testFactory.create(charactersName);
        assertEquals(initialProduction.getAttributes(), finalProduction.getAttributes(), "Changed a parameter" +
                "that were not supposed to influence this production, but it did.");
    }


    /**
     * A method to check that the FactorySetMana method does not interfere whit the creations of the Factory if they
     * dont use the Mana attribute.
     * <p> It has to be used with a no-magic characters factory. </p>
     */
    protected void checkIneffectiveSetMana(ICharacterFactory testFactory, int newMana){
        ICharacter initialProduction = testFactory.create(charactersName);
        testFactory.setMana(newMana);
        ICharacter finalProduction = testFactory.create(charactersName);
        assertEquals(initialProduction.getAttributes(), finalProduction.getAttributes(), "Changed a parameter" +
                "that were not supposed to influence this production, but it did.");
    }

}


