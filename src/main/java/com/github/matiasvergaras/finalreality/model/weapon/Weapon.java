package com.github.matiasvergaras.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class that holds all the information of a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public class Weapon {

  private final String name;
  private final int power; //Could be heal - power or damage - power
  private final int weight;
  private final WeaponType type;

  /**
   * Creates a weapon with a name, a base damage, speed and it's type.
   *
   * @see WeaponType
   */
  public Weapon(final String name, final int power, final int weight,
      final WeaponType type) {
    this.name = name;
    this.power = power;
    this.weight = weight;
    this.type = type;
  }

  private String getName() {
    return name;
  }

  private int getPower() {
    return power;
  }

  public int getWeight() {
    return weight;
  }

  private WeaponType getType() {
    return type;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Weapon)) {
      return false;
    }
    final Weapon weapon = (Weapon) o;
    return getPower() == weapon.getPower() &&
        getWeight() == weapon.getWeight() &&
        getName().equals(weapon.getName()) &&
        getType() == weapon.getType();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getPower(), getWeight(), getType());
  }
}
