import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Map {
	
	protected SpriteSheet ss;
	protected GameHandler handler;
	protected Type[][] tiles;
	protected boolean[][] solids;
	
	protected int width;
	protected int height;
	
	protected int playerStartPosX = 0;
	protected int playerStartPosY = 0;


	public Map(GameHandler handler) {
		this.handler = handler;
	}
	
	public void create(BufferedImage mapImage, SpriteSheet ss) {
		this.ss = ss;
		System.out.println("loaded " + ss);
		loadTiles(mapImage);
	}
	
	public Type getTile(int x, int y) {
		if(x < 0 || x >= width || y < 0 || y >= height)
			return Type.None;
		return tiles[x][y];
	}
	
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
	
	private void loadTiles(BufferedImage image) {
		width = image.getWidth();
		height = image.getHeight();
		
		tiles = new Type[width][height];
		solids = new boolean[width][height];

		for(int w = 0; w < width; w++) {
			for(int h = 0; h < height; h++) {
				int pixel = image.getRGB(w, h);
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
					handler.addGameObject(new Enemy(x, y, "regular enemy", ss));
				}
			}
		}
	}
	
}
