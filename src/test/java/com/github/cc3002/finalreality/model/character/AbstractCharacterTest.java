package com.github.cc3002.finalreality.model.character;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.github.cc3002.finalreality.model.abstractModelTest;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.Assertions;


/**
 * Abstract class containing the common tests for all the types of  playable characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author Matias Vergara Silva.
 * @see ICharacter
 */

  public abstract class AbstractCharacterTest extends abstractModelTest {
  protected List<ICharacter> testCharacters;

  /**
   * Checks that the character waits the appropriate amount of time for it's turn.
   */
  void checkTurns() {
    Assertions.assertTrue(turns.isEmpty());
    testCharacters.get(0).waitTurn();
    try {
      // Thread.sleep is not accurate so this values may be changed to adjust the
      // acceptable error margin.
      // We're testing that the character waits approximately 1 second.
      Thread.sleep(900);
      Assertions.assertEquals(0, turns.size());
      Thread.sleep(200);
      Assertions.assertEquals(1, turns.size());
      Assertions.assertEquals(testCharacters.get(0), turns.peek());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


  /**
   * Checks that the class' constructor and equals method works properly.
   * @param expectedCharacter
   *                          A character of this type
   * @param testEqualCharacter
   *                          A character of this type and with the same common fields
   *                          of expectedCharacter
   * @param sameClassDifferentCharacter
   *                          A character of this type with at least one field different
   *                          from those of expectedCharacter
   * @param differentClassCharacter
   *                          A character from another type with the same common  fields of
   *                          expectedCharacters
   */
  protected void checkConstruction(final ICharacter expectedCharacter,
                                   final ICharacter testEqualCharacter,
                                   final ICharacter sameClassDifferentCharacter,
                                   final ICharacter differentClassCharacter) {
    assertEquals(expectedCharacter, testEqualCharacter);
    assertNotEquals(sameClassDifferentCharacter, testEqualCharacter);
    assertNotEquals(testEqualCharacter, differentClassCharacter);
    assertEquals(expectedCharacter.hashCode(), testEqualCharacter.hashCode());
    assertNotEquals(expectedCharacter.hashCode(), differentClassCharacter.hashCode());
  }

  /**
   * Sets the basic set up for a test of characters.
   */
  protected void basicSetUp() {
    turnSetUp();
    testCharacters = new ArrayList<>();
  }
}
