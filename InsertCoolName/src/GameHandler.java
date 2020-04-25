import java.util.LinkedList;

import processing.core.PApplet;
import processing.core.PConstants;

public class GameHandler {
	
	private PApplet s;
	private LinkedList<GameObject> objects;
	
	private Map currentMap;
	
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	private float newPosX, newPosY;
	
	public GameHandler(PApplet surface) {
		s = surface;
		objects = new LinkedList<GameObject>();
		
		up = false;
		down = false;
		left = false;
		right = false;
	}
	
	public void setMap(String name, Player p) {
		if(currentMap != null) {
			objects.clear();
		}
		currentMap = Assets.getMap(name);
		currentMap.populateGameObjects(p);
	}

	public void addGameObject(GameObject o) {
		objects.add(o);
	}
	
	public Player getPlayer() {
		for(GameObject obj : objects) {
			if(obj instanceof Player)
				return (Player)obj;
		}
		return null;
	}
	
	public void tick(float ellapsedTime) {
		
		for(GameObject obj : objects) {
			obj.act();
			
			if(obj instanceof Creature) {
				Creature cr = (Creature)obj;
				newPosX = cr.getVelX() * ellapsedTime + cr.getPosX();
				newPosY = cr.getVelY() * ellapsedTime + cr.getPosY();

				// movement//collisions
				cr.setPos(newPosX, newPosY);
			}
			
			
		}
	}
	
	public void drawObjects(float offsetX, float offsetY, float tileWidth, float tileHeight) {
		for(GameObject obj : objects) {
			obj.drawSelf((obj.getPosX() - offsetX)*tileWidth, (obj.getPosY() - offsetY)*tileWidth, tileWidth, tileHeight, s);
			
		}
	}
	
	
	public void drawMap(float offsetX, float offsetY, float tileOffsetX, float tileOffsetY, float visibleTilesX, float visibleTilesY, float tileWidth, float tileHeight) {
		for(int x = -1; x < visibleTilesX+2; x++) {
			for(int y = -1; y < visibleTilesY+2; y++) {
				int tile = currentMap.getTile(x + (int)offsetX, y + (int)offsetY);
				
//				s.image(img, a, b);
				
				if(tile == 0) {
					s.fill(255);
				}
				else if(tile == 1) {
					s.fill(0, 0, 255);
				} else {
					s.fill(0);
				}
				s.rect(x * tileWidth - tileOffsetX, y * tileHeight - tileOffsetY, tileWidth + 1f, tileHeight + 1f);
			}
		}
	}
	
//	public boolean isSolidTile() {
//		
//	}
	
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
		if(s.keyCode == PConstants.UP || s.key == 'w' || s.key == 'W') {
			up = true;
			
		} 
		else if(s.keyCode == PConstants.DOWN) {
			down = true;

		} 
		else if(s.keyCode == PConstants.LEFT) {
			left = true;

		} 
		else if(s.keyCode == PConstants.RIGHT) {
			right = true;
		} 
		else if(s.key == 'f' || s.key == 'F') {
			
			setMap("testRoom2", getPlayer());
		}
		else if(s.key == 'r' || s.key == 'R') {
			setMap("testRoom", getPlayer());
		}
		
	}
	
	public void keyReleased() {
		if(s.keyCode == PConstants.UP || s.key == 'w' || s.key == 'W') {
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
