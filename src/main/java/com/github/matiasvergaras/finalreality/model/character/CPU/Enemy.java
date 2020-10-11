package com.github.matiasvergaras.finalreality.model.character.CPU;

import com.github.matiasvergaras.finalreality.model.character.AbstractCharacter;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.Magic.IMagicCharacter;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 * <p>
 * Since we may want to have many units of Enemy with the same name, ex: 3 different Globin,
 * we will add an special field to this class: EnemyID.
 * <p> They also have an state, since they can suffer from magic spells effects. </p>
 * @author Ignacio Slater Muñoz
 * @author Matías Vergara Silva
 */
public class Enemy extends AbstractCharacter implements IEnemy{

  private final int weight;
  private final int power;
  private final int enemyID;
  private String state;

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
   * @param power
   *     the character's power points
   */

  public Enemy(@NotNull final BlockingQueue<ICharacter> turnsQueue,
               @NotNull final String name, int weight,
               int HP, int DP, int power, int ID) {
    super(turnsQueue, name, HP, DP);
    this.weight = weight;
    this.power = power;
    this.state = "NORMAL";
    this.enemyID = ID;
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
            this.getMaxDP() == that.getMaxDP() &&
            this.getMaxHP() == that.getMaxHP() &&
            this.getPower() == that.getPower() &&
            this.getID() == that.getID();
  }

  /**
   * Get the power of this enemy.
   * @return the power of this enemy.
   */
  public int getPower() {
    return power;
  }

  /**
   * Get the ID of this enemy.
   * @return the ID of this enemy.
   */
  public int getID() {
    return enemyID;
  }


  /**
   * Returns an integer value, generated by a hashing algorithm, distinct
   * for distinct objects. Required for the equals method.
   * @return Integer hash code.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.getWeight()  + this.getName()
                        +this.getPower() + this.getMaxDP() + this.getMaxHP() + this.getID());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor
            .schedule(super::addToQueue, this.getWeight() / 10, TimeUnit.SECONDS);
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void normalAttack(IPlayerCharacter character) {
    character.receiveNormalAttack(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void receiveNormalAttack(IPlayerCharacter character) {
    reduceHP(character.getEquippedWeapon().getPower());
  }

  /**
   * {@inheritDoc}
   * @return the weight of this enemy.
   */
  public int getWeight() {
    return weight;
  }

  /**
   * {@inheritDoc}
   * @return the state of this enemy.
   */
  public String getState(){
    return state;
  }


  /**
   * {@inheritDoc}
   */
  public void setBurned(){
    state = "BURNED";
  }

  /**
   * {@inheritDoc}
   */
  public void setParalyzed(){
    state="PARALYZED";
  }

  /**
   * {@inheritDoc}
   */
  public void setPoisoned(){
    state="POISONED";
  }


  /**
   * {@inheritDoc}
   * @param character
   *                the attacking character
   */
  public void receiveFireAttack(IMagicCharacter character){
    //This far this isn't working properly, we have to define a way to access to the magic power.
    this.reduceHP(character.getEquippedWeapon().getPower());
    Random rand = new Random();
    double prob = rand.nextDouble();
    if (prob<=0.2){
      setBurned();
    }
  }

  /**
   * {@inheritDoc}
   * @param character
   *                the attacking character
   */
  public void receiveThunderAttack(IMagicCharacter character){
    //This far this isn't working properly, we have to define a way to access to the magic power.
    this.reduceHP(character.getEquippedWeapon().getPower());
    Random rand = new Random();
    double prob = rand.nextDouble();
    if (prob<=0.3){
      setParalyzed();
    }
  }

  /**
   * {@inheritDoc}
   * @param character
   *                the attacking character
   */
  public void receivePoisonAttack(IMagicCharacter character){
    setPoisoned();
  }

  /**
   * {@inheritDoc}
   * @param character
   *                the attacking character
   */
  public void receiveParalysisAttack(IMagicCharacter character){
    setParalyzed();
  }

}


