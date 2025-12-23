package desktopPals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LoadingManager extends JPanel implements Runnable, ActionListener, Const {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	Timer t;

	TransparentFrame loadingFrame;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	AnimationManager animationManager;

	@Override
	public void run() {
		loadingFrame = new TransparentFrame(new Dimension(128, 128));
		loadingFrame.setX(screenSize.width/2);
		loadingFrame.setY(screenSize.height/2);
		loadingFrame.updateLocation();

		setLocation(0,0);
		setSize(64, 64);
		setBackground(new Color(0, 0, 0, 0));

		loadingFrame.add(this);

		animationManager = new AnimationManager();
		animationManager.registerAnimation("loading", LOADINGFRAMES);

		t = new Timer(25, this);
		t.start();

		loadingFrame.setVisible(true);
	}

	public void terminate() {
		loadingFrame.killFrame();
		t.stop();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(getFrameForAnimation(), 0, 0, this);
	}

	private BufferedImage getFrameForAnimation() {
		String nextFrame = animationManager.getAnimation("loading").getFrame();
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(nextFrame));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
}
