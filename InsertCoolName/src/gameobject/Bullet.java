package gameobject;

/**
 * this class creates the bullet object that works with the players and objects
 * @author Kabir Batra and Kaie Chen
 *
 */
public abstract class Bullet extends GameObject implements DynamicObject {
	
	protected float velX;
	protected float velY;
	protected GameObject shotBy;
	/**
	 * This is a basic blue print for a bullet!
	 * @param x the x coordinate position of the bullet to spawn in
	 * @param y the y coordinate position of the bullet to spawn in
	 * @param angle the angle to which the bullet shall travel
	 * @param speed the speed to which the bullet shall move
	 * @param shooter Who has shot this bullet
	 * @param name The name of this unique bullet
	 */
	public Bullet(float x, float y, float angle, float speed, GameObject shooter, String name) {
		super(x, y, name);
		
		velX = (float)(speed * Math.cos(angle));
		velY = (float) (speed * Math.sin(angle));
		shotBy = shooter;
		
	}
/**
 * This is a getter that return velX!
 */
	public float getVelX() {
		return velX;
	}
/**
 * This is a getter that returns velY!
 */
	public float getVelY() {
		return velY;
	}
/** 
 * This is a setter that sets velX!
 */
	public void setVelX(float velX) {
		this.velX = velX;
	}
/**
 * This is a setter that sets velY!
 */
	public void setVelY(float velY) {
		this.velY = velY;
	}
	/**
	 * This is the method that accounts for damage
	 * @param cr the creature who is going to take the damage from this bullet
	 */
	public abstract void attack(Creature cr);

}
