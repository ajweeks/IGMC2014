package ca.ajweeks.igmc2014;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import ca.ajweeks.igmc2014.gfx.RenderDebugOverlay;
import ca.ajweeks.igmc2014.input.Input;
import ca.ajweeks.igmc2014.state.StateManager;

public class Game extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final String GAME_TITLE = "IGMC 2014";
	public static final Dimension SIZE = new Dimension(1200, 675);
	public static volatile boolean running = false;
	
	public static StateManager sm;
	public static Input input;
	public static boolean leftWasDown;
	
	public static Font font34;
	public static Font font24;
	public static Font fontDebug;
	
	public static int ups = 0;
	public static int fps = 0;
	public int updates = 0;
	public int frames = 0;
	
	private Canvas canvas;
	private boolean renderDebug = true;
	private List<Image> icon;
	
	public Game() {
		super(GAME_TITLE);
		
		canvas = new Canvas();
		canvas.setSize(SIZE);
		font34 = getFonts();
		font24 = font34.deriveFont(24f);
		fontDebug = font34.deriveFont(12f);
		canvas.setFont(font34);
		
		icon = new ArrayList<Image>();
		icon.add(new ImageIcon("res/icon512.png").getImage());
		icon.add(new ImageIcon("res/icon256.png").getImage());
		icon.add(new ImageIcon("res/icon128.png").getImage());
		icon.add(new ImageIcon("res/icon32.png").getImage());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(canvas);
		setResizable(false);
		pack();
		setIconImages(icon);
		setLocationRelativeTo(null);
		setVisible(true);
		
		canvas.setFocusable(true);
		canvas.requestFocus();
		
		sm = new StateManager();
		input = new Input(canvas);
	}
	
	private Font getFonts() {
		Font result = new Font("Consolas", Font.BOLD, 34);
		Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		for (Font f : fonts) {
			if (f.getName().equals("Arial")) return new Font("Arial", Font.BOLD, 34);
			if (f.getName().equals("Microsoft Sans Serif")) return new Font("Microsoft Sans Serif", Font.BOLD, 34);
		}
		return result;
	}
	
	public void update() {
		sm.update();
		input.update();
		leftWasDown = input.lM.clicked;
		updates++;
	}
	
	public void render() {
		BufferStrategy buffer = canvas.getBufferStrategy();
		if (buffer == null) {
			canvas.createBufferStrategy(2);
			return;
		}
		Graphics g = buffer.getDrawGraphics();
		g.setFont(font34);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, SIZE.width, SIZE.height);
		
		if (input.F8.down) {
			input.F8.down = false;
			BufferedImage image = null;
			try {
				image = new Robot()
						.createScreenCapture(new Rectangle(this.getX() + 3, this.getY() + 25, SIZE.width, SIZE.height)); //+25 to account for windows border
			} catch (HeadlessException | AWTException e) {
				e.printStackTrace();
			}
			
			try {
				ImageIO.write(image, "png", new File("screenshot.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		sm.render(g);
		
		if (input.F3.clicked) renderDebug = !renderDebug;
		if (renderDebug) RenderDebugOverlay.render(g);
		
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
				//TODO Add better game loop..
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long now = System.currentTimeMillis();
			long elapsed = now - before;
			
			seconds += elapsed;
			if (seconds >= 1000) {
				fps = frames;
				ups = updates;
				seconds = 0;
				updates = 0;
				frames = 0;
			}
			
			before = System.currentTimeMillis();
		}
		dispose();
		System.exit(0);
	}
	
	public static void stop() {
		running = false;
	}
	
	public static void main(String[] args) {
		new Thread(new Game()).start();
	}
	
}
