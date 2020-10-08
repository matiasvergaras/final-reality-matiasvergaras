package com.github.cc3002.finalreality.model.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.github.matiasvergaras.finalreality.model.character.CPU.Enemy;
import com.github.matiasvergaras.finalreality.model.character.player.AbstractPlayerCharacter;
import java.util.EnumMap;
import java.util.Map;

import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Set of tests for the {@code GameCharacter} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Matias Vergara Silva.
 * @see AbstractPlayerCharacter
 */
class PlayerCharacterTest extends AbstractCharacterTest {

  private static final String BLACK_MAGE_NAME = "Vivi";
  private static final String KNIGHT_NAME = "Adelbert";
  private static final String WHITE_MAGE_NAME = "Eiko";
  private static final String ENGINEER_NAME = "Cid";
  private static final String THIEF_NAME = "Zidane";
  private Map<String, String> characterNames;

  /**
   * Setup method.
   * Creates a new character named Vivi with 10 speed and links it to a turn queue.
   */
  @BeforeEach
  void setUp() {
    super.basicSetUp();

    characterNames = new EnumMap<>(CharacterClass.class);
    characterNames.put("BLACK_MAGE", BLACK_MAGE_NAME);
    characterNames.put("KNIGHT", KNIGHT_NAME);
    characterNames.put("WHITE_MAGE", WHITE_MAGE_NAME);
    characterNames.put("ENGINEER", ENGINEER_NAME);
    characterNames.put("THIEF", THIEF_NAME);

    for (var characterClass :
        characterNames.keySet()) {
      testCharacters.add(
          new IPlayerCharacter(characterNames.get(characterClass), turns, characterClass));
    }
  }

  /**
   * Checks that the class' constructor and equals method works properly.
   */
  @Test
  void constructorTest() {
    var enemy = new Enemy("Enemy", 10, turns, 200, 200);
    for (var character :
        testCharacters) {
      var characterClass = character.getCharacterClass();
      var characterName = characterNames.get(characterClass);
      checkConstruction(new AbstractPlayerCharacter(characterName, turns, characterClass),
          character,
          new AbstractPlayerCharacter("Test", turns, characterClass),
          new AbstractPlayerCharacter(characterName, turns,
              characterClass == CharacterClass.THIEF ? CharacterClass.BLACK_MAGE
                  : CharacterClass.THIEF));
      assertNotEquals(character, enemy);
    }

  }

  @Test
  void equipWeaponTest() {
    for (var character :
        testCharacters) {
      assertNull(character.getEquippedWeapon());
      character.equip(testWeapon);
      assertEquals(testWeapon, character.getEquippedWeapon());
    }
  }
}
