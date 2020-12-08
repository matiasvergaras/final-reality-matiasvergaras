package com.github.cc3002.finalreality.model.character;
import com.github.matiasvergaras.finalreality.model.character.cpu.ICPUCharacter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Abstract class containing the common tests for all CPU characters.
 * @since Homework 2 - Partial 3
 * @author Matias Vergara Silva.
 * @see ICPUCharacter
 */
public abstract class AbstractCPUCharacterTest extends AbstractCharacterTest{
    //This far we have implemented only the Weight and damage getters

    /**
     * Checks that the CPU Character getWeight method works properly.
     *
     * @param character        the CPU Character to be tested
     * @param expectedWeight   the expected value of weight of this CPU Character.
     */
    protected void checkGetWeight(ICPUCharacter character, int expectedWeight) {
        assertEquals(character.getWeight(), expectedWeight);
    }

    /**
     * Checks that the CPU Character getPower method works properly.
     *
     * @param character        the CPU Character to be tested
     * @param expectedPower    the expected value of power of this CPU Character.
     */
    protected void checkGetPower(ICPUCharacter character, int expectedPower) {
        assertEquals(character.getPower(), expectedPower);
    }


}
