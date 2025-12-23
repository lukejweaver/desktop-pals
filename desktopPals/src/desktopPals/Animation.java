package desktopPals;

public class Animation {
	String[] frames;

	String name;

	int previousFrame = 0;

	public Animation(String[] frameArray, String animName) {
		frames = frameArray;
		name = animName;
	}

	public void resetAnimation() {
		previousFrame = 0;
	}

	public String getFrame() {
		if (previousFrame < frames.length - 1) {
			incrementFrame();
		} else {
			resetAnimation();
		}
		return frames[previousFrame];
	}

	public String getName() {
		return name;
	}

	private void incrementFrame() {
		previousFrame += 1;
	}
}
