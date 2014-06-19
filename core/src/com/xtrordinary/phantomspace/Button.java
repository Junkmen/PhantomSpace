package com.xtrordinary.phantomspace;

import com.badlogic.gdx.graphics.Texture;

public class Button {
	Texture texture;
	Texture onTouch;
	int X;
	int Y;
	public boolean GAME_PAUSED = false;
	public void setX(int x) {
		this.X = x;
	}
	public void setY(int y) {
		this.Y = y;
	}
	public void onTouch() {
		if (GAME_PAUSED) {
			GAME_PAUSED = false;
		} else {
			GAME_PAUSED = true;
		}
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	public void setOnTouchTexture(Texture texture){
		this.onTouch = texture;
	}

}
