import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class Creature extends GameObject implements DynamicObject {
		
	protected float velX, velY;
	protected float maxSpeed;

	protected int health;
	protected int maxHealth;
	
	protected float timer; // for animations
	protected SpriteSheet ss;
	protected BufferedImage currentSprite;

	protected enum Direction {NORTH, SOUTH, EAST, WEST};
	protected enum AnimationState {STANDING, WALKING, DEAD};
	protected Direction facingDirection;
	protected AnimationState state;
	
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
	
	public void update(float ellapsedTime) {
		timer += ellapsedTime;
		
		if(timer >= 0.2) {
			timer -= 0.2;
		}
		
		if(health <= 0) {
			state = AnimationState.DEAD;
		}
		else if(velX == 0 && velY == 0) {
			state = AnimationState.STANDING;
		}
		else {
			state = AnimationState.WALKING;
		}
		
		if(velX > 0)
			facingDirection = Direction.WEST;
		if(velX < 0)
			facingDirection = Direction.EAST;
		if(velY > 0)
			facingDirection = Direction.SOUTH;
		if(velY < 0)
			facingDirection = Direction.NORTH;
		
		if(ss != null) {
			currentSprite = ss.getSprite(4,1);
		}
	}
		
	public float getVelX() {
		return velX;
	}
	
	public float getVelY() {
		return velY;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	public void setSpriteSheet(SpriteSheet ss) {
		this.ss = ss;
	}
	
	public Rectangle2D.Double getBounds() {
		return new Rectangle2D.Double(posX, posY, 1, 1);
	}
	
	public float getMaxSpeed() {
		return maxSpeed;
	}
}
