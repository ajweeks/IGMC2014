package ca.ajweeks.rpg;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public enum Sound {
	SELECT("res/select2.wav");
	
	public static enum Volume {
		MUTE, LOW, MEDIUM, HIGH;
		
		public Volume quieter() {
			switch (this) {
			case HIGH:
				return MEDIUM;
			case MEDIUM:
				return LOW;
			case LOW:
				return MUTE;
			case MUTE:
				return MUTE;
			}
			return MUTE;
		}
		
		public Volume louder() {
			switch (this) {
			case HIGH:
				return HIGH;
			case MEDIUM:
				return HIGH;
			case LOW:
				return MEDIUM;
			case MUTE:
				return LOW;
			}
			return LOW;
		}
	}
	
	public static Volume volume = Volume.LOW;
	private Clip clip;
	
	Sound(String filename) {
		try {
			AudioInputStream inStream = AudioSystem.getAudioInputStream(new File(filename));
			clip = AudioSystem.getClip();
			clip.open(inStream);
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
	}
	
	public void play() {
		if (volume != Volume.MUTE) {
			if (clip.isRunning()) clip.stop();
			clip.setFramePosition(0);
			clip.start();
		}
	}
	
}
