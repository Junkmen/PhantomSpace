package com.xtrordinary.phantomspace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class HighScoreManager {
	
	public int HighScore = GetHighScore();
	boolean isLocAvailable = Gdx.files.isLocalStorageAvailable();
	
	public int GetHighScore() {
		int HighScore=0;
		Preferences prefs = Gdx.app.getPreferences("HighScore");
		HighScore = prefs.getInteger("HighScore");
		prefs.flush();
		return HighScore;
	}
	public void UpdateHighScore(int NewHighScore) {
		Preferences prefs = Gdx.app.getPreferences("HighScore");
		prefs.putInteger("HighScore", NewHighScore);
		prefs.flush();
	}
	
}
