import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public abstract class Creature extends GameObject implements Movable {
		
	protected float velX, velY;
	protected int health;
	protected int maxHealth;
	
	protected float maxVel;
	
	public Creature(float x, float y, String name) {
		super(x, y, name);
		velX = 0;
		velY = 0;
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
	
	public Rectangle2D.Double getBounds() {
		return new Rectangle2D.Double(posX, posY, 1, 1);
	}
}
