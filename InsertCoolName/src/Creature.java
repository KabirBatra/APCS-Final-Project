
public abstract class Creature extends GameObject {
	
	protected float velX, velY;
	protected int health;
	protected int maxHealth;
	
	public Creature(float x, float y, String name) {
		super(x, y, name);
		velX = 0;
		velY = 0;
	}
}
