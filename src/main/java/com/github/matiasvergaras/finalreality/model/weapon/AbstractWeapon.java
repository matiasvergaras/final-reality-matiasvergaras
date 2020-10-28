package com.github.matiasvergaras.finalreality.model.weapon;

import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;

/**
 * A class that holds all the information of a weapon.
 * @since Homework 1
 * @author Ignacio Slater Muñoz.
 * @author Matías Vergara Silva.
 */
public abstract class AbstractWeapon implements IWeapon {

    private final String name;
    private final int power; //Could be heal - power or damage - power
    private final int weight;
    private IPlayerCharacter owner;

    /**
     * Constructor for a default item without any special behaviour.
     *
     * @param name   the name of the item
     * @param power  the power of the item
     * @param weight the weight of the item
     */
    public AbstractWeapon(final String name, final int power, final int weight) {
        this.name = name;
        this.power = power;
        this.weight = weight;
        this.owner = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPower() {
        return power;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWeight() {
        return weight;
    }

    /**
     * {@inheritDoc}
     *
     * @param character the Knight to equip with this weapon.
     */
    @Override
    public void equipToKnight(IPlayerCharacter character) {
    }

    /**
     * {@inheritDoc}
     *
     * @param character the Thief to equip with this weapon.
     */
    @Override
    public void equipToThief(IPlayerCharacter character) {
    }

    /**
     * {@inheritDoc}
     *
     * @param character the Engineer to equip with this weapon.
     */
    @Override
    public void equipToEngineer(IPlayerCharacter character) {
    }

    /**
     * {@inheritDoc}
     *
     * @param character the Black Mage to equip with this weapon.
     */
    @Override
    public void equipToBlackMage(IPlayerCharacter character) {
    }

    /**
     * {@inheritDoc}
     *
     * @param character the White Mage to equip with this weapon.
     */
    @Override
    public void equipToWhiteMage(IPlayerCharacter character) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPlayerCharacter getOwner() {
        return owner;
    }

    /**
     * {@inheritDoc}
     */
    public void setOwner(IPlayerCharacter character) {
        owner = character;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWeaponFree() {
        if (getOwner() != null) {
            getOwner().unequip();
            setOwner(null);
        }
    }
}

