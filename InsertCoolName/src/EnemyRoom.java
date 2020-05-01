
public class EnemyRoom extends Map {

	public EnemyRoom(GameHandler handler) {
		super(handler);
		create(Assets.getBufferedImage("enemyRoomTest"), null);
		setPlayerStartPos();
	}

	public void populateGameObjects(Player p) {
		super.populateGameObjects(p);
	}

}
