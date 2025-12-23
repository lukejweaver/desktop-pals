package desktopPals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FoodPellet extends JPanel implements Const {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	TransparentFrame parentFrame;

	Animation pelletAnimation;

	public FoodPellet() {
		pelletAnimation = new Animation(PELLETFRAMES, "pellet");

		setupParentFrame();

		setLocation(0,0);
		setSize(20, 20);
		setBackground(new Color(0, 0, 0, 0));
	}

	public int getFrameX() {
		return parentFrame.getX();
	}

	public int getFrameY() {
		return parentFrame.getY();
	}

	public Rectangle getRectangle() {
		return new Rectangle(getFrameX(), getFrameY(), getWidth(), getHeight());
	}

	public void eaten() {
		parentFrame.killFrame();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(getFrameImage(), 0, 0, this);
	}

	private BufferedImage getFrameImage() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(getFramePath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	private void setupParentFrame() {
		parentFrame = new TransparentFrame(new Dimension(20, 20));
		parentFrame.setFocusable(false);
		parentFrame.setX(MouseInfo.getPointerInfo().getLocation().x);
		parentFrame.setY(MouseInfo.getPointerInfo().getLocation().y);
		parentFrame.updateLocation();
		parentFrame.setVisible(true);
		parentFrame.add(this);
	}

	private String getFramePath() {
		return pelletAnimation.getFrame();
	}
}
