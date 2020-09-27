package com.github.matiasvergaras.finalreality.model.character;

import com.github.matiasvergaras.finalreality.model.character.player.CharacterClass;
import com.github.matiasvergaras.finalreality.model.character.player.PlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.Weapon;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Matias Vergara Silva.
 */
public abstract class AbstractCharacter implements ICharacter {

  protected final BlockingQueue<ICharacter> turnsQueue;
  protected final String name;
  protected final CharacterClass characterClass;
  protected ScheduledExecutorService scheduledExecutor;

  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                              @NotNull String name, CharacterClass characterClass) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.characterClass = characterClass;
  }
  /**
   * Adds this character to the turns queue.
   */
  protected void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  @Override
  public String getName() {
    return name;
  }


  @Override
  public CharacterClass getCharacterClass() {
    return characterClass;
  }

  public void setParalysed() {
    return;
  }

  public void setPoisoned() {
    return;
  }

  public void setBurned() {
    return;
  }

  public void setHealed() {
    return;
  }


  public void receiveFireAttack() {
    return;
  }

  public void receiveHealAttack() {
    return;
  }

  public void receiveParalysisAttack() {
    return;
  }

  public void receivePoisonAttack() {
    return;
  }

  public void receiveThunderAttack() {
    return;
  }

  public void receiveNormalAttack() {
    return;
  }


}



