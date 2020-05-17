package assets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import assets.map.EnemyRoom;
import assets.map.Map;
import assets.map.Maze1;
import assets.map.Room1;
import assets.map.Room2;
import running.GameHandler;
/**
 * This singleton class contains all of the assets 
 * of the project including images, SpriteSheets, and maps
 * @author Kabir Batra
 *
 */
public class Assets {
	
	GameHandler handler;
	public static HashMap<String, BufferedImage> images;
	public static HashMap<String, Map> maps;
	
	public static HashMap<String, SpriteSheet> spriteSheets;


	/**
	 * Initializes all of the assets by grabbing them from the assets folder
	 * @param handler is used for creating new maps
	 */
	public Assets(GameHandler handler) {
		images = new HashMap<String, BufferedImage>();
		maps = new HashMap<String, Map>();
		spriteSheets = new HashMap<String, SpriteSheet>();
		
		
		
		
		
		// images
		images.put("bigTestRoom", loadImage("bigroom.png"));
		images.put("littleTestRoom", loadImage("room.png"));
		images.put("mazeTest", loadImage("mediumMaze.png"));
		images.put("enemyRoomTest", loadImage("enemyRoomTest.png"));
		images.put("player", loadImage("testSpriteSheetUpdate.png"));
		images.put("slime", loadImage("slimeSheet.png"));

		
	//	images.put("player", loadImage("testSpriteSheetUpdate.png"));
		images.put("player", loadImage("testSpriteSheet.png"));
	
		spriteSheets.put("playerSheet", new SpriteSheet(images.get("player")));
		
		// maps
		//maps.put("testRoom", new Map(getBufferedImage("bigTestRoom"), new SpriteSheet("testRoomSpriteSheet"), handler));
		maps.put("testRoom", new Room1(handler, spriteSheets.get("playerSheet")));
		maps.put("testRoom2", new Room2(handler, spriteSheets.get("playerSheet")));
		maps.put("testRoom3", new Maze1(handler, spriteSheets.get("playerSheet")));
		maps.put("testRoom4", new EnemyRoom(handler, spriteSheets.get("playerSheet")));
		
		
	}
	
	private BufferedImage loadImage(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getClassLoader().getResource(path));
		} catch (IOException e) {
			System.out.println("image load unsuccessful");
			e.printStackTrace();
		}
		return image;
	}
	
	/**
	 * @param name A string representing the name of an image defined in the Assets constructor
	 * @return BufferedImage The image with that name; returns null if the image does not exist
	 */
	public static BufferedImage getBufferedImage(String name) {
		return images.get(name);
	}
	/**
	 * @param name A string representing the name of a map defined in the Assets constructor
	 * @return Map The map with that name; returns null if the map does not exist
	 */
	public static Map getMap(String name) {
		return maps.get(name);
	}
	/**
	 * @param name A string representing the name of a SpriteSheet defined in the Assets constructor
	 * @return SpriteSheet The SpriteSheet with that name; returns null if the SpriteSheet does not exist
	 */
	public static SpriteSheet getSpriteSheet(String name) {
		return spriteSheets.get(name);
	}
}