package com.github.matiasvergaras.finalreality.model.character.player;

import com.github.matiasvergaras.finalreality.model.character.AbstractCharacter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Sword;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of all Player Characters of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Matias Vergara Silva.
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements IPlayerCharacter {
   private IWeapon equippedWeapon;

   /**
   * Creates a new Player Character
   *
   * @param name
   *     the character's name
   * @param turnsQueue
   *     the queue with the characters ready to play
   * @param characterClass
   *     the class of this character
   */
  protected AbstractPlayerCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                   @NotNull String name,
                                   final String characterClass, int HP, int DP) {
    super(turnsQueue, name, characterClass, HP, DP);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor
            .schedule(super::addToQueue, equippedWeapon.getWeight() / 10, TimeUnit.SECONDS);
  }

  /**
   * @return the equipped weapon of this character.
   * @see IWeapon
   * */
  @Override
  public IWeapon getEquippedWeapon() {
    return this.equippedWeapon;
  }

  /**
   * {@inheritDoc}
   * @param weapon
   *              The weapon to equip.
   */
  @Override
  public void equip(IWeapon weapon){
    this.equippedWeapon = weapon;
  }


}


