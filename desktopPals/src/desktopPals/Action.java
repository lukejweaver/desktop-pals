package desktopPals;

import java.awt.Point;

public abstract class Action {
	
	public Action() {
		
	}
	
	public abstract boolean isComplete(int currentX, int currentY);
	
	public abstract void beginAction();
	
	public abstract Point getTargetPoint();
	
	public abstract void generateNewTargetPoint();
	
}
