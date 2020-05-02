import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import processing.core.PApplet;

public class NormalBullet extends Bullet {
	
	protected static float speed = 10f;
	protected static float damage = 10;
	
	
	public NormalBullet(float x, float y, float angle, String name) {
		super(x, y, angle, speed, name);
	
	}

	public void update(float ellapsedTime) {

	}

	public void drawSelf(float x, float y, int tileWidth, int tileHeight, PApplet s) {
		s.fill(181, 166, 66);
//		float x1 = x ;
//		float y1 = y + tileHeight * (3f / 7f);
//		s.rect(x1, y1, tileWidth / 4, tileHeight / 7);
		s.fill(184, 115, 51);
//		s.triangle(x1 + tileWidth / 4, y1, x1 + tileWidth / 4, y1 + tileHeight / 7, x1 + tileWidth / 4 + tileWidth / 11,
//				y1 + tileHeight / 14);
		
		
		s.rect(x, y, tileWidth/4f, tileHeight/4f);
		s.pushStyle();
//		s.noFill();
//		s.stroke(0, 255, 255);
//		s.rect(x, y, tileWidth/4, tileHeight/7);
		s.popStyle();

	}

	public void onInteract(GameObject obj) {

	}

	public Rectangle2D.Double getBounds() {
		return new Rectangle2D.Double(posX, posY, 1f/4, 1f/4);
	}

}
