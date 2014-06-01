package ca.ajweeks.rpg.state;

import java.awt.Graphics;

import ca.ajweeks.rpg.Button;
import ca.ajweeks.rpg.Colour;
import ca.ajweeks.rpg.RPG;
import ca.ajweeks.rpg.Sound;

public class CreditState extends BasicState {
	
	Button back;
	
	public CreditState() {
		back = new Button(RPG.SIZE.width / 2 - 100 / 2, RPG.SIZE.height - 120, 100, 75, "Back", Colour.button, Colour.hButton, Colour.offWhite);
		back.setSelected();
	}
	
	@Override
	public void update() {
		if (back.isDown() || RPG.input.esc || RPG.input.enter) {
			RPG.input.esc = false;
			RPG.input.enter = false;
			Sound.SELECT.play();
			RPG.sm.enterState(StateManager.MAIN_MENU_STATE);
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Colour.offBlack);
		g.fillRect(0, 0, RPG.SIZE.width, RPG.SIZE.height);
		
		back.render(g);
	}
	
}
