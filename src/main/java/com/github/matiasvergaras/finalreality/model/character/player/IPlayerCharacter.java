package com.github.matiasvergaras.finalreality.model.character.player;

import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.cpu.IEnemyCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;

public interface IPlayerCharacter extends ICharacter {
    /**
     * Starts the process of equip a weapon to this character,
     * checking if it is possible via double dispatch.
     *
     * @param weapon The weapon to be equipped
     * @see IWeapon
     */
    void equipWeapon(IWeapon weapon);

    /**
     * Sets this weapon to this character, once it's feasibility
     * has been checked.
     *
     * @param weapon The weapon to be equipped
     */
    void equip(IWeapon weapon);

    /**
     * Unequip a weapon from this character.
     */
    void unequip();

    /**
     * @return the equipped weapon of this character
     * @see IWeapon
     */
    IWeapon getEquippedWeapon();


    /**
     * Performs a non-magic attack
     *
     * @param character the character to be attacked.
     */
    void normalAttack(IEnemyCharacter character);

    /**
     * Receive a non-magic attack
     *
     * @param character the character that attacks.
     */
    void receiveNormalAttack(IEnemyCharacter character);

    /**
     * Receive a Heal-Spell ''attack''
     */
    void receiveHeal();


}
