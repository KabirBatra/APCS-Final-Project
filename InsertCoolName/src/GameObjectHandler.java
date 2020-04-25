import java.util.LinkedList;

import processing.core.PApplet;
import processing.core.PConstants;

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
	
	public boolean getUp() {
		return up;
	}
	public boolean getDown() {
		return down;
	}
	public boolean getLeft() {
		return left;
	}
	public boolean getRight() {
		return right;
	}
	
	public void keyPressed() {
		//replace with s.key == 'w' || 'W'
		if(s.keyCode == PConstants.UP) {
			up = true;
			
		} else if(s.keyCode == PConstants.DOWN) {
			down = true;

		} else if(s.keyCode == PConstants.LEFT) {
			left = true;

		} else if(s.keyCode == PConstants.RIGHT) {
			right = true;

		}
		
	}
	
	public void keyReleased() {
		if(s.keyCode == PConstants.UP) {
			up = false;
			
		} else if(s.keyCode == PConstants.DOWN) {
			down = false;

		} else if(s.keyCode == PConstants.LEFT) {
			left = false;

		} else if(s.keyCode == PConstants.RIGHT) {
			right = false;

		}
	}
	
}
