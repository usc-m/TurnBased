package inf1.oop.turnbased;

import inf1.oop.turnbased.display.*;
import inf1.oop.turnbased.util.Point2D;

public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Game game = new Game();
		//game.run();
		VisualDisplay disp = new ConsoleDisplay(16, 16);
		disp.draw('#', new Point2D(8, 8));
		disp.push();//.
	}

}
