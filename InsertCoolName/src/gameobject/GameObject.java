package gameobject;
import java.awt.geom.Rectangle2D;

import processing.core.PApplet;

/*
 * The super class for all objects in the game.
 * @author Kabir Batra
 */
public abstract class GameObject {
	
	protected float posX, posY;
	protected String name;
	protected boolean solidVsWall = true, solidVsGameObject = true;
	
	public GameObject(float x, float y, String name) {
		posX = x;
		posY = y;
		this.name = name;
	}

	
	/*
	 * Updates the state of the object 
	 * ex. changes the sprite as part of the animation.
	 */
	public abstract void update(float ellapsedTime);
	
	/*
	 * Draws the object using the Processing PApplet
	 * @param x The X position in terms of tiles
	 * @param y The Y position in terms of tiles
	 * @param tileWidth The width of each tile
	 * @param tileHeight The height of each tile
	 */
	public abstract void drawSelf(float x, float y, int tileWidth, int tileHeight, PApplet s);
	
	/*
	 * Handles the interation between two GameObjects
	 * ex. knockback or teleportation
	 */
	public abstract void onInteract(GameObject obj);
	
	public float getPosX() {
		return posX;
	}
	
	public float getPosY() {
		return posY;
	}
	
	public void setPos(float posX, float posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public void setPosX(float posX) {
		this.posX = posX;
	}


	public void setPosY(float posY) {
		this.posY = posY;
	}
	
	public String toString() {
		return name;
	}
	
	/*
	 * @return whether the object should collide with walls or not 
	 */
	public boolean isSolidVsWall() {
		return solidVsWall;
	}
	
	/*
	 * @return whether the object should collide with other GameObjects or not
	 */
	public boolean isSolidVsGameObject() {
		return solidVsGameObject;
	}

	/*
	 * @return a rectangle that represents the boundary of the object
	 */
	public abstract Rectangle2D.Double getBounds();
	
}
