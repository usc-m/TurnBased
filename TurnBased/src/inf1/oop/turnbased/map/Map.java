package inf1.oop.turnbased.map;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

/*
 * This class describes maps, containing information about what the terrain is like
 * things that appear on the map and operations that apply to maps, such as 
 */
public class Map {
    //test comment, will work on this -F
	Tile[][] tileGrid;
	int width;
	int height;
	
	int tileWidth, tileHeight;
	
	private ArrayList<Vector2> monsters = new ArrayList<Vector2>();
	private ArrayList<Vector2> items = new ArrayList<Vector2>();
	
	public Map(int width, int height, int tWidth, int tHeight) {
		this.width = width;
		this.height = height;
		
		this.tileWidth = tWidth;
		this.tileHeight = tHeight;
		
		tileGrid = new Tile[width][height];
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public int getTileWidth() { return tileWidth; }
	public int getTileHeight() { return tileHeight; }
	
	public void setTile(int x, int y, Tile newTile) {
		if(!isValidCoordinates(x,y)) throw new IndexOutOfBoundsException();
		
		tileGrid[x][y] = newTile;
	}
	
	private boolean isValidCoordinates(int x, int y) {
		if(x < 0 || y < 0) {
			return false;
		}
		
		if(x > width-1 || y > height-1) { //-1 because array index starts from 0
			return false;
		}
		
		else{
			return true;
		}
	}
	
	public Tile getTile(int x, int y) {
		//if(!isValidCoordinates(x,y)) throw new IndexOutOfBoundsException();
		if(!isValidCoordinates(x,y)){
			//remove later -F
			System.out.println("Invalid");
			return tileGrid[0][0];   //will add handling in check later -F
		}
		else{
			return tileGrid[x][y];
		}
	}
	
	public void setMonsterList(Vector2 monster)
	{
		this.monsters.add(monster);
	}
	
	public ArrayList<Vector2> getMonsterList()
	{
		return monsters;
	}
	
	public void setItemList(Vector2 item)
	{
		this.monsters.add(item);
	}
	
	public ArrayList<Vector2> getItemList()
	{
		return items;
	}
	
	public void clearMonsterList() {
		monsters.clear();
	}
	
	public void clearItemList() {
		items.clear();
	}
	
	public void removeMonster(Vector2 mon) {
		this.monsters.remove(mon);
	}
	
}
