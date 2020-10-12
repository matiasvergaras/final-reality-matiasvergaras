Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com).


Broadly speaking for the combat the player has a group of characters to control and a group of 
enemies controlled by the computer. Characters may be from 2 diferent types: Normal Characters and Magic Characters. Normal Characters include 3 subclasses: Engineer, Thief and Knight. Magic Characters include Black Mages and White Mages. Each subclass can equip specific types of weapons (which can be Staves, Knieves, Swords, Bows and Axes) but just one at a time. Characters fight enemies (who cannot use magic or weapons) through a turn system.

---

# Semestral Project: Final Reality

Course: *CC3002 - Design and Programming Methodologies*

Section: 2

Student: Matías Vergara Silva

Github User: matiasvergaras

Lecture teacher: Mrs. Nancy Hitschfield

Assistant teachers: Matías Ramírez & Ignacio Slater M.

Review assistant: Kevin Pinochet Hérnandez (?) (I guess, because he's on the project view)


**We will be writing updates of the work done in each pull request here, in '' Latest first '' format. Execution instructions will be added at the end, once the program is fully developed and functional.**

--- 

# #Homework 1 

### Partial Delivery 2
In Partial Delivery 2 our work is to create test for the work done in Partial Delivery 1. But as we might expect, when writing the tests, we identified flaws and redundances that we had to be corrected along the way.

##### Improvements and fixes to Partial Delivery 1
At first we thought that Partial Delivery 1 was correct, so we started fixing little things (for example, some abstractions that were apparently redundant) and creating the tests using the **JUnit5 Framework**,  reaching a ''final'' version at the commit 9140 8423523b60fc137edf475675f93bb51f446c14bb (Oct 9, 2020). But when we were about to deliver it, we tried with some edge cases and we found a serious problem:  all characters could equip all weapons, even when we were ''overriding'' ``equip`` method in each concrete class. This error was caused because we did not really made overriding, since the signature of the equip method in the subclasses and in the abstract one was sightly different: while concrete classes had an ``equip(Axe/Bow/... weapon)``, abstract class had ``equip(IWeapon)``. So when  a character was not able to equip some weapon using it own equip method, they did it anyway by using their parent method. 

Since we have already seen **Double Dispatch** at this point, we decided to use it to correct this problem. We delegate the task of ''deciding if it can be equipped'' on the weapons instead of the characters as follows:
- Each character has an ``equipWeapon(IWeapon weapon)`` method, that is empty on the abstract class and is overrided in the concrete ones to calls to ``weapon.equipToThisClass(this)``, where ``ThisClass`` has to be replaced by the name of the class. As example, in Knight we find the following: ``public void equipWeapon(IWeapon weapon) {weapon.equipToEngineer(this); }``. 
- Then on AbstractWeapon we have all the empty methods ``equipToSomeClass(IPlayerCharacter character)``,  that are overrided in the concrete classes when it is appropiate (i.e. when the weapon can be equipped by a SomeClass character). As example, in Axe we found ``public void equipToKnight(IPlayerCharacter character) {character.equip(this);}``. As we can see, it calls to an ``equip`` method in character. 
- Back on character, there is another ``equip(IWeapon weapon)`` method (that we would love to call setWeapon, but we leave it as equip in order to respect the task statement (''each character has to implement equip''). This method just equip the weapon, since the weapon itself has already verified its feasibility.
- There are some additional instructions in the described methos related to making sure that a weapon can be equipped only by one character at a time, but we did not write them here in order to simplify understanding.

In addition, we implemented the way characters interact between themselves - Player Characters versus Enemies, and heals between Player Characters -. This was not required for this first task, but it was not that difficult to implement once we understood double dispatch and is useful for doing more complete tests. We added *spell attacks, states* and an ``AbstractEnemyCharacter`` class.  The reason for the latter is that the Enemy class was somewhat overloaded with very unspecific methods, and we thought that it can be useful to abstract them as we may want to add another type of enemy in the future.

##### Implementing the test
In order to make sure that what we created behaves as it has to, we implemented test for every method. Some of them are tested individually, and others are tested as nested methods (there is a test to another method that uses it directly, and the approval depends on both nested and parent methods). We created three packages of test:
- One for the weapons, using an abstract class ``AbstractWeaponTest`` which is inherited by every ``SomeWeaponTest``. We tested constructors, hashcodes, equals, the ability to equip, unequip, have an owner, get power, get name and get weight.
- One for the characters, using two abstract classes ``AbstractCharacterTest`` and ``AbstractPlayerCharacterTest``. With the first one we test  Enemy class for the playable characters we use the second one, that inherits from the first one. With them we test constructors, hashcode, the ability to equip some weapon (only playable characters), and to wait for a turn (which involves testing the weights of weapons or enemies).
- One for the interactions between characters. There we test the condition of being alive in ``isAliveTest``, the magic attacks and effects in ``magicEffectTest``, and the normal ones in ``normalAttackTest``. 

#### Assumptions made so far
- Characters, weapons and spells do not change type.
- We can identify player characters by the combination of their name, maxDP, maxHP, currentHP and currentDP. We would like to assume that it is enough with the name (to to make sense in game), but it is not really necessary this far. 
- We can identify enemies by the combination of their name, weight, power, maxDP and maxHP. We would like to add an integer field ID to have many characters with the same name but distinguishable, in order to have, for example, a group of Globin units, and be able to attack one of them.t Since it is not described in the statement, it will be discussed with the teaching team for future deliveries.
- Removed the assumption that there will be no more enemy types in the future.
All previous partial delivery assumptions not mentioned here have been removed.




### Partial Delivery 1
In Partial Delivery 1 we focus on the architecture with which our program implements Characters and Weapons.
#### Explanation of design decisions - Characters & Weapons

##### Initial state - Provided code
The code provided by the teaching team corresponds to a low-level hierarchy using abstract classes and interfaces of the Character class, and a null hierarchy of the Weapon class. It also includes test, but since this initial code have several conception mistakes (i.e. bad practices), they won't be usefull once we modify the code. 

The UML diagram of the initial state is presented below (See Figure 1):
![Figure 1. Initial state UML Diagram](/images/initial_state.png)

We identified several problems: Enemies inherit from the AbstractCharacter class, which in turn implements the Equip and EquippedWeapon methods, but from their description they cannot use or carry weapons. This first problem immediately breaks the **Liskov substitution principle.**

On the other hand, the concrete classes "Weapon" and "PlayerCharacter" are too general to implement methods that respect the principle of **single-responsability**, and using them as they come would in turn generate violations of the Liskov substitution principle, due to the fact that that certain characters have their own characteristics (mana, for example) and can use only certain weapons.

We also note that although the code delivered is functional with respect to the implemented tests, it still lacks a lot of information to fully represent the problem (life points of the characters, spells, states in which they can fall, etc).

Finally, we note that the practice of saving an ``Enum``'s with the possible types of characters is a potential bad practice, since if we want to add another type, we will have to modify this enum - which should be immutable in time - in addition to create the class. This would break the **open / closed principle**.

Therefore, there are many things to do. And the first one is to separate the different character types into subclasses and do the same for the weapons.

##### New Hierarchy Levels for Characters
We decided to implement the following hierarchy for the characters:
- First level: different kinds of Characters as concrete classes ``BlackMage, WhiteMage, Engineer, Knight`` and  ``Thief``, that inherits from ``AbstractNormalCharacter`` (Engineer, Knight and Thief) or from ``AbstractMagicCharacter``(Black and White Mage). 
- Second level: abstract classes ``AbstractNormalCharacter`` and ``AbstractMagicCharacter`` that inherits from ``AbstractPlayerCharacter``. 
- Third level: abstract class ``AbstractPlayerCharacter`` that inherits from ``AbstractCharacter``, as well as the concrete class ``Enemy.``
- Fourth level: abstract class ``AbstractCharacter``, that implements the ``ICharacter`` interface. 

The reasons to implement the first level as been explained before: there are many features that are only of MagicCharacters. For the second level is the fact that we might want to add some special properties to magic or normal characters (for example, let's say that we want to add a Aggressive/Defensive state for Normal Characters), and to implement it in each of the different types of characters wouldn't be a good practice. The third level allow us to separate the CPU characters from the player characters, since they have different features. Finally, the fourth one is to hold the common behaviour off all characters in the game. In the opposite sense, we could say that the established levels serve a purpose of giving specificity to the lower-level classes with respect to their parent classes.

We still have many features of the characters to implement, however in this first installment we focused on creating a reasonable architecture.

On the other hand, the type of each character is saved as a String, which each singular type constructor adds when making the call to the character constructor. This allows us extensibility and get rid of enum's.

##### Hierarchy Levels for Weapons
Similar to how we did with the characters, for the weapons we decided to implement the following architecture:
- First level: Different kinds of Weapons as concrete classes ``Staff`` (that inherits from ``AbstractMagicWeapon``) and ``Axe``, ``Bow``, ``Knife`` and ``Sword`` (that inherits from ``AbstractNormalWeapon``).
- Second level: abstract classes ``AbstractMagicWeapon``and ``AbstractNormalWeapon`` that inherits from the abstract class ``AbstractWeapon``.
- Third level: abstract class ``AbstractWeapon``, that implements the ``IWeapon``interface.

The reason for the first one has been explained before: to respect the single-responsability principle. The second level is to make the program *extensible* in case that we want to add some special features that applies only to magic or normal weapons. The last level is to hold the common behaviour of all weapons in the game.

The type of each weapon is stored in a String, analogously to the characters types.

##### Interfaces
In both Characters and Weapons, we use Interfaces as a way to make a not-necessarily-integral identity of the classes that reference them. For example, we will want to have an abstract method ``equip()`` in the ``AbstractPlayerCharacter`` class, and we would like to make sure that it will always equip a weapon. Since we can't do something like ``public void equip(Sword/Axe/Bow... weapon)``, we will use the ``IWeapon`` interface and that will allow us to write ``public void equip(IWeapon weapon)`` and get the same desired effect.

#### Other changes implemented
Although it is not part of what was requested for this installment, we have created basic builders of each type of character and weapon, and we began to create a hierarchy of Spells (which may disappear in future versions). It is important to note that we have been documenting the progress with JavaDoc from the beginning, in order to create clean and readable code.

#### Assumptions made so far
- Characters do not change type
- Weapons do not change type
- Spells do not change type
- There is only one type of enemy, i.e, all enemies have the same functionalities.

