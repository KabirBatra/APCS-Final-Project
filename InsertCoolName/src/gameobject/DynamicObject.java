package gameobject;
import java.awt.geom.Rectangle2D;

/*
 * An interface that represents any object that can move in the game.
 */
public interface DynamicObject {
/**
 * @return gets the velX of this dynamic object
 */
	public float getVelX();
/**
 * 
 * @return gets the velY of this dynamic object
 */
	public float getVelY();
	/**
	 * 
	 * @param velX this changes the velX of this dynamic object
	 */
	public void setVelX(float velX);
/**
 * 
 * @param velY this changes the velY of this dynamic object
 */
	public void setVelY(float velY);
	/**
	 * 
	 * @return this returns the current X position of the dynamic object
	 */
	public float getPosX();
	/**
	 * 
	 * @return this returns the current Y position of the dynamic object
	 */
	public float getPosY();
	/**
	 * 
	 * @param posX this changes the X position of the dynamic object
	 */
	public void setPosX(float posX);
	/**
	 * 
	 * @param posY this changes the Y position of the dynamic object
	 */
	public void setPosY(float posY);
	/**
	 * 
	 * @param posX this changes the X position of the dyanmic object
	 * @param posY this changes the Y position of the dyanmic object
	 */
	public void setPos(float posX, float posY);
	/**
	 * 
	 * @return this returns a rectangle2D of this object
	 */
	public Rectangle2D getBounds();
}
