package gameobject;
import java.awt.geom.Rectangle2D;

import processing.core.PApplet;

/*
 * The standard bullet in the game
 */
public class NormalBullet extends Bullet {
	
	protected static float speed = 13f;
	protected static float damage = 10;
	
	
	public NormalBullet(float x, float y, float angle, GameObject shooter, String name) {
		super(x, y, angle, speed, shooter, name);
	
	}

	public void update(float ellapsedTime) {

	}

	public void drawSelf(float x, float y, int tileWidth, int tileHeight, PApplet s) {
		
		
		s.rect(x, y, tileWidth/4f, tileHeight/4f);


	}

	public void onInteract(GameObject obj) {

	}

	public Rectangle2D.Double getBounds() {
		return new Rectangle2D.Double(posX, posY, 1f/4, 1f/4);
	}


}
