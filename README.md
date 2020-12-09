Final Reality
=============

Course: *CC3002 - Design and Programming Methodologies*

Section: 2

Student: Matías Vergara Silva

Github User: matiasvergaras

Lecture teacher: Mrs. Nancy Hitschfield

Assistant teachers: Matías Ramírez & Ignacio Slater M.

Review assistant: Benjamin del Pino B.

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)


Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com).


Broadly speaking:

- For the combat the player has a group of characters to control and a group of 
enemies controlled by the computer.
- Characters may be from 2 diferent types: Normal Characters and Magic Characters.
- Normal Characters include 3 subclasses: Engineer, Thief and Knight.
- Magic Characters include Black Mages and White Mages. 
- Each subclass can equip specific types of weapons (which can be Staves, Knieves, Swords, Bows and Axes) but just one at a time. 
- Characters fight enemies (who cannot use magic or weapons) through a turn system.

---

**We will be writing updates of the work done in each merge here, in '' Latest first '' format. Execution instructions will be added at each Final Delivery, once the program is fully developed and functional.**

--- 

# #Homework 2 

---
### Final Delivery (Partial Delivery 5)

The Partial Delviery 5 is the final delivery of the Homework 2. The goal is to implement the Game Controller, which will be an intermediary between the user and the model objects, and whose purpose will be to controll all the messages that pass trough him, manipulating and redirecting those that are necessary. More specifically, the controller has to be able to:
- Create and assign objects of the model (characters, enemies, weapons, etc.),
- Know which are the player characters and what are their attributes,
- Know which are the CPU characters and what are their attributes,
- Manipulate the player inventory,
- Equip a weapon to a character,
- Make a character attack another.

Additionally, two more complex tasks that the controller must fulfill are:
- Know when a character's turn begins and ends,
- Know inmediately if the player won or lost the game. A player wins when all enemies are knocked out and loses if their characters are knocked out.

We will follow the same format as Release 1: Execution Instructions, assumptions made up to this point, then we will explain the logic and operation added to cover the new features requested, and finally we will present the new UML diagrams.

#### Execution Instructions
As for the Homework 1,  the game is still under development - so there are no further execution instructions -. However, it is possible to run tests that show that the program logic works as expected.

To do this use IntelliJ's "Run Tests" option, or compile individual tests using ``cp`` and run them with the ``org.junit.runner.JUnitCore`` flag as shown on [this StackOverFlow thread](https://stackoverflow.com/questions/2235276/how-to-run-junit-test-cases-from-the-command-line).

#### Assumptions made so far
New assumptions:
- Weapons and character's name will not change with time. That means, those variables can be implemented as finals. 
- The name of the user, the number of characters that he must have and the name of the stage to be played (name of the CPU) will be delivered as parameters to the GameController.
- **The Game will have 3 main states: Initializing, Active, and Finished.**
  - In **Initializing mode**, the user ask the Controller to set's up his team, the CPU team and the weapons that are going to be able in the fight. He is able to ask for new characters, new weapons, adding them to its party/inventory or removing them as well, and the same for the CPU Party. The controller, on the other hand, will wait for the player's team to have the indicated number of characters (which is delivered when creating a new GameController), and will start the game if this condition is met and it receives the ``StartGame()`` message. If the user has already added as many characters to their party as the condition allows, adding more will have no effect, so if he wants to replace a character, he will have to remove him first.
  - In **Active mode**, the user sends messages to the Controller to fight against the CPU. In this state the player can equip and unequip characters at will, however, he cannot request new characters or weapons. In this state the controller will be attentive to the change of two properties: the end of a character's turn, which occurs when the active character makes an attack, and the death of a character, which can eventually lead to the victory of an opponent. - Which will induce the next state -.
  - In **Finished mode**,  the user only has three options: see how the game turned out once the game ended, sending the ``selectCharacter ()``, ``selectWeapon ()`` and ``getSelectedCharacter / WeaponAttributes()`` messages to the controller, ask for who was the winner through getWinner () controller's method, or launch the initialization of a new game, requesting it from the gameController through the InitializeGame () method.
  - It is worth mentioning that the decision to implement these states is due to the fact that, until now, we did not have a way to prevent the controller from taking actions at inappropriate times and that could break the logic of the game, such as adding new characters or weapons while the fight is in progress.
- **The player inventory will not have a space limit**. It will be infinite for now. 
- A player character who does not have a weapon can remain in the party, but **cannot enter the turn list until he obtain a weapon**. This decision is because otherwise the player could "cheat" by leaving a character without a weapon who will take the turn first, then equip it on the same turn (since equipping does not end the turn), unequip the rest of its team and then attack. 
- **If in any turn a character dies, he will be removed from the queue, but  will remain in his corresponding party (as a dead character)**. This decision is due to two reasons: The first, that perhaps in the future we will want to enable resurrection spells or objects with these properties. The second, to avoid that the condition that the player must always have the same number of characters in his group from being broken. 
    - It is important to note that since a character is ordered to wait for his next turn only when his current turn ends, there is no need to worry about the possibility of a dead character taking the turn eventually. This will never happen, thanks to the way it was implemented: when a character dies, he is removed from the queue, so he will not end a turn never again. 
- All factories will have the possibilty to set a new value for every attribute of characters/weapons, even if their products do not use them. This is to be able to have a  ``selectedCharacterFactory`` and a ``selectedWeaponFactory``  which is configured using setSelectedFactoryAttribute (int newValue) methods instead of overloading the controller with specific methods to configure each factory.
- To get the GameController to have a generic way of asking for the attributes of a model entity without the need to upcast / downcast, it will be accepted that a weapon / character that does not have certain property, returns ``0`` when the controller request for it. This does not mean that the character has the attribute - in fact, it does not -. For example: if the ``selectedPlayerCharacter`` is a Knight, the message ``getSelectedPlayerCharacterMana()`` will return ``0``.
    - Even if this is an effect that we would like to avoid, we will accept it because it allows us to simplify other actions, such as the attack between characters. In fact, to avoid it we would have to have a ``selectedCharacter`` for the player, other for the CPU and other for the magic characters, which would greatly complicate program flow and may induce errors because it would be necessary to downcast the characters getted from the queue to assign them to a ``selected(Specific)Character``, also complicating the code too much).
- We assume that in general the controller could have null values in its ``Selected`` variables frequently during the game. For this reason, we decided to apply the NullPatern for Weapons and Characters, giving rise to the NullWeapon and NullCharacter classes.
- We assume that when the win condition is reached, it does not matter if there are characters lefts in the queue: the game will stop right there. And if after finishing a new game initialization is launched using InitializeGame (), the queue will restart, regardless of whether or not there were characters in it.

Assumptions of Partial Delivery 2 that remains in this delivery:
- Characters and weapons do not change type.
- Weapons can be equipped to only one character at a time. If the player tries to equip a weapon that is already equipped to another character, the weapon user is updated, so the first one ends without equipped weapon.
- In the future, we may be interested in add another type of Enemy (So we abstracted all unspecific methods of Enemy in an AbstractCPUCharacter abstract class).
- We can identify characters by their name, and that works both for CPU and Player characters. There will not be characters with same name, even if they are from different types (Attention: this point is very important, because hashcode only takes in count the names, so if there are a BlackMage and an Enemy with the same name, they will be equals).
- Initially we considered adding the maximum HP and maximum DP to the features that determine a character, however, we will consider that in the future the characters could 'level up' and increase these characteristics, so the key will be only the name.
- Related to the character equipment, we decided to take the option that says that a Thief can equip Swords, Thieves and Bows.

#### Logic and operation of the program
We keep the model logic of the Homework 1, but add some new classes to implement the new features. Among them we have:
- **CharacterAttributeSet and WeaponAttributeSet**. To maintain the set of possible attributes and values of a weapon we decided to create two small classes ``CharacterAtributeSet`` and ``WeaponAttributeSet``that will implement this objective. We  preferred that over using a dictionary or a list because if not we would have to use Object type to store things of different types - such as name and life points, for example - and then downcast them when asking for those attributes. In addition, if we decided to use a dictionary, we would have to use Strings ("Name", "MaxHP", etc)  as keys, while on the other hand the lists would not have a way to indicate what they are keeping corresponds to - so we would have to set an agreement of the order in which they keep things (like, for example, first value is name, second is maxHP, etc). This would introduce a lot of fragility to the code, so adding a class that met the goal via getters without the need for downcasting was the evaluated as the best option.
- **GameState**. This feature was already explained in the previous section. Broadly speaking, the controller will be able to receive messages that effect the requested characteristics at any time, but they will only take effect if its gameState instance variable has the correct value. Example: The ``activeCharacterNormalAttackSelectedCharacter()`` message will only take effect if the value of ``gameState``is an instance of ``Active``.
- **Characters and weapons Factories**. In order to simplify the addition of characters with the same characteristics (eg a group of Globins) we decided to implement the FactoryPattern. The controller configures its factories by choosing a particular one through the variables `` SelectedCharacterFactory`` and ``SelectedWeaponFactory`` and the methods ``SelectCharacterFactory()``, ``SelectWeaponFactory()`` and then he sets the parameters using the methods ``SetSelectedCharacterFactory/Attribute/(int newValue)`` and ``SetSelectedWeaponFactory/Attribute/(int newValue)``. After that, to create a character, its enought to call the corresponding method: ``Add/CharacterType/()``. Player Characters will be added only to playerParty, and CPU Characters to CPUParty. **Since there is no method to add a character by receiving it as parameter, there we will never exist a character in the wrong party.** This is one of the benefits of this implementation.
    - Factory pattern makes our code more robust, less coupled and easy to extend, in exchange for adding a considerable number of methods and making the code somewhat more complex, but we decided that the benefits are greater.
- **NullWeapon and NullCharacters**. This feature was also mentioned before, but we will explain it briefly. ``NullWeapon``'s are weapons with `0` power and weight whose name is the empty string, and which can be equipped to any character. The ``NullCharacter`` is analogous. The null weapon extends from magic weapons, so that it can behave like ``IMagicWeapon`` and `IWeapon`. The null character extends from the PlayerCharacter, so that it can behave as ``IPlayerCharacter`` and ``Character``. We do not need a null character that behaves like ``ICPUCharacter`` as we don't have anything of the SelectedCPUCharacter style. This is one of the implementation benefits that would be lost if we specify ``getAttribute()``.
- **Masterminds**. We added a new group of classes to the game model, the Masterminds. They represents the player (Class ``PlayerMastermind``) and the CPU (Class ``CPUMastermind``) in the game. They extends from ``AbstractMastermind``,which in time implements ``IMastermind``. Its existence is mainly due to the search to delegate tasks that are presented as duties of the controller in other classes that are specifically created for that purpose, and this way the controller will requests they from them when necessary. The inspiration comes from the fact that it seemed strange that the instance variables ``CPUParty``, ``PlayerParty``, ``PlayerInventory``, etc, belonged to the Controller and not to an object of type Player / CPU. In this way we are also adding **extensibility** to the code and respecting the **single responsibility** principle.
- **Observer for Turn's end, Character's Death and QueueAddition**. This is probably the most complex part of the code so far. In order to avoid busy-waitings while waiting for the end of a turn or the death of a character, we decided to implement the ``GameController`` as an **Observer** of properties of their Masterminds in play, who in turn are Observers of the characters in their parties. 
   - Once a character dies, it will fire an event that will be notified to its Mastermind, which will keep the count of his living characters and will notify to the GameController of the dead The player will notify the controller of the death so that he removes it from the queue. 
   -  Once a character attacks, it will fire an event that will be notified to its Mastermind, which in time will notify to the GameController so he ends the character's turn by sending to the active character the order of wait for his new turn and taking a new character from the queue, setting him as activeCharacter and waiting for his action.
   - Once a character adds himself to the turns queue, it will fire an event that will be notified to its Mastermind, which in time will notify it to the the GameController and he will start a new turn in case there is no turn running (if the queue is empty). This is accomplished through the program's threads-based logic: each time a character is added to the turn queue, a new turn will be launched. It seems that this is dangerous as it could overwrite the current turn, if there is one. But this is not the case: since the controller runs in a single thread, it will only run the new turns once the current turn has ended, but since one turn calls the next, this will only be done if the queue becomes empty at some point, i.e., if the chain of turns stops at a certain point. In this way, we met all the instructions presented in section 2.2 of the project statement.
 
The above concludes the new features of the logic of our program. Everything else remains exactly the same as Homework 1.

### UML Diagrams

To date the UML diagrams of the model looks like this (showing only class names):

Figure 3. Homework 2 Final Delivery Model UML Diagram

![Figure 3. Homework 2 Final Delivery Model UML Diagram](/images/UML_T2_Model.png)

Figure 4. Homework 2 Final Delivery State UML Diagram

![Figure 4. Homework 2 Final Delivery State UML Diagram](/images/UML_T2_State.png)

Figure 5. Homework 2 Final Delivery Factory UML Diagram

![Figure 5. Homework 2 Final Delivery Factory UML Diagram](/images/UML_T2_Factory.png)

Figure 6. Homework 2 Final Delivery Controller UML Diagram

![Figure 6. Homework 2 Final Delivery Controller UML Diagram](/images/UML_T2_Controller.png)

To see the complet UML Diagram, check for **UML_Model.pdf**, **UML_State.pdf**, **UML_Factory.pdf**, **UML_Controller.pdf** in the root folder.

---
### Partial Delivery 3 & 4

All the features that are not required yet are removed as they were probably wrong  (or at least Slater said that on U-cursitos). So far the code contemplates:
- The same class hierarchy described in Homework 1
- Weapon equipment
- Normal attacks
- Life status of the characters
- Tests of the above.

That is, **all the methods related to magic attacks and adverse states were removed.** They will be re-implemented in the future, once we have delved further into the course.

The equipping process is carried out via double dispatch, as we described before. Similar for the normal attacks, but **we have added the new Deffense Points based behavior**.
#### Assumptions made so far
- Characters and weapons do not change type.
- Weapons can be equipped to only one character at a time. If the player tries to equip a weapon that is already equipped to another character, the weapon user is updated, so the first one ends without equipped weapon.
- In the future, we may be interested in add another type of Enemy (So we abstracted all unspecific methods of Enemy in an AbstractCPUCharacter abstract class).
- We can identify characters by their name, and that works both for CPU and Player characters. There will not be characters with same name, even if they are from different types (Attention: this last point is very important, because hashcode only takes in count the names, so if there are a BlackMage and an Enemy with the same name, they will have the same hashcode).
- Initially we considered adding the maximum HP and maximum DP to the features that determine a character, however, we will consider that in the future the characters could 'level up' and increase these characteristics, so the key will be only the name.
- Related to the character equipment, we decided to take the option that says that a Thief can equip Swords, Thieves and Bows.
--- 

# #Homework 1 
### Final Delivery 
As Final Delivery we are requested to finish the work done to Partial Delivery 1 and 2. 

#### Execution Instructions
Until now the game is still under development, so there are no further execution instructions. However, it is possible to run tests that show that the program logic works as expected.

To do this use IntelliJ's "Run Tests" option, or compile individual tests using ``cp`` and run them with the ``org.junit.runner.JUnitCore`` flag as shown on [this StackOverFlow thread](https://stackoverflow.com/questions/2235276/how-to-run-junit-test-cases-from-the-command-line).

#### Assumptions made so far
Assumptions are the same of Partial Delivery 2:
- Characters, weapons and spells do not change type.
- Weapons can be equipped to only one character at a time.
- Enemies can suffer from only one state at a time, and those states are overwritten when a new one is set.
- We can identify player characters by the combination of their name, maxDP, maxHP, currentHP and currentDP. We would like to assume that it is enough with the name (to to make sense in game), but it is not really necessary this far, and we will discuss about that with the teaching team for future deliveries.
- We can identify enemies by the combination of their name, weight, power, maxDP and maxHP. We would like to add an integer field ID to have many enemies with the same name but distinguishable, in order to have -for example- a group of Globin units, and be able to differentiate them. Since it is not described in the statement, it will be discussed with the teaching team for future deliveries.
- In the future, we may be interested in add another type of Enemy (So we abstracted all unspecific methods of Enemy in an AbstractCPUCharacter abstract class).
- Related to the character equipment, we decided to take the option that says that a Thief can equip Swords, Thieves and Bows.


#### Logic and operation of the program
Our program works based on a hierarchy of character and weapon classes.

For the characters:
- They are implemented through a base abstract class called `AbstractCharacter`, from where `AbstractCPUCharacter` and `AbstractPlayerCharacter` inherit. In turn, it implements the `ICharacter` interface.
- `AbstractCPUCharacter` gives the methods common to all enemies to the `Enemy` class (the only enemy class so far), which in turn implements the `ICPUCharacter` interface.
- `AbstractPlayerCharacter` serves in an analogous way to `AbstractCPUCharacter`, with the difference that it is inherited on the one hand from the concrete classes `Thief`, `Knight` and `Engineer` (directly, since there are not specific normal-character features), and from the abstract class `AbstractMagicCharacter`, on the other hand (since magic characters have their own features). In turn, `AbstractPlayerCharacter` implements the `IPlayerCharacter` interface.
- The `AbstractMagicCharacter` class is inherited by the concrete classes `BlackMage` and `WhiteMage`, and gives them the methods related to the new mana field and magic attacks. In turn, it implements the `IMagicCharacter` interface.

Interfaces are used as a guideline for what each character can and cannot do and also as a way to tell abstract methods what type of objects they can receive.

The equipping process is carried out through Double Dispatch, where the task of deciding whether a weapon can be equipped to a character or not is delegated to the weapon itself. Likewise, the interaction between characters - attacks between CPU and Player and heals between Player and Player - are implemented via double dispatch. 

It is not necessary to have fields that store the type of weapons or characters, because given the way in which these were implemented, such information is redundant. Concrete classes "know what they are" and implement methods based on it.

In addition to what is requested by the statement, we added some fields that allows us to implement certain characteristics assumed or implicit in the logic of the game, such as that a weapon can be equipped by only one character at a time. To do this, we create the `owner` field, and the `setOwner` and `getOwner` methods, which are called each time a weapon is equipped.

#### UML Diagram
To date the UML diagram of the model looks like this (showing only class names):

Figure 2. Homework 1 Final Delivery UML Diagram
![Figure 2. Homework 1 Final Delivery UML Diagram](/images/UML_T1_Final_Delivery.png)

To see the complet UML Diagram, check for **UML.pdf** in the root folder.

If you want to go deeper, you can continue reading the next section, Partial Delivery 2. The changes from that version to this are minimal and only relate to the tests, not to the way the program works.

---

### Partial Delivery 2
In Partial Delivery 2 our work is to create test for the work done in Partial Delivery 1. But as we might expect, when writing the tests, we identified flaws and redundances that we had to fix and iterate again.

##### Improvements and fixes to Partial Delivery 1
At first we thought that Partial Delivery 1 was correct, so we started fixing little things (for example, some abstractions that were apparently redundant) and creating the tests using the **JUnit5 Framework**,  reaching a ''final'' version at the commit 91408423523b60fc137edf475675f93bb51f446c14bb (Oct 9, 2020). But when we were about to deliver it, we tried with some edge cases and we found a serious problem:  all characters could equip all weapons, even when we were ''overriding'' `equip` method in each concrete class. This error was caused because we did not really made overriding, since the signature of the equip method in the subclasses and in the abstract one was sightly different: while concrete classes had an `equip(Axe/Bow/... weapon)`, abstract class had `equip(IWeapon)`. So when  a character was not able to equip some weapon using it own equip method, they did it anyway by using their parent method. 

Since we have already seen **Double Dispatch** at this point, we decided to use it to correct this problem. We delegate the task of ''deciding if it can be equipped'' on the weapons instead of the characters as follows:
- Each character has an `equipWeapon(IWeapon weapon)` method, that is empty on the abstract class and is overrided in the concrete ones to calls to `weapon.equipToThisClass(this)`, where `ThisClass` has to be replaced by the name of the class. As example, in Knight we find the following: 
```
public void equipWeapon(IWeapon weapon) {
    weapon.equipToEngineer(this); 
    }
```

- Then on `AbstractWeapon` we have all the empty methods `equipToSomeClass(IPlayerCharacter character)`,  that are overrided in the concrete classes when it is appropiate (i.e. when the weapon can be equipped by a SomeClass character). As example, in Axe we found the following:
```
  public void equipToKnight(IPlayerCharacter character) {
      character.equip(this);
  }
```
 
- As we can see, the `equipToSomeCharacter` weapon methods call to an `equip` method in character. ack on character, there is another `equip(IWeapon weapon)` method (that we would love to call setWeapon, but we leave it as equip in order to respect the task statement (''each character has to implement equip''). This method just equip the weapon, since the weapon itself has already verified its feasibility.
- There are some additional instructions in the described methos related to making sure that a weapon can be equipped only by one character at a time, but we did not write them here in order to simplify understanding.

**We also removed the String field `type` from weapons and characters, since we noticed that they were not longer needed.** 

In addition, we implemented the way characters interact between themselves - Player Characters versus Enemies, and heals between Player Characters -. This was not required for this first task, but it was not that difficult to implement once we understood double dispatch and is useful for doing more complete tests. We added *spell attacks, states* and an `AbstractCPUCharacter` class.  The reason for the latter is that the `Enemy` class was somewhat overloaded with very unspecific methods, and we thought that it can be useful to abstract them as we may want to add another type of enemy in the future.

##### Implementing the test
In order to make sure that what we created behaves as it has to, we implemented test for every method. Some of them are tested individually, and others are tested as nested methods (there is a test to another method that uses it directly, and the approval depends on both nested and parent methods). We created three packages of test:
- One for the weapons, using an abstract class `AbstractWeaponTest` which is inherited by every `SomeWeaponTest`. We tested constructors, hashcodes, equals, the ability to equip, unequip, have an owner, get power, get name and get weight.
- One for the characters, using two abstract classes `AbstractCharacterTest` and `AbstractPlayerCharacterTest`. With the first one we test  `Enemy` class for the playable characters we use the second one, that inherits from the first one. With them we test constructors, hashcode, the ability to equip some weapon (only playable characters), and to wait for a turn (which involves testing the weights of weapons or enemies).
- One for the interactions between characters. There we test the condition of being alive in `isAliveTest`, the magic attacks and effects in `magicEffectTest`, and the normal ones in `normalAttackTest`. 

#### Assumptions made so far
- Characters, weapons and spells do not change type.
- Weapons can be equipped to only one character at a time.
- We can identify player characters by the combination of their name, maxDP, maxHP, currentHP and currentDP. We would like to assume that it is enough with the name (to to make sense in game), but it is not really necessary this far. 
- Enemies can suffer from only one state at a time, and those states are overwritten when a new one is set.
- We can identify enemies by the combination of their name, weight, power, maxDP and maxHP. We would like to add an integer field ID to have many enemies with the same name but distinguishable, in order to have -for example- a group of Globin units, and be able to differentiate them. Since it is not described in the statement, it will be discussed with the teaching team for future deliveries.
- Removed the assumption that there will be no more enemy types in the future.
All previous partial delivery assumptions not mentioned here have been removed.


---

### Partial Delivery 1
In Partial Delivery 1 we focus on the architecture with which our program implements Characters and Weapons.
#### Explanation of design decisions - Characters & Weapons

##### Initial state - Provided code
The code provided by the teaching team corresponds to a low-level hierarchy using abstract classes and interfaces of the Character class, and a null hierarchy of the Weapon class. It also includes test, but since this initial code have several conception mistakes (i.e. bad practices), they won't be usefull once we modify the code. 

The UML diagram of the initial state is presented below:

Figure 1. Initial state UML Diagram
![Figure 1. Initial state UML Diagram](/images/initial_state.png)

We identified several problems: Enemies inherit from the `AbstractCharacter` class, which in turn implements the `equip` and `equippedWeapon` methods, but from their description they cannot use or carry weapons. This first problem immediately breaks the **Liskov substitution principle.**

On the other hand, the concrete classes `Weapon` and `PlayerCharacter` are too general to implement methods that respect the principle of **single-responsability**, and using them as they come would in turn generate violations of the Liskov substitution principle, due to the fact that that certain characters have their own characteristics (mana, for example) and can use only certain weapons.

We also note that although the code delivered is functional with respect to the implemented tests, it still lacks a lot of information to fully represent the problem (life points of the characters, spells, states in which they can fall, etc).

Finally, we note that the practice of saving an `Enum` with the possible types of characters is a potential bad practice, since if we want to add another type, we will have to modify this enum - which should be immutable in time - in addition to create the class. This would break the **open / closed principle**.

Therefore, there are many things to do. And the first one is to separate the different character types into subclasses and do the same for the weapons.

##### New Hierarchy Levels for Characters
We decided to implement the following hierarchy for the characters:
- First level: different kinds of Characters as concrete classes `BlackMage, WhiteMage, Engineer, Knight` and  `Thief`, that inherits from `AbstractNormalCharacter` (Engineer, Knight and Thief) or from `AbstractMagicCharacter`(Black and White Mage). 
- Second level: abstract classes `AbstractNormalCharacter` and `AbstractMagicCharacter` that inherits from `AbstractPlayerCharacter`. 
- Third level: abstract class `AbstractPlayerCharacter` that inherits from `AbstractCharacter`, as well as the concrete class `Enemy`.
- Fourth level: abstract class `AbstractCharacter`, that implements the `ICharacter` interface. 

The reasons to implement the first level as been explained before: there are many features that are only of MagicCharacters. For the second level is the fact that we might want to add some special properties to magic or normal characters (for example, let's say that we want to add a Aggressive/Defensive state for Normal Characters), and to implement it in each of the different types of characters wouldn't be a good practice. The third level allow us to separate the CPU characters from the player characters, since they have different features. Finally, the fourth one is to hold the common behaviour off all characters in the game. In the opposite sense, we could say that the established levels serve a purpose of giving specificity to the lower-level classes with respect to their parent classes.

We still have many features of the characters to implement, however in this first installment we focused on creating a reasonable architecture.

On the other hand, the type of each character is saved as a String, which each singular type constructor adds when making the call to the character constructor. This allows us extensibility and get rid of enum's.

##### Hierarchy Levels for Weapons
Similar to how we did with the characters, for the weapons we decided to implement the following architecture:
- First level: Different kinds of Weapons as concrete classes `Staff` (that inherits from `AbstractMagicWeapon`) and `Axe`, `Bow`, `Knife` and `Sword` (that inherits from `AbstractNormalWeapon`).
- Second level: abstract classes `AbstractMagicWeapon` and `AbstractNormalWeapon` that inherits from the abstract class `AbstractWeapon`.
- Third level: abstract class `AbstractWeapon`, that implements the `IWeapon` interface.

The reason for the first one has been explained before: to respect the single-responsability principle. The second level is to make the program *extensible* in case that we want to add some special features that applies only to magic or normal weapons. The last level is to hold the common behaviour of all weapons in the game.

The type of each weapon is stored in a String, analogously to the characters types.

##### Interfaces
In both Characters and Weapons, we use Interfaces as a way to make a not-necessarily-integral identity of the classes that reference them. For example, we will want to have an abstract method `equip()` in the `AbstractPlayerCharacter` class, and we would like to make sure that it will always equip a weapon. Since we can't do something like `public void equip(Sword/Axe/Bow... weapon)`, we will use the `IWeapon` interface and that will allow us to write `public void equip(IWeapon weapon)` and get the same desired effect.

#### Other changes implemented
Although it is not part of what was requested for this installment, we have created basic builders of each type of character and weapon, and we began to create a hierarchy of Spells (which may disappear in future versions). It is important to note that we have been documenting the progress with JavaDoc from the beginning, in order to create clean and readable code.

#### Assumptions made so far
- Characters do not change type
- Weapons do not change type
- Spells do not change type
- There is only one type of enemy, i.e, all enemies have the same functionalities.


