package ca.ajweeks.igmc2014.input;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Input implements MouseListener, MouseMotionListener, KeyListener {
	
	public class Key {
		public boolean down; //key is down
		public boolean clicked; //key is down, and wasn't down last frame
		
		public Key() {
			keys.add(this);
		}
		
		public void toggle(boolean pressed) {
			if (!down && pressed) clicked = true;
			else clicked = false;
			
			down = pressed;
		}
		
		public void tick() {
			clicked = false;
		}
	}
	
	private int mouseX, mouseY;
	private boolean leftMouseDown, leftMouseClicked, rightMouseDown, rightMouseClicked;
	
	public ArrayList<Key> keys = new ArrayList<>();
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key r = new Key();
	public Key shift = new Key();
	public Key space = new Key();
	public Key enter = new Key();
	public Key esc = new Key();
	public Key F3 = new Key();
	
	public Input(Canvas canvas) {
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		canvas.addKeyListener(this);
	}
	
	public void tick() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).tick();
		}
		leftMouseClicked = false;
		rightMouseClicked = false;
	}
	
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (!leftMouseDown) leftMouseClicked = true;
			else leftMouseClicked = false;
			
			leftMouseDown = true;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			if (!rightMouseDown) rightMouseClicked = true;
			else rightMouseClicked = false;
			
			rightMouseDown = true;
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftMouseClicked = false;
			leftMouseDown = false;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			rightMouseClicked = false;
			rightMouseDown = false;
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (!leftMouseDown) leftMouseClicked = true;
			else leftMouseClicked = false;
			
			leftMouseDown = true;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			if (!rightMouseDown) rightMouseClicked = true;
			else rightMouseClicked = false;
			
			rightMouseDown = true;
		}
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	public void keyChanged(KeyEvent e, boolean pressed) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			up.toggle(pressed);
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			down.toggle(pressed);
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			left.toggle(pressed);
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			right.toggle(pressed);
			break;
		case KeyEvent.VK_SPACE:
			space.toggle(pressed);
			break;
		case KeyEvent.VK_ENTER:
			enter.toggle(pressed);
			break;
		case KeyEvent.VK_ESCAPE:
			esc.toggle(pressed);
			break;
		case KeyEvent.VK_F3:
			F3.toggle(pressed);
			break;
		case KeyEvent.VK_R:
			r.toggle(pressed);
			break;
		case KeyEvent.VK_SHIFT:
			shift.toggle(pressed);
			break;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		keyChanged(e, true);
	}
	
	public void keyReleased(KeyEvent e) {
		keyChanged(e, false);
	}
	
	public int getMouseX() {
		return mouseX;
	}
	
	public int getMouseY() {
		return mouseY;
	}
	
	public boolean leftMouseDown() {
		return leftMouseDown;
	}
	
	public boolean rightMouseDown() {
		return rightMouseDown;
	}
	
	public boolean leftMouseClicked() {
		return leftMouseClicked;
	}
	
	public boolean rightMouseClicked() {
		return rightMouseClicked;
	}
	
	public void mouseClicked(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
	
	public void keyTyped(KeyEvent e) {}
	
}
