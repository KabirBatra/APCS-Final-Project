import java.util.LinkedList;

import processing.core.PApplet;

public class GameObjectHandler {
	
	private PApplet s;
	private LinkedList<GameObject> objects;
	
	public GameObjectHandler(PApplet surface) {
		s = surface;
		objects = new LinkedList<GameObject>();
	}

	// game object stuff
	public void addGameObject(GameObject o) {
		objects.add(o);
	}
	
	public void tick() {
		
		for(GameObject obj : objects) {
			obj.act();
			obj.drawSelf(s);
		}
	}
	
}
