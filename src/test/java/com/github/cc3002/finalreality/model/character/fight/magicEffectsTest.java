package com.github.cc3002.finalreality.model.character.fight;

import com.github.cc3002.finalreality.model.abstractModelTest;
import com.github.matiasvergaras.finalreality.model.character.CPU.IEnemy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to test that the methods related to magic effects works properly.
 * <p>
 *     In the future, we will add tests to prove that those states can also be reached by
 *     magic attacks.
 * </p>
 * @see IEnemy
 * @author Matías Vergara Silva.
 */

public class magicEffectsTest extends abstractModelTest {

    /**
     * To test that state setters methods works properly.
     */
    @Test
    void stateTest(){
        assertEquals(exampleEnemy.getState(), "NORMAL");
        exampleEnemy.setBurned();
        assertEquals(exampleEnemy.getState(), "BURNED");
        exampleEnemy.setParalyzed();
        assertEquals(exampleEnemy.getState(), "PARALYZED");
        exampleEnemy.setPoisoned();
        assertEquals(exampleEnemy.getState(), "POISONED");
    }


    /**
     * To test that receiveHeal method works properly. We will test it with a ''heal Attack'' in the future.
     */
    @Test
    void healTest(){
        exampleKnight.receiveHeal();
        assertEquals(exampleKnight.getCurrentHP(), exampleKnight.getMaxHP()*1.3);
    }
}
