package com.github.cc3002.finalreality.model.Mastermind;

import com.github.matiasvergaras.finalreality.model.Mastermind.CPUMastermind;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test a CPUMastermind.
 * @author Matías Vergara Silva
 * @since Homework 2
 */
public class CPUMastermindTest extends AbstractMastermindTest {

    /**
     * Basic set-up to test a mastermind.
     */
    @BeforeEach
    void CPUSetUp(){
        super.setUp();

    }

    /**
     * Test the constructor, equals and hashcode
     */
    @Test
    void equalsTest(){
        super.checkEquals(cpu, new CPUMastermind("CPU"), player);
    }

    /**
     * Test the AddToParty method.
     */
    @Test
    void addToPartyTest(){
        super.checkAddToParty(cpu, exampleEnemy);
    }

    /**
     * Test the getParty and getPartySize methods.
     */
    @Test
    void partyGettersTest(){
        super.checkPartyGetters(3, enemyFactory, cpu);
    }

    /**
     * Test the removeFromParty method.
     */
    @Test
    void removeFromPartyTest(){
        super.checkRemoveFromParty(exampleEnemy, cpu);
    }

    /**
     * Test the getCharacterFromParty method.
     */
    @Test
    void getCharacterFromPartyTest(){
        super.checkGetCharacterFromParty(exampleEnemy, cpu);
    }

    /**
     * Test the makeNormalAttack test adding the character to the party.
     */
    @Test
    void UnarmoredEffectiveAttackTest(){
        super.checkUnarmoredEffectiveAttack(exampleEnemy, weakEngineer, cpu);
    }

    /**
     * Test the makeNormalAttack without adding the character to the party.
     */
    @Test
    void UnarmoredIneffectiveAttackTest(){
        super.checkUnarmoredIneffectiveAttack(exampleEnemy, weakEngineer, cpu);
    }

}
