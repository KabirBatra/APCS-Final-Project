package gameobject;

/**
 * this class creates the bullet object that works with the players and objects
 * @author Kaie & Kabir
 *
 */
public abstract class Bullet extends GameObject implements DynamicObject {
	
	protected float velX;
	protected float velY;
	protected GameObject shotBy;
	/**
	 * This creates a bullet!
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
	
	public abstract void attack(Creature cr);

}
