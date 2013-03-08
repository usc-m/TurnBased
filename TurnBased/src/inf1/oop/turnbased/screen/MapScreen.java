package inf1.oop.turnbased.screen;

import inf1.oop.turnbased.ServiceProvider;
//import inf1.oop.turnbased.entity.Entity;
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

	//PLAYER INITIAL VARIABLES
	//limits player's movement, will only be able to walk 1 grid per 30 times the code is checked. (see player movement script beneath)
	int player_walktimertotal=30;
	int player_walktimer=player_walktimertotal;
	
	
	//VARIABLES, load these from a .json file later on?
	int margin_bottom=0; //distance starting to draw grid, number is index?
	int margin_left=0; //same from left, number is index?
	int map_height=20; //map slots
	int map_width=20; //map slots
	int grid_size=16; //pixels
	int map_pixelheight=map_height*grid_size; //map total height in pixels
	int map_pixelwidth=map_width*grid_size; //map total width in pixels
	int map_centerx=Gdx.graphics.getWidth()/2; //center of screen on x-axis
	int map_centery=Gdx.graphics.getHeight()/2; //center of screen on y-axis
	
	//VARIABLES
	int player_x=0; //x-position of player (number is the grid index, so NOT in pixels)
	int player_y=0; //y-position of player (number is the grid index, so NOT in pixels)
	

	//Entity obj_Player = new Entity(5,5,"player",10,4,100);
	//obj_Player.setY(2);

	
	
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
		renderer.draw(map_centerx-(map_pixelwidth/2),0); //map draw margins, precisely centered on x-axis, 0 margin on y
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
		
		player_walktimer-=1;
		
		if (player_walktimer <= 0)
		{
			//moving left
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) 
			{
				map.setTile(player_x, player_y, new Tile("assets/data/spr_EmptySquare.png"));
				if (margin_left+player_x > 0)
				{
					player_x -= 1; 
					System.out.println("player_x: "+player_x);
					player_walktimer=player_walktimertotal;
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
					player_walktimer=player_walktimertotal;
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
					player_walktimer=player_walktimertotal;
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
					player_walktimer=player_walktimertotal;
				}
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
