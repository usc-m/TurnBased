package inf1.oop.turnbased.graphics;

import inf1.oop.turnbased.map.Map;
import inf1.oop.turnbased.map.Tile;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MapRenderer {
	// TODO: Refactor this so services like this are centrally managed instead of being passed one by one in constructor
	AssetManager assets;
	
	Map currentMap;
	
	public MapRenderer(AssetManager asst) {
		assets = asst;
	}
	
	public void setMap(Map newMap) { currentMap = newMap; }
	
	public void draw(SpriteBatch batch, float xOffset, float yOffset) {
		if(currentMap == null) throw new NullPointerException();
		
		int w = currentMap.getTileWidth();
		int h = currentMap.getTileHeight();
		
		for(int x = 0; x < currentMap.getWidth(); x++) {
			for(int y = 0; y < currentMap.getHeight(); y++) {
				Tile t = currentMap.getTile(x, y);
				
				if(t != null && t.hasTexture()) {
					batch.draw((Texture)assets.get(t.getTextureName()), xOffset + (x*w), yOffset + (y*h));
				}
			}
		}
	}
}
