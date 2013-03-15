package inf1.oop.turnbased.map;

// A map square
public class Tile {
	private boolean hasTexture;
	private String textureName;
	private boolean passable;
	
	public void setPassable(boolean p){
		passable = p;
	}
	
	public boolean isPassable(){
		return passable;
	}
	
	public String getTextureName() {
		return textureName;
	}
	
	public boolean hasTexture() {
		return hasTexture;
	}
	
	public Tile(String tex) {
		textureName = tex;
		hasTexture = true;
	}
	
	public void setTextureName(String t){
		this.textureName = t;
	}
	
	public Tile() {
		hasTexture = false;
	}
}
