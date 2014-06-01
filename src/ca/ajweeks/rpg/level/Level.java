package ca.ajweeks.rpg.level;

import java.awt.Graphics;
import java.util.ArrayList;

import ca.ajweeks.rpg.entity.Entity;

public class Level {
	
	private ArrayList<Entity> entites;
	
	public Level() {
		entites = new ArrayList<>();
	}
	
	public void addEntity(Entity entity) {
		entites.add(entity);
	}
	
	public void update() {
		for (int i = 0; i < entites.size(); i++) {
			entites.get(i).update();
		}
	}
	
	public void render(Graphics g) {
		for (Entity e : entites) {
			e.render(g);
		}
	}
	
}
