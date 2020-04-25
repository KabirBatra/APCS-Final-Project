import java.awt.image.BufferedImage;

public class Room1 extends Map {

	public Room1(GameHandler handler) {
		super(handler);
		create(Assets.getBufferedImage("bigTestRoom"), null);
	}

	public void populateGameObjects(Player p) {
		if(p != null) {
			p.setPos(25, 20);
			handler.addGameObject(p);

		} else {
			handler.addGameObject(new Player(25, 20, "player", handler));
		}
	}
	
	

}
