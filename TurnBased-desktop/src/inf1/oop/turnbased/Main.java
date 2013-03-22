package inf1.oop.turnbased;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "TurnBased";
		cfg.useGL20 = false;
		cfg.width = 672;
		cfg.height = 378;
		cfg.vSyncEnabled = true;
		
		new LwjglApplication(new TurnBasedGame(), cfg);
		//test
	}
}
