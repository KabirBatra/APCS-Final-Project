[Return of the toilet paper insurrection]
Authors: Kabir Batra, Kaie Chen
Revision: April 26, 2020


Introduction: 


[In a few paragraphs totalling about ½ page, introduce the high-level concept of your program. What this looks like depends a lot on what type of thing you are making. An introduction for an application will look different than one for a game. In general, your introduction should address questions like these:


What does your program do?


This program will provide entertainment for the end-user in the form of a top-down shooter game with an endless number of enemies, special rooms, and weapons. 


What problem does it solve? Why did you write it?


This solves the problem that Covid-19 has created: boredom because of isolation. Thus, this program will provide a way for people to entertain themselves with the latest meme game.  


What is the story?


You are trying to buy some toilet paper at Costco, and as you grasp the package of toilet paper, you suddenly teleport into a slimy sewer like room. Thus, with the limited ammo and weapons that you had purchased from Costco that you brought with you, you must fight your way through the horde of slimes trying to kill you! Do you have what it takes to get home safely with the toilet paper you purchased? Let’s find out……..


What are the rules? What is the goal?


Rule #1: No cheating
Rule #2: Utilize your current tool to make the enemies not living
Rule #3: Try to get to the checkout line
Rule #4: You can’t win…… There are too many slimes who want that toilet paper. 


Who would want to use your program?


EVERYONE!!!!!! 


What are the primary features of your program?


Feature #1: It provides insight into how valuable toilet paper is during this pandemic.
Feature #2: It will have auto aim!
Feature #3: Different kinds of enemies!~
Feature #4: Collisions with walls and creatures!
Feature #5: Multiple waves of angry slimes trying to get your toilet papers!


________________


Instructions:
[Explain how to use the program. This needs to be specific: 
Which keyboard keys will do what? 


w- Move up
s- Move down
a- Move Left
D- Move right


Space bar- Attack!! (shoot toilet paper at the nearest enemy)






Where will you need to click? 


Click on buttons to start game and you can also click the pause button to pause the game


Will you have menus that need to be navigated? What will they look like? 


There will be two buttons: One play, one quit
Then there will be an instructions menu, which then offers the button to the game


Do actions need to be taken in a certain order?]


You need to click on a button in the menu screen before you can start playing.


Features List (THE ONLY SECTION THAT CANNOT CHANGE LATER):
Must-have Features:
[These are features that we agree you will definitely have by the project due date. A good final project would have all of these completed. At least 5 are required. Each feature should be fully described (at least a few full sentences for each)]
* There is a player that can move around. It will move with the keys W A S D. Diagonal movements are possible by pressing 2 buttons at once. 
* There is a ‘camera’ that follows the player around. This camera keeps the player on the screen centered as the background moves. 
* You can auto-aim attacks. This means the direction of the laser is automatically computed. When you shoot a laser, it automatically goes from the player to the nearest enemy. 
* There are boundaries and obstacles. The player cannot walk through these boundaries so the player has to stay inside of the room and has to navigate around the obstacles. 
* There are enemies with ai. The enemies use their attacks to hurt your player. Their lasers will be directed toward the player and they can randomly move around or toward the player. 
* There are multiple waves of enemies. This means that once all the enemies are defeated, more of them appear in the room. You need to complete multiple of these waves of enemies before you can move to the next room/level. 


Want-to-have Features:
[These are features that you would like to have by the project due date, but you’re unsure whether you’ll hit all of them. A good final project would have perhaps half of these completed. At least 5 are required. Again, fully describe each.]


* There are different room possibilities. They have different sizes and different obstacles to hide behind. There are also different types of enemies. 
* There is a currency system. Currency can be increased after killing enemies and defeating all the enemies in a room.
* There are different types of weapons. Some shoot lasers at a faster rate or slower rate. They look different. They also do a different amount of damage to enemies
* After defeating a set number of rooms/levels, there is a peaceful room where in game currency can be used, health is restored, and there are more weapons. 
* There are multiple different types of enemies. They have different speeds and different ai and different amounts of health. 


Stretch Features:
[These are features that we agree a fully complete version of this program would have, but that you probably will not have time to implement. A good final project does not necessarily need to have any of these completed at all. At least 3 are required. Again, fully describe each.]


* There will be animation to how the characters would move! There would be walking animations, shooting animation, and dying animations!
* There would be a multiplayer pvp version of this game too where there is an overlord that spawns in the enemies 
* New mechanics for weapons and perhaps a portal gun??? For the lolz? Random generated maps and random drop system?
* 



Class List:
[This section lists the Java classes that make up the program and very briefly describes what each represents. It’s totally fine to put this section in list format and not to use full sentences.]


* DrawingSurface - represents the processing window
   * Contains draw method etc 
   * Contains game loop?
* GameHandler - a way to update all objects at once, a way to access all objects in the game to do collisions
   * contains array (linkedlist) of all the dynamic objects
* WindowHandler: used to control what window the user is currently on
   * Contains an instance of all the different windows in a list
* Window - abstract class for blueprint of all windows
* MenuWindow: blueprint for main menu
* InstructionsWindow: blueprint for instructions window
* GameWindow: keeps track of what room you are in; displays everything
* GameObject DynamicObject - any object in the game that is interactable (other than boundaries)
   * x, y position
   * Method to get object’s boundary?
   * Method called onInteract(gameObject)
* InteractableTile - a tile that causes something to happen when you step on it; extends dynamic object
   * What you step on when you want to shop
   * Portal gun’s portal??
* Chest - has loot the player wants; extends interactableTile
   * Contains a weapon (or currency?)
* Creature - anything that moves, extends dynamic object 
* Player - represents the player being controlled, extends creature
   * position
   * Health
   * Weapon Slot
   * movement
* Enemy - represents the enemies, extends creature
   * Health
   * Movement ai
   * position
* weapon/item - represents a weapon/food the player (and enemies?) can hold
   * Integer Damage 
   * Rate of fire
   * isEquipable boolean
   * isConsumable
* bullet - the actual bullet object
   * Position, velocity etc
   * Uses handler to check if it collided with an enemy (if so, the enemy loses health and the bullet is removed from the handler’s array) 
   * Sprite, damage, knockback set in constructor
* Laser - a bullet with its own sprite
   * Specific size/bounds
   * Damage set in constructor
   * Knockback set in constructor
   * Fastest bullet
* RifleBullet - another type of bullet with different speed and damage
* Abstract map - represents the room that the game is taking place in with boundaries etc.
   * 2d array of integers to describe room (each integer represents a tile on the spriteSheet)
   * Constructed from text file(?) image file
* A class for every map that exists (Room1, Room2, JungleRoom etc) - extends map
* Camera - follows the player around the screen
   * Position
   * Bounds off of the edge of the screen
* Assets - class with all of the image and sound data
   * Stores all of the sprite sheets in a static array
   * Initialized only once (constructor called only once?)
   * No constructor and only a method to initialize?
* SpriteSheet: 
   * Image path passed into constructor
   * Method to get a sprite at a certain row and column


Credits:
[Gives credit for project components. This includes both internal credit (your group members) and external credit (other people, websites, libraries). To do this:
* List the group members and describe how each member contributed to the completion of the final program. This could be classes written, art assets created, leadership/organizational skills exercises, or other tasks. Initially, this is how you plan on splitting the work.
* Give credit to all outside resources used. This includes downloaded images or sounds, external java libraries, parent/tutor/student coding help, etc.]


Splitting the work:
Kabir writes foundational code, player code, window classes, movement, input/output
Kaie designs different types of enemies, enemy graphics, weapons


Outside resources: 
Processing java library
Tutorials
* https://youtu.be/xXXt3htgDok - role playing game in C++
* https://youtu.be/ZM8yIIRGruM - shooter game in Java
* https://processing.org/examples/animatedsprite.html - animations in processing
* https://alvinalexander.com/blog/post/java/getting-rgb-values-for-each-pixel-in-image-using-java-bufferedi/ - pixel rgb