package ca.ajweeks.rpg;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public enum Sound {
	SELECT("res/select.wav");
	
	public static float volume = -8.0f;
	public final static float MAX_VOLUME = 4.0f;
	public final static float MIN_VOLUME = -22.0f;
	
	private Clip clip;
	private FloatControl control;
	
	Sound(String filename) {
		try {
			AudioInputStream inStream = AudioSystem.getAudioInputStream(new File(filename));
			clip = AudioSystem.getClip();
			clip.open(inStream);
			control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
	}
	
	public static float louder(float n) {
		return Math.min(n + 2, MAX_VOLUME);
	}
	
	public static float quieter(float n) {
		return Math.max(n - 2, MIN_VOLUME);
	}
	
	public void play() {
		if (volume <= MIN_VOLUME) return; //No sound
		control.setValue((float) volume);
		
		if (clip.isRunning()) clip.stop();
		clip.setFramePosition(0);
		clip.start();
	}
	
}
