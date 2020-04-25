import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Map {
	
	private String name;
	private SpriteSheet ss;
	private GameObjectHandler handler;
	private int[][] tiles;
	private boolean[][] solids;
	
	private int width;
	private int height;

	public Map(String name, BufferedImage mapImage, SpriteSheet ss, GameObjectHandler handler) {
		this.name = name;
		this.ss = ss;
		this.handler = handler;
		//for testing purposes: 
		// should be String name = "forest";mapImage = Assets.getBufferedImage(name);
		mapImage = Assets.getBufferedImage("bigTestRoom");
		loadTiles(mapImage);
		
		
	}
	
	public int getTile(int x, int y) {
		if(x < 0 || x >= width || y < 0 || y >= height)
			return -1;
		return tiles[x][y];
	}
	
	public boolean isSolidTile(int x, int y) {
		if(x < 0 || x >= width || y < 0 || y >= height)
			return true; //outside of the map is sold
		return solids[x][y];
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private void loadTiles(BufferedImage image) {
		width = image.getWidth();
		height = image.getHeight();
		tiles = new int[width][height];
		solids = new boolean[width][height];
		for(int w = 0; w < width; w++) {
			for(int h = 0; h < height; h++) {
				int pixel = image.getRGB(w, h);
				//int alpha = (pixel >> 24) & 0xff;
			    int r = (pixel >> 16) & 0xff;
			    int g = (pixel >> 8) & 0xff;
			    int b = (pixel) & 0xff;
			    
			    //depending on the rgb values, add objects to the handler (if they resemble game objects
			    if(b == 255) {
			    	handler.addGameObject(new Player(w, h, "player", handler));
			    }
			    else if(r == 255) {
			    	tiles[w][h] = 1;
			    	solids[w][h] = true;
			    }
			    else {
			    	tiles[w][h] = 0;
			    	//solids[w][h] = false;
			    }
			    
			}
		}
	}
	
	
}
