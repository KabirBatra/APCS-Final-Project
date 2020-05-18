package assets.map;
import assets.Assets;
import assets.SpriteSheet;
import gameobject.Player;
import running.GameHandler;

public class Level1 extends Map {

	public Level1(GameHandler handler, SpriteSheet ss) {
		super(handler);
		create(Assets.getBufferedImage("Level1"), ss);
		setPlayerStartPos();
	}

	public void populateGameObjects(Player p) {
		super.populateGameObjects(p);
	}
	
	

}
