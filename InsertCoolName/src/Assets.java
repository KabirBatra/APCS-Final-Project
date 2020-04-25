import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Assets {
	
	public static HashMap<String, BufferedImage> dict;

	// should only run once
	public Assets() {
		dict = new HashMap<String, BufferedImage>();
		dict.put("bigTestRoom", loadImage("bigroom.png"));
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
}
