import java.awt.Rectangle;

import processing.core.PApplet;

public abstract class Bullet extends GameObject implements Movable {
	
	protected float velX;
	protected float velY;
	
	public Bullet(float x, float y, float angle, float speed, String name) {
		super(x, y, name);
		
		velX = (float)(speed * Math.cos(angle));
		velY = (float) (speed * Math.sin(angle));
		
		
	}

	public float getVelX() {
		return velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

}
