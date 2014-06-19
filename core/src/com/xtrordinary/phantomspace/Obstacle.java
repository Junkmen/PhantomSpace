package com.xtrordinary.phantomspace;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Obstacle {
		Texture texture;
		int X;
		int Y;
		int Speed;
		int speedY = 30;
		int Rotation;
		int Radius=58;
		int direction=0;
		boolean active = true;
		
		
		public void setTexture(Texture pTexture) {
			this.texture = pTexture;
		}
		
		public void setX(int x) {
			this.X = x;
		}
		
		public void setY(int y) {
			this.Y = y;
		}
		
		public void setRotation (int rotation) {
			this.Rotation = rotation;
		}
		
		public int getWidth() {
			return this.texture.getWidth();
		}
		
		public int getHeight() {
			return this.texture.getHeight();
		}
		
		public void reset(int ScreenWidth) {
			X = ScreenWidth + getWidth() + MathUtils.random(0,250);
			Y = MathUtils.random(0,600 - getHeight());
			//TODO update function to match any ScreenHeight;
			direction = MathUtils.random(-3,3);
			this.active = true;
		}
}
