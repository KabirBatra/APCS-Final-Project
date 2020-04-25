import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Assets {
	
	GameHandler handler;
	public static HashMap<String, BufferedImage> dict;
	public static HashMap<String, Map> maps;

	// should only run once
	public Assets(GameHandler handler) {
		dict = new HashMap<String, BufferedImage>();
		maps = new HashMap<String, Map>();

		
		// images
		dict.put("bigTestRoom", loadImage("bigroom.png"));
		dict.put("littleTestRoom", loadImage("room.png"));
		
		
		
		// maps
		//maps.put("testRoom", new Map(getBufferedImage("bigTestRoom"), new SpriteSheet("testRoomSpriteSheet"), handler));
		maps.put("testRoom", new Room1(handler));
		maps.put("testRoom2", new Room2(handler));
	}
	
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
	
	public static BufferedImage getBufferedImage(String name) {
		return dict.get(name);
	}
	
	public static Map getMap(String name) {
		return maps.get(name);
	}
}
