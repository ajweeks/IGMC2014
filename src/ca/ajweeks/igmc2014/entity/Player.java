package ca.ajweeks.igmc2014.entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.state.GameState;

public class Player extends Mob {
	
	public static int speed = 5;
	
	private Image image = new ImageIcon("res/player.png").getImage();
	
	public boolean hasDoubleJumped = false;
	public boolean onGround = false;
	
	public int jumps = 0; // can't remember why this is here......
	
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
		} else if (gs.mobIntersects(this)) {
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
		
		if(onGround) hasDoubleJumped = false;
		
		//TODO decrease gravity
		ya++;
		y += ya;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(image, x, y, null);
	}
}
