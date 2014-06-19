package com.xtrordinary.phantomspace;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuWindow extends Background {
	
	Texture container;
	private int OPTIONS_LAYER = 1; 
	private int OPTIONS_ACTIVE = 0;
	public int MENU_ACTIVE = 1;
	
	public void swapLayers(int layer,int swap) {
		container = backgroundLayers[layer];
		backgroundLayers[layer] = backgroundLayers[swap];
		backgroundLayers[swap] = container;
	}
	
	public void onOptionsClicked() {
		OPTIONS_ACTIVE = 1 - OPTIONS_ACTIVE;
	}
	
	@Override
	public void drawLayers(SpriteBatch batch) {
		if (MENU_ACTIVE == 1) {
			for (loopUse = 0; loopUse < Layers; loopUse++) {
				if (loopUse == OPTIONS_LAYER && OPTIONS_ACTIVE == 1) {
					batch.draw(backgroundLayers[loopUse], this.X, this.Y);
				} else if (loopUse != OPTIONS_LAYER) {
					batch.draw(backgroundLayers[loopUse], this.X, this.Y);
				}
			}	
		}
	}

}
