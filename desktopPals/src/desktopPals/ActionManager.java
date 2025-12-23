package desktopPals;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Random;

public class ActionManager {
	Action meanderAction, feedingAction, followMouse, currentAction, runAction;

	public enum Actions {
		MEANDER,
		FEED,
		FOLLOW,
		RUN
	}

	Action[] actionArray;

	FoodManager foodManager;

	String horizontalMovement = "neither", verticalMovement = "neither", previousSideMovement;

	Boolean differentMovement = false;

	int velX = 2, velY = 2;

	int currentActionStep = 0;

	public ActionManager() {
		registerActions();
		initializeActionArray();

		foodManager = new FoodManager();
		Thread foodThread = new Thread(foodManager);
		foodThread.start();
	}

	public void executeAction(Rectangle frameRectangle) {
		if (foodManager.isFoodPresent() && !currentAction.equals(feedingAction)) {
			currentAction = feedingAction;
			currentAction.beginAction(foodManager.foodArray);
			isCurrentActionComplete(frameRectangle);
		} else if (isCurrentActionComplete(frameRectangle)) {
			chooseNewAction();
		}
		updateMovements(new Point(frameRectangle.x, frameRectangle.y));
	}

	public int getVelX() {
		return velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelX(int newVal) {
		velX = newVal;
	}

	public void setVelY(int newVal) {
		velY = newVal;
	}

	// Not sure if this is the best way to do this or if it would be better to use:
	// 	(1) ENUM (This is my fave idea) -- not sure if good. look into better way
	//  (2) HashMap
	public void setCurrentAction(Actions actionEnum, Rectangle frameRectangle) {
		switch (actionEnum) {
            case MEANDER:
				currentAction = meanderAction;
				currentAction.beginAction();
                break;

            case FOLLOW:
				currentAction = followMouse;
				currentAction.beginAction();
                break;

            case FEED:
				currentAction = feedingAction;
				currentAction.beginAction();
                break;

			case RUN:
				currentAction = runAction;
				currentAction.beginAction(frameRectangle);
                break;

            default:
                System.out.println("Not Implemented");
                break;
        }
	}

	public String getVerticalMovement() {
		return verticalMovement;
	}

	public String getHorizontalMovement() {
		return horizontalMovement;
	}

	public Boolean isMovementDifferent() {
		if (horizontalMovement == "neither") {
			return false;
		} else {
			return previousMovement() != horizontalMovement;
		}
	}

	private String previousMovement() {
		return previousSideMovement;
	}

	private void updateMovements(Point framePoint) {
		horizontalMovement(framePoint.x);
		verticalMovement(framePoint.y);
	}

	private void chooseNewAction() {
		Random rand = new Random();
		currentAction = actionArray[rand.nextInt(2)];
		currentAction.beginAction();
		setVelocityRandom();
	}

	private void setVelocityRandom() {
		Random rand = new Random();
		int newVel = rand.nextInt(2) + 1;
		velX = newVel;
		velY = newVel;
	}


	private String horizontalMovement(int frameX) {
		String newMovement;
		if (currentAction.getTargetPoint().x < frameX
				&& !(frameX <= currentAction.getTargetPoint().x + 2 && frameX >= currentAction.getTargetPoint().x - 2)) {
			newMovement = "left";
		} else if (currentAction.getTargetPoint().x > frameX) {
			newMovement = "right";
		} else {
			newMovement = "neither";
		}
		updateMovement();
		return horizontalMovement = newMovement;
	}

	private void updateMovement() {
//		System.out.println(newMovement);
//		System.out.println(horizontalMovement);
		if (horizontalMovement != "neither") {
			previousSideMovement = horizontalMovement;
		}
	}

	private String verticalMovement(int frameY) {
		if (currentAction.getTargetPoint().y < frameY
				&& !(frameY <= currentAction.getTargetPoint().y + 2 && frameY >= currentAction.getTargetPoint().y - 2)) {
			return verticalMovement = "up";
		} else if (currentAction.getTargetPoint().y > frameY
				&& !(frameY <= currentAction.getTargetPoint().y + 2 && frameY >= currentAction.getTargetPoint().y - 2)) {
			return verticalMovement = "down";
		} else {
			return verticalMovement = "neither";
		}
	}

	private boolean isCurrentActionComplete(Rectangle comparisonRectangle) {
		return currentAction.isComplete(comparisonRectangle);
	}

	private void registerActions() {
		meanderAction = new Meander();
		followMouse = new FollowMouse();
		feedingAction = new Feed();
		runAction = new Run();
		currentAction = new Meander();
	}

	private void initializeActionArray() {
		actionArray = new Action[] { meanderAction, followMouse };
	}

}
