

import processing.core.PApplet;
import processing.core.PImage;

public class Player extends Creature {

	static int a = 0;
	
	private GameHandler handler;
		
	public Player(float x, float y, String name, SpriteSheet ss, GameHandler handler) {
		super(x, y, name, ss);
		this.handler = handler;
		maxVel = 10f;
	}

	public void update(float ellapsedTime) {
		super.update(ellapsedTime);
		// movement
		velX = 0;
		velY = 0;
		if(handler.getUp()) {
			velY += -maxVel;
		}
		if(handler.getDown()) {
			velY += maxVel;
		}
		if(handler.getLeft()) {
			velX += -maxVel;
		}
		if(handler.getRight()) {
			velX += maxVel;
		}
		
		
		// diagonal speed is still maxVel
		if(Math.abs(velX) + Math.abs(velY) > maxVel) { //works same way as pythagorean theorem
			float ratio = (float)Math.sqrt(2);
			velX /= ratio;
			velY /= ratio;
		}
	}
	
	public void shoot(PApplet s) {
		float angle = (float)(a * Math.PI / 180); //temporary calculation
		handler.addGameObject(new NormalBullet(handler.getPlayer().getPosX()+0.5f, handler.getPlayer().getPosY()+0.5f, angle, "308"));
		a += 30;
	}

	public void drawSelf(float x, float y, int tileWidth, int tileHeight, PApplet s) {
		//s.fill(255, 0, 0);
		//s.rect(x, y, tileWidth, tileHeight);
		
		if(currentSprite != null) {
			PImage img = new PImage((java.awt.Image)currentSprite); 
			img.resize((int)tileWidth, 0);
			s.image(img, x, y);
		}
	}

	public void onInteract(GameObject obj) {
		// TODO Auto-generated method stub
		
	}
}
