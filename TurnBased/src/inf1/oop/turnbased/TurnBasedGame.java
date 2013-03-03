package inf1.oop.turnbased;

import inf1.oop.turnbased.graphics.MapRenderer;
import inf1.oop.turnbased.graphics.RenderingParameters;
import inf1.oop.turnbased.map.Map;
import inf1.oop.turnbased.map.Tile;

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
		
		
		
		
		//------------------------------------------------------
		/* #################################################### *
		 * DRAW MAP, put this as method in external class later *
		 * #################################################### */
		

		//VARIABLES, load these from a .json file later on?
		int margin_bottom=0; //distance starting to draw grid, number is index?
		int margin_left=0; //same from left, number is index?
		int map_height=10; //map slots
		int map_width=10; //map slots
		int grid_size=16; //pixels
		
		//DRAW GRID :: logic error on margin_left and margin_bottom, try to puzzle it out
		map = new Map(map_height+margin_bottom,map_width+margin_left, grid_size, grid_size); //<-- what do the 5's mean?
		for (int width=0; width<map_width; width+=1)
		{
			for (int height=0; height<map_height; height+=1)
			{
				map.setTile(margin_bottom+width, margin_left+height, new Tile("assets/data/spr_EmptySquare.png"));
			}
		}
		//------------------------------------------------------
		
		
		
		
		//------------------------------------------------------
		/* ####################################################### *
		 * DRAW PLAYER, put this as method in external class later * <-- and then run in the render method instead of create (create only runs ONCE, at game start)
		 * ####################################################### */
		
		//VARIABLES
		int player_x=0; //x-position of player (number is the grid index, so NOT in pixels)
		int player_y=0; //y-position of player (number is the grid index, so NOT in pixels)
		
		//PLAYER MOVEMENT USING ARROW KEYS
		//ERROR: code does not update/trigger on key press, tested this with the System.out.println.
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {player_x -= 1; System.out.println("player_x: "+player_x);} //take away the println parts when working.
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {player_x += 1; System.out.println("player_x: "+player_x);}
		else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {player_y += 1; System.out.println("player_y: "+player_y);}
		else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {player_y -= 1; System.out.println("player_y: "+player_y);}
		
		// :: DRAW PLAYER ::
		//IMPORTANT -> margin_bottom and margin_left must come from the map-drawing code above.
		//do not make separate variables with the same definition, important when putting these blocks in external classes.
		//ie. load from the same .json file as from the "DRAW MAP" block above
		map.setTile(margin_bottom+player_x, margin_left+player_y, new Tile("assets/data/spr_Player.png"));
		//------------------------------------------------------
		
		
		
		
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
		mapRenderer.draw();
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
