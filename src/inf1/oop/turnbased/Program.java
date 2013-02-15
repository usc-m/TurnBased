package inf1.oop.turnbased;

import inf1.oop.display.*;

public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Game game = new Game();
		//game.run();
		Display disp = new ConsoleDisplay(16, 16);
		disp.draw('#', 8, 8);
		disp.pushToScreen();
	}

}
