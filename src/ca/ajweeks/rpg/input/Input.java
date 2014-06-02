package ca.ajweeks.rpg.input;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import ca.ajweeks.rpg.entity.Player;

public class Input implements MouseMotionListener, MouseListener, KeyListener {
	
	public int x, y;
	//FIXME Implement a better key listener system already..
	public boolean leftMouse, rightMouse, esc, debug = true;
	public boolean up, down, right, left, space, tab, enter;
	public static boolean mouseIsStill = false;
	
	public void releaseAll() {
		leftMouse = false;
		rightMouse = false;
	}
	
	public void update() {
		leftMouse = false;
		rightMouse = false;
		esc = false;
	}
	
	public Input(Canvas canvas) {
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		canvas.addKeyListener(this);
		canvas.setFocusTraversalKeysEnabled(false);
	}
	
	public void mouseMoved(MouseEvent e) {
		mouseIsStill = false;
		x = e.getX();
		y = e.getY();
	}
	
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) leftMouse = true;
		if (e.getButton() == MouseEvent.BUTTON3) rightMouse = true;
	}
	
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) leftMouse = false;
		if (e.getButton() == MouseEvent.BUTTON3) rightMouse = false;
	}
	
	public void toggle(KeyEvent e, boolean down) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) esc = down;
		if (e.getKeyCode() == KeyEvent.VK_F3) debug = !debug;
		
		if (e.getKeyCode() == KeyEvent.VK_UP) this.up = down;
		if (e.getKeyCode() == KeyEvent.VK_W) this.up = down;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) this.right = down;
		if (e.getKeyCode() == KeyEvent.VK_D) this.right = down;
		if (e.getKeyCode() == KeyEvent.VK_DOWN) this.down = down;
		if (e.getKeyCode() == KeyEvent.VK_S) this.down = down;
		if (e.getKeyCode() == KeyEvent.VK_LEFT) this.left = down;
		if (e.getKeyCode() == KeyEvent.VK_A) this.left = down;
		if (e.getKeyCode() == KeyEvent.VK_SPACE) this.space = down;
		if (e.getKeyCode() == KeyEvent.VK_TAB) this.tab = down;
		if (e.getKeyCode() == KeyEvent.VK_ENTER) this.enter = down;
	}
	
	public void keyPressed(KeyEvent e) {
		mouseIsStill = true;
		if (e.isShiftDown()) Player.speed = 10;
		toggle(e, true);
	}
	
	public void keyReleased(KeyEvent e) {
		if (!e.isShiftDown()) Player.speed = 5;
		toggle(e, false);
	}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
	
	public void mouseClicked(MouseEvent e) {}
	
	public void keyTyped(KeyEvent e) {}
}
