package com.github.cc3002.finalreality.factory.weapon;
import com.github.matiasvergaras.finalreality.factory.Weapons.IWeaponFactory;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.magic.IMagicWeapon;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Abstract class containing the common tests for all types of  Weapon Factories.
 *
 * @author Matias Vergara Silva.
 * @see IWeaponFactory
 * @since Homework 2
 */
public abstract class WeaponFactoryTest {
    protected BlockingQueue<ICharacter> turns;
    protected String weaponsName = "TEST WEAPON";

    /**
     * A method to check if the factory produces the expected weapon.
     */
    protected void checkFactoryCreate(IWeaponFactory testFactory, IWeapon expectedWeapon){
        assertEquals(testFactory.create(weaponsName), expectedWeapon, "WeaponFactory create with name method" +
                " created something different from the given expected character.");
        assertEquals(testFactory.create(weaponsName), expectedWeapon,"WeaponFactory create without parameters method " +
                "created something different from the given expected character." );
    }

    /**
     * A method to check that the FactorySetWeight method sets the new default weight correctly.
     */
    protected void checkFactorySetWeight(IWeaponFactory testFactory, int newWeight){
        IWeapon initialProduction = testFactory.create(weaponsName);
        testFactory.setWeight(newWeight);
        IWeapon finalProduction = testFactory.create(weaponsName);
        if(initialProduction.getWeight()!=newWeight){
            assertNotEquals(initialProduction.getWeight(), finalProduction.getWeight(), "The " +
                    "factory products did not change after using setWeight.");
        }
        else{
            assertEquals(initialProduction.getWeight(), finalProduction.getWeight(), "Factory products" +
                    "did change after using setWeight, but they should not have.");
        }
        assertEquals(newWeight, finalProduction.getWeight(), "The factory new default Weight value was not set" +
                "up properly.");
    }

    /**
     * A method to check that the FactorySetPower method sets the new default Power correctly.
     */
    protected void checkFactorySetPower(IWeaponFactory testFactory, int newPower){
        IWeapon initialProduction = testFactory.create(weaponsName);
        testFactory.setPower(newPower);
        IWeapon finalProduction = testFactory.create(weaponsName);
        if(initialProduction.getPower()!=newPower){
            assertNotEquals(initialProduction.getPower(), finalProduction.getPower(), "The " +
                    "factory products did not change after using setPower.");
        }
        else{
            assertEquals(initialProduction.getPower(), finalProduction.getPower(), "Factory products" +
                    "did change after using setPower, but they should not have.");
        }
        assertEquals(newPower, finalProduction.getPower(), "The factory new default Power value was not set" +
                "up properly.");
    }

    /**
     * A method to check that the FactorySetMagicPower method sets the new default MagicPower correctly.
     */
    protected void checkFactorySetMagicPower(IWeaponFactory testFactory, int newMagicPower){
        IMagicWeapon initialProduction = (IMagicWeapon) testFactory.create(weaponsName);
        testFactory.setMagicPower(newMagicPower);
        IMagicWeapon finalProduction = (IMagicWeapon) testFactory.create(weaponsName);
        if(initialProduction.getMagicPower()!=newMagicPower){
            assertNotEquals(initialProduction.getMagicPower(), finalProduction.getMagicPower(), "The " +
                    "factory products did not change after using setMagicPower.");
        }
        else{
            assertEquals(initialProduction.getMagicPower(), finalProduction.getMagicPower(), "Factory products" +
                    "did change after using setMagicPower, but they should not have.");
        }
        assertEquals(newMagicPower, finalProduction.getMagicPower(), "The factory new default " +
                "MagicDamage value was not set up properly.");
    }


    /**
     * A method to check that the FactorySetMagicPower method does not have effect whit the creations of the Factory
     * if they dont use the MagicPower attribute.
     * <p> It has to be used with a no-magic weapons factory. </p>
     */
    protected void checkIneffectiveSetMagicPower(IWeaponFactory testFactory, int newMagicPower){
        IWeapon initialProduction = testFactory.create(weaponsName);
        testFactory.setMagicPower(newMagicPower);
        IWeapon finalProduction = testFactory.create(weaponsName);
        assertEquals(initialProduction, finalProduction, "Changed a parameter" +
                "that should not influence this production, but it did.");
    }
}
