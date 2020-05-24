package assets.map;
import assets.Assets;
import assets.SpriteSheet;
import gameobject.Player;
import running.GameHandler;
/**
 * 
 * This creates the room for the game!
 * @author Kabir & Kaie
 *
 */
public class EnemyRoom extends Map {

	/**
	 * This generates the map with the given sprite
	 * and it adds the different creatures that needed to be added
	 * to the handler
	 * 
	 * 
	 * @param handler This is all the in-game object list
	 * @param ss This is the sprite sheet to be taken in
	 */
	public EnemyRoom(GameHandler handler, SpriteSheet ss) {
		super(handler);
		create(Assets.getBufferedImage("enemyRoomTest"), ss);
		setPlayerStartPos();
		currentWave = 1;
		numberOfWaves = 3;
	}

	public void populateGameObjects(Player p) {
		super.populateGameObjects(p);
	}

}
