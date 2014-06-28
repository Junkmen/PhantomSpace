package com.xtrordinary.phantomspace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class MenuWindow extends Background {
	
	Texture container;
	//private int OPTIONS_LAYER = 1; 
	private int OPTIONS_ACTIVE = 0;
	public int MENU_ACTIVE = 1;  //Draw menu.Awesome video optimizing int <3.
	MusicStreamer mStream;  //Music controller.
	BitmapFont highScoreFont = new BitmapFont();  //HighScore displayer.
	HighScoreManager hsm = new HighScoreManager(); //HighScore updater (file handler)
	public boolean ActiveLayers[] = {
		true,false,false,false,false,false	
	}; //Button light up marker.Used to optimize draw performance and skip draws that are not neccessary.
	
	private int ButtonCoordinates[][] = {
			{256,120},
			{256+120,120+115},	//TODO: Optimize this shit to be based on the setX and Y :(.
			{256+118,120+52}, 				//Problem: When this is inited, X and Y are 0.
			{256+53,120+83},				//Possible solution: Init the X of the array in setX and the Y of the array in setY.
			{256+120,120+115},
			{256+120,120+115}
	};
	
	
	
	
	public void swapLayers(int layer,int swap) {
		container = backgroundLayers[layer]; 			   //Some usless function to swapLayers. May be used for the resume 
		backgroundLayers[layer] = backgroundLayers[swap]; // Button press and release. *Its way too slow to swap layers though
		backgroundLayers[swap] = container;              //  better use int marker.
	}
	
	public void onOptionsClicked() {
		OPTIONS_ACTIVE = 1 - OPTIONS_ACTIVE;
	}
	
	
	@Override
	public void drawLayers(SpriteBatch batch) {
		if (MENU_ACTIVE == 1) { //Draws only if the menu is active. (Wins time and frames. Ty libGDX <3
			for (loopUse = 0; loopUse < Layers; loopUse++) {
				if (ActiveLayers[loopUse]) { //Draws active layers. Menu-> PlayButtonPress -> ExitButtonPress -> soundButtonPress -> ...
					batch.draw(backgroundLayers[loopUse], this.ButtonCoordinates[loopUse][0],this.ButtonCoordinates[loopUse][1]);
				}
			} 
			highScoreFont.setColor(Color.BLACK);
			highScoreFont.draw(batch,Integer.toString(hsm.HighScore),this.X+450, this.Y +330);
		}
	}
	
	public int CheckCoordinates(Vector3 touchHandle,Player player,boolean touchUp) {  //TouchUP - button pressed and released.
	
		ActiveLayers[1] = false;
		ActiveLayers[2] = false;
		ActiveLayers[5] = false;
		if((touchHandle.x > 256 + 123) && (touchHandle.x < 256 + 338))
    		if (touchHandle.y > 120 + 123 && touchHandle.y < 120 + 177) {  
    			if (touchUp) {
    				this.MENU_ACTIVE = 0; //Disables menu draw.
    				if(player.X < 0) {//Play Button Released.
	    				player.X = 200; //Brings player back on screen if he's dead.
	    				return 1;      //Tells the game that player has been revived.Resets obstacles.
    				} else {
    					//Enters here if game is paused !!!
    					ActiveLayers[4] = false;
    					return 2;    //The game has been resumed.No reset.
    				}
    			} else { 
    				if(player.X < 0) {
    					ActiveLayers[1] = true; //Play button pressed down. Lights up.
    				} else {
    					ActiveLayers[5] = true; //Resume button pressed down. 
    				}
    			}
    		} else if ((touchHandle.y > 120 + 64) && (touchHandle.y < 120 + 110)) {
    			if (touchUp) {
    				Gdx.app.exit(); //Exit button pressed and released.
    			} else {
    				ActiveLayers[2] = true;
    			}
    		}
		if((touchHandle.x > 256 + 53) && (touchHandle.x < 256 + 113))
    		if (touchHandle.y > 120 + 86 && touchHandle.y < 120 + 155) {  
    			//mStream.soundtrackMp3.pause();
    			if(!touchUp) {
    				if (ActiveLayers[3]) {
    					ActiveLayers[3] = false;
    					if(!mStream.soundtrackMp3.isPlaying()){
    						mStream.soundtrackMp3.play();
    					}
    					//	mStream.soundtrackMp3.setVolume(1f-mStream.soundtrackMp3.getVolume());
    				} else {
    					ActiveLayers[3] = true;
    					mStream.soundtrackMp3.pause();
    				}
    			}
    				//Mutes/Unmutes music. Add future sound effects/musics here.
    		}
		return 0;
	}
	
	public void setMusicStreamer(MusicStreamer mStream) {
		this.mStream = mStream; //sets the resource pointer.
		mStream.StreamMusic(Gdx.files.internal("mfx/soundtrack.mp3"),true,1f); //Begins stream playback.
	}

		
	

}
