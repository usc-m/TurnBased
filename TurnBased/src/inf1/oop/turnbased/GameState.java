package inf1.oop.turnbased;

/*
 * This class should be used to store information about the player,
 * the map generator seed, etc - Information that should be preserved
 * when the game is saved or loaded
 */
public class GameState {
	public void save(String fileName) {
		
	}
	
	/*
	 *  The throws and exception stuff is temporary
	 *  It makes sure that if we try to load things, without having a written load function
	 *  the game should error out
	 */
	public static GameState load(String fileName) throws Exception {
		throw new Exception();
	}
}
