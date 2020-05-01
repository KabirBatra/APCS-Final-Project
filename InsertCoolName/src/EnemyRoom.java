
public class EnemyRoom extends Map {

	public EnemyRoom(GameHandler handler, SpriteSheet ss) {
		super(handler);
		create(Assets.getBufferedImage("enemyRoomTest"), ss);
		setPlayerStartPos();
	}

	public void populateGameObjects(Player p) {
		super.populateGameObjects(p);
	}

}
