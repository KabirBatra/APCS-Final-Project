package assets.map;
import assets.Assets;
import assets.SpriteSheet;
import gameobject.Player;
import running.GameHandler;

public class Room2 extends Map {

	public Room2(GameHandler handler, SpriteSheet ss) {
		super(handler);
		create(Assets.getBufferedImage("littleTestRoom"), ss);
		setPlayerStartPos();
	}

	public void populateGameObjects(Player p) {
		super.populateGameObjects(p);
	}
	
	

}
