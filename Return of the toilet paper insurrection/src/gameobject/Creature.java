package gameobject;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import assets.SpriteSheet;
import gameobject.Creature.AnimationState;
/**
 *  This is the class that manages all the characters that are in the game!
 * @author Kabir Batra and Kaie Chen
 *
 */
public abstract class Creature extends GameObject implements DynamicObject {
		
	protected float velX, velY;
	protected float maxSpeed;

	protected int health;
	protected int maxHealth;
	
	protected float timer; // for animations
	protected int graphicState; // either 0 or 1
	protected int ticks; // increases every 0.1 seconds
	protected SpriteSheet ss;
	protected BufferedImage currentSprite;

	protected enum Direction {NORTH, SOUTH, EAST, WEST};
	public enum AnimationState {STANDING, WALKING, DEAD};
	protected Direction facingDirection;
	protected AnimationState state;
/**
 * This creates a creature in the game!	
 * @param x The x location of this creature
 * @param y The y location of this creature
 * @param name The name of this creature!
 * @param ss the sprite that this image inherits!
 */
	public Creature(float x, float y, String name, SpriteSheet ss) {
		super(x, y, name);
		velX = 0;
		velY = 0;
		facingDirection = Direction.SOUTH;
		state = AnimationState.STANDING;
		timer = 0;
		this.ss = ss;
		health = 10;
		maxHealth = 10;
	}
	
	/**
	 * This method updates the creature's animations based on the walking direction
	 */
	public void update(float ellapsedTime) {
		if(state == AnimationState.DEAD) {
			velX = 0;
			velY = 0;
			return;
		}
		timer += ellapsedTime;
		
		//every 0.1 seconds
		if(timer >= 0.1) {
			timer -= 0.1;
			ticks++;
			if(ticks % 2 == 0) graphicState++;
			graphicState %= 2;
		}
		
		if(health <= 0) {
			health = 0;
			state = AnimationState.DEAD;
		}
		else if(velX == 0 && velY == 0) {
			state = AnimationState.STANDING;
		}
		else {
			state = AnimationState.WALKING;
		}
		
		if(velY > 0)
			facingDirection = Direction.SOUTH;
		if(velY < 0)
			facingDirection = Direction.NORTH;
		// west and east have precedence
		if(velX > 0) 
			facingDirection = Direction.WEST;
		if(velX < 0)
			facingDirection = Direction.EAST;
		
		
		if(ss != null) {
			if(state == AnimationState.DEAD) {
				currentSprite = ss.getSprite(4,0);
			}
			else if(state == AnimationState.STANDING) {
				currentSprite = ss.getSprite(4,1);
				
			}
			else { //if moving
				
				switch(facingDirection) {
				case SOUTH:
					currentSprite = ss.getSprite(0,graphicState);
					break;
				case EAST:
					currentSprite = ss.getSprite(1,graphicState);
					break; 
				case NORTH:
					currentSprite = ss.getSprite(2,graphicState);
					break; 
				case WEST:
					currentSprite = ss.getSprite(3,graphicState);
					break; 
				
				}
				
				
			}
			
			
			
		}
	}
	/**
	 * getter for x velocity
	 */
	public float getVelX() {
		return velX;
	}
	/**
	 * getter for y velocity
	 */
	public float getVelY() {
		return velY;
	}
/**
 * getter for x velocity
 */
	public void setVelX(float velX) {
		this.velX = velX;
	}
/**
 * setter for y velocity
 */
	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	/**
	 * Getter for the creature's health!
	 * @return the current health of this creature
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Setter for the creature's health!
	 * @param health must be a positive integer that is zero or above
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * This is a getter for the max health of this creature!
	 * @return the maxHealth of this creature
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

/**
 * This changes the max health of this creature if this creature recieves a buff	
 * @param maxHealth must be a positive whole number above 0!
 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	
	/**
	 * Gets the spritesheet of this creature!
	 * @param ss the sprite sheet for this creature
	 */
	public void setSpriteSheet(SpriteSheet ss) {
		this.ss = ss;
	}
/**
 * This draws a Rectangle2D hit box around this creature object!
 */
	public Rectangle2D.Double getBounds() {
		return new Rectangle2D.Double(posX, posY, 1, 1);
	}
	/**
	 * @return the maximum speed of this Creature
	 */
	public float getMaxSpeed() {
		return maxSpeed;
	}
	/**
	 * This increments the health of this creature by healthPoints!
	 * @param healthPoints changes the health of the creature by healthPoints. Can be positive or negative whole numbers
	 */
	public void deltaHealth(int healthPoints) {
		health += healthPoints;
		System.out.println(healthPoints);
	}
	/**
	 * This makes the creature dead
	 * @return the state of the creature being dead
	 */
	public boolean isDead() {
		return state == AnimationState.DEAD;
	}

}
