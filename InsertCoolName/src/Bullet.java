import java.awt.Rectangle;

import processing.core.PApplet;

public class Bullet extends GameObject implements Movable {
	
	private float velX, velY;
	private static float maxVel = 5;

	public Bullet(float x, float y, float angle, String name) {
		super(x, y, name);
		velX = 1; 
		velY = 1;
		// TODO Auto-generated constructor stub
	}

	public float getVelX() {
		// TODO Auto-generated method stub
		return velX;
	}

	public float getVelY() {
		// TODO Auto-generated method stub
		return velY;
	}

	public void setVelX(float velX) {
		// TODO Auto-generated method stub
		this.velX = velX;
	}

	public void setVelY(float velY) {
		// TODO Auto-generated method stub
		this.velY = velY;
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawSelf(float x, float y, float tileWidth, float tileHeight, PApplet s) {
		s.fill(0, 255, 0);
		s.rect(x, y, 20, 20);
		
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
