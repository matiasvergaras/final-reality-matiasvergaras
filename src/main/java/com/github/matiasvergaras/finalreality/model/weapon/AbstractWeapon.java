package com.github.matiasvergaras.finalreality.model.weapon;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;

import java.util.Objects;

/**
 * A class that holds all the information of a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public abstract class AbstractWeapon implements IWeapon{

  private final String name;
  private final int power; //Could be heal - power or damage - power
  private final int weight;
  private IPlayerCharacter owner;

  /**
   * Constructor for a default item without any special behaviour.
   *
   * @param name
   *     the name of the item
   * @param power
   *     the power of the item
   * @param weight
   *     the weight of the item
   */
  public AbstractWeapon(final String name, final int power, final int weight) {
    this.name = name;
    this.power = power;
    this.weight = weight;
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
  public int getPower() {
    return power;
  }

  /**
   * {@inheritDoc}
   *
   */
  @Override
  public int getWeight() {
    return weight;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public abstract void equipTo(IPlayerCharacter character);

  /**
   * {@inheritDoc}
   */
  public void setOwner(IPlayerCharacter character){
    owner = character;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IPlayerCharacter getOwner(){
    return owner;
  }
}


