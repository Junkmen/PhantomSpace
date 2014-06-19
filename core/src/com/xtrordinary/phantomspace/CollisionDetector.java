package com.xtrordinary.phantomspace;


public class CollisionDetector {
	int newX, newY; //for GetDistanceFromPoint()
	int result; //for GetDistanceFromPoint()
	private Obstacle asteroid;
	private Player player;
/*
 *  playerLeg = 67,82 	asteroidCenter = 64,64
 *  playerHand = 78,41
 *  playerHelmet = 48,1 
 *  playerShoulder 63,19
 *  playerBack = 
 */
	
	public boolean SetValues(Player player, Obstacle asteroid) 
	{
		this.asteroid = asteroid;
		this.player = player;
		return true;
	}
	
	public int GetDistanceFromPoint(int xCenterX,int xCenterY, int x, int y) {
		  newX = x - xCenterX;
		  newY = xCenterY - y;
		  result = newX*newX + newY*newY;
		  result = (int) java.lang.Math.sqrt((float)result);
		  return result;
	}
	
	public Player CheckForCollision() {
		if (GetDistanceFromPoint(asteroid.X + 64, asteroid.Y + 64,player.X + 78 , player.Y + 41) < asteroid.Radius) player.X = -200; 
		else if (GetDistanceFromPoint(asteroid.X + 64, asteroid.Y + 64,player.X + 48 , player.Y + 60) < asteroid.Radius) player.X = -200;
		else if (GetDistanceFromPoint(asteroid.X + 64, asteroid.Y + 64,player.X + 78 , player.Y + 41) < asteroid.Radius) player.X = -200;
		else if (GetDistanceFromPoint(asteroid.X + 64, asteroid.Y + 64,player.X + 63 , player.Y + 19) < asteroid.Radius) player.X = -200;
		else if (GetDistanceFromPoint(asteroid.X + 64, asteroid.Y + 64,player.X + 19 , player.Y + 8) < asteroid.Radius) player.X = -200;
		return player;
	}
	
	public Obstacle CheckForEnviromentCollision() 
	{
		if(asteroid.Y + asteroid.getHeight() > 580) 
		{
			asteroid.direction *= -1; 
		} else if(asteroid.Y < 50) 
		{
			asteroid.direction *= -1;
		}
		return asteroid;
	}
	
}
