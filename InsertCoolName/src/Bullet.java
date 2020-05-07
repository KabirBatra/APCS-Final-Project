

public abstract class Bullet extends GameObject implements DynamicObject {
	
	protected float velX;
	protected float velY;
	protected GameObject shotBy;
	
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
	
	public void onInteract(GameObject obj) {
		
	}

}
