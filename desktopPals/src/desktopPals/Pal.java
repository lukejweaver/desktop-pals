package desktopPals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;

public class Pal extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	int x = 12, y = 12;
	
	Render render = new Render();
	Meander meanderAction;
	Action[] actionArray;
	
	int velX = 2, velY = 2;
	
	Action currentAction;
	int currentActionStep = 0;
	
	public Pal(String title, Dimension dim) {
		super(title);
//		Change to pack and implement a layout manager instead
		setSize(dim);
		meanderAction = new Meander();
		currentAction = meanderAction;
		initializeActionArray();
        JRootPane root = getRootPane();
        root.putClientProperty("Window.shadow", root);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		setAlwaysOnTop(true);
		setVisible(true);
		JLabel label = new JLabel();
		label.setText("text");
		add(label);
		add(render);
		setLocation(12, 12);
		Timer t = new Timer(25, this);
		t.start();
	}
	
	public Integer radius() {
		int xDim = Math.abs(getX() - currentAction.getTargetPoint().x);
		int yDim = Math.abs(getY() - currentAction.getTargetPoint().y);
		
		int radius = (int) (Math.sqrt((yDim^2) + (xDim^2)) / 2);
		int s = radius^2;
		return s;
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
	
	public String horizontalMovement(int targetPoint) {
		if (targetPoint < getX() && !(getX() <= targetPoint + 2 && getX() >= targetPoint - 2)) {
			render.setHorizontalMovement("left");
		} else if (targetPoint > getX()) {
			render.setHorizontalMovement("right");
		} else {
			render.setHorizontalMovement("neither");
		}

		return render.getHorizontalMovement();
	}
	
	public String verticalMovement(int targetPoint) {
		if (targetPoint < getY() && !(getY() <= targetPoint + 2 && getY() >= targetPoint - 2)) {
			render.setVerticalMovement("up");
		} else if (targetPoint > getY() && !(getY() <= targetPoint + 2 && getY() >= targetPoint - 2)) {
			render.setVerticalMovement("down");
		} else {
			render.setVerticalMovement("neither");
		}

		return render.getVerticalMovement();
	}
	
	public void findMouse() {
		int mouseX = MouseInfo.getPointerInfo().getLocation().x + 15;
		int mouseY = MouseInfo.getPointerInfo().getLocation().y + 15;
		if (horizontalMovement(mouseX) == "left") {
			setX(-velX);
		} else if(horizontalMovement(mouseX) == "right") {
			setX(velX);
		}
		if (verticalMovement(mouseY) == "up") {
			setY(-velY);
		} else if(verticalMovement(mouseY) == "down") {
			setY(velY);
		}
		setLocation(getX(), getY());
		currentActionStep += 1;
	}
	
	public void executeAction() {
		if (horizontalMovement(currentAction.getTargetPoint().x) == "left") {
			setX(-velX);
		} else if(horizontalMovement(currentAction.getTargetPoint().x) == "right") {
			setX(velX);
		}
		if (verticalMovement(currentAction.getTargetPoint().y) == "up") {
			setY(-velY);
		} else if(verticalMovement(currentAction.getTargetPoint().y) == "down") {
			setY(velY);
		}
		setLocation(getX(), getY());
	}
	
	public boolean isCurrentActionComplete() {
		return currentAction.isComplete(getX(), getY());
	}
	
	public void chooseAction() {
		if (isCurrentActionComplete()) {
			Random rand = new Random();
			currentAction = actionArray[rand.nextInt(2)];
			currentAction.beginAction();			
		} else {
			executeAction();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		chooseAction();
		render.repaint();
	}
	
	private void initializeActionArray() {
		actionArray = new Action[]{ meanderAction, meanderAction };
	}

}
