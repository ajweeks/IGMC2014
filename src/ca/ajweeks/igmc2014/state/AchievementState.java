package ca.ajweeks.igmc2014.state;

import java.awt.Graphics;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.achievements.AchievementManager;
import ca.ajweeks.igmc2014.gfx.Button;
import ca.ajweeks.igmc2014.gfx.Colour;
import ca.ajweeks.igmc2014.sound.Sound;

public class AchievementState extends BasicState {
	
	public AchievementManager am;
	
	private Button back;
	private BasicState parent;
	
	public AchievementState(BasicState parent) {
		this.parent = parent;
		am = new AchievementManager();
		back = new Button(Game.SIZE.width / 2 - 100 / 2, Game.SIZE.height - 120, 110, 75, "Back", Colour.button, Colour.hButton,
				Colour.offWhite);
	}
	
	@Override
	public void update(double delta) {
		if (back.isDown() || Game.input.esc.clicked || Game.input.space.clicked || Game.input.enter.clicked) { //Change if we ever put add more buttons!
			Sound.SELECT.play();
			Game.sm.enterState(parent);
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Colour.offBlack);
		g.fillRect(0, 0, Game.SIZE.width, Game.SIZE.height);
		
		back.render(g);
	}
	
}
