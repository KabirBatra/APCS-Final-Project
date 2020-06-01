package assets;
import java.awt.image.BufferedImage;

/*
 * Represents a sheet of tiled images with dimensions 32 by 32 pixels each
 * that are used by a map to draw things.
 * @author Kabir Batra
 */
public class SpriteSheet {
	
	BufferedImage sheet;
	
	/*
	 * @param sheet Represents the SpriteSheet 
	 */
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	/*
	 * returns the image at a certain row and column of the SpriteSheet
	 * @param col The column of the sprite
	 * @param row The row of the sprite
	 */
	public BufferedImage getSprite(int col, int row) {
		//hard coded for testing purposes
		// should be a variable that is initialized in the constructor: number of columns ... to determine width and height of sprite
		// sheet.getWidth();
		return sheet.getSubimage(col*32, row*32, 32, 32);
	}
}
