import java.awt.Rectangle;

import processing.core.PApplet;

public class Player extends Creature {

	
	private GameHandler handler;
	
	// for collisions
	private float newPosX, newPosY;
	
	
	public Player(float x, float y, String name, GameHandler handler) {
		super(x, y, name);
		this.handler = handler;
		maxVel = 10f;
	}

	@Override
	public void act() {
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

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
}
