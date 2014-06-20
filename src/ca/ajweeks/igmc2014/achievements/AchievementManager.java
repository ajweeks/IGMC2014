package ca.ajweeks.igmc2014.achievements;

import static ca.ajweeks.igmc2014.achievements.Achievement.JUMPER;
import static ca.ajweeks.igmc2014.achievements.Achievement.DOUBLE_JUMPER;

import java.io.Serializable;

import ca.ajweeks.igmc2014.Game;
import ca.ajweeks.igmc2014.entity.AchievementParticle;
import ca.ajweeks.igmc2014.sound.Sound;

public class AchievementManager implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Achievement[] a;
	
	public AchievementManager() {
		a = Achievement.values().clone();
	}
	
	public boolean valueChanged(String label) {
		if (!JUMPER.isAchieved() && label.equals(JUMPER.getMessage())) {
			Game.addParticle(new AchievementParticle[] { new AchievementParticle(Game.SIZE.width - 245, 35, 220, 65, 150, JUMPER
					.getMessage()) });
			Sound.ACHIEVE.play();
			JUMPER.setAchieved(true);
		} else if (!DOUBLE_JUMPER.isAchieved() && label.equals(DOUBLE_JUMPER.getMessage())) {
			Game.addParticle(new AchievementParticle[] { new AchievementParticle(Game.SIZE.width - 245, 35, 220, 65, 150, DOUBLE_JUMPER
					.getMessage()) });
			Sound.ACHIEVE.play();
			DOUBLE_JUMPER.setAchieved(true);
		} else return false;
		return true;
	}
	
	public Achievement[] resetAchievements() {
		Achievement[] b = a.clone();
		for (int i = 0; i < b.length; i++) {
			b[i].setAchieved(false);
		}
		return b;
	}
	
}
