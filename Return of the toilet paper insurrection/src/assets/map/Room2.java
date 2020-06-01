package assets.map;
import assets.Assets;
import assets.SpriteSheet;
import gameobject.Player;
import running.GameHandler;

/**
 * 
 * This class represents the other testing room we have had!
 * @author kaiechen
 *
 */
public class Room2 extends Map {
/**
 *  This generates the map with the given sprite
	 * and it adds the different creatures that needed to be added
	 * to the handler
 * @param handler the list that holds all the in-game objects!
 * @param ss The sprite sheet affiliated with this room!
 */
	public Room2(GameHandler handler, SpriteSheet ss) {
		super(handler);
		create(Assets.getBufferedImage("littleTestRoom"), ss);
		setPlayerStartPos();
	}

	public void populateGameObjects(Player p) {
		super.populateGameObjects(p);
	}
	
	

}
