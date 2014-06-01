package ca.ajweeks.rpg.state;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.rpg.RPG;
import ca.ajweeks.rpg.Sound;
import ca.ajweeks.rpg.entity.Player;
import ca.ajweeks.rpg.level.Level;

public class GameState extends BasicState {
	
	Level level;
	Player player;
	
	public GameState() {
		level = new Level();
		player = new Player(level);
		level.addEntity(player);
	}
	
	public void update() {
		if (RPG.input.esc) {
			Sound.SELECT.play();
			RPG.sm.enterState(StateManager.MAIN_MENU_STATE);
		}
		
		if (RPG.input.up && player.ya == 0) player.ya = -1;
		if (RPG.input.down && player.ya == 0) player.ya = 1;
		if (RPG.input.right && player.xa == 0) player.xa = 1;
		if (RPG.input.left && player.xa == 0) player.xa = -1;
		
		if (player.ya == -1 && !RPG.input.up) player.ya = 0;
		if (player.ya == 1 && !RPG.input.down) player.ya = 0;
		if (player.xa == 1 && !RPG.input.right) player.xa = 0;
		if (player.xa == -1 && !RPG.input.left) player.xa = 0;
		
		if (!RPG.input.up && !RPG.input.down) player.ya = 0;
		if (!RPG.input.right && !RPG.input.left) player.xa = 0;
		
		level.update();
	}
	
	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, RPG.SIZE.width, RPG.SIZE.height);
		level.render(g);
	}
	
}
