package inf1.oop.turnbased;

import inf1.oop.turnbased.graphics.MapRenderer;
import inf1.oop.turnbased.graphics.RenderingParameters;
import inf1.oop.turnbased.map.Map;
import inf1.oop.turnbased.map.Tile;
import inf1.oop.turnbased.screen.MapScreen;
import inf1.oop.turnbased.screen.Screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TurnBasedGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private MapRenderer mapRenderer;
	private Map map;
	private AssetManager assets;
	private ServiceProvider services;
	private RenderingParameters renderParams;
	
	private Screen currentScreen; // TODO: Make a proper screen manager so we don't have to handle that in the main class
	
	@Override
	public void create() {		
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
		assets.finishLoading();
		
		//MATTHEWS WORKING CODE, can use as backup later
		//map = new Map(5, 5, 16, 16); //<-- what do the 5's mean?
		//map.setTile(2, 2, new Tile("assets/data/tile.png"));
		
		currentScreen = new MapScreen(services);
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		assets.dispose();
	}

	@Override
	public void render() {
		float dt = Gdx.graphics.getDeltaTime(); // this is the number of seconds since last frame (so a value of 1 would be 1 second, but 0.5 would be 500 milliseconds)
		currentScreen.update(dt);
		currentScreen.draw(dt);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
