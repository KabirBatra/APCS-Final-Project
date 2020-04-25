import java.util.LinkedList;

import processing.core.PApplet;

public class GameObjectHandler {
	
	private PApplet s;
	private LinkedList<GameObject> objects;
	
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	public GameObjectHandler(PApplet surface) {
		s = surface;
		objects = new LinkedList<GameObject>();
		
		up = false;
		down = false;
		left = false;
		right = false;
	}

	// game object stuff
	public void addGameObject(GameObject o) {
		objects.add(o);
	}
	
	public GameObject getPlayer() {
		for(GameObject obj : objects) {
			if(obj instanceof Player)
				return obj;
		}
		return null;
	}
	
	public void tick() {
		
		for(GameObject obj : objects) {
			obj.act();
		}
	}
	
	public void draw(float offSetX, float offSetY, float tileWidth, float tileHeight) {
		for(GameObject obj : objects) {
			obj.drawSelf((obj.getPosX() - offSetX)*tileWidth, (obj.getPosY() - offSetY)*tileWidth, tileWidth, tileHeight, s);
			
		}
	}
	
	public void keyPressed() {
		
		
	}
	
	public void keyReleased() {
		
	}
	
}
