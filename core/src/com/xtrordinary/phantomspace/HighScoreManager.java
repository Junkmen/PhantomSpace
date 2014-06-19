package com.xtrordinary.phantomspace;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class HighScoreManager {
	
	public int HighScore = GetHighScore();
	boolean isLocAvailable = Gdx.files.isLocalStorageAvailable();
	
	public int GetHighScore() {
		int HighScore=0;
		if (isLocAvailable){
			String filename = "HighScore.csv";
		
			File file = new File(Gdx.files.getLocalStoragePath(),filename);
			if (file != null) {
			InputStream inS = null; 
				try {
					inS = new BufferedInputStream(new FileInputStream(file));
					HighScore = inS.read();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (inS != null){
						try {
							inS.close();
						} catch (IOException e) {
						
							e.printStackTrace();
						}
					}
				}
			}
		}	
		return HighScore;
	}
	public void UpdateHighScore(int NewHighScore) {
		String filename = "HighScore.csv";
		FileHandle file = Gdx.files.local(Gdx.files.getLocalStoragePath()+filename);
		file.writeString(Integer.toString(NewHighScore), false);
	}
	
}
