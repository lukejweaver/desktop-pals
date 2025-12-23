package desktopPals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JRootPane;

public class TransparentFrame extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	int x = 0, y = 0;

	public TransparentFrame(Dimension dim) {
		super("transparent");

		setupFrame(dim);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int setX(int val) {
		return x += val;
	}

	public int setY(int val) {
		return y += val;
	}

	public void updateLocation() {
		setLocation(getX(), getY());
	}

	public void killFrame() {
		removeAll();
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	public Rectangle getRectangle() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	private void setupFrame(Dimension dim) {
//		Change to pack and implement a layout manager instead
		setSize(dim);
		JRootPane root = getRootPane();
		root.putClientProperty("Window.shadow", root);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		setAlwaysOnTop(true);
		setLocation(12, 12);
	}

}
