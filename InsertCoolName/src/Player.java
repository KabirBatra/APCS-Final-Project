import java.awt.Rectangle;

import processing.core.PApplet;

public class Player extends Creature {

	
	private GameHandler handler;
	
	// for collisions	
	
	public Player(float x, float y, String name, GameHandler handler) {
		super(x, y, name);
		this.handler = handler;
		maxVel = 10f;
	}

	public void act() {
		
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
		// I am trying to get it so that the bullets go towards the 
		// direction of the mouse, auto aim can be enabled later when enemies are visible
	
//		System.out.println(p.x + " " + p.y + "\n" + newPosX + " " + newPosY);
//		float angle = (float)Math.atan((float) ((numberOne.getPosY() - offSetY) * 60) - p.y / (float) (numberOne.getPosX() - offSetX) * 60 - p.x);
		
		float angle = (float)Math.tan((s.mouseY - s.height)/(s.mouseX - s.width)); //temporary calculation
		//handler.addGameObject(new RifleBullet(handler.getPlayer().getPosX(), handler.getPlayer().getPosX(), 0, 20, 0, 0, "308"));
		handler.addGameObject(new Bullet(handler.getPlayer().getPosX()+0.5f, handler.getPlayer().getPosY()+0.5f, angle, "308"));
	}

	@Override
	public void drawSelf(float x, float y, float tileWidth, float tileHeight, PApplet s) {
		// TODO Auto-generated method stub
		s.fill(255, 0, 0);
		s.rect(x, y, tileWidth, tileHeight);
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
