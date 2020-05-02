
import processing.core.PApplet;

public class Enemy extends Creature {

	public Enemy(float x, float y, String name, SpriteSheet ss) {
		super(x, y, name, ss);
		velX = 1;
		velY = 1;
	}

	public void update(float ellapsedTime) {
		super.update(ellapsedTime);
		//ai movement per tick
		//if(ellapsedTime == 0)
		velX = (int)(Math.random() * 15) - 7;
		velY = (int)(Math.random() * 15) - 7;
	}

	public void drawSelf(float x, float y, int tileWidth, int tileHeight, PApplet s) {
		s.fill(0, 255, 0);
		s.rect(x, y, tileWidth, tileHeight);
	}

	public void onInteract(GameObject obj) {
		
	}


}
