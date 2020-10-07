package com.github.matiasvergaras.finalreality.model.weapon;

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
  private final String type;

  /**
   * Creates a weapon with a name, a base damage, speed and it's type.
   *
   */
  public AbstractWeapon(final String name, final int power, final int weight,
                        final String type) {
    this.name = name;
    this.power = power;
    this.weight = weight;
    this.type = type;
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
   *
   */
  @Override
  public String getType() {
    return type;
  }

}
