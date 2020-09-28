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
  private final WeaponType type;

  /**
   * Creates a weapon with a name, a base damage, speed and it's type.
   *
   * @see WeaponType
   */
  public AbstractWeapon(final String name, final int power, final int weight,
                        final WeaponType type) {
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
  public WeaponType getType() {
    return type;
  }

  /**
   * {@inheritDoc}
   *
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbstractWeapon)) {
      return false;
    }
    final AbstractWeapon weapon = (AbstractWeapon) o;
    return getPower() == weapon.getPower() &&
        getWeight() == weapon.getWeight() &&
        getName().equals(weapon.getName()) &&
        getType() == weapon.getType();
  }

  /**
   * {@inheritDoc}
   *
   */
  @Override
  public int hashCode() {
    return Objects.hash(getName(), getPower(), getWeight(), getType());
  }
}
