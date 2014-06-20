package ca.ajweeks.igmc2014.achievements;

public enum Achievement {
	
	JUMPER("Jumper"), DOUBLE_JUMPER("Double Jumper");
	
	private String message;
	private boolean done;
	
	Achievement(String message) {
		this.message = message;
		this.done = false;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setAchieved(boolean done) {
		this.done = done;
	}
	
	public boolean isAchieved() {
		return done;
	}
}
