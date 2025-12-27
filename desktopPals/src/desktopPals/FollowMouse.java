package desktopPals;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class FollowMouse extends Action {

	Point mousePoint;

	Boolean isComplete;

	int duration, durationCounter;

	public FollowMouse() {
		duration = 400;
		durationCounter = 0;
		isComplete = true;
		mousePoint = new Point(0, 0);
	}

	@Override
	public boolean isComplete(Rectangle currentPosition, int maxVelocity) {
		if (duration == durationCounter) {
			isComplete = true;
		} else {
			incrementDurationCounter();
		}
		return isComplete;
	}

	@Override
	public void beginAction() {
		isComplete = false;
		durationCounter = 0;
	}

	@Override
	public Point getTargetPoint() {
		generateNewTargetPoint();
		return mousePoint;
	}

	@Override
	public void generateNewTargetPoint() {
		int mouseX = MouseInfo.getPointerInfo().getLocation().x + 15;
		int mouseY = MouseInfo.getPointerInfo().getLocation().y + 15;
		mousePoint = new Point(mouseX, mouseY);
	}

	private void incrementDurationCounter() {
		durationCounter += 1;
	}

	@Override
	protected void beginAction(ArrayList<FoodPellet> foodArray) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void beginAction(Rectangle currentPosition) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'beginAction'");
	}

}
