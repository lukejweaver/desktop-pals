package desktopPals;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Feed extends Action {

	ArrayList<FoodPellet> pellets;

	FoodPellet currentPellet;

	Point targetPoint;

	int lastSize = 0;

	double currentDistance;
	int index;

	public Feed() {
	}

	@Override
	public boolean isComplete(Rectangle currentPosition, int maxVelocity) {
		if (currentPellet != null) {updatePellets(currentPosition);}
		if (!pellets.contains(currentPellet) || pellets.size() != lastSize) {
			findClosestPellet(currentPosition);
		}
		return pellets.isEmpty();
	}

	public void beginAction(ArrayList<FoodPellet> foodArray) {
		pellets = foodArray;
	}

	private void updatePellets(Rectangle currentPosition) {
		if (currentPosition.contains(currentPellet.getRectangle())) {
			currentPellet.eaten();
			pellets.remove(index);
			currentPellet = null;
		}
	}

	@Override
	public Point getTargetPoint() {
		return new Point(getX(), getY());
	}

	private int getY() {
		return currentPellet.getFrameY() - 20;
	}

	private int getX() {
		return currentPellet.getFrameX() - 20;
	}

	private void findClosestPellet(Rectangle currentPosition) {
		int i = 0;
		lastSize = pellets.size();
		for (FoodPellet pellet : pellets) {
			int pelletX = pellet.getFrameX();
			int pelletY = pellet.getFrameY();
			int xDiff = Math.abs(pelletX - currentPosition.x);
			int yDiff = Math.abs(pelletY - currentPosition.y);
			double newDistance = Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
			if (currentPellet == null) {
				currentPellet = pellet;
				currentDistance = newDistance;
				index = i;
			} else if (currentDistance > newDistance) {
				currentPellet = pellet;
				currentDistance = newDistance;
				index = i;
			}
			i++;
		}
	}

	@Override
	public void generateNewTargetPoint() {
		// TODO Auto-generated method stub

	}

	@Override
	public void beginAction() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void beginAction(Rectangle currentPosition) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'beginAction'");
	}

}
