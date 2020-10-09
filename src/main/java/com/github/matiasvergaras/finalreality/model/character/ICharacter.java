package com.github.matiasvergaras.finalreality.model.character;

import com.github.matiasvergaras.finalreality.model.character.CPU.Enemy;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author Matías Vergara Silva.
 */
public interface ICharacter {

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  void waitTurn();

  /**
   * A method to get the name of this character.
   * @return this character's name.
   */
  String getName();

  /**
   * A method to get the class of this character.
   * @return  this character's class.
   */
  String getCharacterClass();

  /**
   * A method to get the HP of this character.
   * @return this character's HP.
   */
  int getHP();

  /**
   * A method to get the DP of this character.
   * @return this character's DP.
   */
  int getDP();
  }





