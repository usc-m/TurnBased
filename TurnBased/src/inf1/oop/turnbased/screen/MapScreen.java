package inf1.oop.turnbased.screen;

import inf1.oop.turnbased.ServiceProvider;
import inf1.oop.turnbased.graphics.MapRenderer;
import inf1.oop.turnbased.graphics.RenderingParameters;
import inf1.oop.turnbased.map.Map;
import inf1.oop.turnbased.map.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MapScreen extends Screen {
	
	ServiceProvider services;
	SpriteBatch batch;
	MapRenderer renderer;
	Camera camera;
	AssetManager assets;
	
	
	Map map;

	//PLAYER INITIAL VARIABLES
	//limits player's movement, will only be able to walk 1 grid per 30 times the code is checked. (see player movement script beneath)
	int player_walktimertotal=30;
	int player_walktimer=player_walktimertotal;
	
	
	//VARIABLES, load these from a .json file later on?
	int map_height=20;							//map height in tiles
	int map_width=20; 							//map width in tiles
	int tile_size=16; 							//tile size in px
	int map_pixelheight=map_height*tile_size; 	//map height in pixels
	int map_pixelwidth=map_width*tile_size; 	//map width in pixels
	
	//Player Data
	int player_x=0; 	//player x-position in px
	int player_y=0; 	//yplayer y-position in px
	Texture playerS;	//player sprite
	
	//global rendering parameters
	RenderingParameters renderParams;
	
	//x,y to draw map at
	float xShift = 240;
	float yShift = 0;
	
	public MapScreen(ServiceProvider services) {
		assets = services.get(AssetManager.class);
		this.services = services;
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		renderParams = services.get(RenderingParameters.class);
		
		camera = new OrthographicCamera(w,h);
		
		renderer = new MapRenderer(services);
		batch = services.get(SpriteBatch.class);
		
		map = new Map(map_height,map_width, tile_size, tile_size);
		for (int x=0; x<map_width; x+=1)
		{
			for (int y=0; y<map_height; y+=1)
			{
				//will be subbed with generate map method, suggest removing(i.e. integrating in tile class) the pathing
				map.setTile(x, y, new Tile("assets/data/spr_EmptySquare.png"));
			}
		}
		//------------------------------------------------------
		
		renderer.setMap(map);
		playerS = assets.get("assets/data/spr_Player.png");
	}
	
	@Override
	public void draw(float dt) {
		//bring coordinates for player bottom-lef, like in the map renderer
		float xOffset = renderParams.getXOffset() + xShift;
		float yOffset = renderParams.getYOffset() + yShift;
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		renderer.draw(xShift,yShift);
	    batch.draw(playerS, xOffset + player_x, yOffset  + player_y);
	    batch.end();
		
		
		
	}

	//NOTE: I am not offseting player_x, y itself in order to make the processing of the array less of a hassle -F
	
	@Override
	public void update(float dt) {
		//------------------------------------------------------
		/* ####################################################### *
		 * DRAW PLAYER, put this as method in external class later * <-- and then run in the render method instead of create (create only runs ONCE, at game start)
		 * ####################################################### */
		
		//PLAYER MOVEMENT USING ARROW KEYS	
		
		//moving left
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) 
		{
			if (player_x + xShift > xShift)
			{
				player_x -= 1; 	//consider using delta and f values -F
				System.out.println("player_x: "+player_x);
				player_walktimer=player_walktimertotal;
			}
		} //take away the println parts when working.
			
		//moving right
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) 
		{
			if (player_x + 16 + xShift < xShift + map_pixelwidth) //there is a +16 adjustment to playerX since detection point is bottom left
			{
				player_x += 1; 
				System.out.println("player_x: "+player_x);
				player_walktimer=player_walktimertotal;
			}
		}
			
		//moving up
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) 
		{
			if (player_y + 16 + yShift < yShift + map_pixelheight)
			{
				player_y += 1; 
				System.out.println("player_y: "+player_y);
				player_walktimer=player_walktimertotal;
			}
		}
			
		//moving down
		else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) 
		{
			if (player_y + yShift> yShift)
			{
				player_y -= 1; 
				System.out.println("player_y: "+player_y);
				player_walktimer=player_walktimertotal;
			}
		}
	}
		//IMPORTANT -> margin_bottom and margin_left must come from the map-drawing code above.
		//do not make separate variables with the same definition, important when putting these blocks in external classes.
		//ie. load from the same .json file as from the "DRAW MAP" block above
		//------------------------------------------------------

}
