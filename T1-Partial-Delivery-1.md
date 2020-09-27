**Markdown Syntax**. Can be opened in [Dilinger.io](https://dillinger.io).
# Semestral Project: Final Reality
# CC3002 - Design and Programming Methodologies
Section: 2
Student: Matías Vergara Silva
Github: matiasvergaras
Lecture teacher: Mrs. Nancy Hitschfield
Assistant teacher: Ignacio Slater M.
Review assistant: Kevin Pinochet Hérnandez

# #Homework 1 
### Partial Delivery 1
In Partial Delivery 1 we focus on the architecture with which our program implements Characters and Weapons.
#### Explanation of design decisions - Characters & Weapons

##### Initial state - Provided code
The code provided by the teaching team corresponds to a low-level hierarchy using abstract classes and interfaces of the Character class, and a null hierarchy of the Weapon class. //The UML diagram of the initial state is presented below (See Figure 1)//

Several problems are identified: Enemies inherit from the AbstractCharacter class, which in turn implements the Equip and Equipped Weapon methods, but from their description they cannot use or carry weapons. This first problem immediately breaks the **Liskov substitution principle.**

On the other hand, the concrete classes "Weapon" and "PlayerCharacter" are too general to implement methods that respect the principle of **single-responsability**, and using them as they come would in turn generate violations of the Liskov substitution principle, due to the fact that that certain characters have their own characteristics (mana, for example) and can use only certain weapons.

We also note that although the code delivered is functional with respect to the implemented tests, it still lacks a lot of information to fully represent the problem (life points of the characters, spells, states in which they can fall, etc).

Therefore, there are many things to do. And the first one is to separate the different character types into subclasses and do the same for the weapons.

##### New Hierarchy Levels for Characters
We decided to implement the following hierarchy for the characters //(See Figure 2).//
- First level: different kinds of Characters as concrete classes ``BlackMage, WhiteMage, Engineer, Knight`` and  ``Thief``, that inherits from ``AbstractNormalCharacter`` (Engineer, Knight and Thief) or from ``AbstractMagicCharacter``(Black and White Mage). 
- Second level: abstract classes ``AbstractNormalCharacter`` and ``AbstractMagicCharacter`` that inherits from ``AbstractPlayerCharacter``. 
- Third level: abstract class ``AbstractPlayerCharacter`` that inherits from ``AbstractCharacter``, as well as the concrete class ``Enemy.``
- Fourth level: abstract class ``AbstractCharacter``, that implements the ``ICharacter`` interface. 

The reasons to implement the first level as been explained before. For the second level is the fact that we might want to add some special properties to magic or normal characters (for example, let's say that we want to add a Aggressive/Defensive state for Normal Characters), and to implement it in each of the different types of characters wouldn't be a good practice. The third level allow us to separate the CPU characters from the player characters, since they have different features. Finally, the fourth one is to hold the common behaviour off all characters in the game. In the opposite sense, we could say that the established levels serve a purpose of giving specificity to the lower-level classes with respect to their parent classes.

We still have many features of the characters to implement, however in this first installment we focused on creating a reasonable architecture.

##### Hierarchy Levels for Weapons
Similar to how we did with the characters, for the weapons we decided to implement the following architecture:
- First level: Different kinds of Weapons as concrete classes ``Staff`` (that inherits from ``AbstractMagicWeapon``) and ``Axe``, ``Bow``, ``Knife`` and ``Sword`` (that inherits from ``AbstractNormalWeapon``).
- Second level: abstract classes ``AbstractMagicWeapon``and ``AbstractNormalWeapon`` that inherits from the abstract class ``AbstractWeapon``.
- Third level: abstract class ``AbstractWeapon``, that implements the ``IWeapon``interface.

The reason for the first one has been explained before: to respect the single-responsability principle. The second level is to make the program *extensible* in case that we want to add some special features that applies only to magic or normal weapons. The last level is to hold the common behaviour of all weapons in the game.

##### Interfaces
In both Characters and Weapons, we use Interfaces as a way to make a not-necessarily-integral identity of the classes that reference them. For example, we will want to have an abstract method ``equip()`` in the ``AbstractPlayerCharacter`` class, and we would like to make sure that it will always equip a weapon. Since we can't do something like ``public void equip(Sword/Axe/Bow... weapon)``, we will use the ``IWeapon`` interface and that will allow us to write ``public void equip(IWeapon weapon)`` and get the same desired effect.

#### Other changes implemented
Although it is not part of what was requested for this installment, we have created basic builders of each type of character and weapon, and we began to create a hierarchy of Spells (which may disappear in future versions). It is important to note that we have been documenting the progress with JavaDoc from the beginning, in order to create clean and readable code.


