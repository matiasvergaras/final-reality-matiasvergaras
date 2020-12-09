package com.github.matiasvergaras.finalreality.GameState;


import com.github.matiasvergaras.finalreality.factory.Characters.ICharacterFactory;
import com.github.matiasvergaras.finalreality.factory.Weapons.IWeaponFactory;
import com.github.matiasvergaras.finalreality.model.character.ICharacter;

import java.util.ArrayList;

/**
 * Interface to hold the common behavior for every single GameState.
 * @author Matías Vergara Silva
 * @since Homework 2
 */
public interface IGameState {

    /**
     * Returns true if the current state is Initializing.
     * @return  boolean isInitializing
     */
    boolean isInitializing();
    /**
     * Returns true if the current state is Active
     * @return  boolean isActive
     */
    boolean isActive();
    /**
     * Returns true if the current state is Finished.
     * @return  boolean isFinished
     */
    boolean isFinished();

    /**
     * Resets the turns queue and changes the
     * current state to Initializing.
     */
    void setInitializing();

    /**
     * Changes the current state to Active.
     */
    void setActive();

    /**
     * Changes the current state to Finished.
     */
    void setFinished();

    /**
     * Sends the waitTurn() message to every character in both player and cpu parties.
     * This method will be called only in Active state. Otherwise it will have
     * no effect.
     * <p> It checks if the character has a non-null weapon equipped or a weight
     * different from 0, if so, he sends the message. Otherwise, it will not let
     * the character start to wait until he meets the condition of having some sort
     * of weight. </p>
     */
    void startWaitTurns();


    /**
     * Starts the game.
     * This method will be called only in Initializing state. Otherwise it will have
     * no effect.
     * <p> Checks if the player has the correct number of characters
     * to play, and if that is the case, it changes the state of
     * the game to Active.</p>
      */
    void startGame();

    /**
     * Starts the configuration of a new game.
     * <p> Changes the gameController status to ''initializing''.</p>
     * <p> This method has to have effect only if called from
     * Finished state. </p>
     */
    void initializeGame();

    /**
     * <p> Sends to the character that just ended his turn the wait for re-entry order. </p>
     * <p> Calls to StartTurn, in order to start a new Turn. </p>
     * <p> A character will wait for its turn only if he is alive (new feature in waitTurn). </p>
      */
    void endTurn();

    /**
     * Sends the message that a character was added to the queue,
     * so if it was originally empty (the condition
     * is checked by verifying size of turns queue = 1)
     * now it is no longer empty, so the game can continue, reason
     * why it finally sends the startTurn() message.
     * <p> If the queue was not empty, this method will have no effect. </p>
     */
    void addToQueue();

    /**
     * Gets the next characters in the queue and set him as activeCharacter
     * (without removing it from the queue).
     * <p> If the queue is empty, the take will send the thread to sleep until
     * an element becomes available. </p>
     */
    void startTurn();

    /**
     * This method receives a dead character and the number of characters
     * remaining in his team after his death. If the dead character is
     * in the turns queue, it will remove him, and if the remaining
     * characters are 0, it calls to checkForWinner.
     * @param deadCharacter     The character to remove from queue
     * @param charactersAlive   The number of remaining characters in the dead character's team.
     */
    void removeDeadCharacter(ICharacter deadCharacter, int charactersAlive);

    /**
     * <p> Check which team was left without alive characters after the last death and assign
     * the winner to the opposing player.</p>>
     * <p> It will be private in order to make sure that it will be called only
     * when some team has every member dead, condition trapped by removeCharacterFromQueue. </p>
     */
    void setWinner();

    /**
     * Request a new BlackMage character to the corresponding factory and tries to add it to the User's party
     * by calling to addToParty method.
     * <p> the character will have the default parameters, which can be modified using the set methods </p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    void addBlackMageToPlayer(String name);

    /**
     * Request a new WhiteMage character to the corresponding factory and tries to add it to the User's party
     * by calling to addToParty method.
     * <p> the character will have the default parameters, which can be modified using the set methods. </p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    void addWhiteMageToPlayer(String name);

    /**
     * Request a new Engineer character to the corresponding factory and tries to add it to the User's party
     * by calling to addToParty method.
     * <p> the character will have the default parameters, which can be modified using the set methods. </p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    void addEngineerToPlayer(String name);

    /**
     * Request a new Thief character to the corresponding factory and tries to add it to the User's party
     * by calling to addToParty method.
     * <p> the character will have the default parameters, which can be modified using the set methods. </p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    void addThiefToPlayer(String name);

    /**
     * Request a new Knight character to the corresponding factory and and tries to add it to the User's party
     * by calling to addToParty method.
     * <p> the character will have the default parameters, which can be modified using the set methods. </p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    void addKnightToPlayer(String name);

    /**
     * Request a new CPU character to the corresponding factory and add it to the CPU's party.
     * <p> the character will have the default parameters, which can be modified using the set methods. </p>
     * @param name      The name of the character to create.
     * @see ICharacterFactory
     */
    void addEnemyToCPU(String name);

    /**
     * Request a new Bow weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * @see IWeaponFactory
     */
    void addBowToInventory();

    /**
     * Request a new Bow weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * @param name      The name of the weapon to create.
     * @see IWeaponFactory
     */
    void addBowToInventory(String name);

    /**
     * Request a new Sword weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * @see IWeaponFactory
     */
    void addSwordToInventory();

    /**
     * Request a new Bow weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * <p> This add method allow the user to give the name of the weapon, in order to have some special weapons. </p>
     * @param name      The name of the weapon to create.
     * @see IWeaponFactory
     */
    void addSwordToInventory(String name);

    /**
     * Request a new Axe weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * @see IWeaponFactory
     */
    void addAxeToInventory();

    /**
     * Request a new Bow weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * <p> This add method allow the user to give the name of the weapon, in order to have some special weapons. </p>
     * @param name      The name of the weapon to create.
     * @see IWeaponFactory
     */
    void addAxeToInventory(String name);

    /**
     * Request a new Staff weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * @see IWeaponFactory
     */
    void addStaffToInventory();

    /**
     * Request a new Bow weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * <p> This add method allow the user to give the name of the weapon, in order to have some special weapons. </p>
     * @param name      The name of the weapon to create.
     * @see IWeaponFactory
     */
    void addStaffToInventory(String name);

    /**
     * Request a new Knife weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> the weapon will have the default parameters, which can be modified using the set methods. </p>
     * @see IWeaponFactory
     */
    void addKnifeToInventory();

    /**
     * Request a new Knife weapon to the corresponding factory and add it to the userPlayer inventory.
     * <p> The weapon will have the default parameters, which can be modified using the set methods. </p>
     * <p> This add method allow the user to give the name of the weapon, in order to have some special weapons. </p>
     * @param name      The name of the weapon to create.
     * @see IWeaponFactory
     */
    void addKnifeToInventory(String name);

    /**
     * Launch the equipping process of the selectedWeapon to the SelectedCharacter.
     * <p> In case of a bad index, the method will catch and ignore the error, and
     * no weapon will be equipped. </p>
     * <p> This method will be available only in Initializing and Active mode.
     * In order to use after played a game, it will be necessary to send initializeGame()
     * message again.</p>
     */
    void equipSelectedWeaponToSelectedCharacter();

    /**
     * Unequip the SelectedCharacter.
     * If the SelectedCharacter is not in the Player's party,
     * it will have no effect.
     * <p> This method will be available only in Initializing and Active mode.
     * In order to use after played a game, it will be necessary to send initializeGame()
     * message again.</p>
     */
    void unequipSelectedCharacter();

    /**
     * Removes the selectedCharacter from its party.
     * <p> Note that there will never have a character on both parties, as Controller does not have
     * methods to make wrong adds. </p>
     * <p> The method removes the character if it is present in any of the teams, and do nothing otherwise. </p>
     * <p> This method will be available only in Initializing mode.
     * In order to use after played a game, it will be necessary to send initializeGame()
     * message again.</p>
     */
    void removeSelectedCharacterFromItsParty();

    /**
     * Removes the selectedWeapon from the userPlayer inventory and sets the selectedWeapon as the NullWeapon.
     * <p> This method will be available only in Initializing mode.
     * In order to use after played a game, it will be necessary to send initializeGame()
     * message again.</p>
     */
    void removeSelectedWeaponFromInventory();

    /**
     * This method makes the activeCharacter performs a normal attack against the selectedCharacter character.
     * <p> The method checks that any of the following cases are true:</p>
     * <p> activeCharacter is in userPlayer Party and selectedCharacter is in CPUPlayer Party, or </p>
     * <p> activeCharacter is in CPUPlayer Party and selectedCharacter in userPlayer Party.
     * Party. </p>
     * <p> If so, it sends the attack message in the corresponding direction. Otherwise, it has no effect.</p>
     * <p> In this way, the if's fulfill a double function: they ensure that the attacking and receiving characters
     * are in the game (to avoid bugs) and at the same time they avoid attacks between the same team. </p>
     * <p> This method will be available only in Active mode. </p>
     */
    void activeCharacterNormalAttackSelectedCharacter();

    /**
     * Gives the list with all the character factories of this gameController.
     * @return      ArrayList<ICharacterFactory> character's factories.
     */
    ArrayList<ICharacterFactory> getCharacterFactories();

    /**
     * Gives the list with all the weapon factories of this gameController.
     * @return      ArrayList<IWeaponFactory> weapon factories.
     */
    ArrayList<IWeaponFactory> getWeaponFactories();

}
