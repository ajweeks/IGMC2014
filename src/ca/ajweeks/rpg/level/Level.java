package ca.ajweeks.rpg.level;

import java.awt.Graphics;
import java.util.ArrayList;

import ca.ajweeks.rpg.entity.Entity;
import ca.ajweeks.rpg.entity.Mob;

public class Level {
	
	private ArrayList<Entity> entites;
	
	public Level() {
		entites = new ArrayList<>();
	}
	
	public void setMobAcceleration(Mob e, int xa, int ya) {
		if (!(entites.get(entites.indexOf(e)) instanceof Mob)) return;
		if (entites.contains(e)) {
			((Mob) entites.get(entites.indexOf(e))).xa = xa;
			((Mob) entites.get(entites.indexOf(e))).ya = ya;
		} else throw new IllegalArgumentException("Entity does not exist!");
		
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
