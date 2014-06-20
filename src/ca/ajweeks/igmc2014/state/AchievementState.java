package ca.ajweeks.igmc2014.state;

import java.awt.Graphics;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.achievements.AchievementManager;
import ca.ajweeks.igmc2014.button.Button;
import ca.ajweeks.igmc2014.button.ButtonManager;
import ca.ajweeks.igmc2014.gfx.Colour;
import ca.ajweeks.igmc2014.sound.Sound;

public class AchievementState extends BasicState {
	
	public static int BACK = 0;
	
	public AchievementManager am;
	
	private ButtonManager buttons;
	private BasicState parent;
	
	public AchievementState(BasicState parent) {
		this.parent = parent;
		am = new AchievementManager();
		
		buttons = new ButtonManager();
		buttons.addButton(new Button(Game.SIZE.width / 2 - 100 / 2, Game.SIZE.height - 120, 110, 75, "Back"));
		buttons.setSelectedButton(BACK);
	}
	
	@Override
	public void update(double delta) {
		buttons.update();
		
		if (buttons.getButton(BACK).isDown()
				|| (buttons.getSelectedButton() == BACK && (Game.input.esc.clicked || Game.input.space.clicked || Game.input.enter.clicked))) {
			Sound.SELECT.play();
			Game.sm.enterState(parent);
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Colour.offBlack);
		g.fillRect(0, 0, Game.SIZE.width, Game.SIZE.height);
		
		buttons.render(g);
	}
	
}
