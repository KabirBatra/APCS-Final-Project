
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
