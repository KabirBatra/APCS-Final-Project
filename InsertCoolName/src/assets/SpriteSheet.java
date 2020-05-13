package assets;
import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	BufferedImage sheet;

	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	public BufferedImage getSprite(int row, int col) {
		//hard coded for testing purposes
		// should be a variable that is initialized in the constructor: number of columns ... to determine width and height of sprite
		// sheet.getWidth();
		return sheet.getSubimage(row*32, col*32, 32, 32);
	}
}
