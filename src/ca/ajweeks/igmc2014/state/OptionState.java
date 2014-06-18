package ca.ajweeks.igmc2014.state;

import java.awt.Graphics;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.button.Button;
import ca.ajweeks.igmc2014.gfx.Colour;
import ca.ajweeks.igmc2014.sound.Sound;

public class OptionState extends BasicState {
	
	private Button back;
	private Button resetAchievements;
	private File optionsFile;
	private String[] options;
	
	private boolean changed = false;
	
	public OptionState() {
		back = new Button(Game.SIZE.width / 2 - 100 / 2, Game.SIZE.height - 120, 110, 75, "Back", Colour.button, Colour.hButton,
				Colour.offWhite);
		back.setSelected();
		resetAchievements = new Button(150, 150, 280, 75, "Reset Achivements", Colour.button, Colour.hButton, Colour.offWhite);
		
		options = new String[] {};
		optionsFile = new File("options.txt");
		
		if (!optionsFile.exists()) try {
			optionsFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void save() {
		try (FileWriter writer = new FileWriter(optionsFile)) {
			for (int i = 0; i < options.length; i++) {
				writer.write(options[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		changed = false;
	}
	
	@Override
	public void update(double delta) {
		if (back.isDown() || Game.input.space.clicked || Game.input.esc.clicked || Game.input.enter.clicked) {
			Sound.SELECT.play();
			Game.sm.enterState(StateManager.MAIN_MENU_STATE);
		}
		
		if (resetAchievements.isDown()) {
			Sound.SELECT.play();
			Game.am.resetAchievements();
		}
		
		//If anything has been changed, set changed = true;
		
		if (changed) save();
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Colour.offBlack);
		g.fillRect(0, 0, Game.SIZE.width, Game.SIZE.height);
		back.render(g);
		resetAchievements.render(g);
	}
	
}
