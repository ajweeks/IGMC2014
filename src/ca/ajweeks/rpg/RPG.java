package ca.ajweeks.rpg;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import ca.ajweeks.rpg.input.Input;
import ca.ajweeks.rpg.state.StateManager;

public class RPG extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final String GAME_TITLE = "TITLE";
	public static final Dimension SIZE = new Dimension(1200, 675);
	
	public static StateManager sm;
	public static Input input;
	public static boolean leftWasDown;
	
	private Canvas canvas;
	private Font font;
	
	public static int fps = 0;
	public int frames = 0;
	
	private static volatile boolean running = false;
	
	public RPG() {
		super(GAME_TITLE);
		
		canvas = new Canvas();
		canvas.setSize(SIZE);
		canvas.setFont(font);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(canvas);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		canvas.setFocusable(true);
		canvas.requestFocus();
		
		font = new Font("Consolas", Font.BOLD, 32);
		
		sm = new StateManager();
		input = new Input(canvas);
	}
	
	public void update() {
		sm.update();
		input.update();
		leftWasDown = input.leftMouse;
	}
	
	public void render() {
		BufferStrategy buffer = canvas.getBufferStrategy();
		if (buffer == null) {
			canvas.createBufferStrategy(2);
			return;
		}
		Graphics g = buffer.getDrawGraphics();
		g.setFont(font);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, SIZE.width, SIZE.height);
		
		sm.render(g);
		
		if (input.debug) {
			int xoff = 0;
			int yoff = 0;
			
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(xoff + 2, yoff + 2, 50, 14);
			
			g.setFont(font.deriveFont(12f));
			g.setColor(Color.WHITE);
			g.drawString(fps + " fps", xoff + 4, yoff + 12);
			
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(xoff + 2, yoff + 16, 205, 14);
			
			g.setColor(Color.WHITE);
			g.drawString("Current state: " + sm.getCurrentState(), xoff + 4, yoff + 26);
		}
		
		g.dispose();
		buffer.show();
		frames++;
	}
	
	public void run() {
		running = true;
		long before = System.currentTimeMillis();
		long seconds = 0;
		while (running) {
			update();
			render();
			try {
				//TODO add better game loop..
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long now = System.currentTimeMillis();
			long elapsed = now - before;
			
			seconds += elapsed;
			if (seconds >= 1000) {
				fps = frames;
				seconds = 0;
				frames = 0;
			}
			
			before = System.currentTimeMillis();
		}
	}
	
	public static void stop() {
		running = false;
	}
	
	public static void main(String[] args) {
		new Thread(new RPG()).start();
	}
	
}
