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
	// rename this varible as BORDER_OFFSET
	private static final float HOW_HARD_IT_IS_TO_GET_THROUGH_ONE_TILE_TUNNELS = 0.1F; //values from 0.00001 - 0.1
	
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
	
	public Map getCurrentMap() {
		return currentMap;
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
				
				// movement//collisions
				newPosX = cr.getVelX() * ellapsedTime + cr.getPosX();
				newPosY = cr.getVelY() * ellapsedTime + cr.getPosY();
				
				if(newPosX < 0)
					newPosX = 0;
				if(newPosY < 0)
					newPosY = 0;
				
				// x direction
				if(cr.getVelX() < 0 
						&& (currentMap.isSolidTile((int)newPosX, (int)(cr.getPosY() + HOW_HARD_IT_IS_TO_GET_THROUGH_ONE_TILE_TUNNELS))
						|| currentMap.isSolidTile((int)newPosX, (int)(cr.getPosY() + 1-HOW_HARD_IT_IS_TO_GET_THROUGH_ONE_TILE_TUNNELS))) ) {
					cr.setVelX(0);
					newPosX = (int)newPosX + 1;
				}
				else if(cr.getVelX() > 0 
						&& (currentMap.isSolidTile((int)newPosX+1, (int)(cr.getPosY() + HOW_HARD_IT_IS_TO_GET_THROUGH_ONE_TILE_TUNNELS))
						|| currentMap.isSolidTile((int)newPosX+1, (int)(cr.getPosY()+1-HOW_HARD_IT_IS_TO_GET_THROUGH_ONE_TILE_TUNNELS))) ) {
					cr.setVelX(0);
					newPosX = (int)newPosX;
				}
				
				// y dir
				if(cr.getVelY() < 0 
						&& (currentMap.isSolidTile((int)(cr.getPosX() + HOW_HARD_IT_IS_TO_GET_THROUGH_ONE_TILE_TUNNELS), (int)newPosY)
						|| currentMap.isSolidTile((int)(cr.getPosX() + 1-HOW_HARD_IT_IS_TO_GET_THROUGH_ONE_TILE_TUNNELS), (int)newPosY)) ) {
					cr.setVelY(0);
					newPosY = (int)newPosY + 1;
				}
				else if(cr.getVelY() > 0 
						&& (currentMap.isSolidTile((int)(cr.getPosX() + HOW_HARD_IT_IS_TO_GET_THROUGH_ONE_TILE_TUNNELS), (int)newPosY + 1)
						|| currentMap.isSolidTile((int)(cr.getPosX() + 1-HOW_HARD_IT_IS_TO_GET_THROUGH_ONE_TILE_TUNNELS), (int)newPosY + 1)) ) {
					cr.setVelY(0);
					newPosY = (int)newPosY;
				}

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
		for(int x = -1; x < visibleTilesX+1; x++) {
			for(int y = -1; y < visibleTilesY+1; y++) {
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
