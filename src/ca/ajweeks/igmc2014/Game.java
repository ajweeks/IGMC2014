package ca.ajweeks.igmc2014;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import ca.ajweeks.igmc2014.graphics.Colour;
import ca.ajweeks.igmc2014.input.Input;
import ca.ajweeks.igmc2014.state.StateManager;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final String GAME_TITLE = "IGMC 2014";
	public static final Dimension SIZE = new Dimension(1140, 640);
	
	public static Font font24;
	public static Font font34;
	public static Font fontDebug;
	public static StateManager sm;
	
	public static boolean renderDebug = true; //TODO MAKE FALSE FOR RELEASES
	
	private Input input;
	private JFrame frame;
	private boolean running = false;
	private int fps = 0;
	int frames = 0;
	
	public Game() {
		super();
		setSize(SIZE);
		
		sm = new StateManager(this);
		input = new Input(this);
		
		frame = new JFrame(GAME_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Thread(new Game()).start();
	}
	
	@Override
	public void run() {
		running = true;
		
		long before = System.nanoTime();
		long elapsed = 0;
		while (running) {
			long now = System.nanoTime();
			long delta = now - before;
			before = now;
			elapsed += delta;
			
			if (elapsed > 1_000_000) {
				fps = frames;
				frames = 0;
				elapsed = 0;
			}
			
			sm.update(delta);
			input.tick();
			
			BufferStrategy buffer = getBufferStrategy();
			if (buffer == null) {
				createBufferStrategy(2);
				continue;
			}
			Graphics g = buffer.getDrawGraphics();
			
			g.setColor(Colour.offBlack);
			g.fillRect(0, 0, SIZE.width, SIZE.height);
			
			sm.render(g);
			frames++;
			
			g.dispose();
			buffer.show();
		}
		frame.dispose();
		System.exit(0);
	}
	
	public void stop() {
		running = false;
	}
	
	public void enterState(int ID) {
		sm.enterState(ID, this);
	}
	
	public Input getInput() {
		return input;
	}
	
	public int getFps() {
		return fps;
	}
}
