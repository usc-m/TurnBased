package inf1.oop.turnbased;

import inf1.oop.turnbased.screen.*;

public class Game {
	
	private Screen screen;
	
	public void run() {
		/*
		 *  This is temporary, probably shouldn't be using while(true)
		 *  Also, need to implement a way of calculating the time
		 *  that has passed since the last draw or update
		 */
		while(true) {
			screen.draw(0);
			screen.update(0);
		}
	}
}
