package com.github.matiasvergaras.finalreality.model.character;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Matias Vergara Silva.
 */
public abstract class AbstractCharacter implements ICharacter {

  private final BlockingQueue<ICharacter> turnsQueue;
  private final String characterClass;
  private final String name;
  protected ScheduledExecutorService scheduledExecutor;
 /* protected final StateType state;
  private final int currentHP;
  private final int maxHP;
  private final int currentDP;
  private final int maxDP; */


  /**
   * Creates a character with turns queue, name and class.
   *
   */
  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                              @NotNull String name, @NotNull String characterClass
                              ) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.characterClass = characterClass;
    /*this.state = state;
    this.maxHP = HP;
    this.currentHP = HP ;
    this.currentDP = DP;
    this.maxDP = DP; */
}


  /**
   * Adds this character to the turns queue.
   */
  protected void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  /**
   * {@inheritDoc}
   *
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * {@inheritDoc}
   *
   */
  @Override
  public int hashCode() {
    return Objects.hash(getCharacterClass());
  }

  /**
   * {@inheritDoc}
   *
   */
  @Override
  public String getCharacterClass() {
    return characterClass;
  }


  /**
   * {@inheritDoc}
   *
   */
  @Override
  public void setParalysed() {

  }

  /**
   * {@inheritDoc}
   * @param damage
   *              damage to apply in each turn, already divided by 3.
   */
  @Override
  public void setPoisoned(int damage) {

  }

  /**
   * {@inheritDoc}
   * @param damage
   *              damage to apply in each turn, already divided by 2.
   */
  @Override
  public void setBurned(int damage) {

  }

  /**
   * {@inheritDoc}
   *
   */
  @Override
  public void setHealed() {

  }

}



