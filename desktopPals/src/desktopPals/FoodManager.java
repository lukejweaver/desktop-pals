package desktopPals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.Timer;

public class FoodManager implements Runnable, ActionListener {

	Boolean addFood, wellFed;

	ArrayList<FoodPellet> foodArray;

	TransparentFrame foodCounter;

	int totalFoodEaten = 0, currentFoodLoop = 0;
	JLabel label;

	@Override
	public void run() {
		addFood = false;
		wellFed = false;
		foodArray = new ArrayList<FoodPellet>();

		foodCounter = new TransparentFrame(new Dimension(120, 30));
		label = new JLabel();
		label.setText("Food counter");
		foodCounter.setFocusable(false);

		foodCounter.add(label);
		foodCounter.setBackground(Color.white);
		foodCounter.setVisible(false);

		Timer t = new Timer(300, this);
		t.start();
	}

	public void showFoodFrame() {
		foodCounter.setVisible(true);
	}

	public void hideFoodFrame() {
		foodCounter.setVisible(false);
	}

	public Boolean isShowingFoodFrame() {
		return foodCounter.isVisible();
	}

	public void setAddFoodTrue() {
		addFood = true;
		currentFoodLoop = 0;
	}

	public Boolean isWellFed() {
		return wellFed;
	}

	public void resetWellFed() {
		wellFed = false;
	}

	public Boolean isFoodPresent() {
		return !foodArray.isEmpty();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		updateFoodBoolean();
		if (addFood) {
			addFood();
		}
		if (!foodArray.isEmpty()) {
			iterateDown();
		}
		updateFoodFrameText();
		currentFoodLoop ++;
	}

	private void updateFoodFrameText() {
		// label.setText("Food Left: " + wellFed);
		// label.setText("Food Left: " + foodArray.size());
	}

	public void resetFoodEaten() {
		totalFoodEaten = 0;
	}

	private void updateFoodBoolean() {
//		foodArray.size() <= 15
		if (addFood && currentFoodLoop == 5) {
			addFood = false;
			wellFed = true;
		}
	}

	private void addFood() {
		FoodPellet pellet = new FoodPellet();
		foodArray.add(pellet);
	}

	private void iterateDown() {
		for (FoodPellet pellet : foodArray) {
			pellet.repaint();
		}
	}

}
