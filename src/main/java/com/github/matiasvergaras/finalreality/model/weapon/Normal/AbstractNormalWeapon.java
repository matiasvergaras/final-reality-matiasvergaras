package com.github.matiasvergaras.finalreality.model.weapon.Normal;

import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.AbstractWeapon;

/**
 * An abstract class that holds the common behaviour of all Normal Weapons in the game.
 *
 * @author Matias Vergara Silva.
 * <p>
 * This class could serve us, for example, if we wanted to add properties of
 * '' sharp '' or '' penetration '' to normal weapons.
 * </p>
 */
public abstract class AbstractNormalWeapon extends AbstractWeapon {

    /**
     * Constructor for a default normal weapon without any special behaviour.
     *
     * @param name   the name of the item
     * @param power  the power of the item
     * @param weight  the weight of the item
     */
    public AbstractNormalWeapon(String name, int power, int weight) {
        super(name, power, weight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void equipTo(IPlayerCharacter character);


}
