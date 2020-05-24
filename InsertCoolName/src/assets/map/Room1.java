package assets.map;
import assets.Assets;
import assets.SpriteSheet;
import gameobject.Player;
import running.GameHandler;


/**
 * This class represents one of the first rooms we have created!
 * @author Kabir & kaiechen
 *
 */
public class Room1 extends Map {

	/**
	 * This is represents the room to which the game will be set to
	 * @param handler The list of in-game objects to be added!
	 * @param ss The 
	 */
	public Room1(GameHandler handler, SpriteSheet ss) {
		super(handler);
		create(Assets.getBufferedImage("bigTestRoom"), ss);
		setPlayerStartPos();
	}
/**
 * This adds the player to the map at the specified place!
 */
	public void populateGameObjects(Player p) {
		super.populateGameObjects(p);
	}
	
	

}
