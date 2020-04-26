import java.awt.Rectangle;

import processing.core.PApplet;

public class RifleBullet extends Bullet {

	private float maxVel;

	public RifleBullet(float x, float y, float angle, float speed, float damage, float fireRate, String name) {
		super(x, y, angle, speed, damage, fireRate, name);
		maxVel = 50f;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act() {

		velX = (float) (speed * Math.cos(angle));
		velY = (float) (speed * Math.sin(angle));
		// System.out.println("hello!! " + velX + " " + velY);

		if (Math.abs(velX) + Math.abs(velY) > maxVel) { // works same way as pythagorean theorem
			float ratio = (float) Math.sqrt(2);
			velX /= ratio;
			velY /= ratio;
		}

	}

	@Override
	public void drawSelf(float x, float y, float tileWidth, float tileHeight, PApplet s) {
		s.fill(181, 166, 66);
		float x1 = x ;
		float y1 = y + tileHeight * (3f / 7f);
		s.rect(x1, y1, tileWidth / 4, tileHeight / 7);
		s.fill(184, 115, 51);
		s.triangle(x1 + tileWidth / 4, y1, x1 + tileWidth / 4, y1 + tileHeight / 7, x1 + tileWidth / 4 + tileWidth / 11,
				y1 + tileHeight / 14);

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
