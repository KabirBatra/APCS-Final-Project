import java.awt.Rectangle;

import processing.core.PApplet;

public class RifleBullet extends Bullet {
	
	protected static float speed = 7;
	protected static float damage = 10;
	
	
	public RifleBullet(float x, float y, float angle, String name) {
		super(x, y, angle, name);
		
	}

	public void act() {

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
