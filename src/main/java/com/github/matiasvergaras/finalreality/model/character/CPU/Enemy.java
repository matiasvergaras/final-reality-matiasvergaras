package com.github.matiasvergaras.finalreality.model.character.CPU;

import com.github.matiasvergaras.finalreality.model.character.AbstractCharacter;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author <Your name>
 */
public class Enemy extends AbstractCharacter {

  private final int weight;

  /**
   * Creates a new Enemy Character.
   * @param turnsQueue
   *     the queue with the characters ready to play
   * @param name
   *     the character's name
   * @param HP
   *     the character's heal points
   * @param DP
   *     the character's defense points
   */

  public Enemy(@NotNull final String name, final int weight,
               @NotNull final BlockingQueue<ICharacter> turnsQueue,
               @NotNull int HP, @NotNull int DP) {
    super(turnsQueue, name, "ENEMY", HP, DP);
    this.weight = weight;
  }


  /**
   * Check if this is equal to a given object o.
   * @param o The target object
   * @return True if are equals, false otherwise
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy that = (Enemy) o;
    return this.getWeight() == that.getWeight() &&
            this.getName().equals(that.getName()) &&
            this.getCharacterClass().equals(that.getCharacterClass()) &&
            this.getDP() == that.getDP() &&
            this.getHP() == that. getHP();
  }



  @Override
  public int hashCode() {
    return Objects.hash(getWeight());
  }



  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor
            .schedule(super::addToQueue, this.getWeight() / 10, TimeUnit.SECONDS);
  }

  /**
   * Returns the weight of this enemy.
   */
  public int getWeight() {
    return weight;
  }


  }


