package ca.ajweeks.igmc2014.entity;

import java.awt.Color;
import java.awt.Graphics;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.state.GameState;

public class Player extends Mob {
	
	public static int speed = 5;
	
	public boolean hasDoubleJumped = false;
	public boolean onGround;
	
	private GameState gs;
	
	public Player(GameState gs) {
		super(110, 200);
		x = Game.SIZE.width / 2 - width / 2;
		y = Game.SIZE.height / 2 - height / 2;
		this.gs = gs;
	}
	
	@Override
	public void update() {
		if (Game.input.right.down && xa == 0) xa = 1;
		if (Game.input.left.down && xa == 0) xa = -1;
		
		if (xa == 1 && !Game.input.right.down) xa = 0;
		if (xa == -1 && !Game.input.left.down) xa = 0;
		
		if (!Game.input.right.down && !Game.input.left.down) xa = 0;
		
		x += xa * speed;
		if (x + width > Game.SIZE.width) x = Game.SIZE.width - width;
		if (x < 0) x = 0;
		if (y > Game.SIZE.height - height) {
			onGround = true;
			ya = 0;
			y = Game.SIZE.height - height + 1;
		}else if(gs.mobIntersects(this)) {
				onGround = true;
				ya = 0;
				y = 300;
		} else onGround = false;
		
		if (Game.input.space.clicked && !onGround) {
			if (!hasDoubleJumped) {
				hasDoubleJumped = true;
				onGround = false;
				ya = -20;
			}
		} else if (Game.input.space.clicked && onGround) {
			hasDoubleJumped = false;
			onGround = false;
			ya = -20;
		}
		
		//TODO decrease gravity
		ya++;
		y += ya;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, width, height);
		
		g.setColor(Color.WHITE);
		g.fillRect(x + 5, y + 5, width - 10, height - 10);
	}
}
