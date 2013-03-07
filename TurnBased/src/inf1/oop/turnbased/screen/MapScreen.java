package inf1.oop.turnbased.screen;

import inf1.oop.turnbased.ServiceProvider;
import inf1.oop.turnbased.graphics.MapRenderer;
import inf1.oop.turnbased.map.Map;
import inf1.oop.turnbased.map.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MapScreen extends Screen {
	
	ServiceProvider services;
	SpriteBatch batch;
	MapRenderer renderer;
	Camera camera;
	
	Map map;

	//VARIABLES, load these from a .json file later on?
	int margin_bottom=0; //distance starting to draw grid, number is index?
	int margin_left=0; //same from left, number is index?
	int map_height=10; //map slots
	int map_width=10; //map slots
	int grid_size=16; //pixels
	
	//VARIABLES
	int player_x=0; //x-position of player (number is the grid index, so NOT in pixels)
	int player_y=0; //y-position of player (number is the grid index, so NOT in pixels)
	
	public MapScreen(ServiceProvider services) {
		this.services = services;
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(w,h);
		
		renderer = new MapRenderer(services);
		batch = services.get(SpriteBatch.class);
		
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
		
		renderer.setMap(map);
		
	}
	
	@Override
	public void draw(float dt) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		renderer.draw();
		batch.end();
		
	}

	@Override
	public void update(float dt) {
		//------------------------------------------------------
		/* ####################################################### *
		 * DRAW PLAYER, put this as method in external class later * <-- and then run in the render method instead of create (create only runs ONCE, at game start)
		 * ####################################################### */
		
		//PLAYER MOVEMENT USING ARROW KEYS
		//ERROR: code does not update/trigger on key press, tested this with the System.out.println.
		
		//moving left
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) 
		{
			map.setTile(player_x, player_y, new Tile("assets/data/spr_EmptySquare.png"));
			if (margin_left+player_x > 0)
			{
				player_x -= 1; 
				System.out.println("player_x: "+player_x);
			}
		} //take away the println parts when working.
		
		//moving right
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) 
		{
			map.setTile(player_x, player_y, new Tile("assets/data/spr_EmptySquare.png"));
			if (margin_left+player_x+1 < map_width)
			{
				player_x += 1; 
				System.out.println("player_x: "+player_x);
			}
		}
		
		//moving up
		else if (Gdx.input.isKeyPressed(Input.Keys.UP)) 
		{
			map.setTile(player_x, player_y, new Tile("assets/data/spr_EmptySquare.png"));
			if (margin_bottom+player_y+1 < map_height)
			{
				player_y += 1; 
				System.out.println("player_y: "+player_y);
			}
		}
		
		//moving down
		else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) 
		{
			map.setTile(player_x, player_y, new Tile("assets/data/spr_EmptySquare.png"));
			if (margin_bottom+player_y > 0)
			{
				player_y -= 1; 
				System.out.println("player_y: "+player_y);
			}
		}
		
		// :: DRAW PLAYER ::
		//IMPORTANT -> margin_bottom and margin_left must come from the map-drawing code above.
		//do not make separate variables with the same definition, important when putting these blocks in external classes.
		//ie. load from the same .json file as from the "DRAW MAP" block above
		map.setTile(margin_bottom+player_x, margin_left+player_y, new Tile("assets/data/spr_Player.png"));
		//------------------------------------------------------
		
		
	}

}
