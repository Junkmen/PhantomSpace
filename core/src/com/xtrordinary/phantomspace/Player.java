package com.xtrordinary.phantomspace;

import com.badlogic.gdx.graphics.Texture;

public class Player {
	int X;
	int Y;
	int Speed,SpeedMultiplier;
	Texture texture;
	Texture animatedTextures[] = new Texture[5];
	int numberOfTextures;
	int CurrentWalk = 1;
	public int WALK_1 = 1,WALK_2=2,WALK_3=3,JUMP_1=4;
	
	public void nullSpeed() {
		this.Speed = 0;
	}
	public void addSpeed(int speed) {
		if (this.Speed < 0) this.Speed = 0; 
		this.Speed += speed;
		this.SpeedMultiplier = 0;
	}
	
	public void setX(int x) {
		X = x;
	}
	public void setY(int y) {
		Y = y;
	}
	public void addTexture(Texture texture,int x) {
		this.animatedTextures[x] = texture;
		if (numberOfTextures < 5){
			numberOfTextures ++;
		}
	}
	public void setDrawableTexture(int texture){
		this.texture = animatedTextures[texture];
		CurrentWalk = texture;
	}
	public int getWidth() {
		return texture.getWidth();
	}
	public int getHeight() {
		return texture.getHeight();
	}
	public void nextWalkAnimation() {
		this.CurrentWalk++;
		if (this.CurrentWalk > 2) this.CurrentWalk = 1;
	}
}
