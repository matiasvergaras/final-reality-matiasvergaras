package com.github.matiasvergaras.finalreality.model.character.player;

import com.github.matiasvergaras.finalreality.model.character.CPU.IEnemy;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.Magic.Staff;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Axe;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Bow;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Knife;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Sword;


public interface IPlayerCharacter extends ICharacter {
    /**
     * Equips a given weapon to this character
     * @see IWeapon
     * @param weapon
     *              The weapon to equip
     */
    void equipWeapon(IWeapon weapon);

    /**
     * Sets this character's weapon
     * @param weapon
     */
    public void equip(IWeapon weapon);

    /**
     * Equips a given axe to this character.
     * @param weapon
     *        the axe to equip.
     */
    void equipAxe(Axe weapon);

    /**
     * Equips a given bow to this character.
     * @param weapon
     *        the bow to equip.
     */
    void equipBow(Bow weapon);

    /**
     * Equips a given knife to this character.
     * @param weapon
     *        the knife to equip.
     */
    void equipKnife(Knife weapon);

    /**
     * Equips a given staff to this character.
     * @param weapon
     *        the staff to equip.
     */
    void equipStaff(Staff weapon);

    /**
     * Equips a given sword to this character.
     * @param weapon
     *        the sword to equip.
     */
    void equipSword(Sword weapon);

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
    void normalAttack(ICharacter character);

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
