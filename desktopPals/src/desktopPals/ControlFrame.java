package desktopPals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class ControlFrame extends TransparentFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public ControlFrame() {
		super(new Dimension(128, 64));

		setBackground(new Color(207, 253, 255, 123));
		getContentPane().setBackground(new Color(207, 253, 255, 123));
	}

	public void setToVisible() {
		setVisible(true);
	}

	public void snapToBorder() {
		int halfX = screenSize.width/2;
		int halfY = screenSize.height/2;
		int widthDif = halfX - getX();
		int heightDif = halfY - getY();
		if (widthDif <= 0) {
			if (heightDif <= 0) {
				setLocation(screenSize.width - getWidth(), screenSize.height - getHeight());
			}
			else {
				setLocation(screenSize.width - getWidth(), 0);
			}
		} else {
			if (heightDif <= 0) {
				setLocation(0, screenSize.height - getHeight());
			}
			else {
				setLocation(0, 0);
			}
		}
	}

}
