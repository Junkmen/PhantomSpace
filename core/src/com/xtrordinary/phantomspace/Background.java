package com.xtrordinary.phantomspace;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
	
	int X;
	int Y;
	Texture backgroundLayers[] = new Texture[6]; //Texture container.
	int Layers=0,loopUse; // loopUse = pre initialized variable for loop uses.Garbage collector optimization.
	
	public int getLayers(){
		return Layers; // Returns the number of added layers.
	}
	public void setX(int x){
		this.X = x;
	}
	public void setY(int y){
		this.Y = y;
	}
	public void drawLayers(SpriteBatch batch){
		for (loopUse = 0; loopUse < Layers; loopUse++) {
			batch.draw(backgroundLayers[loopUse], this.X, this.Y);
		}	
	}
	public void addLayer(Texture texture) {
		if (Layers < backgroundLayers.length) { 
			this.backgroundLayers[this.Layers] = texture;
			this.Layers++;
		}
	}
}
