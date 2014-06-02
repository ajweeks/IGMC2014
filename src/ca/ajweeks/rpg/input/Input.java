package ca.ajweeks.rpg.input;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import ca.ajweeks.rpg.entity.Player;

public class Input implements MouseMotionListener, MouseListener, KeyListener {
	
	public class Key {
		public int presses, absorbs;
		public boolean clicked, down;
		
		public Key() {
			keys.add(this);
		}
		
		public void toggle(boolean pressed) {
			if (pressed != down) down = pressed;
			if (pressed) presses++;
		}
		
		public void tick() {
			if (absorbs < presses) {
				absorbs++;
				clicked = true;
			} else {
				clicked = false;
			}
		}
	}
	
	public ArrayList<Key> keys = new ArrayList<>();
	
	public Key lM = new Key();
	public Key rM = new Key();
	//TODO make up synonymous with jump ?
	public Key up = new Key();
	//TODO delete down?
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key space = new Key();
	public Key tab = new Key();
	public Key enter = new Key();
	public Key esc = new Key();
	public Key F3 = new Key();
	
	public void releaseAll() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).down = false;
		}
	}
	
	public int x, y;
	public static boolean mouseIsStill = false;
	
	public void update() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).tick();
		}
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
		if (e.getButton() == MouseEvent.BUTTON1) lM.toggle(true);
		if (e.getButton() == MouseEvent.BUTTON3) rM.toggle(true);
	}
	
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) lM.toggle(false);
		if (e.getButton() == MouseEvent.BUTTON3) rM.toggle(false);
	}
	
	public void toggle(KeyEvent e, boolean pressed) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) esc.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_F3) F3.toggle(pressed);
		
		if (e.getKeyCode() == KeyEvent.VK_UP) up.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_W) up.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) right.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_D) right.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_DOWN) down.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_S) down.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_LEFT) left.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_A) left.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_SPACE) space.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_TAB) tab.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_ENTER) enter.toggle(pressed);
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
