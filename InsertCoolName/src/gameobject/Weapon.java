package gameobject;
import java.awt.geom.Rectangle2D.Double;

import processing.core.PApplet;

/*
 * @author Kaie Chen
 */
public class Weapon extends GameObject{

	public Weapon(float x, float y, String name) {
		super(x, y, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float ellapsedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawSelf(float x, float y, int tileWidth, int tileHeight, PApplet s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onInteract(GameObject obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Double getBounds() {
		// TODO Auto-generated method stub
		return null;
	}


}
