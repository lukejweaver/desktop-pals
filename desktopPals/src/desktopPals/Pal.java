package desktopPals;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Pal implements ActionListener, Const {

	final int HUNGERMAXIMUM = 1700;

	// Render render = new Render();
	TransparentFrame parentFrame;

	ActionManager actionManager;

	AnimationManager animationManager;

	ControlFrame controlFrame;

	Boolean loaded = false;

	int clickCount = 0;

	// Decrement for however long it takes to feed this guy :(
	boolean isHungry = false;
	int hungerCount = HUNGERMAXIMUM;

	public Pal() {

		actionManager = new ActionManager();

		animationManager = new AnimationManager();

		parentFrame = new TransparentFrame(new Dimension(64, 64));
		// parentFrame.add(render);

		// Setup our mouse listener and add it to the parent frame
		Mouse m = new Mouse(this);

		parentFrame.addMouseListener(m);
		parentFrame.add(animationManager);

		animationManager.registerAnimation("left", LEFTFRAMES);
		animationManager.registerAnimation("right", RIGHTFRAMES);
		animationManager.registerEmotion("heart", HEARTEMOTEFRAMES);
		animationManager.registerEmotion("frustrated", FRUSTRATEDEMOTEFRAMES);
		animationManager.registerEmotion("hungry", HUNGRYEMOTEFRAMES);
		animationManager.addRenderableAnimation("left");

		animationManager.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("F"), "feed");
		animationManager.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("S"), "visibility");
		animationManager.getActionMap().put("feed", new BeginFeeding());
		animationManager.getActionMap().put("visibility", new SetPosition());

		controlFrame = new ControlFrame();
		controlFrame.snapToBorder();

		animationManager.requestFocus();

		Timer t = new Timer(35, this);

		// I'm very proud of my loading animation but the program is too fast so it doesnt load. I added a delay hehe
		try {
			// to sleep 10 seconds
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// recommended because catching InterruptedException clears interrupt flag
			Thread.currentThread().interrupt();
			// you probably want to quit if the thread is interrupted
			return;
		}

		loaded = true;
		t.start();
	}

	public void setAllVisible() {
		controlFrame.setToVisible();
		parentFrame.setVisible(true);
	}

	protected void incrementClickCount() {
		clickCount += 1;
	}

	public Boolean loaded() {
		return loaded;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		hungerCount--;

		updateFoodFrameVisibility();
		actionManager.executeAction(parentFrame.getRectangle());
		updateRenderContext();
		moveFrame();
		updateEmotions();
		animationManager.repaint();
		// render.repaint();
	}

	private void updateFoodFrameVisibility() {
		if (animationManager.hasFocus() && !actionManager.foodManager.isShowingFoodFrame()) {
			// actionManager.foodManager.showFoodFrame();
			parentFrame.toFront();
			animationManager.requestFocusInWindow();
		} else if (!animationManager.hasFocus() && actionManager.foodManager.isShowingFoodFrame()) {
			actionManager.foodManager.hideFoodFrame();
			parentFrame.toFront();
			animationManager.requestFocusInWindow();
		}
	}

	private void updateEmotions() {
		if (actionManager.foodManager.isWellFed()) {
			actionManager.foodManager.resetWellFed();

			if (isHungry) {
				animationManager.addRenderableEmotion("heart");
				resetHunger();
			}
		}

		if (!isHungry && hungerCount <= 0) {
			isHungry = true;
			animationManager.addRenderableEmotion("hungry");
		}

		if (clickCount >= 5) {
			animationManager.addRenderableEmotion("frustrated");
			actionManager.setCurrentAction(ActionManager.Actions.RUN, parentFrame.getRectangle());
			actionManager.setVelX(3);
			actionManager.setVelY(3);
			clickCount = 0;
		}

	}

	private void resetHunger() {
		isHungry = false;
		hungerCount = HUNGERMAXIMUM;
	}

	// Might not need to be updated each cycle (pehaps the reason for animationToRemove string?)
	private void updateRenderContext() {
		if (actionManager.isMovementDifferent()) {
//			String animationToRemove = ;
			animationManager.removeRenderableAnimation(actionManager.getHorizontalMovement() == "left" ? "right" : "left");
			animationManager.addRenderableAnimation(actionManager.getHorizontalMovement());
		}
//		render.setVerticalMovement(actionManager.getVerticalMovement());
	}

	private void moveFrame() {
		if (actionManager.getHorizontalMovement() == "left") {
			parentFrame.setX(-actionManager.getVelX());
		} else if (actionManager.getHorizontalMovement() == "right") {
			parentFrame.setX(actionManager.getVelX());
		}
		if (actionManager.getVerticalMovement() == "up") {
			parentFrame.setY(-actionManager.getVelY());
		} else if (actionManager.getVerticalMovement() == "down") {
			parentFrame.setY(actionManager.getVelY());
		}
		parentFrame.updateLocation();
	}

	private class BeginFeeding extends AbstractAction {

		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		BeginFeeding() {
		};

		@Override
		public void actionPerformed(ActionEvent e) {
			actionManager.foodManager.setAddFoodTrue();
		}
	}

	private class SetPosition extends AbstractAction {

		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		SetPosition() {
		};

		@Override
		public void actionPerformed(ActionEvent e) {
			parentFrame.setAlwaysOnTop(!parentFrame.isAlwaysOnTop());;
		}
	}

	// What should the best scope for this be?
	public class Mouse implements MouseListener {
		Pal p;


		public Mouse (Pal newPal) {
			p = newPal;
		}

		public void mouseClicked(MouseEvent e) {
			p.incrementClickCount();
		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {

		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}
	}

}
