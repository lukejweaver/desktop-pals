package desktopPals;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
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
	public boolean isComplete(int currentX, int currentY) {
		if ((currentX >= getTargetPoint().x - 2 && currentX <= getTargetPoint().x + 2) &&
			(currentY >= getTargetPoint().y - 2 && currentY <= getTargetPoint().y + 2)) {
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
	};
}
