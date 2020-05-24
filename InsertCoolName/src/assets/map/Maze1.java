package assets.map;
import assets.Assets;
import assets.SpriteSheet;
import gameobject.Player;
import running.GameHandler;
/**
 * This is the maze map for the game!
 * @author kaiechen
 *
 */
public class Maze1 extends Map {
	/**
	 *  This generates the map with the given sprite
	 * and it adds the different creatures that needed to be added
	 * to the handler
	 * 
	 * @param handler The list of objects in the game
	 * @param ss The sprite image associated with this room
	 */
	public Maze1(GameHandler handler, SpriteSheet ss) {
		super(handler);
		create(Assets.getBufferedImage("mazeTest"), ss);
		setPlayerStartPos();
	}
/**
 * This adds the player object into the game at the specified place in the map!
 */
	public void populateGameObjects(Player p) {
		super.populateGameObjects(p);
	}
	
	

}
