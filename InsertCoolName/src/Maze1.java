import java.awt.image.BufferedImage;

public class Maze1 extends Map {
	
	private static int playerStartPosX = 0;
	private static int playerStartPosY = 4;


	public Maze1(GameHandler handler) {
		super(handler);
		create(Assets.getBufferedImage("mazeTest"), null);
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				if(tiles[x][y] == Type.Player) {
					playerStartPosX = x;
					playerStartPosY = y;
				}
				
			}
			
		}
	}

	public void populateGameObjects(Player p) {
		if(p != null) {
			p.setPos(playerStartPosX, playerStartPosY);
			handler.addGameObject(p);

		} else {
			handler.addGameObject(new Player(playerStartPosX, playerStartPosY, "player", handler));
		}
	}
	
	

}
