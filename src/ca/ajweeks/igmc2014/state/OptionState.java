package ca.ajweeks.igmc2014.state;

import java.awt.Graphics;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.button.Button;
import ca.ajweeks.igmc2014.button.ButtonManager;
import ca.ajweeks.igmc2014.gfx.Colour;
import ca.ajweeks.igmc2014.sound.Sound;

public class OptionState extends BasicState {
	
	public static final int BACK = 0;
	public static final int RESET_ACHIEVEMENTS = 1;
	
	private ButtonManager buttons;
	private File optionsFile;
	private String[] options;
	
	private boolean changed = false;
	
	public OptionState() {
		buttons = new ButtonManager();
		buttons.addButton(new Button(Game.SIZE.width / 2 - 100 / 2, Game.SIZE.height - 120, 110, 75, "Back"));
		buttons.addButton(new Button(150, 30, 355, 75, "Reset Achivements"));
		
		buttons.setSelectedButton(BACK);
		
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
		buttons.update();
		
		if( Game.input.space.clicked || Game.input.enter.clicked) {
			switch(buttons.getSelectedButton()) {
			case BACK:
				enterState(StateManager.MAIN_MENU_STATE);
				break;
			case RESET_ACHIEVEMENTS:
				resetAchievements();
				break;
			}
		}
		
		if (Game.input.up.clicked || Game.input.left.clicked) {
			do {
				buttons.previousButton(); //set selected buttons to previous enabled button
			} while (!buttons.getButton(buttons.getSelectedButton()).enabled);
		}
		
		if (Game.input.tab.clicked || Game.input.down.clicked || Game.input.right.clicked) {
			do {
				buttons.nextButton(); //previous enabled button
			} while (!buttons.getButton(buttons.getSelectedButton()).enabled);
		}
		
		if (buttons.getButton(BACK).isDown() || Game.input.esc.clicked) {
			enterState(StateManager.MAIN_MENU_STATE);
		}
		
		if (buttons.getButton(RESET_ACHIEVEMENTS).isDown()) {
			resetAchievements();
		}
		
		if (changed) save();
		buttons.updateSelectedButton();
	}
	
	private void resetAchievements() {
		Sound.WIZZLE.play();
		Game.am.resetAchievements();		
	}

	private void enterState(int id) {
		Sound.SELECT.play();
		Game.sm.enterState(id);
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Colour.offBlack);
		g.fillRect(0, 0, Game.SIZE.width, Game.SIZE.height);
		buttons.render(g);
	}
	
}
