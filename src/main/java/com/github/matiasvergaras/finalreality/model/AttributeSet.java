package com.github.matiasvergaras.finalreality.model;

import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;

import javax.print.attribute.Attribute;

/**
 * A class to hold all the possible attributes of a character.
 * Created to avoid using hashmaps with Strings as keys or Objects as values, since they introduce
 * fragility to code.
 * <p> Every character will have an getAttribute method that will create and return a new AttributeSet.</p>
 * <p> If a character does not have an attribute, the AttributeSet will keep '0' as value. This is to
 * avoid having a lot of null pointers that could lead to exceptions while executing the program. </p>
 */
public class AttributeSet {
    private String name;
    private int maxHP = 0;
    private int currentHP = 0;
    private int DP = 0;
    private int maxMana = 0;
    private int currentMana = 0;
    private int weight = 0;
    private int power = 0;
    IWeapon equippedWeapon = null;

    /**
     * Constructor of an AttributeSet for a normal character.
     */
    public AttributeSet(String name, int maxHP, int currentHP, int DP, IWeapon equippedWeapon){
        this.maxHP = maxHP;
        this.currentHP = currentHP;
        this.DP = DP;
        this.equippedWeapon = equippedWeapon;
    }

    /**
     * Constructor of an AttributeSet for a magic character.
     */
    public AttributeSet(String name, int maxHP, int currentHP, int DP, int maxMana, int currentMana, IWeapon equippedWeapon){
        this.maxHP = maxHP;
        this.currentHP = currentHP;
        this.DP = DP;
        this.maxMana = maxMana;
        this.currentMana = currentMana;
        this.equippedWeapon = equippedWeapon;
    }

    /**
     * Constructor of a CPU Character.
     */
    public AttributeSet(String name, int maxHP, int currentHP, int DP, int weight, int power){
        this.maxHP = maxHP;
        this.currentHP = currentHP;
        this.DP = DP;
        this.weight = weight;
        this.power = power;
    }

    /**
     * Getter for name
     */
    public String getName(){
        return name;
    }

    /**
     * Getter for maxHP
     */
    public int getMaxHP(){
        return maxHP;
    }

    /**
     * Getter for currentHP
     */
    public int getCurrentHP(){
        return currentHP;
    }

    /**
     * Getter for DP
     */
    public int getDP(){
        return DP;
    }

    /**
     * Getter for maxMana
     */
    public int getMaxMana(){
        return maxMana;
    }

    /**
     * Getter for currentMana
     */
    public int getCurrentMana(){
        return currentMana;
    }

    /**
     * Getter for weight
     */
    public int getWeight(){
        return weight;
    }

    /**
     * Getter for power
     */
    public int getPower(){
        return power;
    }

    /**
     * Getter for EquippedWeapon
     */
    public IWeapon getEquippedWeapon(){
        return equippedWeapon;
    }
}
