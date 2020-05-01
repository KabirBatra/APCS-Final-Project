import java.awt.image.BufferedImage;

public class Room1 extends Map {

	public Room1(GameHandler handler) {
		super(handler);
		create(Assets.getBufferedImage("bigTestRoom"), null);
		setPlayerStartPos();
	}

	public void populateGameObjects(Player p) {
		super.populateGameObjects(p);
	}
	
	

}
