package desktopPals;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

public class Run extends Action {
    Point runPoint;
	Boolean isComplete;
	Dimension screenSize;

    public Run() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		runPoint = new Point(0,0);
		isComplete = true;
    }


    @Override
    public void beginAction(Rectangle currentPosition) {
        isComplete = false;
        generateNewTargetPoint(currentPosition);
    }

    public void generateNewTargetPoint(Rectangle currentPosition) {
        Random rand = new Random();
        double r = rand.nextGaussian()*10 + 200.0;
		double theta = Math.toRadians(rand.nextDouble(360.0));

        int x = (int) Math.round(r*Math.cos(theta));
        int y = (int) Math.round(r*Math.sin(theta));

        runPoint = new Point(x + currentPosition.x, y + currentPosition.y);
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
    public Point getTargetPoint() {
        return runPoint;
    }

    @Override
    public void beginAction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'beginAction'");
    }

    @Override
    // Pick a point in a radius around the current position
    public void generateNewTargetPoint() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateNewTargetPoint'");
    }

    @Override
    protected void beginAction(ArrayList<FoodPellet> foodArray) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'beginAction'");
    }

}
