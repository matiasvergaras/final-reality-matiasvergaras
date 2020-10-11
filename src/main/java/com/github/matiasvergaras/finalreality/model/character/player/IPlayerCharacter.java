package com.github.matiasvergaras.finalreality.model.character.player;

import com.github.matiasvergaras.finalreality.model.character.CPU.IEnemy;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;

public interface IPlayerCharacter extends ICharacter {
    /**
     * Starts the process of equip a weapon to this character,
     * checking if it is possible via double dispatch.
     * @see IWeapon
     * @param weapon
     *              The weapon to be equipped
     */
    void equipWeapon(IWeapon weapon);

    /**
     * Sets this weapon to this character, once it's feasibility
     * has been checked.
     * @param weapon
     *              The weapon to be equipped
     */
    void equip(IWeapon weapon);

    /**
     * Unequip a weapon from this character.
     */
    void unequip();

    /**
     * @see IWeapon
     * @return  the equipped weapon of this character
     */
    IWeapon getEquippedWeapon();

    
    /**
     * Performs a non-magic attack
     * @param character the character to be attacked.
     *
     */
    void normalAttack(IEnemy character);

    /**
     * Receive a non-magic attack
     * @param character the character that attacks.
     *
     */
    void receiveNormalAttack(IEnemy character);

    /**
     * Receive a Heal-Spell ''attack''
     *
     */
    void receiveHeal();



}
