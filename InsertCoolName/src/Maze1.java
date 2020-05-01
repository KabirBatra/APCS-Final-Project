
public class Maze1 extends Map {

	public Maze1(GameHandler handler) {
		super(handler);
		create(Assets.getBufferedImage("mazeTest"), null);
		setPlayerStartPos();
	}

	public void populateGameObjects(Player p) {
		super.populateGameObjects(p);
	}
	
	

}
