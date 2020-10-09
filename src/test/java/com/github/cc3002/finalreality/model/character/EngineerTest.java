package com.github.cc3002.finalreality.model.character;
import com.github.matiasvergaras.finalreality.model.character.CPU.Enemy;
import com.github.matiasvergaras.finalreality.model.character.player.IPlayerCharacter;
import com.github.matiasvergaras.finalreality.model.character.player.Normal.Engineer;
import com.github.matiasvergaras.finalreality.model.character.player.Normal.Thief;
import com.github.matiasvergaras.finalreality.model.weapon.Magic.Staff;
import com.github.matiasvergaras.finalreality.model.weapon.Normal.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EngineerTest extends AbstractPlayerCharacterTest {

    private static final String ENGINEER_NAME = "Cid";
    private static final Sword EXAMPLE_SWORD = new Sword("Example Sword",
            200, 100);

    /**
     * Sets up an instance to test this class.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
        testCharacters.add(new Engineer(turns, ENGINEER_NAME, 100, 200));
    }


    /**
     * {@inheritDoc}
     * @param character
     *                  the character to try to equip.
     */
    @Override
    void tryToEquip(IPlayerCharacter character) {
        character.equip(EXAMPLE_SWORD);
    }


    /**
     * Checks that the class' constructor and equals method works properly.
     */
    @Test
    void constructorTest() {
        checkConstruction(new Engineer(turns, ENGINEER_NAME, 100, 200),
                testCharacters.get(0),
                new Engineer(turns, ENGINEER_NAME, 110, 200),
                new Enemy(turns, ENGINEER_NAME, 11,110, 200));
    }



}