package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.sun.javafx.css.converters.FontConverter.FontStyleConverter;

public class BlockContainer {
	
	public static final int CONTAINER_WIDTH = 12;
	public static final int CONTAINER_HEIGHT = 19;
	
	private int score;
	private Color container[][] = new Color[CONTAINER_HEIGHT][CONTAINER_WIDTH];
	private Block movingBlock;
	
	public BlockContainer() {
		for(int i = 0; i < container.length; i++) {
			for(int j = 0; j < container[i].length; j++) {
				if(j == 0 || j == container[i].length - 1 || i == container.length - 1) {
					container[i][j] = Color.GRAY;
				} else {
					container[i][j] = Color.WHITE;
				}
			}
		}
		makeNewMovingBlock();
	}
	
	public void gameOver() {
		movingBlock = null;
	}
	
	public void makeNewMovingBlock() {
		removeCompleteRow();
		int randomType = (int)(Math.random() * 7);
		int randomRotate = (int)(Math.random() * 4);
		movingBlock = BlockFactory.makeBlock(randomType, randomRotate, this);
		movingBlock.start();
	}
	
	public void removeCompleteRow() {
		boolean finded = true;
		while(finded) {
			finded = false;
			for(int i = CONTAINER_HEIGHT - 2; i >= 0; i--) {
				boolean completed = true;
				for(int j = 1; j < CONTAINER_WIDTH - 1; j++) {
					if(container[i][j] == Color.WHITE) {
						completed = false;
						break;
					}
				}
				
				if(completed) {
					finded = true;
					score += 10;
					for(int k = i; k >= 1; k--) {
						for(int w = 1; w < CONTAINER_WIDTH - 1; w++) {
							container[k][w] = container[k - 1][w];
						}
					}
					break;
				}
			}
		}
	}
	
	public Color[][] getContainer() {
		return container;
	}

	public void setContainer(Color[][] container) {
		this.container = container;
	}
	
	public void setContainer(int x, int y, Color c) {
		this.container[x][y] = c;
	}
	
	public Color getContainer(int x, int y) {
		return this.container[x][y];
	}
	
	public Block getMovingBlock() {
		return movingBlock;
	}

	public void setMovingBlock(Block movingBlock) {
		this.movingBlock = movingBlock;
	}

	public void draw(Graphics2D g) {
		for(int i = 0; i < CONTAINER_HEIGHT; i++) {
			for(int j = 0; j < CONTAINER_WIDTH; j++) {
				g.setColor(container[i][j]);
				g.fillRect(MainPanel.START_X + j * MainPanel.BLOCK_WIDTH, MainPanel.START_Y + i * MainPanel.BLOCK_HEIGHT, MainPanel.BLOCK_WIDTH, MainPanel.BLOCK_HEIGHT);
			}
		}
		g.setFont(new Font("맑은고딕", Font.BOLD, 20));
		g.drawString("점수 : " + score, 600, 300);
	}
	
	
}
