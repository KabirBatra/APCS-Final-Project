import java.awt.Rectangle;

import processing.core.PApplet;

public class Bullet extends GameObject implements Movable {
	
	protected float velX;
	protected float velY;
	
	protected static float speed = 5f;
	protected static float damage = 5f;
	
	//protected float fireRate;  part of player/weapon class

	public Bullet(float x, float y, float angle, String name) {
		super(x, y, name);
				
		velX = speed * (float)Math.cos(angle);
		velY = (float) (speed * Math.sin(angle));
		System.out.println(Math.cos(angle));
		
		
		
		//this.damage = damage;
		//this.fireRate = fireRate;
	}

	public void act() {
		
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

	public void drawSelf(float x, float y, float tileWidth, float tileHeight, PApplet s) {
		
	}

	public void onInteract(GameObject obj) {
		
	}

	public Rectangle getBounds() {
		return null;
	}

}
