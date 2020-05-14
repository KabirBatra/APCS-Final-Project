package gameobject;
import java.awt.geom.Rectangle2D;

/*
 * An interface that represents any object that can move in the game.
 */
public interface DynamicObject {

	public float getVelX();

	public float getVelY();
	
	public void setVelX(float velX);

	public void setVelY(float velY);
	
	public float getPosX();
	
	public float getPosY();
	
	public void setPosX(float posX);
	
	public void setPosY(float posY);
	
	public void setPos(float posX, float posY);
	
	public Rectangle2D getBounds();
}
