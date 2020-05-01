import java.awt.geom.Rectangle2D;

import processing.core.PApplet;

public abstract class GameObject {
	
	protected float posX, posY;
	protected String name;
	protected boolean solidVsWall = true, solidVsGameObject = true;
	
	protected enum Direction {North, South, East, West};
	protected enum AnimationState {Standing, Walking, Dead};

	protected Direction facingDirection = Direction.South;
	protected AnimationState state = AnimationState.Standing;

	
	public GameObject(float x, float y, String name) {
		posX = x;
		posY = y;
		this.name = name;
	}
	
	
	// method for animation purposes
	public abstract void act(float ellapsedTime);
	// draws itself
	public abstract void drawSelf(float x, float y, float tileWidth, float tileHeight, PApplet s);
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


	public abstract Rectangle2D.Double getBounds();
	
}
