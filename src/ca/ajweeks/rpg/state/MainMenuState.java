package ca.ajweeks.rpg.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.rpg.Button;
import ca.ajweeks.rpg.Colour;
import ca.ajweeks.rpg.RPG;
import ca.ajweeks.rpg.Sound;

public class MainMenuState extends BasicState {
	
	private Colour colour;
	
	private Image up;
	private Image down;
	
	private Button startGame;
	private Button louder;
	private Button quieter;
	
	public MainMenuState() {
		up = new ImageIcon("res/up.png").getImage();
		down = new ImageIcon("res/down.png").getImage();
		
		colour = new Colour();
		
		startGame = new Button(RPG.SIZE.width / 2 - 160 / 2, 225, 160, 75, "Play!", colour.button, colour.hButton, Color.WHITE);
		louder = new Button(RPG.SIZE.width - 70, 30, 50, 50, "", colour.button, colour.hButton, Color.WHITE, up);
		quieter = new Button(RPG.SIZE.width - 130, 30, 50, 50, "", colour.button, colour.hButton, Color.WHITE, down);
	}
	
	public void update() {
		if (startGame.isDown(RPG.input)) {
			Sound.SELECT.play();
			RPG.sm.enterState(StateManager.GAME_STATE);
		}
		
		if (louder.isDown(RPG.input)) {
			RPG.input.leftMouse = false;
			Sound.volume = Sound.louder(Sound.volume);
			if (Sound.volume == Sound.MAX_VOLUME) louder.disable();
			if (Sound.volume > Sound.MIN_VOLUME) quieter.enable();
			Sound.SELECT.play();
		}
		
		if (quieter.isDown(RPG.input)) {
			RPG.input.leftMouse = false;
			Sound.volume = Sound.quieter(Sound.volume);
			if (Sound.volume == Sound.MIN_VOLUME) quieter.disable();
			if (Sound.volume < Sound.MAX_VOLUME) louder.enable();
			Sound.SELECT.play();
		}
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(15, 15, 15));
		g.fillRect(0, 0, RPG.SIZE.width, RPG.SIZE.height);
		
		startGame.render(g);
		louder.render(g);
		quieter.render(g);
	}
	
}
