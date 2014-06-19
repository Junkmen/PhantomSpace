package com.xtrordinary.phantomspace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;

public class MusicStreamer {
	Music soundtrackMp3; 
	public void StreamMusic(FileHandle file,boolean isLooping,float volume) {
		soundtrackMp3 = Gdx.audio.newMusic(file);
		soundtrackMp3.play();
		soundtrackMp3.setLooping(isLooping);
		soundtrackMp3.setVolume(volume);
	}
	
}
