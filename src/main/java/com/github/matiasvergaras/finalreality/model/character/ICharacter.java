package com.github.matiasvergaras.finalreality.model.character;

import com.github.matiasvergaras.finalreality.model.character.player.CharacterClass;

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
   * Returns this character's name.
   */
  String getName();

  /**
   * Returns this character's class.
   */
  CharacterClass getCharacterClass();

  /**
   * Sets this character in paralysed state.
   */
  void setParalysed();

  /**
   * Sets this character in poisoned state.
   * @param damage
   *              damage to apply in each turn (already divided by 3)
   */
  void setPoisoned(int damage);

  /**
   * Sets this character in burned state.
   * @param damage
   *               damage to apply in each turn (already divided by 2)
   */
  void setBurned(int damage);

  /**
   * Heals this unit
   */
  void setHealed();

  /**
   * I can swear that I dont have any idea of wtf is this
   */
  int hashCode();

}
