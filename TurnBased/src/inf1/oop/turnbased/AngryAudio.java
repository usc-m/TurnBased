package inf1.oop.turnbased;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AngryAudio {

	private AngryAudio() {
	}

	public static Music song = Gdx.audio.newMusic(Gdx.files.internal("assets/data/Anticipation.ogg"));
	public static Sound lvup = Gdx.audio.newSound(Gdx.files.internal("assets/data/LvUp.ogg"));
	public static Sound pickup = Gdx.audio.newSound(Gdx.files.internal("assets/data/Pickup.ogg"));
	public static Sound sweep = Gdx.audio.newSound(Gdx.files.internal("assets/data/Sweep1.ogg"));

	
	public static void playMusic(boolean looping) {
		song.setLooping(looping);
		song.play();
	}

	public static void stopMusic() {
		song.stop();
	}
	
	public static void pickup() {
		pickup.play((float) 0.5);
	}
	
	public static void lvup() {
		lvup.play((float) 0.5);
	}

	public static void sweep() {
		sweep.play((float) 0.5);
	}

	public static void dispose() {
		lvup.dispose();
		song.dispose();
		sweep.dispose();
	}

}