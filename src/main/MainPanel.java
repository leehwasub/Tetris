package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements KeyListener{
	
	private static final long serialVersionUID = 1L;
	
	public static final int BLOCK_HEIGHT = 30;
	public static final int BLOCK_WIDTH = 30;
	
	public static final int START_X = 150;
	public static final int START_Y = 70;
	
	private BlockContainer blockContainer = new BlockContainer();

	public MainPanel() {
		addKeyListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		rendering((Graphics2D)g);
		blockContainer.draw((Graphics2D)g);
		g.setColor(Color.black);
		for(int i = 0; i < BlockContainer.CONTAINER_HEIGHT; i++) {
			for(int j = 0; j < BlockContainer.CONTAINER_WIDTH; j++) {
				g.drawRect(START_X + j * BLOCK_WIDTH, START_Y + i * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
			}
		}
		repaint();
	}
	
	private void rendering(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(blockContainer.getMovingBlock() != null) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				blockContainer.getMovingBlock().moveLeft();
			} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				blockContainer.getMovingBlock().moveRight();
			} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				blockContainer.getMovingBlock().moveDown();
			} else if(e.getKeyCode() == KeyEvent.VK_Q) {
				blockContainer.getMovingBlock().rotateCounterClockWise();
			} else if(e.getKeyCode() == KeyEvent.VK_W) {
				blockContainer.getMovingBlock().rotateClockWise();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
