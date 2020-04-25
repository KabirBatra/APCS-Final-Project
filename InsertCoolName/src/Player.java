import processing.core.PApplet;

public class Player extends Creature {

	private GameObjectHandler handler;
	public Player(float x, float y, String name, GameObjectHandler handler) {
		super(x, y, name);
		this.handler = handler;
		maxVel = 0.3f;
	}

	@Override
	public void act() {
		velX = 0;
		velY = 0;
		if(handler.getUp()) {
			velY = -0.3f;
		}
		if(handler.getDown()) {
			velY = 0.3f;
		}
		if(handler.getLeft()) {
			velX = -0.3f;
		}
		if(handler.getRight()) {
			velX = 0.3f;
		}
		
		if(Math.abs(velX) + Math.abs(velY) > maxVel) { //pretend that is pythagorean theorem
			float ratio = (float)Math.sqrt(2);
			velX /= ratio;
			velY /= ratio;
		}
		
		super.act();
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
