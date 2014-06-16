package ca.ajweeks.igmc2014.achievements;

import java.io.Serializable;

public class AchievementManager implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Achievement[] a;
	
	public AchievementManager() {
		a = Achievement.values().clone();
	}
	
	public void valueChanged(int id) {
		
	}
	
	public Achievement[] resetAchievements() {
		Achievement[] b = a.clone();
		for (int i = 0; i < b.length; i++) {
			b[i].setAchieved(false);
		}
		return b;
	}
	
}
