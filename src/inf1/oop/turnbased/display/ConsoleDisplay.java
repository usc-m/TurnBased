package inf1.oop.turnbased.display;

import inf1.oop.turnbased.util.Point2D;

public class ConsoleDisplay extends VisualDisplay {
	int width, height;
	char[][] backbuffer;
	
	public ConsoleDisplay(int w, int h) {
		width = w;
		height = h;
		
		backbuffer = new char[w][h];
	}

	@Override
	public void draw(char identifier, Point2D position) {

		int x = position.getX(), y = position.getY();
		
		if(x < 0 || x >= width) {
			throw new IndexOutOfBoundsException("X coordinate out of bounds");
		}
		
		if(y < 0 || y >= height) {
			throw new IndexOutOfBoundsException("Y coordinate out of bounds");
		}
		
		backbuffer[x][y] = identifier;
	}

	@Override
	public void push() {
		for(char[] line : backbuffer) {
			System.out.println(new String(line));
		}
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}
	
	
}
