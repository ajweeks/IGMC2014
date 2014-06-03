package ca.ajweeks.igmc2014;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public enum Sound {
	SELECT("res/select2.wav");
	
	public static float volume = -9.0f;
	public final static float MAX_VOLUME = 6.0f;
	public final static float MIN_VOLUME = -24.0f;
	
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
		return Math.min(n + 3, MAX_VOLUME);
	}
	
	public static float quieter(float n) {
		return Math.max(n - 3, MIN_VOLUME);
	}
	
	public void play() {
		if (volume <= MIN_VOLUME) return; //No sound
		control.setValue((float) volume);
		
		if (clip.isRunning()) clip.stop();
		clip.setFramePosition(0);
		clip.start();
	}
	
}
