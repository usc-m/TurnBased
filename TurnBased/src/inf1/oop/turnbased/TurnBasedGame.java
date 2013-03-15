package inf1.oop.turnbased;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import inf1.oop.turnbased.screen.MainMenu;

public class TurnBasedGame extends Game {
	
	public static final String VERSION = "0.0.0.02 Pre-Alpha";
	public static final String LOG = "Turn-Based Game";
	public static final boolean DEBUG = false;
	FPSLogger log;
	
	@Override
	public void create() {
		log = new FPSLogger();
		setScreen(new MainMenu(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		
	}

	@Override
	public void render() {		
		super.render();
		log.log();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
