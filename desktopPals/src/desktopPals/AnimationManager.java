package desktopPals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


// TODO: Add an animation + emotion buffer
public class AnimationManager extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	HashMap<String, Animation> registeredAnimations;
	HashMap<String, Emote> registeredEmotions;

	ArrayList<Animation> renderableAnimations;
	ArrayList<Emote> renderableEmotions;

	public AnimationManager() {
		setLocation(0,0);
		setSize(64, 64);
		setBackground(new Color(0, 0, 0, 0));

		registeredAnimations = new HashMap<String, Animation>();
		registeredEmotions = new HashMap<String, Emote>();
		renderableAnimations = new ArrayList<Animation>();
		renderableEmotions = new ArrayList<Emote>();
	}

	public void addRenderableEmotion(String emotionName) {
		renderableEmotions.add(getEmotion(emotionName));
	}

	public void removeRenderableEmotion(String emotionName) {
		renderableEmotions.remove(getEmotion(emotionName));
	}

	public void addRenderableAnimation(String animationName) {
		renderableAnimations.add(getAnimation(animationName));
	}

	public void removeRenderableAnimation(String animationName) {
		renderableAnimations.remove(getAnimation(animationName));
	}

	public void removeRenderableAnimation(String[] animationNames) {
		for(String animationName : animationNames) {
			removeRenderableAnimation(animationName);
			// renderableAnimations.remove(getAnimation(animationName));
		}
	}

	public void removeRenderableEmotion(String[] emotionNames) {
		for(String emotionName : emotionNames) {
			removeRenderableEmotion(emotionName);
		}
	}

	public void registerAnimation(String animationName, String[] frameList) {
		registeredAnimations.put(animationName, new Animation(frameList, animationName));
	}

	public void registerEmotion(String emotionName, String[] frameList) {
		registeredEmotions.put(emotionName, new Emote(frameList, emotionName));
	}

	public Animation getAnimation(String animationName) {
		return registeredAnimations.get(animationName);
	}

	public Emote getEmotion(String animationName) {
		return registeredEmotions.get(animationName);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		ArrayList<String> emotionsToRemove = new ArrayList<String>();
		for(Animation animationToRender : renderableAnimations) {
			// Dont know if this is really needed
			// if (renderableAnimations.size() > 1) {
			// 	// TODO: Finish this
			// }

			g2d.drawImage(getFrameForAnimation(animationToRender), 0, 0, this);
			for(Emote emotionToRender : renderableEmotions) {
				// if (renderableAnimations.size() > 1) {
				// 	// TODO: Finish this perhaps?
				// }
				if (!emotionToRender.isComplete()) {
					g2d.drawImage(getFrameForEmote(emotionToRender), 44, 0, this);
				} else {
					emotionToRender.resetAnimation();
					emotionsToRemove.add(emotionToRender.getName());
				}
				// g2d.drawImage(getFrameForAnimation(animationToRender), 0, 0, this);
			}
		}
		removeRenderableEmotion(emotionsToRemove.toArray(new String[ emotionsToRemove.size() ]));
	}

	private BufferedImage getFrameForAnimation(Animation animationForRender) {
		String nextFrame = animationForRender.getFrame();
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(nextFrame));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	private BufferedImage getFrameForEmote(Emote emotionForRender) {
		String nextFrame = emotionForRender.getFrame();
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(nextFrame));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

}
