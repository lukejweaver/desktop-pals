package desktopPals;

public class Animation {
	String[] frames;
	
	int previousFrame = 0;
	
	public Animation(String[] frameArray) {
		frames = frameArray;
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
	
	private void incrementFrame() {
		previousFrame += 1;
	}
}
