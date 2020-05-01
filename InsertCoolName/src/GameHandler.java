import java.awt.Rectangle;
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
	private static final float BORDER_OFFSET_FOR_CREATURES = 0.1F; // values from 0.00001 - 0.1

	public GameHandler(PApplet surface) {
		s = surface;
		objects = new LinkedList<GameObject>();

		up = false;
		down = false;
		left = false;
		right = false;
	}

	public void setMap(String name, Player p) {
		if (currentMap != null) {
			objects.clear();
		}
		currentMap = Assets.getMap(name);
		currentMap.populateGameObjects(p);
	}

	public Map getCurrentMap() {
		return currentMap;
	}
	
	public void addGameObject(GameObject obj) {
		objects.add(obj);
		System.out.println("added a game object of " + obj.getClass());
	}

	public Player getPlayer() {
		for (GameObject obj : objects) {
			if (obj instanceof Player)
				return (Player) obj;
		}
		return null;
	}

	public void tick(float ellapsedTime) {
		
		LinkedList<GameObject> bulletsToRemove = new LinkedList<GameObject>();

		for (GameObject obj : objects) {
			obj.act();

			if (obj instanceof Creature) {
				Creature cr = (Creature)obj;

				creatureCollisionsAndMovement(cr, ellapsedTime);
			} 
			else if(obj instanceof Bullet) {
				Bullet b = (Bullet)obj;

//				bulletCollisionsAndMovement(b, ellapsedTime);
				if(bulletCollisionsAndMovement(b, ellapsedTime)) {
					bulletsToRemove.add(obj);
				}
			}
		}
		
		for(GameObject destroyedBullet : bulletsToRemove) {
			objects.remove(destroyedBullet);
		}
		
	}

	public void drawObjects(float offsetX, float offsetY, float tileWidth, float tileHeight) {
		for (GameObject obj : objects) {
			obj.drawSelf((obj.getPosX() - offsetX) * tileWidth, (obj.getPosY() - offsetY) * tileWidth, tileWidth,
					tileHeight, s);

		}
	}

	public void drawMap(float offsetX, float offsetY, float tileOffsetX, float tileOffsetY, float visibleTilesX,
			float visibleTilesY, float tileWidth, float tileHeight) {
		for (int x = -1; x < visibleTilesX + 1; x++) {
			for (int y = -1; y < visibleTilesY + 1; y++) {
				Type tile = currentMap.getTile(x + (int) offsetX, y + (int) offsetY);

//				s.image(img, a, b);

				if (tile == Type.Wall) {
					s.fill(0, 0, 255);
				} 
				else if (tile == Type.None){
					s.fill(0);
				} 
				else if (tile == Type.Floor || tile == Type.Enemy || tile == Type.Player) {
					s.fill(255);
				}
				else {
					s.fill(51); // error color
				}
				s.rect(x * tileWidth - tileOffsetX, y * tileHeight - tileOffsetY, tileWidth + 1f, tileHeight + 1f);
			}
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
		if (s.keyCode == PConstants.UP || s.key == 'w' || s.key == 'W') {
			up = true;

		} 
		else if (s.keyCode == PConstants.DOWN || s.key == 's' || s.key == 'S') {
			down = true;

		} 
		else if (s.keyCode == PConstants.LEFT || s.key == 'a' || s.key == 'A') {
			left = true;

		} 
		else if (s.keyCode == PConstants.RIGHT || s.key == 'd' || s.key == 'D') {
			right = true;
			
		} 
		else if (s.key == ' ') {
			getPlayer().shoot(s);
			
		} 
		else if (s.key == 'f' || s.key == 'F') {
			setMap("testRoom2", getPlayer());
			
		} 
		else if (s.key == 'r' || s.key == 'R') {
			setMap("testRoom", getPlayer());
			
		} 
		else if (s.key == 'm' || s.key == 'M') {
			setMap("testRoom3", getPlayer());
			
		}
		
	}

	public void keyReleased() {
		if (s.keyCode == PConstants.UP || s.key == 'w' || s.key == 'W') {
			up = false;

		} 
		else if (s.keyCode == PConstants.DOWN || s.key == 's' || s.key == 'S') {
			down = false;

		} 
		else if (s.keyCode == PConstants.LEFT || s.key == 'a' || s.key == 'A') {
			left = false;

		} 
		else if (s.keyCode == PConstants.RIGHT || s.key == 'd' || s.key == 'D') {
			right = false;

		}

	}
	
	public void creatureCollisionsAndMovement(Creature cr, float ellapsedTime) {
		// movement/collisions
		newPosX = cr.getVelX() * ellapsedTime + cr.getPosX();
		newPosY = cr.getVelY() * ellapsedTime + cr.getPosY();

		if (newPosX < 0)
			newPosX = 0;
		if (newPosY < 0)
			newPosY = 0;

		// x direction
		if (cr.getVelX() < 0 && (currentMap.isSolidTile((int) newPosX,
				(int) (cr.getPosY() + BORDER_OFFSET_FOR_CREATURES))
				|| currentMap.isSolidTile((int) newPosX,
						(int) (cr.getPosY() + 1 - BORDER_OFFSET_FOR_CREATURES)))) {
			cr.setVelX(0);
			newPosX = (int) newPosX + 1;
		} else if (cr.getVelX() > 0 && (currentMap.isSolidTile((int) newPosX + 1,
				(int) (cr.getPosY() + BORDER_OFFSET_FOR_CREATURES))
				|| currentMap.isSolidTile((int) newPosX + 1,
						(int) (cr.getPosY() + 1 - BORDER_OFFSET_FOR_CREATURES)))) {
			cr.setVelX(0);
			newPosX = (int) newPosX;
		}

		// y dir
		if (cr.getVelY() < 0 && (currentMap.isSolidTile(
				(int) (cr.getPosX() + BORDER_OFFSET_FOR_CREATURES), (int) newPosY)
				|| currentMap.isSolidTile(
						(int) (cr.getPosX() + 1 - BORDER_OFFSET_FOR_CREATURES),
						(int) newPosY))) {
			cr.setVelY(0);
			newPosY = (int) newPosY + 1;
		} else if (cr.getVelY() > 0 && (currentMap.isSolidTile(
				(int) (cr.getPosX() + BORDER_OFFSET_FOR_CREATURES), (int) newPosY + 1)
				|| currentMap.isSolidTile(
						(int) (cr.getPosX() + 1 - BORDER_OFFSET_FOR_CREATURES),
						(int) newPosY + 1))) {
			cr.setVelY(0);
			newPosY = (int) newPosY;
		}

		cr.setPos(newPosX, newPosY);
	}
	
	
	
	
	//true if there is a collision
	public boolean bulletCollisionsAndMovement(Bullet b, float ellapsedTime) {
		// movement/collisions
		newPosX = b.getVelX() * ellapsedTime + b.getPosX();
		newPosY = b.getVelY() * ellapsedTime + b.getPosY();

		if (newPosX < 0 || newPosY < 0)
			return true;

		Rectangle bounds = b.getBounds(); // old position
		float x = bounds.x/1000f;
		float y = bounds.y/1000f;
		
		float width = bounds.width/1000f;
		float height = bounds.height/1000f;
		//bounds.width;
		//bounds.height;
		
		// x direction
		if (b.getVelX() < 0 &&
				(currentMap.isSolidTile((int)newPosX, (int)y)
				|| currentMap.isSolidTile((int)newPosX, (int)(y + height)))) {
			return true;
		} else if (b.getVelX() > 0 && 
				(currentMap.isSolidTile((int)(newPosX + width), (int) y)
				|| currentMap.isSolidTile((int)(newPosX + width), (int) (y + height)))) {
			return true;
		}

		// y dir
		if (b.getVelY() < 0 &&
				(currentMap.isSolidTile((int) (x), (int) newPosY)
				|| currentMap.isSolidTile((int) (x + width), (int) newPosY))) {
			return true;
		} else if (b.getVelY() > 0 &&
				(currentMap.isSolidTile((int) (x), (int)(newPosY + height))
				|| currentMap.isSolidTile((int) (x + width), (int)(newPosY + height)))) {
			return true;
		}
		
		//no collision
		
		b.setPos(newPosX, newPosY);
		return false;
	}


}
