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

	
	// method for animation purposes
	public abstract void update(float ellapsedTime);
	// draws itself
	public abstract void drawSelf(float x, float y, int tileWidth, int tileHeight, PApplet s);
	// when 2 game objects are touching
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
	
	public boolean isSolidVsWall() {
		return solidVsWall;
	}
	
	public boolean isSolidVsGameObject() {
		return solidVsGameObject;
	}


	public abstract Rectangle2D.Double getBounds();
	
}
