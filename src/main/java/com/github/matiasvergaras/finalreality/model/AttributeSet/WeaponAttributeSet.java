package com.github.matiasvergaras.finalreality.model.AttributeSet;
/**
 * A class to hold all the possible attributes of a weapon.
 * <p> In order to avoid having to ask for the type or downcast/upcast every time that we want to get attributes from a
 * weapon, we need a transversal way to get their attributes.</p>
 * <p> To achieve that without weakening the code, we created a special class to hold the attributes. This way we
 * avoid having maps or list with Object type or String-type keys. </p>
 * <p> Every  Weapon will have a getAttribute method that will create and return a new AttributeSet.</p>
 * <p> If a character does not have an attribute, the AttributeSet will keep '0' as value. This is to
 * avoid having a lot of null pointers that could lead to exceptions while executing the program. </p>
 * @since Homework 2
 * @author Matías Vergara Silva
 */
public class WeaponAttributeSet {
    private final String name;
    private final int power;
    private final int weight;
    private final int magicPower;

    /**
     * Constructor of an AttributeSet for a normal weapon.
     */
    public WeaponAttributeSet(String name, int power, int weight){
        this.name = name;
        this.power = power;
        this.weight = weight;
        this.magicPower = 0;
    }

    /**
     * Constructor of an AttributeSet for a magic weapon.
     * Creates a new WeaponAttributeSet.
     */
    public WeaponAttributeSet(String name, int power, int weight, int magicPower){
        this.name = name;
        this.power = power;
        this.weight = weight;
        this.magicPower = magicPower;
    }

    /**
     * Getter for the weapon name
     * @return  Weapon's name
     */
    public String getName(){
        return name;
    }

    /**
     * Getter for the weapon power
     * @return Weapon's power
     */
    public int getPower(){
        return power;
    }

    /**
     * Getter for the weapon weight
     * @return Weapon's weight
     */
    public int getWeight(){
        return weight;
    }

    /**
     * Getter for the weapon magicPower
     * @return Weapon's magicPower
     */
    public int getMagicPower(){
        return magicPower;
    }
}
