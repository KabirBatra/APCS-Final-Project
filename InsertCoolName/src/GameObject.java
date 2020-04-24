import processing.core.PApplet;

public abstract class GameObject {
	
	protected float posX, posY;
	String name;
	
	public GameObject(float x, float y, String name) {
		posX = x;
		posY = y;
		this.name = name;
	}
	
	
	// every frame
	public abstract void act();
	// act should call this
	public abstract void drawSelf(PApplet p);
	// when 2 game objects are touching
	public abstract void onInteract(GameObject obj);
	
	
}
