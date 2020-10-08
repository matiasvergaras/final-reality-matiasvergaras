package com.github.cc3002.finalreality.model.character;

import com.github.matiasvergaras.finalreality.model.character.CPU.Enemy;
import com.github.matiasvergaras.finalreality.model.character.player.Normal.Engineer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Goblin";

  /**
   * Sets up an instance to test this class.
   */
  @BeforeEach
  void setUp() {
    basicSetUp();
    testCharacters.add(new Enemy(turns, ENEMY_NAME, 10, 200, 100));
  }

  /**
   * Checks that the class' constructor and equals method works properly.
   */
  @Test
  void constructorTest() {
    checkConstruction(new Enemy(turns, ENEMY_NAME, 10, 200, 100),
        testCharacters.get(0),
        new Enemy(turns, ENEMY_NAME, 11, 200, 100),
        new Engineer(turns, ENEMY_NAME, 11, 200));
  }
}