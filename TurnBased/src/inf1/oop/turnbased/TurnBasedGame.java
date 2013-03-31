package inf1.oop.turnbased;

import inf1.oop.turnbased.graphics.MapRenderer;
import inf1.oop.turnbased.graphics.RenderingParameters;
import inf1.oop.turnbased.map.Map;
import inf1.oop.turnbased.screen.MainMenu;
import inf1.oop.turnbased.screen.MapScreen;
import inf1.oop.turnbased.screen.Screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.FPSLogger;

public class TurnBasedGame extends Game implements ApplicationListener {
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private MapRenderer mapRenderer;
	private Map map;
	private AssetManager assets;
	private ServiceProvider services;
	private RenderingParameters renderParams;
	
	private Screen currentScreen; // TODO: Make a proper screen manager so we don't have to handle that in the main class
	
	//Michelle's stuff
	public static final String VERSION = "0.0.0.02 Pre-Alpha";
	public static final String LOG = "Turn-Based Game";
	public static final boolean DEBUG = false;
	FPSLogger log;
	//------------------------------------
	
	@Override
	public void create() {
		log = new FPSLogger();
		
		 float w = Gdx.graphics.getWidth();
		 float h = Gdx.graphics.getHeight();
		 
		 camera = new OrthographicCamera(w,h);
		 batch = new SpriteBatch();
		 
		 services = new ServiceProvider();
		 services.set(batch, SpriteBatch.class);
		  
		 renderParams = new RenderingParameters().setXOffset(-w/2).setYOffset(-h/2);
		 services.set(renderParams, RenderingParameters.class);   
		 assets = new AssetManager();
		 services.set(assets, AssetManager.class);
		 //LOAD "TEXTURES" / sprites / images
		 assets.load("assets/data/tile.png", Texture.class);    
		 assets.load("assets/data/spr_EmptySquare.png", Texture.class);  
		 assets.load("assets/data/spr_Player.png", Texture.class);  
		 assets.load("assets/data/spr_16x16Wall.png", Texture.class);  
		 assets.load("assets/data/spr_16x16Wall2.png", Texture.class);  
		 assets.load("assets/data/spr_16x16Edge.png", Texture.class);  
		 assets.load("assets/data/spr_16x16Floor.png", Texture.class);
		 assets.load("assets/data/spr_16x16Stairs.png", Texture.class);
		 assets.load("assets/data/spr_16x16Item.png", Texture.class);
		 //Monsters
		 assets.load("assets/data/Monsters/spr_16x16Monster1.png", Texture.class);
		 assets.load("assets/data/Monsters/spr_16x16Monster2.png", Texture.class);
		 assets.load("assets/data/Monsters/spr_16x16Monster3.png", Texture.class);
		 //My apologies this part is messy. Couldn't work out how to do it from one image...
		 assets.load("assets/data/PlayerSprite/Stand.png", Texture.class);
		 assets.load("assets/data/PlayerSprite/StandBack.png", Texture.class);
		 assets.load("assets/data/PlayerSprite/StandLeft.png", Texture.class);
		 assets.load("assets/data/PlayerSprite/StandRight.png", Texture.class);
		 assets.load("assets/data/PlayerSprite/StepBackLeft.png", Texture.class);
		 assets.load("assets/data/PlayerSprite/StepBackRight.png", Texture.class);
		 assets.load("assets/data/PlayerSprite/StepForwardLeft.png", Texture.class);
		 assets.load("assets/data/PlayerSprite/StepForwardRight.png", Texture.class);
		 assets.load("assets/data/PlayerSprite/StepLeftLeft.png", Texture.class);
		 assets.load("assets/data/PlayerSprite/StepLeftRight.png", Texture.class);
		 assets.load("assets/data/PlayerSprite/StepRightLeft.png", Texture.class);
		 assets.load("assets/data/PlayerSprite/StepRightRight.png", Texture.class);

		 assets.finishLoading();
		  
		//comment out respectively to enter map/menu screen, for now 
		currentScreen = new MapScreen(services);
		//setScreen(new MainMenu(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		
	}

	@Override
	public void render() {
		//comment out for Menu
		float dt = Gdx.graphics.getDeltaTime(); // this is the number of seconds since last frame (so a value of 1 would be 1 second, but 0.5 would be 500 milliseconds)
		currentScreen.update(dt);
		currentScreen.draw(dt);
		//---------------------------------------
		
		//comment out for Map
		//super.render();
		//log.log();
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
