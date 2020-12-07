package com.github.matiasvergaras.finalreality.model;

import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;

import java.util.Objects;

/**
 * A class to hold all the possible attributes of a character.
 * <p> In order to avoid having to ask for the type or downcast/upcast every time that we want to get attributes from a
 * weapon, we need a transversal way to get their attributes.</p>
 * <p> To achieve that without weakening the code, we created a special class to hold the attributes. This way we
 * avoid having maps or list with Object type or String-type keys. </p>
 * <p> Every character will have an getAttribute method that will create and return a new AttributeSet.</p>
 * <p> If a character does not have an attribute, the AttributeSet will keep '0' as value. This is to
 * avoid having a lot of null pointers that could lead to exceptions while executing the program. </p>
 * @since Homework 2
 * @author Matías Vergara Silva
 */
public class CharacterAttributeSet {
    private String name;
    private int maxHP;
    private int currentHP;
    private int DP;
    private int maxMana;
    private int currentMana;
    private int weight;
    private int power;
    IWeapon equippedWeapon;

    /**
     * Constructor of an AttributeSet for a normal character.
     */
    public CharacterAttributeSet(String name, int maxHP, int currentHP, int DP, IWeapon equippedWeapon){
        this.name = name;
        this.maxHP = maxHP;
        this.currentHP = currentHP;
        this.DP = DP;
        this.equippedWeapon = equippedWeapon;
        this.maxMana = 0; this.currentMana = 0;
        this.weight = 0; this.power = 0;
        this.equippedWeapon = equippedWeapon;
    }

    /**
     * Constructor of an AttributeSet for a magic character.
     */
    public CharacterAttributeSet(String name, int maxHP, int currentHP, int DP, int maxMana, int currentMana, IWeapon equippedWeapon){
        this.name = name;
        this.maxHP = maxHP;
        this.currentHP = currentHP;
        this.DP = DP;
        this.maxMana = maxMana;
        this.currentMana = currentMana;
        this.equippedWeapon = equippedWeapon;
        this.weight = 0; this.power = 0;
    }

    /**
     * Constructor of a CPU Character.
     */
    public CharacterAttributeSet(String name, int maxHP, int currentHP, int DP, int weight, int power){
        this.name = name;
        this.maxHP = maxHP;
        this.currentHP = currentHP;
        this.DP = DP;
        this.weight = weight;
        this.power = power;
        this.maxMana = 0; this.currentMana = 0;
        this.equippedWeapon = null;
    }

    /**
     * Getter for character's name
     * @return  Weapon name
     */
    public String getName(){
        return name;
    }

    /**
     * Getter for character's maxHP
     * @return character maxHP
     */
    public int getMaxHP(){
        return maxHP;
    }

    /**
     * Getter for character's currentHP
     * @return character currentHP
     */
    public int getCurrentHP(){
        return currentHP;
    }

    /**
     * Getter for character's DP
     * @return character DP
     */
    public int getDP(){
        return DP;
    }

    /**
     * Getter for character's maxMana
     * @return character MaxMana
     */
    public int getMaxMana(){
        return maxMana;
    }

    /**
     * Getter for character's currentMana
     * @return character currentMana
     */
    public int getCurrentMana(){
        return currentMana;
    }

    /**
     * Getter for character's weight
     * @return character weight
     */
    public int getWeight(){
        return weight;
    }

    /**
     * Getter for character's power
     * @return character power
     */
    public int getPower(){
        return power;
    }

    /**
     * Getter for character's EquippedWeapon
     * @return character EquippedWeapon
     */
    public IWeapon getEquippedWeapon(){
        return equippedWeapon;
    }

    /**
     * Check if this is equal to a given object o.
     * <p> Since a characterAttributeSet is always linked to its character and
     * characters are identified by its name, it is enough to check for that condition. </p>
     * @param o The target object
     * @return True if are equals, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CharacterAttributeSet)) return false;
        CharacterAttributeSet that = (CharacterAttributeSet) o;
        return this.getName().equals(that.getName());
    }

    /**
     * Returns an integer value, generated by a hashing algorithm, distinct
     * for distinct objects. Required for the equals method.
     *
     * @return Integer hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }

}
