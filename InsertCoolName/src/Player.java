

import processing.core.PApplet;

public class Player extends Creature {

	static int a = 0;
	
	private GameHandler handler;
		
	public Player(float x, float y, String name, GameHandler handler) {
		super(x, y, name);
		this.handler = handler;
		maxVel = 10f;
	}

	public void act(float ellapsedTime) {
		
		// movement
		velX = 0;
		velY = 0;
		if(handler.getUp()) {
			velY += -maxVel;
		}
		if(handler.getDown()) {
			velY += maxVel;
		}
		if(handler.getLeft()) {
			velX += -maxVel;
		}
		if(handler.getRight()) {
			velX += maxVel;
		}
		
		
		// diagonal speed is still maxVel
		if(Math.abs(velX) + Math.abs(velY) > maxVel) { //works same way as pythagorean theorem
			float ratio = (float)Math.sqrt(2);
			velX /= ratio;
			velY /= ratio;
		}
	}
	
	public void shoot(PApplet s) {
		float angle = (float)(a * Math.PI / 180); //temporary calculation
		handler.addGameObject(new NormalBullet(handler.getPlayer().getPosX()+0.5f, handler.getPlayer().getPosY()+0.5f, angle, "308"));
		a += 30;
	}

	public void drawSelf(float x, float y, float tileWidth, float tileHeight, PApplet s) {
		// TODO Auto-generated method stub
		s.fill(255, 0, 0);
		s.rect(x, y, tileWidth, tileHeight);
	}

	public void onInteract(GameObject obj) {
		// TODO Auto-generated method stub
		
	}
}
