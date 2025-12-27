package desktopPals;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Action {

	public Action() {

	}

	// The fact that I have so many beginAction methods means that this is the incorrect architecture
	// Consider following the advice of comment: https://stackoverflow.com/questions/63413274/is-it-possible-to-overload-abstract-methods-in-an-abstract-java-class-but-imple
	// and implement a manager

	public abstract boolean isComplete(Rectangle currentPosition, int maxVelocity);

	public abstract void beginAction();

	public abstract Point getTargetPoint();

	public abstract void generateNewTargetPoint();

	protected abstract void beginAction(ArrayList<FoodPellet> foodArray);

	protected abstract void beginAction(Rectangle currentPosition);

}
