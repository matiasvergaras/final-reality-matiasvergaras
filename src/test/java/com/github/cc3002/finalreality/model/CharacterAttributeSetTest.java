package com.github.cc3002.finalreality.model;

import com.github.matiasvergaras.finalreality.model.AttributeSet.CharacterAttributeSet;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;
import com.github.matiasvergaras.finalreality.model.character.player.magic.BlackMage;
import com.github.matiasvergaras.finalreality.model.character.player.magic.WhiteMage;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Engineer;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Knight;
import com.github.matiasvergaras.finalreality.model.character.player.normal.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to test the behavior of CharacterAttributeSet
 * class.
 * We will check constructor, equals and hashcode.
 * Getters are too basics and will be tested inside of
 * GameController tests.
 * @author Matías Vergara Silva
 * @since Homework 2
 */
public class CharacterAttributeSetTest {
    LinkedBlockingQueue<ICharacter> turns;

    /**
     * Basic set-up: A turns queue.
     */
    @BeforeEach
    void setUp(){
        turns = new LinkedBlockingQueue<>();
    }

    /**
     * Checks the constructor, equals and hashcode methods.
     * <p> An attributeSet is identified only by the name. If two
     * characters have the same name, their attributeSets should be equal.</p>
     * <p> (Remember that we will not allow characters with the same name).</p>
     * @param character                         The character to be tested.
     * @param sameCharacter                     Another instance of the same character
     *                                          with the same name.
     * @param sameClassDifferentName            An instance of the same character but
     *                                          with different name.
     * @param anotherClassSameName              An instance of another character but with
     *                                          the same name.
     * @param anotherClassDifferentName         An instance of another character with
     *                                          different name.
     */
    void checkEquals(ICharacter character, 
                     ICharacter sameCharacter,
                     ICharacter sameClassDifferentName,
                     ICharacter anotherClassSameName,
                     ICharacter anotherClassDifferentName){
        CharacterAttributeSet attr = character.getAttributes();
        assertTrue(attr.equals(attr));
        assertEquals(character.getAttributes(), sameCharacter.getAttributes());
        assertEquals(character.getAttributes().hashCode(), sameCharacter.getAttributes().hashCode());
        assertNotEquals(character.getAttributes(), sameClassDifferentName.getAttributes());
        assertNotEquals(character.getAttributes().hashCode(), sameClassDifferentName.getAttributes().hashCode());
        assertEquals(character.getAttributes(), anotherClassSameName.getAttributes());
        assertEquals(character.getAttributes().hashCode(), anotherClassSameName.getAttributes().hashCode());
        assertNotEquals(character.getAttributes(), anotherClassDifferentName.getAttributes());
        assertNotEquals(character.getAttributes().hashCode(), anotherClassDifferentName.getAttributes().hashCode());
    }



    @Test
    void testBlackMageAttributes(){
        checkEquals(new BlackMage(turns, "Torasu", 140, 60, 160),
                    new BlackMage(turns, "Torasu", 140, 60, 160),
                    new BlackMage(turns, "Azelf", 140, 120, 100),
                    new WhiteMage(turns, "Torasu", 140, 60, 160),
                    new WhiteMage(turns, "Azelf", 100, 120, 68));
    }

    @Test
    void testWhiteMageAttributes(){
        checkEquals(new WhiteMage(turns, "Torasu", 140, 60, 160),
                new WhiteMage(turns, "Torasu", 140, 60, 160),
                new WhiteMage(turns, "Azelf", 140, 120, 100),
                new WhiteMage(turns, "Torasu", 140, 60, 160),
                new WhiteMage(turns, "Azelf", 100, 120, 68));
    }

    @Test
    void testEngineerAttributes(){
        checkEquals(new Engineer(turns, "Narsha", 140, 60),
                new Engineer(turns, "Narsha", 140, 60),
                new Engineer(turns, "Warlock", 140, 120),
                new Engineer(turns, "Narsha", 140, 60),
                new Engineer(turns, "Warlock", 100, 120));
    }

    @Test
    void testThiefAttributes(){
        checkEquals(new Thief(turns, "Lyle", 140, 60),
                new Thief(turns, "Lyle", 140, 60),
                new Thief(turns, "Pitt", 140, 120),
                new Thief(turns, "Lyle", 140, 60),
                new Thief(turns, "Pitt", 100, 120));
    }

    @Test
    void testKnightAttributes(){
        checkEquals(new Knight(turns, "Gort", 140, 60),
                new Knight(turns, "Gort", 140, 60),
                new Knight(turns, "Luke", 140, 120),
                new Knight(turns, "Gort", 140, 60),
                new Knight(turns, "Luke", 100, 120));
    }

}
