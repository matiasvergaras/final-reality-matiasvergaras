package com.github.matiasvergaras.finalreality.model.Mastermind;

import com.github.matiasvergaras.finalreality.factory.Characters.ThiefFactory;
import com.github.matiasvergaras.finalreality.factory.Weapons.SwordFactory;
import com.github.matiasvergaras.finalreality.model.character.player.NullCharacter;
import com.github.matiasvergaras.finalreality.model.weapon.IWeapon;
import com.github.matiasvergaras.finalreality.model.weapon.NullWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to test a PlayerMastermind.
 * @author Matías Vergara Silva
 * @since Homework 2
 */
public class PlayerMastermindTest extends AbstractMastermindTest{

    /**
     * Basic set-up to test a mastermind.
     */
    @BeforeEach
    void CPUSetUp(){
        super.setUp();

    }

    /**
     * Test the constructor, equals and hashcode
     */
    @Test
    void equalsTest(){
        super.checkEquals(player, new PlayerMastermind(playerName, characterQuantity), cpu);
    }

    /**
     * Test the AddToParty method.
     */
    @Test
    void addToPartyTest(){
        super.checkAddToParty(player, exampleEngineer);
    }

    /**
     * Test that the player will not have more characters than characterQuantity
     */
    @Test
    void overflowAddingTest(){
        ThiefFactory factory = new ThiefFactory(turns, 100, 120);
        for(int i=0; i<characterQuantity*2; i++){
            player.addToParty(factory.create("A Thief"));
        }
        assertEquals(player.getPartySize(), characterQuantity);
    }

    /**
     * Test the getParty and getPartySize methods.
     */
    @Test
    void partyGettersTest(){
        super.checkPartyGetters(3, knightFactory, player);
    }

    /**
     * Test the removeFromParty method.
     */
    @Test
    void removeFromPartyTest(){
        super.checkRemoveFromParty(exampleEngineer, player);
    }

    /**
     * Test the getCharacterFromParty method.
     */
    @Test
    void getCharacterFromPartyTest(){
        super.checkGetCharacterFromParty(exampleWhiteMage, player);
    }

    /**
     * Test the makeNormalAttack test adding the character to the party and armoring it with a
     * powerful weapon before attack.
     */
    @Test
    void ArmoredEffectiveAttackTest(){
        exampleThief.equipWeapon(powerfulKnife);
        player.addToParty(exampleThief);
        player.makeNormalAttack(exampleThief, exampleEnemy);
        assertEquals(Integer.max(exampleEnemy.getMaxHP()-(exampleThief.getAttackPower()-exampleEnemy.getDP()),0),
                exampleEnemy.getCurrentHP());
    }



    /**
     * Test the makeNormalAttack without adding the character to the party.
     */
    @Test
    void UnarmoredIneffectiveAttackTest(){
        super.checkUnarmoredIneffectiveAttack(exampleThief, weakEnemy, player);
    }

    /**
     * Test the getPlayerName and getCharacterQuantity methods of PlayerMastermind
     */
    @Test
    void playerMastermindGettersTest(){
        PlayerMastermind player = (PlayerMastermind) this.player;
        assertEquals(player.getName(), playerName);
        assertEquals(player.getCharacterQuantity(), characterQuantity);
    }

    /**
     * Test the PlayerMastermind's addToInventory and getWeaponFromInventory method.
     */
    @Test
    void addToInventoryTest(){
        PlayerMastermind player = (PlayerMastermind)this.player;
        player.addToInventory(exampleAxe);
        assertEquals(player.getWeaponFromInventory(0), exampleAxe);
    }

    /**
     * Test the PlayerMastermind's removeFromInventory method.
     */
    @Test
    void removeFromInventory(){
        PlayerMastermind player = (PlayerMastermind)this.player;
        assertEquals(player.getInventorySize(), 0);
        player.addToInventory(exampleAxe);
        assertEquals(player.getInventorySize(), 1);
        player.removeFromInventory(exampleAxe);
        assertEquals(player.getInventorySize(), 0);
    }

    /**
     * Test the getInventory and getInventorySize methods of PlayerMastermind by adding 5 weapons.
     */
    @Test
    void inventoryTest(){
        SwordFactory factory = new SwordFactory("Steel Sword", 70, 11);
        ArrayList<IWeapon> inventoryCopy = new ArrayList<>();
        PlayerMastermind player = (PlayerMastermind)this.player;
        for(int i=0; i<5; i++){
            IWeapon weapon = factory.create();
            player.addToInventory(weapon);
            inventoryCopy.add(weapon);
            assertEquals(player.getInventorySize(), i+1);
        }
        assertEquals(inventoryCopy, player.getInventory());
    }

    /**
     * Test the PlayerMastermind's equip method with a party character and inventory weapon.
     */
    @Test
    void EffectiveEquipTest(){
        PlayerMastermind player = (PlayerMastermind)this.player;
        player.addToParty(exampleKnight);
        player.addToInventory(exampleAxe);
        player.equipCharacter(exampleAxe, exampleKnight);
        assertEquals(exampleKnight.getEquippedWeapon(), exampleAxe);
    }

    /**
     * Test the PlayerMastermind's equip method with a party character but a weapon that is not in the inventory.
     */
    @Test
    void IneffectiveWeaponEquipTest(){
        PlayerMastermind player = (PlayerMastermind)this.player;
        player.addToParty(exampleKnight);
        player.equipCharacter(exampleAxe, exampleKnight);
        assertEquals(exampleKnight.getEquippedWeapon(), new NullWeapon());
        assertNull(exampleAxe.getOwner());
    }


    /**
     * Test the PlayerMastermind's equip method with an inventory weapon but a character that is not in the party.
     */
    @Test
    void IneffectiveCharacterEquipTest(){
        PlayerMastermind player = (PlayerMastermind)this.player;
        player.addToInventory(exampleAxe);
        player.equipCharacter(exampleAxe, exampleKnight);
        assertEquals(exampleKnight.getEquippedWeapon(), new NullWeapon());
        assertNull(exampleAxe.getOwner());
    }


    /**
     * Test the PlayerMastermind's unequip method with a party character.
     *
     */
    @Test
    void EffectiveUnequipTest(){
        PlayerMastermind player = (PlayerMastermind)this.player;
        player.addToParty(exampleKnight);
        player.addToInventory(exampleAxe);
        player.equipCharacter(exampleAxe, exampleKnight);
        System.out.println(exampleKnight.getEquippedWeapon());
        player.unequipCharacter(exampleKnight);
        System.out.println(exampleKnight.getEquippedWeapon());
        assertFalse(exampleKnight.isEquipped());
        assertEquals(exampleAxe.getOwner(), new NullCharacter());
    }

    /**
     * Test the PlayerMastermind's unequip method with a non-party character..
     *
     */
    @Test
    void IneffectiveUnequipTest(){
        PlayerMastermind player = (PlayerMastermind)this.player;
        exampleKnight.equipWeapon(exampleAxe);
        player.unequipCharacter(exampleKnight);
        assertTrue(exampleKnight.isEquipped());
        assertEquals(exampleAxe.getOwner(), exampleKnight);
    }



}
