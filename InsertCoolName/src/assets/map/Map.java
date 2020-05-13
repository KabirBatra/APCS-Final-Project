package assets.map;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import assets.SpriteSheet;
import gameobject.Enemy;
import gameobject.Player;
import gameobject.Type;
import running.GameHandler;

/*
 * A class to represent a map in the game.
 * A map is generated using a Buffered image from the Assets class 
 * and a SpriteSheet to define the images for all the tiles. 
 * @author Kabir Batra
 */
public abstract class Map {
	
	protected SpriteSheet ss;
	protected GameHandler handler;
	protected Type[][] tiles;
	protected boolean[][] solids;
	
	protected int width;
	protected int height;
	
	protected int playerStartPosX = 0;
	protected int playerStartPosY = 0;

	/*
	 * Stores a reference to the GameHandler
	 * @param handler is stored for use in other methods
	 */
	public Map(GameHandler handler) {
		this.handler = handler;
	}
	
	/*
	 * Loads the tiles for the instance of this class's map and saves the SpriteSheet.
	 * to be used when drawing the tiles
	 * @param mapImage is the Buffered image used to create the map
	 * @param ss is the SpriteSheet of the instance of this class
	 */
	public void create(BufferedImage mapImage, SpriteSheet ss) {
		this.ss = ss;
		loadTiles(mapImage);
	}
	
	/*
	 * returns the tile at position x, y
	 * @param x the X position of the tile
	 * @param y the Y position of the tile
	 * @return a Type of tile 
	 */
	public Type getTile(int x, int y) {
		if(x < 0 || x >= width || y < 0 || y >= height)
			return Type.None;
		return tiles[x][y];
	}
	
	/*
	 * @param x the X position of the tile
	 * @param y the Y position of the tile
	 * @return whether or not the tile at x, y is solid or not
	 */
	public boolean isSolidTile(int x, int y) {
		if(x < 0 || x >= width || y < 0 || y >= height)
			return true; //outside of the map is solid
		return solids[x][y];
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public SpriteSheet getSpriteSheet() {
		return ss;
	}
	
	/*
	 * Uses rgb values of mapImage to determine what type of tile
	 * each pixel is. Types are stored in a 2-D array
	 * @param mapImage The Buffered Image that represents the map
	 */
	private void loadTiles(BufferedImage mapImage) {
		width = mapImage.getWidth();
		height = mapImage.getHeight();
		
		tiles = new Type[width][height];
		solids = new boolean[width][height];

		for(int w = 0; w < width; w++) {
			for(int h = 0; h < height; h++) {
				int pixel = mapImage.getRGB(w, h);
				//int alpha = (pixel >> 24) & 0xff;
			    int r = (pixel >> 16) & 0xff;
			    int g = (pixel >> 8) & 0xff;
			    int b = (pixel) & 0xff;
			    
			    //depending on the rgb values
			    
			    if(r == 255) { // walls
			    	// here would be index of thing in sprite sheet
			    	tiles[w][h] = Type.Wall;
			    	solids[w][h] = true;
			    }
			    else if(g == 255) {
			    	tiles[w][h] = Type.Enemy;
			    }
			    else if(b == 255) {
			    	tiles[w][h] = Type.Player;
			    }
			    else { // floor
			    	tiles[w][h] = Type.Floor;
			    	//solids[w][h] = false;
			    }
			}
		}
	}
	
	/*
	 * Searches for the tile that represents the player to 
	 * determine where the player should be when this map is
	 * populated.
	 */
	public void setPlayerStartPos() {
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				if(tiles[x][y] == Type.Player) {
					playerStartPosX = x;
					playerStartPosY = y;
				}
			}
		}
	}
	
	/*
	 * Populates all of the GameObjects referenced in the tiles array
	 * and uses the original Player object if it already exists.
	 * @param p the Player object currently in the game; null if there is not player yet
	 */
	public void populateGameObjects(Player p) {
		if(p != null) {
			p.setPos(playerStartPosX, playerStartPosY);
			p.setSpriteSheet(ss);
			handler.addGameObject(p);

		} else {
			handler.addGameObject(new Player(playerStartPosX, playerStartPosY, "player", ss, handler));
		}
		
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				if(tiles[x][y] == Type.Enemy) {
					handler.addGameObject(new Enemy(x, y, "regular enemy", handler, ss));
				}
			}
		}
	}
	
}
