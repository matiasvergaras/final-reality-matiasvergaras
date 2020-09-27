package com.github.matiasvergaras.finalreality.model.character;

import com.github.matiasvergaras.finalreality.model.character.player.CharacterClass;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
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

  public void setParalysed();

  public void setPoisoned();

  public void setBurned();

  public void setHealed();

  public void receiveFireAttack();

  public void receiveHealAttack();

  public void receiveParalysisAttack();

  public void receivePoisonAttack();

  public void receiveThunderAttack();

  public void receiveNormalAttack();

}
