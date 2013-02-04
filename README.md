TurnBased
=========

A Turn-based Strategy Game developed for Inf1 OOP Group Project

Team Members
-------------
- [Michelle Chu](https://github.com/michellechu15)
- [Michael Inglis]()
- [Teun Kokke](https://github.com/TeunK)
- [Fotis Papadogeorgopoulos](https://github.com/fpapado)
- [Matthew Summers](https://github.com/Wishf)

Project Description
-------------------
Our project will be a turn-based strategy game in which the player navigates a procedurally-generated "overworld" map and engages enemy NPCs (Non-Player Characters).

The objective of the player is to complete as many levels as possible and see how long they can survive in the game. To complete a level, the player must reach the
door/stairs present on that level. However, enemy NPCs will attempt to stop the player and slow their progress. 

When an engagement begins, the screen will shift from map-mode to combat-mode, in which the player will take turns against the computer selecting moves for their 
character to perform. During this time, the player can take damage from the NPC, depleting the player's health. When the player runs out of health completely, their 
character dies and the player must restart the game, and since lost health will not regenerate outside of combat, the player must be wary of what encounters they
engage in and plan ahead in order to successfully progress in the game.


Minimum Feature Set
--------------------

### General ###

- Menu screen with instructions/play/options etc.
- Basic ASCII graphics
- User Interface displaying player status
- Basic stats system (attack, defense etc.)
- Hi-score system which tracks how well the player has done previously
- Turn-based: The game state does not change until the player inputs an action. This means NPCs will not move, and other events will not trigger, until input is recieved

### Map Screen ###

- A map grid for the player to move through, and objects to be stored in
- A procedural map generator to produce levels based on a random seed for the player to complete
- Shows if entities (boss monsters, traps, items etc.) are present on a given grid tile
- Upon movement to a tile, a random encounter with a lesser monster may trigger
- Shows if a grid square is a viable movement choice
- NPCs have basic pathfinding, attempting to reach the player if the player is in their field of view

### Combat Screen ###

- NPC that attack the player in a 1v1 battle
- Simplistic AI (random?) dictating the NPCs attack pattern
- Fixed skill sets: Default set of skills given to the player at the start of the game
- Basic stats system: Player may set these at the start of the game to influence the way battles play out
- Menu system providing options for combat (such as attack, flee, defend etc.)
- Displays relevant data about the battle (attack damage, remaining health/magic?)


Potential Features
-------------------

These may be implemented if there is extra time available:

- Game-saving
- Improved graphics and animations (2D)
- Sound/music
- Friendly NPCs (shops etc.)
- inventory and items (includes convenience features such as auto-arrange button)
- NvM battles (possibly including multiplayer?)
- Player-chosen, flexible skills
- Particle systems
