package main;

import java.awt.Color;

public class Block extends Thread{
	
	private int x, y;
	private int area[][];
	private Color color;
	private BlockContainer blockContainer;
	private boolean isMove;
	private boolean isMaked;
	
	public Block() {
		isMove = true;
	}
	
	public boolean checkGameOver() {
		for(int i = 0; i < area.length; i++) {
			for(int j = 0; j < area[i].length; j++) {
				if(area[i][j] == 0 && blockContainer.getContainer(x+i, y+j) != Color.WHITE) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void run() {
		if(checkGameOver()) {
			this.interrupt();
			isMove = false;
			blockContainer.gameOver();
		}
		while(isMove) {
			try {
				paintContainer();
				sleep(1000);
				if(!CheckAndMove(x + 1, y, true)) break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(!isInterrupted()) {
			blockContainer.makeNewMovingBlock();
		}
	}
	
	public void moveLeft() {
		CheckAndMove(x, y - 1, false);
	}
	
	public void moveRight() {
		CheckAndMove(x, y + 1, false);
	}
	
	public void moveDown() {
		CheckAndMove(x + 1, y, true);
	}
	
	public int[][] makeTempArea() {
		int tempArea[][] = new int[area.length][area.length];
		for(int i = 0; i < area.length; i++) {
			for(int j = 0; j < area[i].length; j++) {
				tempArea[i][j] = area[i][j];
			}
		}
		return tempArea;
	}
	
	private boolean CheckAndMove(int nextX, int nextY, boolean stop) {
		removePaintContainer();
		for(int i = 0; i < area.length; i++) {
			for(int j = 0; j < area[i].length; j++) {
				if(area[i][j] == 1 && blockContainer.getContainer(nextX+i, nextY+j) != Color.WHITE) {
					if(stop) {
						isMove = false;
					}
					paintContainer();
					return false;
				}
			}
		}
		x = nextX;
		y = nextY;
		paintContainer();
		return true;
	}
	
	private boolean checkAndRotate(int[][] nextArea) {
		removePaintContainer();
		for(int i = 0; i < nextArea.length; i++) {
			for(int j = 0; j < nextArea[i].length; j++) {
				if(nextArea[i][j] == 1 && blockContainer.getContainer(x+i, y+j) != Color.WHITE) {
					paintContainer();
					return false;
				}
			}
		}
		area = nextArea;
		paintContainer();
		return true;
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Block makeArea(int height, int width) {
		area = new int[height][width];
		return this;
	}
	
	public Block makeRow(int row, int[] data) {
		area[row] = data;
		return this;
	}
	
	public Block makeColor(Color color) {
		this.color = color;
		return this;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public BlockContainer getBlockContainer() {
		return blockContainer;
	}

	public void setBlockContainer(BlockContainer blockContainer) {
		this.blockContainer = blockContainer;
	}

	public boolean isMaked() {
		return isMaked;
	}

	public void setMaked(boolean isMaked) {
		this.isMaked = isMaked;
	}

	public void rotateClockWise() {
		int nextArea[][] = new int[area.length][area.length];
		for(int j = 0; j < area.length; j++) {
			for(int i = area.length - 1; i >= 0; i--) {
				int x = j;
				int y = area.length - i - 1;
				if(x < 0 || y < 0 || x >= area.length || y >= area.length) return;
				nextArea[x][y] = area[i][j];
			}
		}
		checkAndRotate(nextArea);
	}
	
	public void rotateCounterClockWise() {
		int nextArea[][] = new int[area.length][area.length];
		for(int j = area.length - 1; j >= 0; j--) {
			for(int i = 0; i < area.length; i++) {
				int x = area.length - j - 1;
				int y = i;
				if(x < 0 || y < 0 || x >= area.length || y >= area.length) return;
				nextArea[x][y] = area[i][j];
			}
		}
		checkAndRotate(nextArea);
	}
	
	public void paintContainer() {
		if(!isMaked) return;
		for(int i = 0; i < area.length; i++) {
			for(int j = 0; j < area[i].length; j++) {
				if(area[i][j] == 1) {
					blockContainer.setContainer(x + i, y + j, color);
				}
			}
		}
	}
	
	public void removePaintContainer() {
		if(!isMaked) return;
		for(int i = 0; i < area.length; i++) {
			for(int j = 0; j < area[i].length; j++) {
				if(area[i][j] == 1) {
					blockContainer.setContainer(x + i, y + j, Color.WHITE);
				}
			}
		}
	}
	
}
