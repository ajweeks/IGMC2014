package ca.ajweeks.igmc2014.level;

import java.awt.Graphics;
import java.util.ArrayList;

import ca.ajweeks.igmc2014.entity.Entity;

public class Level {
	
	private ArrayList<Entity> entites;
	
	public Level() {
		entites = new ArrayList<>();
	}
	
	public void addEntity(Entity entity) {
		entites.add(entity);
	}
	
	public void update(double delta) {
		for (int i = 0; i < entites.size(); i++) {
			entites.get(i).update(delta);
		}
	}
	
	public void render(Graphics g) {
		for (Entity e : entites) {
			e.render(g);
		}
	}
	
}
