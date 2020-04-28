import java.awt.Rectangle;

import processing.core.PApplet;

public class NormalBullet extends Bullet {
	
	protected static float speed = 10f;
	protected static float damage = 10;
	
	
	public NormalBullet(float x, float y, float angle, String name) {
		super(x, y, angle, speed, name);
	
	}

	public void act() {

	}

	@Override
	public void drawSelf(float x, float y, float tileWidth, float tileHeight, PApplet s) {
		s.fill(181, 166, 66);
//		float x1 = x ;
//		float y1 = y + tileHeight * (3f / 7f);
//		s.rect(x1, y1, tileWidth / 4, tileHeight / 7);
		s.fill(184, 115, 51);
//		s.triangle(x1 + tileWidth / 4, y1, x1 + tileWidth / 4, y1 + tileHeight / 7, x1 + tileWidth / 4 + tileWidth / 11,
//				y1 + tileHeight / 14);
		
		
		s.rect(x, y, tileWidth/4, tileHeight/7);
		s.pushStyle();
		s.noFill();
		s.stroke(0, 255, 255);
		s.rect(x, y, tileWidth/4, tileHeight/7);
		s.popStyle();

	}

	@Override
	public void onInteract(GameObject obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Rectangle getBounds() {
		//constructor only allows integers... so now 1000 represents 3 more decimal places
		return new Rectangle((int)(posX*1000), (int)(posY*1000), (int)(1f/4 * 1000), (int)(1f/7 * 1000));
	}

}
