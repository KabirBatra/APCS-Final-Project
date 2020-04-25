import java.awt.image.BufferedImage;

public class Room2 extends Map {

	public Room2(GameHandler handler) {
		super(handler);
		create(Assets.getBufferedImage("littleTestRoom"), null);
	}

	public void populateGameObjects(Player p) {
		if(p != null) {
			p.setPos(1, 1);
			handler.addGameObject(p);

		} else {
			handler.addGameObject(new Player(1, 1, "player", handler));
		}
	}
	
	

}
