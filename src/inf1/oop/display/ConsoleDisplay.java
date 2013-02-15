package inf1.oop.display;

public class ConsoleDisplay extends Display {
	char[][] backbuffer;
	
	public ConsoleDisplay(int w, int h) {
		backbuffer = new char[w][h];
	}

	@Override
	public void draw(char identifier, int x, int y) {
		// TODO Auto-generated method stub
		backbuffer[x][y] = identifier;
	}

	@Override
	public void pushToScreen() {
		// TODO Auto-generated method stub
		for(char[] line : backbuffer) {
			System.out.println(new String(line));
		}
	}
	
	
}
