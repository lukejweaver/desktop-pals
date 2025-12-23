package desktopPals;

public class Emote extends Animation {

	final int duration = 200;

	int previousFrame = 0, beginExit, exitFrame;

	String[] frames, exitFrames;
	String name;

	public Emote(String[] frameArray, String name) {
		super(frameArray, name);
		frames = frameArray;
		exitFrame = frames.length - 1;
		beginExit = duration - frames.length;
	}

	@Override
	public String getFrame() {
		if (!isEntranceComplete()) {
			String frame = frames[previousFrame];
			incrementFrame();
			return frame;
		} else if (isExitAnimation()) {
			incrementFrame();
			String frame = frames[exitFrame];
			decrementExitFrame();
			return frame;
		} else {
			incrementFrame();
			return frames[exitFrame];
		}
	}

	@Override
	public void resetAnimation() {
		previousFrame = 0;
		exitFrame = frames.length - 1;
	}

	public Boolean isComplete() {
		return previousFrame >= duration;
	}

	private void incrementFrame() {
		previousFrame += 1;
	}

	private void decrementExitFrame() {
		exitFrame -= 1;
	}

	private Boolean isEntranceComplete() {
		return previousFrame >= (frames.length);
	}

	private Boolean isExitAnimation() {
		return previousFrame > beginExit;
	}


}
