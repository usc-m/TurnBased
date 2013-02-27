package inf1.oop.turnbased.map;

/*
 * This class describes maps, containing information about what the terrain is like
 * things that appear on the map and operations that apply to maps, such as 
 */
public class Map {
	Tile[][] tileGrid;
	int width;
	int height;
	
	int tileWidth, tileHeight;
	
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
		
		if(x > width || y > height) {
			return false;
		}
		
		return true;
	}
	
	public Tile getTile(int x, int y) {
		if(!isValidCoordinates(x,y)) throw new IndexOutOfBoundsException();
		
		return tileGrid[x][y];
	}
}
