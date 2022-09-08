# Tron - Java Project
## By: Akshat Jain, Coby Young, and Gokulkrishnan Harikrishnan

The Tron movie is based on bikes that leave light trails behind them. If another player hits its own trail or another’s, then that player is dead. In this java project, we will simulate the idea from the movie, into a real game.

The program will be used through mouse commands to navigate the menu and settings. The controls for the game itself will use the keyboard commands to control to two bikes. Arrow keys for one and wasd for the other.

There are two bikes that run in any direction, depending on the arrows the user clicks. Each bike creates a trail running after it, and if another bike hits any trail, the bike dies and the other user wins. The trail can belong to anyone.
  
Classes: 

Bike - class to create a bike and will have its own trail.

Grid - creates the background for the game.

CoordinateSystem - the coordinate system will find the coordinates of the two bikers.

Controls - Takes key input from user to turn bikes

Menu - includes different options like how music volume, speed of bikers etc.

TronGame - runnable class, creates the two bikers and creates the window and runs the game using the all of the other classes.

Music - background music for the game. Creo - carnivores

Trail - Creates trail

Move - Moves bike and trail
	
Responsibility List: 

Akshat: Grid, CoordinateSystem, Controls, Bike, Trail

Gokul: TronGame, Music, Tron, Move

Coby: Menu, Art and Special Effects for the whole program
