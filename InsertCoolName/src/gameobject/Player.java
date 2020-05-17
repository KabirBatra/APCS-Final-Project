package gameobject;


import assets.SpriteSheet;
import processing.core.PApplet;
import processing.core.PImage;
import running.GameHandler;

/*
 * Represents the controllable Player of the game
 * @author Kabir Batra
 */
public class Player extends Creature {
	
	private GameHandler handler;
	
	/*
	 * sets health and speed to defaults
	 */
	public Player(float x, float y, String name, SpriteSheet ss, GameHandler handler) {
		super(x, y, name, ss);
		this.handler = handler;
		maxSpeed = 8f;
		maxHealth = 100;
		health = 100;
	}

	/*
	 * Updates the velocity of the player depending on the state of keys 
	 * in the handler
	 */
	public void update(float ellapsedTime) {
		super.update(ellapsedTime);
		// movement
		velX = 0;
		velY = 0;
		if(handler.getUp()) {
			velY += -maxSpeed;
		}
		if(handler.getDown()) {
			velY += maxSpeed;
		}
		if(handler.getLeft()) {
			velX += -maxSpeed;
		}
		if(handler.getRight()) {
			velX += maxSpeed;
		}
		
		
		// diagonal speed is still maxVel
		if(Math.abs(velX) + Math.abs(velY) > maxSpeed) {
			float ratio = (float)Math.sqrt(2);
			velX /= ratio;
			velY /= ratio;
		}
	}
	
	/*
	 * Creates a bullet game object at the player's position
	 */
	public void shoot(PApplet s) {
		Enemy closestEnemy = null;
		float closestDistanceSquared = -1;
		float distanceSquared;
		for(GameObject obj : handler.objects) {
			if(obj instanceof Enemy) {
				
				distanceSquared = (posX - obj.posX)*(posX - obj.posX) + (posY - obj.posY)*(posY - obj.posY);
				
				if(closestEnemy == null || distanceSquared < closestDistanceSquared) {
					closestEnemy = (Enemy)obj;
					closestDistanceSquared = distanceSquared;
				}
			}
		}
		float angle;

		if(closestEnemy != null) {
			angle = (float)Math.atan((posY - closestEnemy.posY)/(posX - closestEnemy.posX));
			if(posX > closestEnemy.posX) {
				angle+=Math.PI;
			}
		} else {
			angle = 0; // shoot in the direction moving (using enum's index?)
		}
		handler.addGameObject(new NormalBullet(this.posX+0.5f, this.posY+0.5f, angle, this, "308"));
	}

	/*
	 * Draws the player using the current sprite
	 */
	public void drawSelf(float x, float y, int tileWidth, int tileHeight, PApplet s) {
		//s.fill(255, 0, 0);
		//s.rect(x, y, tileWidth, tileHeight);
		
		if(currentSprite != null) {
			PImage img = new PImage((java.awt.Image)currentSprite); 
			s.image(img, x, y, tileWidth, tileHeight);
		}
	}

	public void onInteract(GameObject obj) {
		
	}

}
