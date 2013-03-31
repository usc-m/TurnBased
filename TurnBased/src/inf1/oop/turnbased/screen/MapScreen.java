package inf1.oop.turnbased.screen;

import java.util.ArrayList;

import inf1.oop.turnbased.AngryAudio;
import inf1.oop.turnbased.ServiceProvider;
import inf1.oop.turnbased.TurnBasedGame;
import inf1.oop.turnbased.combat.Battle;
import inf1.oop.turnbased.combat.BattleEndCondition;
import inf1.oop.turnbased.combat.BattleEndListener;
import inf1.oop.turnbased.combat.Stats;
import inf1.oop.turnbased.entity.Entity;
import inf1.oop.turnbased.graphics.MapRenderer;
import inf1.oop.turnbased.graphics.RenderingParameters;
import inf1.oop.turnbased.map.Map;
import inf1.oop.turnbased.map.MapGenerator;
import inf1.oop.turnbased.map.Tile;
import inf1.oop.turnbased.combat.CombatEntity;
import inf1.oop.turnbased.combat.DeathEventListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MapScreen implements Screen {
	
	ServiceProvider services;
	SpriteBatch batch;
	MapRenderer renderer;
	Camera camera;
	AssetManager assets;
	MapGenerator generator;
	
	
	Map map;
	
	//VARIABLES, load these from a .json file later on?
	int map_height=24;							//map height in tiles
	int map_width=42; 							//map width in tiles
	int tile_size=16; 							//tile size in px
	int map_pixelheight=map_height*tile_size; 	//map height in pixels
	int map_pixelwidth=map_width*tile_size; 	//map width in pixels
	
	//Player Data
	private Entity player;
	int stairX, stairY;
	
	Texture playerS;	//player sprite
	//global rendering parameters
	RenderingParameters renderParams;
	

	private TurnBasedGame game;
	
	private final Tile blankTile;
	
	//x,y to draw map at
	float xShift = 0;
	float yShift = 0;
	
	public MapScreen(TurnBasedGame game) {
		this.game = game;
		this.services = game.getServices();		
		assets = services.get(AssetManager.class);
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		renderParams = services.get(RenderingParameters.class);
		
		camera = new OrthographicCamera(w,h);
		
		renderer = new MapRenderer(services);
		batch = services.get(SpriteBatch.class);
		
		map = new Map(map_width,map_height, tile_size, tile_size);
		
		//make map, generate and spawn player initially
		generator = new MapGenerator(map);
		spawn();
		
		//player sprite
		playerS = assets.get("assets/data/PlayerSprite/Stand.png");
		//if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
		//	playerS = assets.get("assets/data/PlayerSprite/StandLeft.png");
		//}
		
		blankTile = new Tile("assets/data/spr_16x16Floor.png");
		blankTile.setPassable(true);
		
	}

	
	//spawns the player and the map(maybe enemies later) -F
	public void spawn(){
		//generate map, pass to renderer
		renderer.setMap(generator.generate());
		
		//spawn x, y
		int x = generator.getMapStartX() * 16;
		int y= generator.getMapStartY() * 16;
		
		player = new Entity(x,y, "player");
		
		//stair to next floor
		stairX = generator.getMapEndX();
		stairY = generator.getMapEndY();
	}
	
	public void steps(){
		
		//LEFT WALKING
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			
				playerS = assets.get("assets/data/PlayerSprite/StandLeft.png");
				
				if (0 < player.getX() %16 && player.getX() %16 <8) {					
					playerS = assets.get("assets/data/PlayerSprite/StepLeftRight.png");
				} else {
					playerS = assets.get("assets/data/PlayerSprite/StepLeftLeft.png");
				}
				
				
		//RIGHT WALKING
		} else if 
			 (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			
			playerS = assets.get("assets/data/PlayerSprite/StandRight.png");
			
			if (0 < player.getX() %16 && player.getX() %16 <8) {				
				playerS = assets.get("assets/data/PlayerSprite/StepRightRight.png");
			} else {
				playerS = assets.get("assets/data/PlayerSprite/StepRightLeft.png");
			}
			
		//UP WALKING	
			} else if 
			 (Gdx.input.isKeyPressed(Input.Keys.UP)){
				
				playerS = assets.get("assets/data/PlayerSprite/StandBack.png");		
				
				if (0 < player.getY() %16 && player.getY() %16 <8) {					
					playerS = assets.get("assets/data/PlayerSprite/StepBackRight.png");
				} else {
					playerS = assets.get("assets/data/PlayerSprite/StepBackLeft.png");
				}
				
		//DOWN WALKING		
		} else if 
		 (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			
			playerS = assets.get("assets/data/PlayerSprite/Stand.png");
			
			if (0 < player.getY() %16 && player.getY() %16 <8) {
				playerS = assets.get("assets/data/PlayerSprite/StepForwardRight.png");
			} else {
				playerS = assets.get("assets/data/PlayerSprite/StepForwardLeft.png");
			}
		} 
	}

	public void draw(float dt) {
		//bring coordinates for player bottom-lef, like in the map renderer
		float xOffset = renderParams.getXOffset() + xShift;
		float yOffset = renderParams.getYOffset() + yShift;
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		renderer.draw(xShift,yShift);
	    batch.draw(playerS, xOffset + player.getX(), yOffset  + player.getY());
	    batch.end();
		
		
		
	}

	private BattleEndListener generateBattleWatcher(final Vector2 monster) {	
		return new BattleEndListener() {
			private Vector2 mon = monster;
			@Override
			public void onBattleEnd(BattleEndCondition cond){
				switch(cond) {
				case LOSE:
					game.setScreen(new GameOverScreen(game));
					break;
				default: // WIN and FLEE
					map.setTile((int)mon.x, (int)mon.y, blankTile);
					map.removeMonster(mon);
					break;
				}
			}
		};
	}
	
	//NOTE: I am not offseting player.getX(), y itself in order to make the processing of the array less of a hassle -F
	
	public void update(float dt) {
		//------------------------------------------------------
		/* ####################################################### *
		 * DRAW PLAYER, put this as method in external class later * <-- and then run in the render method instead of create (create only runs ONCE, at game start)
		 * ####################################################### */
		
		//PLAYER MOVEMENT USING ARROW KEYS	
		// check if player is at stairs x,y
		if((player.getX()+8)/16 == stairX && (player.getY()+8)/16 == stairY){
			spawn();
		}
		
		//testing generation
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)){
			spawn();
		}
		
		//collision with monster
		ArrayList<Vector2> monsters = map.getMonsterList();
		for (Vector2 monster : monsters)
		{
			if ((player.getX()+8)/16 == monster.x && (player.getY()+8)/16 == monster.y)
			//if ((player.getX())/16 == monster.x && (player.getY())/16 == monster.y)
				{
					//collision
					System.out.println("COLLIIIIISION with monster !!! QEWTQWETIPOUQETOPIUY!OH#@N$FGV&Y#!!#()*%");
					System.out.println("player.getX()/16 = "+(player.getX()+8)/16+", player.getY()/16 = "+(player.getY()+8)/16+" || monster.x = "+ monster.x + ", monster.y = "+monster.y);
					
					CombatEntity mon = new CombatEntity(100, 100, new Stats(), "Monster");
					
					Battle battle = new Battle(player.getCombatEntity(), mon);
					
					battle.addBattleEndListener(this.generateBattleWatcher(monster));
					
					game.setScreen(new CombatScreen(game, battle, player.getCombatEntity(), mon, this));
					
					AngryAudio.shoot();
				}
		}
		
		
		//collision with item
		ArrayList<Vector2> items = map.getItemList();
		for (Vector2 item : items)
		{
			if ((player.getX()+8)/16 == item.x && (player.getY()+8)/16 == item.y)
			//if ((player.getX())/16 == item.x && (player.getY())/16 == item.y)	
				{
					//collision
					System.out.println("COLLIIIIISION with item !!! QEWTQWETIPOUQETOPIUY!OH#@N$FGV&Y#!!#()*%");
					System.out.println("player.getX()/16 = "+(player.getX()+8)/16+", player.getY()/16 = "+(player.getY()+8)/16+" || monster.x = "+ item.x + ", monster.y = "+item.y); 
				}
		}
		
		
		//moving left
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) 
			
		{
			steps();
			if (player.getX() + xShift > xShift)
			{
				player.setX(player.getX() - 1); 	//consider using delta and f values -F
				System.out.println("player.getX(): "+player.getX() + "_" + player.getX()/16 + "_" + player.getY()/16);
				//this kind of detection avoids exceptions at boundaries with no handling
				if(!map.getTile(player.getX()/16, player.getY()/16).isPassable() || !map.getTile(player.getX()/16, (player.getY()+16)/16).isPassable()){ //check both corners
					player.setX(player.getX() + 1);  
				}
			}
		} //take away the println parts when working.
			
		//moving right
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) 
		{
			steps();
			if (player.getX() + 16 + xShift < xShift + map_pixelwidth) //there is a +16 adjustment to playerX since detection point is bottom left
			{
				player.setX(player.getX() + 1);  
				System.out.println("player.getX(): "+player.getX()+ "_" + player.getX()/16 + "_" + player.getY()/16 );
				if(!map.getTile((player.getX()+16)/16, player.getY()/16).isPassable() || !map.getTile((player.getX()+16)/16, (player.getY()+16)/16).isPassable()){
					player.setX(player.getX() - 1); 
				}
			}
		}
			
		//moving up
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) 
		{			
			steps();
			
			if (player.getY() + 16 + yShift < yShift + map_pixelheight)
			{
				player.setY(player.getY() + 1); 
				System.out.println("player.getY(): "+player.getY()+ "_" + player.getX()/16 + "_" + player.getY()/16);
				if(!map.getTile(player.getX()/16, (player.getY()+16)/16).isPassable() || !map.getTile((player.getX()+16)/16, (player.getY()+16)/16).isPassable()){
					player.setY(player.getY() - 1); 
				}
			}
				
		}
			
		//moving down
		else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) 
		{
			steps();

			if (player.getY() + yShift> yShift)
			{
				player.setY(player.getY() - 1); 
				System.out.println("player.getY(): "+player.getY()+ "_" + player.getX()/16 + "_" + player.getY()/16 );
				if(!map.getTile(player.getX()/16, player.getY()/16).isPassable() || !map.getTile((player.getX()+16)/16, player.getY()/16).isPassable()){
					player.setY(player.getY() + 1); 
				}
			}
		}

	}
		//IMPORTANT -> margin_bottom and margin_left must come from the map-drawing code above.
		//do not make separate variables with the same definition, important when putting these blocks in external classes.
		//ie. load from the same .json file as from the "DRAW MAP" block above
		//------------------------------------------------------


	@Override
	public void render(float delta) {
		this.update(delta);
		this.draw(delta);
		
	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
	
}
