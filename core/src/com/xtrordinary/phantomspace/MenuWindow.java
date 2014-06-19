package com.xtrordinary.phantomspace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class MenuWindow extends Background {
	
	Texture container;
	private int OPTIONS_LAYER = 1; 
	private int OPTIONS_ACTIVE = 0;
	public int MENU_ACTIVE = 1;
	MusicStreamer mStream;
	
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
	
	public boolean CheckCoordinates(Vector3 touchHandle,Player player) {
		if((touchHandle.x > 256 + 123) && (touchHandle.x < 256 + 338))
    		if (touchHandle.y > 120 + 128 && touchHandle.y < 120 + 177) {  
    				this.MENU_ACTIVE = 0;
    				player.X = 200;
    				return true;
    		} else if ((touchHandle.y > 120 + 64) && (touchHandle.y < 120 + 110)) {
    			Gdx.app.exit();
    		}
		if((touchHandle.x > 256 + 53) && (touchHandle.x < 256 + 113))
    		if (touchHandle.y > 120 + 86 && touchHandle.y < 120 + 155) {  
    			//mStream.soundtrackMp3.pause();
    				mStream.soundtrackMp3.setVolume(1f-mStream.soundtrackMp3.getVolume());
    		}
		return false;
	}
	
	public void setMusicStreamer(MusicStreamer mStream) {
		this.mStream = mStream;
		mStream.StreamMusic(Gdx.files.internal("mfx/soundtrack.mp3"),true,1.0f);
	}

		
	

}
