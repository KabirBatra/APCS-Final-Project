import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Map {
	
	private String name;
	private SpriteSheet ss;
	private int[][] tiles;
	
	private int width;
	private int height;

	public Map(String name, BufferedImage mapImage, SpriteSheet ss) {
		this.name = name;
		this.ss = ss;
		//for testing purposes: 
		// should be String name = "forest";mapImage = Assets.getBufferedImage(name);
		mapImage = loadImage("room.png");
		loadTiles(mapImage);
		
		
	}
	
	public int getTile(int x, int y) {
		return tiles[x][y];
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
		for(int w = 0; w < width; w++) {
			for(int h = 0; h < height; h++) {
				int pixel = image.getRGB(w, h);
				//int alpha = (pixel >> 24) & 0xff;
			    int r = (pixel >> 16) & 0xff;
			    int g = (pixel >> 8) & 0xff;
			    int b = (pixel) & 0xff;
			    
			    //depending on the rgb values, add objects to the handler (if they resemble game objects
			    if(r == 255) {
			    	tiles[w][h] = 1;
			    }
			    else {
			    	tiles[w][h] = 0;
			    }
			    
			}
		}
	}
	
	// should be in assets class 
	private BufferedImage loadImage(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			System.out.println("image load unsuccessful");
			e.printStackTrace();
		}
		return image;
	}
}
