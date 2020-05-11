import java.awt.geom.Rectangle2D;

public interface DynamicObject {
	/**
	 * This gets the x Velocity
	 * @return velX
	 */
	public float getVelX();
	/**
	 * This gets the y Velocity
	 * @return velY
	 */
	public float getVelY();
	/**
	 * This sets the x Velocity
	 * @param velX must be within the range of the y 
	 */
	public void setVelX(float velX);

	public void setVelY(float velY);
	
	public float getPosX();
	
	public float getPosY();
	
	public void setPosX(float posX);
	
	public void setPosY(float posY);
	
	public void setPos(float posX, float posY);
	
	public Rectangle2D getBounds();
}
