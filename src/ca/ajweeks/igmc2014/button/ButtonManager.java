package ca.ajweeks.igmc2014.button;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class ButtonManager {
	
	private ArrayList<Button> buttons;
	/** is -1 if there are no buttons */
	private int selectedButton;
	
	public ButtonManager() {
		buttons = new ArrayList<>();
		selectedButton = -1;
	}
	
	public void update() {
		for (int i = 0; i < buttons.size(); i++) {
			if (buttons.get(i).hover && buttons.get(i).enabled) selectedButton = i;
		}
	}
	
	public void render(Graphics g) {
		for (Button b : buttons) {
			b.render(g);
		}
	}
	
	public void updateSelectedButton() {
		for (int i = 0; i < buttons.size(); i++) {
			if (i == selectedButton && buttons.get(i).enabled) buttons.get(i).setSelected(true);
			else buttons.get(i).setSelected(false);
		}
	}
	
	public int getSelectedButton() {
		return selectedButton;
	}
	
	public void setSelectedButton(int i) {
		if (i >= 0 && i <= buttons.size()) selectedButton = i;
		else System.err.println("Invalid selected button: " + i);
	}
	
	public Button getButton(int i) {
		return buttons.get(i);
	}
	
	public void nextButton() {
		if (selectedButton + 1 > buttons.size() - 1) selectedButton = 0;
		else selectedButton++;
	}
	
	public void previousButton() {
		if (selectedButton - 1 < 0) selectedButton = buttons.size() - 1;
		else selectedButton--;
	}
	
	public void addButton(Button b) {
		if (!buttons.contains(b)) buttons.add(b);
		if (selectedButton == -1) {
			selectedButton = 0;
			buttons.get(selectedButton).selected = true;
		}
	}
}
