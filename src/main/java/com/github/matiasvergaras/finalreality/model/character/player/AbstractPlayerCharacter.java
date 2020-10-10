package com.github.matiasvergaras.finalreality.model.character.player;

import com.github.matiasvergaras.finalreality.model.character.AbstractCharacter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.matiasvergaras.finalreality.model.character.CPU.Enemy;
import com.github.matiasvergaras.finalreality.model.character.CPU.IEnemy;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.weapon.Magic.Staff;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.*;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of all Player Characters of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Matias Vergara Silva.
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements IPlayerCharacter {
   protected IWeapon equippedWeapon;

   /**
   * Creates a new Player Character
   *
   * @param name
   *     the character's name
   * @param turnsQueue
   *     the queue with the characters ready to play
   * @param HP
   *    the character's max heal points
   * @param DP
   *    the character's max defense points
   */
  protected AbstractPlayerCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                   @NotNull String name,
                                    int HP, int DP) {
    super(turnsQueue, name, HP, DP);

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
    return equippedWeapon;
  }

  /**
   * {@inheritDoc}
   * @param weapon
   *              The weapon to equip.
   */
  @Override
  public void equip(IWeapon weapon) {
    if (weapon.equals(new NullWeapon())) {
      weapon.equipTo(this);
    }
  }

  /**
   * {@inheritDoc}
   * @param weapon
   *              The Axe to equip.
   */
  public void equipAxe(Axe weapon){
  }

  /**
   * {@inheritDoc}
   * @param weapon
   *              The Bow to equip.
   */
  public void equipBow(Bow weapon){
  }

  /**
   * {@inheritDoc}
   * @param weapon
   *              The Staff to equip.
   */
  public void equipStaff(Staff weapon){
  }

  /**
   * {@inheritDoc}
   * @param weapon
   *              The Knife to equip.
   */
  public void equipKnife(Knife weapon){
  }

  /**
   * {@inheritDoc}
   * @param weapon
   *              The Sword to equip.
   */
  public void equipSword(Sword weapon){
  }

  public void normalAttack(IEnemy character){
    character.receiveNormalAttack(this);
  };

  /**
   * Receive a non-magic attack
   * @param character the attacking character.
   *
   */
  public void receiveNormalAttack(IEnemy character){
    this.currentHP-=character.getPower();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void receiveHeal() {
    this.currentHP+=this.currentHP*0.3;
  }
}


