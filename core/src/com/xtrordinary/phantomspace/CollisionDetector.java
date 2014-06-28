package com.xtrordinary.phantomspace;


public class CollisionDetector {
	int newX, newY; //for GetDistanceFromPoint()
	int result; //for GetDistanceFromPoint()
	private Obstacle asteroid;
	private Player player;
	private Obstacle asteroid2;
/*
 *  playerLeg = 67,82 	asteroidCenter = 64,64 floor = 24;  top = 600;
 *  playerHand = 78,41
 *  playerHelmet = 48,1 
 *  playerShoulder 63,19
 *  playerBack = 
 */
	public void SetAsteroidValues(Obstacle asteroid,Obstacle asteroid2) {
		this.asteroid = asteroid;
		this.asteroid2 = asteroid2;
	}
	
	public boolean CheckForAsteroidCollision() {
		if (GetDistanceFromPoint(asteroid.X+62,asteroid.Y+62,asteroid2.X+62,asteroid2.Y+62) < 126) {
			asteroid.direction *= -1;
			asteroid2.direction *= -1;
			//asteroid.speedY = -20;
			return true;
		}
		//asteroid.direction *= -1;
		return false;
	}
	
	
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
		if(asteroid.Y > 472 ) 
		{
			asteroid.Y = 471;
			asteroid.direction *= -1; 
		} else if(asteroid.Y < 24) 
		{
			asteroid.Y = 25;
			asteroid.direction *= -1;
		}
		return asteroid;
	}
	
}
