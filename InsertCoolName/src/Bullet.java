import java.awt.Rectangle;

import processing.core.PApplet;

public abstract class Bullet extends GameObject {
	protected float velX, velY;
	protected float locx;
	protected float locy;
	protected float angle;
	protected float speed;
	protected float damage;
	protected float fireRate;

	public Bullet(float x, float y, float angle, float speed, float damage, float fireRate, String name) {
		super(x, y, name);
		locx = x;
		locy = y;
		this.angle = angle;
		this.speed = speed;
		this.damage = damage;
		this.fireRate = fireRate;
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract void act();

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
