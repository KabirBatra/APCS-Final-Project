import java.awt.image.BufferedImage;

public class Room2 extends Map {

	public Room2(GameHandler handler) {
		super(handler);
		create(Assets.getBufferedImage("littleTestRoom"), null);
		setPlayerStartPos();
	}

	public void populateGameObjects(Player p) {
		super.populateGameObjects(p);
	}
	
	

}
