package com.github.matiasvergaras.finalreality.model.character;

import com.github.matiasvergaras.finalreality.model.character.player.CharacterClass;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
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
  public int hashCode() {
    return Objects.hash(getCharacterClass());
  }

  @Override
  public CharacterClass getCharacterClass() {
    return characterClass;
  }


  @Override
  public void setParalysed() {
    return;
  }

  @Override
  public void setPoisoned() {
    return;
  }

  @Override
  public void setBurned() {
    return;
  }

  @Override
  public void setHealed() {
    return;
  }


  @Override
  public void receiveFireAttack() {
    return;
  }

  @Override
  public void receiveHealAttack() {
    return;
  }

  @Override
  public void receiveParalysisAttack() {
    return;
  }

  @Override
  public void receivePoisonAttack() {
    return;
  }

  @Override
  public void receiveThunderAttack() {
    return;
  }

  @Override
  public void receiveNormalAttack() {
    return;
  }


}



