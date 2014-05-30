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
	
	public static final String TITLE = "TITLE";
	public static final Dimension SIZE = new Dimension(1200, 675);
	
	public StateManager sm;
	public Canvas canvas;
	public Input input;
	public Font font;
	
	public int frames = 0;
	
	private volatile boolean running = false;
	
	public RPG() {
		super(TITLE);
		
		font = new Font("Consolas", Font.BOLD, 32);
		sm = new StateManager();
		
		canvas = new Canvas();
		canvas.setSize(SIZE);
		canvas.setFont(font);
		
		input = new Input(canvas);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(canvas);
		setResizable(false);
		pack();
		setFocusable(true);
		setLocationRelativeTo(null);
		setVisible(true);
		requestFocus();
	}
	
	public void update() {
		sm.update(input, sm);
	}
	
	public void render() {
		BufferStrategy buffer = canvas.getBufferStrategy();
		if (buffer == null) {
			canvas.createBufferStrategy(2);
			return;
		}
		Graphics g = buffer.getDrawGraphics();
		g.setFont(canvas.getFont());
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, SIZE.width, SIZE.height);
		
		sm.render(g);
		
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
				Thread.sleep(1000 / 70);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long now = System.currentTimeMillis();
			long elapsed = now - before;
			
			seconds += elapsed;
			if (seconds >= 1000) {
				seconds = 0;
				System.out.println(frames + " FPS");
				frames = 0;
			}
			
			before = System.currentTimeMillis();
		}
	}
	
	public void stop() {
		running = false;
	}
	
	public static void main(String[] args) {
		new Thread(new RPG()).start();
	}
	
}
