package ca.ajweeks.igmc2014;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import ca.ajweeks.igmc2014.graphics.Colour;
import ca.ajweeks.igmc2014.input.Keyboard;
import ca.ajweeks.igmc2014.input.Mouse;
import ca.ajweeks.igmc2014.state.StateManager;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final String GAME_TITLE = "IGMC 2014";
	public static final Dimension SIZE = new Dimension(1140, 640);
	
	public static Font font24;
	public static Font font34;
	public static Font fontDebug;
	public static StateManager sm;
	public static Keyboard keyboard;
	public static Mouse mouse;
	
	public static boolean renderDebug = true; //TODO MAKE FALSE FOR RELEASES
	
	private JFrame frame;
	
	private boolean running = false;
	//	private boolean hasFocus = false;
	private int fps = 0;
	private int frames = 0;
	
	static {
		font24 = new Font("Consolas", Font.PLAIN, 24);
		font34 = new Font("Consolas", Font.PLAIN, 34);
		fontDebug = new Font("Consolas", Font.BOLD, 16);
	}
	
	public Game() {
		super();
		setSize(SIZE);
		
		sm = new StateManager(this);
		keyboard = new Keyboard(this);
		mouse = new Mouse(this);
		
		frame = new JFrame(GAME_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setFocusable(true);
	}
	
	public static void main(String[] args) {
		new Thread(new Game()).start();
	}
	
	@Override
	public void run() {
		requestFocus();
		running = true;
		
		double NS_PER_TICK = 1_000_000_000 / 64;
		long before = System.nanoTime();
		long elapsed = 0;
		while (running) {
			long now = System.nanoTime();
			double delta = now - before;
			before = now;
			elapsed += delta;
			double extra = NS_PER_TICK - delta;
			
			if (elapsed > 1_000_000_000) { //one second
				fps = frames;
				frames = 0;
				elapsed -= 1_000_000_000;
				frame.setTitle(GAME_TITLE + " | " + fps + " fps");
			}
			
			update(delta / 10_000_000); // around 1.0
			
			render();
			
			if (extra > 0) {
				try {
					Thread.sleep((long) (extra / 1_000_000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		frame.dispose();
		System.exit(0);
	}
	
	public void update(double delta) {
		sm.update(delta);
		keyboard.update();
		mouse.update();
	}
	
	public void render() {
		BufferStrategy buffer = getBufferStrategy();
		if (buffer == null) {
			createBufferStrategy(2);
			return;
		}
		Graphics g = buffer.getDrawGraphics();
		
		//clear screen
		g.setColor(Colour.offBlack);
		g.fillRect(0, 0, SIZE.width, SIZE.height);
		
		sm.render(g);
		frames++;
		
		g.dispose();
		buffer.show();
	}
	
	public void stop() {
		running = false;
	}
	
	public void enterState(int ID) {
		sm.enterState(ID, this);
	}
	
	public int getFps() {
		return fps;
	}
}
