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
  private final int HP;
  private final int DP;
  protected ScheduledExecutorService scheduledExecutor;


  /**
   * Creates a character with turns queue, name and class.
   */
  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                              @NotNull String name, @NotNull String characterClass,
                              int HP,  int DP)
  {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.characterClass = characterClass;
    this.HP = HP;
    this.DP = DP;
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
   */
  @Override
  public String getName() {
    return name;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public String getCharacterClass() {
    return characterClass;
  }

  public int getHP(){
    return HP;
  }

  public int getDP(){
    return DP;
  }

}






