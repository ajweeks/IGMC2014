package ca.ajweeks.rpg.input;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Input implements MouseMotionListener, MouseListener, KeyListener {
	
	public int x, y;
	public boolean leftMouse, rightMouse;
	public ArrayList<Key> keys;
	
	public class Key {
		public boolean down = false;
		
		public void toggle(boolean down) {
			this.down = down;
		}
		
		public void update() {
			
		}
	}
	
	public Key up = new Key();
	public Key down = new Key();
	public Key right = new Key();
	public Key left = new Key();
	public Key esc = new Key();
	public Key space = new Key();
	
	public void releaseAll() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).down = false;
		}
		leftMouse = false;
		rightMouse = false;
		
	}
	
	public void update() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).update();
		}
	}
	
	public Input(Canvas canvas) {
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		canvas.addKeyListener(this);
	}
	
	public void mouseMoved(MouseEvent e) {
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
	
	public void keyPressed(KeyEvent e) {
		toggle(e, true);
	}
	
	public void keyReleased(KeyEvent e) {
		toggle(e, false);
	}
	
	public void toggle(KeyEvent e, boolean pressed) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_NUMPAD8:
		case KeyEvent.VK_UP:
			up.toggle(pressed);
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_NUMPAD6:
		case KeyEvent.VK_RIGHT:
			right.toggle(pressed);
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_NUMPAD2:
		case KeyEvent.VK_DOWN:
			down.toggle(pressed);
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_NUMPAD4:
		case KeyEvent.VK_LEFT:
			left.toggle(pressed);
			break;
		case KeyEvent.VK_ESCAPE:
			esc.toggle(pressed);
			break;
		}
	}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
	
	public void mouseClicked(MouseEvent e) {}
	
	public void keyTyped(KeyEvent e) {}
}
