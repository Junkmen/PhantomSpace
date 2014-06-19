package com.xtrordinary.phantomspace;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
	
	int X;
	int Y;
	Texture backgroundLayers[] = new Texture[3];
	int Layers=0,loopUse;
	
	public int getLayers(){
		return Layers;
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
		if (Layers < 3) {
			this.backgroundLayers[this.Layers] = texture;
			this.Layers++;
		}
	}
}
