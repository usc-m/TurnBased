Program Design
==============

Program Class
-------------

Contains main function

Game Class
----------

Handles screen transitions, updating and drawing screens etc.

GameState Class
---------------

Contains information that should be preserved when the game is saved
such as the map, player position and stats

MapGenerator Class
------------------

When given a seed, produces a map for the player to explore/complete

Map Class
---------

Contains information about the terrain of the level, and the positions of
entities and objects in the level

EmtityCollection Class
----------------------

Contains the entities that are present on the map, so they can be iterated
over easily for updating/drawing

Entity Class
------------

Contains components that describe an entity, like how to draw the entity
(is it an ASCII character, a static image, an animated image? where does it appear?),
how the entity behaves on the map screen or the combat screen, does it respond to
player input etc.?

EntityComponent Class
---------------------

A specific component of an entity, like how it's drawn, or how it behaves etc.

Screen Class
------------

A screen in the game (like the menu, or the map, or a battle), can be transitioned to 
and from

MapScreen Class
---------------

The map screen for the game, derived from the above Screen class, handles drawing the map,
showing position of the player etc.

CombatScreen Class
------------------

The battle screen for the game, derived from the above Screen class, handles drawing the battle,
giving the player combat options and showing the outcome of attacks (on health etc.) to the player


