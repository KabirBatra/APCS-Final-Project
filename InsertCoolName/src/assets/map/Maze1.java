package assets.map;
import assets.Assets;
import assets.SpriteSheet;
import gameobject.Player;
import running.GameHandler;

public class Maze1 extends Map {

	public Maze1(GameHandler handler, SpriteSheet ss) {
		super(handler);
		create(Assets.getBufferedImage("mazeTest"), ss);
		setPlayerStartPos();
	}

	public void populateGameObjects(Player p) {
		super.populateGameObjects(p);
	}
	
	

}
