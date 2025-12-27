package desktopPals;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

public class Meander extends Action {

	Point meanderPoint;
	Boolean isComplete;
	Dimension screenSize;

	public Meander() {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		meanderPoint = new Point(0,0);
		isComplete = true;
	}

	@Override
	public boolean isComplete(Rectangle currentPosition, int maxVelocity) {
		if ((currentPosition.x >= getTargetPoint().x - maxVelocity/2 && currentPosition.x <= getTargetPoint().x + maxVelocity/2) &&
			(currentPosition.y >= getTargetPoint().y - maxVelocity/2 && currentPosition.y <= getTargetPoint().y + maxVelocity/2)) {
			isComplete = true;
		}
		return isComplete;
	}

	@Override
	public void beginAction() {
		isComplete = false;
		generateNewTargetPoint();
	}

	@Override
	public Point getTargetPoint() {
		return meanderPoint;
	}

	@Override
	public void generateNewTargetPoint() {
		Random rand = new Random();
		int x = rand.nextInt(screenSize.width - 64);
		int y = rand.nextInt(screenSize.height - 64);
		meanderPoint = new Point(x,y);
	}

	@Override
	protected void beginAction(ArrayList<FoodPellet> foodArray) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void beginAction(Rectangle currentPosition) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'beginAction'");
	};
}
