
public abstract class Creature extends GameObject {
		
	protected float velX, velY;
	protected int health;
	protected int maxHealth;
	
	protected float maxVel;
	
	public Creature(float x, float y, String name) {
		super(x, y, name);
		velX = 0;
		velY = 0;
	}
	
	public abstract void act();
	
	public float getVelX() {
		return velX;
	}
	
	public float getVelY() {
		return velY;
	}
}
