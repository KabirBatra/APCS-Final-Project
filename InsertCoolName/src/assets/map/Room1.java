package assets.map;
import assets.Assets;
import assets.SpriteSheet;
import gameobject.Player;
import running.GameHandler;

public class Room1 extends Map {

	public Room1(GameHandler handler, SpriteSheet ss) {
		super(handler);
		create(Assets.getBufferedImage("bigTestRoom"), ss);
		setPlayerStartPos();
	}

	public void populateGameObjects(Player p) {
		super.populateGameObjects(p);
	}
	
	

}
