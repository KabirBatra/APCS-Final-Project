
import processing.core.PApplet;

public class Enemy extends Creature {

	public Enemy(float x, float y, String name) {
		super(x, y, name);
		velX = 2;
		velY = 2;
	}

	public void act(float ellapsedTime) {
		//ai movement per tick
		if(ellapsedTime == 0)
		velX *= -1;
		velY *= -1;
	}

	public void drawSelf(float x, float y, float tileWidth, float tileHeight, PApplet s) {
		s.fill(0, 255, 0);
		s.rect(x, y, tileWidth, tileHeight);
	}

	public void onInteract(GameObject obj) {
		
	}


}
