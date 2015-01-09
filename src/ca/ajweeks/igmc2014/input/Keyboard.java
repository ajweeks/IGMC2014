package ca.ajweeks.igmc2014.input;

import java.awt.Canvas;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	public static enum Key {
		//letters
		A("a", false), B("b", false), C("c", false), D("d", false), E("e", false), F("f", false), G("g", false), H("h", false), I(
				"i", false), J("j", false), K("k", false), L("l", false), M("m", false), N("n", false), O("o", false), P("p",
				false), Q("q", false), R("r", false), S("s", false), T("t", false), U("u", false), V("v", false), W("w", false), X(
				"x", false), Y("y", false), Z("z", false),
		
		//numbers
		NUM0("0", ")", false), NUM1("1", "!", false), NUM2("2", "@", false), NUM3("3", "#", false), NUM4("4", "$", false), NUM5(
				"5", "%", false), NUM6("6", "^", false), NUM7("7", "&", false), NUM8("8", "*", false), NUM9("9", "(", false),
		
		//other non-function keys
		SPACE(" ", false), SLASH("/", "?", false), BACKSLASH("\\", "|", false), COMMA(",", "<", false), PERIOD(".", ">", false), GRAVE(
				"`", "~", false), LSQUARE("[", "{", false), RSQUARE("]", "}", false), SEMICOLON(";", ":", false), APOSTROPHE(
				"\'", "\"", false), MINUS("-", "_", false), EQUALS("=", "+", false),
		
		//function keys
		UP_ARROW("up arrow", true), DOWN_ARROW("down arrow", true), LEFT_ARROW("left arrow", true), RIGHT_ARROW("right arrow",
				true), ENTER("enter", true), ESC("esc", true), BACKSPACE("bksp", true), DEL("del", true), SHIFT("shift", true), CONTROL(
				"control", true), HOME("home", true), END("end", true), TAB("tab", true), CAPS_LOCK("caps lock", true), NUM_LOCK(
				"num lock", true), INSERT("insert", true),
		
		//F keys
		F1("F1", true), F2("F2", true), F3("F3", true), F4("F4", true), F5("F5", true), F6("F6", true), F7("F7", true), F8("F8",
				true), F9("F9", true), F10("F10", true), F11("F11", true), F12("F12", true);
		
		public final String text, TEXT;
		public boolean clicked, isFunctionKey;
		public boolean isNumber = false, isLetter = false;
		public int down = -1;
		
		/** @param text - the string to be printed when this key is down
		 * 	@param TEXT - the string to be printed when shift is being held as well as this key  */
		Key(String text, String TEXT, boolean isFunctionKey) {
			this.text = text;
			this.TEXT = TEXT;
			this.isFunctionKey = isFunctionKey;
			
			if (text.matches("^[a-zA-Z_]*$")) {
				isLetter = true;
			} else if (text.matches("^[0-9]*$")) {
				isNumber = true;
			}
		}
		
		Key(String text, boolean isFunctionKey) {
			this(text, text.toUpperCase(), isFunctionKey);
		}
		
		public void update() {
			if (clicked) clicked = false;
			if (down != -1) down++;
		}
		
		public void changed(boolean pressed) {
			if (down == -1 && pressed) clicked = true; //mouse wasn't down last frame but is down this frame
			else clicked = false;
			
			if (pressed) down++;
			else down = -1;
		}
	}
	
	public static boolean capsLock = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
	public static boolean numLock = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_NUM_LOCK);
	public static boolean insert = false;
	
	//LATER figure out what scroll lock is used for, and possibly implement it
	
	private void updateCapsLock(boolean pressed) {
		if (pressed && Key.CAPS_LOCK.clicked) capsLock = !capsLock;
	}
	
	private void updateNumLock(boolean pressed) {
		if (pressed && Key.NUM_LOCK.clicked) numLock = !numLock;
	}
	
	private void updateInsert(boolean pressed) {
		if (pressed && Key.INSERT.clicked) insert = !insert;
	}
	
	public void update() {
		Key[] keys = Key.values();
		for (int i = 0; i < keys.length; i++) {
			keys[i].update();
		}
	}
	
	public Keyboard(Canvas canvas) {
		canvas.addKeyListener(this);
	}
	
	public void releaseAll() {
		Key[] keys = Key.values();
		for (int i = 0; i < keys.length; i++) {
			keys[i].clicked = false;
			keys[i].down = -1;
		}
	}
	
	private void keyUpdated(KeyEvent ke, boolean pressed) {
		Mouse.setStill(true);
		
		//function keys
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_UP:
			Key.UP_ARROW.changed(pressed);
			break;
		case KeyEvent.VK_LEFT:
			Key.LEFT_ARROW.changed(pressed);
			break;
		case KeyEvent.VK_DOWN:
			Key.DOWN_ARROW.changed(pressed);
			break;
		case KeyEvent.VK_RIGHT:
			Key.RIGHT_ARROW.changed(pressed);
			break;
		case KeyEvent.VK_ENTER:
			Key.ENTER.changed(pressed);
			break;
		case KeyEvent.VK_ESCAPE:
			Key.ESC.changed(pressed);
			break;
		case KeyEvent.VK_SHIFT:
			Key.SHIFT.changed(pressed);
			break;
		case KeyEvent.VK_CONTROL:
			Key.CONTROL.changed(pressed);
			break;
		case KeyEvent.VK_BACK_SPACE:
			Key.BACKSPACE.changed(pressed);
			break;
		case KeyEvent.VK_DELETE:
			Key.DEL.changed(pressed);
			break;
		case KeyEvent.VK_HOME:
			Key.HOME.changed(pressed);
			break;
		case KeyEvent.VK_END:
			Key.END.changed(pressed);
			break;
		case KeyEvent.VK_INSERT:
			Key.INSERT.changed(pressed);
			updateInsert(pressed);
			break;
		case KeyEvent.VK_CAPS_LOCK:
			Key.CAPS_LOCK.changed(pressed);
			updateCapsLock(pressed);
			break;
		case KeyEvent.VK_NUM_LOCK:
			Key.NUM_LOCK.changed(pressed);
			updateNumLock(pressed);
			break;
		
		//others
		case KeyEvent.VK_SPACE:
			Key.SPACE.changed(pressed);
			break;
		case KeyEvent.VK_BACK_SLASH:
			Key.BACKSLASH.changed(pressed);
			break;
		case KeyEvent.VK_SLASH:
			Key.SLASH.changed(pressed);
			break;
		case KeyEvent.VK_PERIOD:
		case KeyEvent.VK_DECIMAL:
			Key.PERIOD.changed(pressed);
			break;
		case KeyEvent.VK_COMMA:
			Key.COMMA.changed(pressed);
			break;
		case KeyEvent.VK_QUOTE:
			Key.APOSTROPHE.changed(pressed);
			break;
		case KeyEvent.VK_MINUS:
			Key.MINUS.changed(pressed);
			break;
		case KeyEvent.VK_EQUALS:
			Key.EQUALS.changed(pressed);
			break;
		case KeyEvent.VK_DEAD_TILDE:
			Key.GRAVE.changed(pressed);
			break;
		case KeyEvent.VK_BRACELEFT:
			Key.LSQUARE.changed(pressed);
			break;
		case KeyEvent.VK_BRACERIGHT:
			Key.RSQUARE.changed(pressed);
			break;
		case KeyEvent.VK_TAB:
			Key.TAB.changed(pressed);
			break;
		
		//F keys
		case KeyEvent.VK_F1:
			Key.F1.changed(pressed);
			break;
		case KeyEvent.VK_F2:
			Key.F2.changed(pressed);
			break;
		case KeyEvent.VK_F3:
			Key.F3.changed(pressed);
			break;
		case KeyEvent.VK_F4:
			Key.F4.changed(pressed);
			break;
		case KeyEvent.VK_F5:
			Key.F5.changed(pressed);
			break;
		case KeyEvent.VK_F6:
			Key.F6.changed(pressed);
			break;
		case KeyEvent.VK_F7:
			Key.F7.changed(pressed);
			break;
		case KeyEvent.VK_F8:
			Key.F8.changed(pressed);
			break;
		case KeyEvent.VK_F9:
			Key.F9.changed(pressed);
			break;
		case KeyEvent.VK_F10:
			Key.F10.changed(pressed);
			break;
		case KeyEvent.VK_F11:
			Key.F11.changed(pressed);
			break;
		case KeyEvent.VK_F12:
			Key.F12.changed(pressed);
			break;
		
		//letters
		case KeyEvent.VK_A:
			Key.A.changed(pressed);
			break;
		case KeyEvent.VK_B:
			Key.B.changed(pressed);
			break;
		case KeyEvent.VK_C:
			Key.C.changed(pressed);
			break;
		case KeyEvent.VK_D:
			Key.D.changed(pressed);
			break;
		case KeyEvent.VK_E:
			Key.E.changed(pressed);
			break;
		case KeyEvent.VK_F:
			Key.F.changed(pressed);
			break;
		case KeyEvent.VK_G:
			Key.G.changed(pressed);
			break;
		case KeyEvent.VK_H:
			Key.H.changed(pressed);
			break;
		case KeyEvent.VK_I:
			Key.I.changed(pressed);
			break;
		case KeyEvent.VK_J:
			Key.J.changed(pressed);
			break;
		case KeyEvent.VK_K:
			Key.K.changed(pressed);
			break;
		case KeyEvent.VK_L:
			Key.L.changed(pressed);
			break;
		case KeyEvent.VK_M:
			Key.M.changed(pressed);
			break;
		case KeyEvent.VK_N:
			Key.N.changed(pressed);
			break;
		case KeyEvent.VK_O:
			Key.O.changed(pressed);
			break;
		case KeyEvent.VK_P:
			Key.P.changed(pressed);
			break;
		case KeyEvent.VK_Q:
			Key.Q.changed(pressed);
			break;
		case KeyEvent.VK_R:
			Key.R.changed(pressed);
			break;
		case KeyEvent.VK_S:
			Key.S.changed(pressed);
			break;
		case KeyEvent.VK_T:
			Key.T.changed(pressed);
			break;
		case KeyEvent.VK_U:
			Key.U.changed(pressed);
			break;
		case KeyEvent.VK_V:
			Key.V.changed(pressed);
			break;
		case KeyEvent.VK_W:
			Key.W.changed(pressed);
			break;
		case KeyEvent.VK_X:
			Key.X.changed(pressed);
			break;
		case KeyEvent.VK_Y:
			Key.Y.changed(pressed);
			break;
		case KeyEvent.VK_Z:
			Key.Z.changed(pressed);
			break;
		
		//numbers
		case KeyEvent.VK_0:
		case KeyEvent.VK_NUMPAD0:
			Key.NUM0.changed(pressed);
			break;
		case KeyEvent.VK_1:
		case KeyEvent.VK_NUMPAD1:
			Key.NUM1.changed(pressed);
			break;
		case KeyEvent.VK_2:
		case KeyEvent.VK_NUMPAD2:
			Key.NUM2.changed(pressed);
			break;
		case KeyEvent.VK_3:
		case KeyEvent.VK_NUMPAD3:
			Key.NUM3.changed(pressed);
			break;
		case KeyEvent.VK_4:
		case KeyEvent.VK_NUMPAD4:
			Key.NUM4.changed(pressed);
			break;
		case KeyEvent.VK_5:
		case KeyEvent.VK_NUMPAD5:
			Key.NUM5.changed(pressed);
			break;
		case KeyEvent.VK_6:
		case KeyEvent.VK_NUMPAD6:
			Key.NUM6.changed(pressed);
			break;
		case KeyEvent.VK_7:
		case KeyEvent.VK_NUMPAD7:
			Key.NUM7.changed(pressed);
			break;
		case KeyEvent.VK_8:
		case KeyEvent.VK_NUMPAD8:
			Key.NUM8.changed(pressed);
			break;
		case KeyEvent.VK_9:
		case KeyEvent.VK_NUMPAD9:
			Key.NUM9.changed(pressed);
			break;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		keyUpdated(e, true);
	}
	
	public void keyReleased(KeyEvent e) {
		keyUpdated(e, false);
	}
	
	public void keyTyped(KeyEvent e) {}
	
}
