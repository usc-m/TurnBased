package inf1.oop.turnbased;

import inf1.oop.turnbased.graphics.MapRenderer;
import inf1.oop.turnbased.map.Map;
import inf1.oop.turnbased.map.Tile;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TurnBasedGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private MapRenderer mapRenderer;
	private Map map;
	private AssetManager assets;
	private ServiceProvider services;
	private float xO, yO;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		xO = -w/2;
		yO = -h/2;
		
		camera = new OrthographicCamera(w,h);
		batch = new SpriteBatch();
		
		services = new ServiceProvider();
		services.set(batch, SpriteBatch.class);
		
		assets = new AssetManager();
		services.set(assets, AssetManager.class);
		assets.load("assets/data/tile.png", Texture.class);		
		assets.finishLoading();
		
		map = new Map(5, 5, 16, 16);		
		map.setTile(2, 2, new Tile("assets/data/tile.png"));
		
		mapRenderer = new MapRenderer(services);
		mapRenderer.setMap(map);
	}

	@Override
	public void dispose() {
		batch.dispose();
		assets.dispose();
	}

	@Override
	public void render() {				
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		mapRenderer.draw(xO, yO);
		batch.end();
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
