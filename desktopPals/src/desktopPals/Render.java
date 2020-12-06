package desktopPals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Render extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String horizontalMovement = "left", verticalMovement, previousHorizontalMovement;
		
	Emote heartEmote;
	Animation leftAnimation, rightAnimation;
	
	public Render() {
		Const constantClass = new Const();
		
		heartEmote = new Emote(constantClass.getHeartEmoteFrames());
		leftAnimation = new Animation(constantClass.getLeftAnimationFrames());
		rightAnimation = new Animation(constantClass.getRightAnimationFrames());
		
		setLocation(0,0);
		setSize(64, 64);
		setBackground(new Color(0, 0, 0, 0));
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
	
	public String setHorizontalMovement(String movement) {
		if (movement != "neither") {
			previousHorizontalMovement = horizontalMovement;			
		}
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
	
	private Animation horizontalAnimationForFrame() {
		if (trueRenderDirection() == "left") {
			 return leftAnimation;
		} else {
			return rightAnimation;
		}
	}
	
	private Boolean animationShouldReset() {
		return (trueRenderDirection() != previousHorizontalMovement);
	}
	
	private BufferedImage getFrameForAnimation() {
		if (animationShouldReset()) {
			horizontalAnimationForFrame().resetAnimation();	
		}
		String nextFrame = horizontalAnimationForFrame().getFrame();
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
