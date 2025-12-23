package desktopPals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Render extends JPanel implements Const {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	String horizontalMovement = "left", verticalMovement, previousHorizontalMovement = "left";

	Emote heartEmote;

	AnimationManager animationManager;

	public Render() {
		animationManager = new AnimationManager();
		animationManager.registerAnimation("left", LEFTFRAMES);
		animationManager.registerAnimation("right", RIGHTFRAMES);

		heartEmote = new Emote(HEARTEMOTEFRAMES, "heart");

		setLocation(0,0);
		setSize(64, 64);
		setBackground(new Color(0, 0, 0, 0));
	}

	public String setHorizontalMovement(String movement) {
		previousHorizontalMovement = horizontalMovement;
		return horizontalMovement = movement;
	}

	public String getHorizontalMovement() {
		return horizontalMovement;
	}

	public String setVerticalMovement(String movement) {
		return verticalMovement = movement;
	}

	public String getVerticalMovement() {
		return verticalMovement;
	}

	private String trueRenderDirection() {
		if (horizontalMovement != "neither") {
			return horizontalMovement;
		} else {
			return previousHorizontalMovement;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(getFrameForAnimation(), 0, 0, this);
		if (trueRenderDirection() == "right" && !heartEmote.isComplete()) {
			g2d.drawImage(getFrameForEmote(), 44, 0, this);
		} else if(!heartEmote.isComplete()) {
			g2d.drawImage(getFrameForEmote(), 0, 0, this);
		}

	}

	private BufferedImage getFrameForAnimation() {
		String nextFrame = animationManager.getAnimation(trueRenderDirection()).getFrame();
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(nextFrame));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	private BufferedImage getFrameForEmote() {
		String nextFrame = heartEmote.getFrame();
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(nextFrame));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

}
