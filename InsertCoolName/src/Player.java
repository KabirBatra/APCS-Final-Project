import processing.core.PApplet;

public class Player extends Creature {

	private GameObjectHandler handler;
	public Player(float x, float y, String name, GameObjectHandler handler) {
		super(x, y, name);
		this.handler = handler;
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawSelf(float x, float y, float tileWidth, float tileHeight, PApplet s) {
		// TODO Auto-generated method stub
		s.fill(255, 0, 0);
		s.rect(x, y, tileWidth, tileHeight);
	}

	@Override
	public void onInteract(GameObject obj) {
		// TODO Auto-generated method stub
		
	}
}
