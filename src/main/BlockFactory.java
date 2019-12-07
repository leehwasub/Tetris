package main;

import java.awt.Color;

public class BlockFactory {
	public static Block makeBlock(int type, int rotate, BlockContainer blockContainer) {
		Block block = new Block();
		
		switch(type) {
		case 0:
			block.makeArea(3, 3).makeRow(0, new int[] {0,0,1}).makeRow(1, new int[] {1,1,1}).makeRow(2, new int[] {0,0,0}).makeColor(Color.YELLOW);
			break;
		case 1:
			block.makeArea(3, 3).makeRow(0, new int[] {1,0,0}).makeRow(1, new int[] {1,1,1}).makeRow(2, new int[] {0,0,0}).makeColor(Color.BLUE);
			break;
		case 2:
			block.makeArea(4, 4).makeRow(0, new int[] {0,1,0,0}).makeRow(1, new int[] {0,1,0,0})
			.makeRow(2, new int[] {0,1,0,0}).makeRow(3, new int[] {0,1,0,0}).makeColor(Color.RED);
			break;
		case 3:
			block.makeArea(2, 2).makeRow(0, new int[] {1,1}).makeRow(1, new int[] {1,1}).makeColor(Color.CYAN);
			break;
		case 4:
			block.makeArea(3, 3).makeRow(0, new int[] {1,1,0}).makeRow(1, new int[] {0,1,1}).makeRow(2, new int[] {0,0,0}).makeColor(Color.ORANGE);
			break;
		case 5:
			block.makeArea(3, 3).makeRow(0, new int[] {0,1,1}).makeRow(1, new int[] {1,1,0}).makeRow(2, new int[] {0,0,0}).makeColor(Color.MAGENTA);
			break;
		case 6:
			block.makeArea(3, 3).makeRow(0, new int[] {0,1,0}).makeRow(1, new int[] {1,1,1}).makeRow(2, new int[] {0,0,0}).makeColor(Color.GREEN);
			break;
		}
		block.setXY(0, 5);
		block.setBlockContainer(blockContainer);
		for(int i = 0; i < rotate; i++) {
			block.rotateClockWise();
		}
		block.setMaked(true);
		return block;
	}
}
