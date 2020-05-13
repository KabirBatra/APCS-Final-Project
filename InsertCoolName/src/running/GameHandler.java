package running;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import assets.Assets;
import assets.map.Map;
import gameobject.Bullet;
import gameobject.Creature;
import gameobject.DynamicObject;
import gameobject.Enemy;
import gameobject.GameObject;
import gameobject.Player;
import gameobject.Type;
import processing.core.PApplet;
import processing.core.PConstants;

/*
 * The class that controls and maintains nearly everything in the game.
 * Updates, collisions, controls, the GameObjects, and the current map are all stored here.
 * @author Kabir Batra
 */
public class GameHandler {

	private PApplet s;
	
	// so that player can access it and shoot toward nearest enemy
	public LinkedList<GameObject> objects;

	private Map currentMap;
	//private SpriteSheet playerSpriteSheet;

	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	private float newPosX, newPosY;
	private static final float BORDER_OFFSET_FOR_CREATURES = 0.1F; // values from 0.00001 - 0.1

	/*
	 * Creates the objects list and sets all of the current keys pressed to false. 
	 * @param surface the PApplet for drawing things like the objects and the map
	 */
	public GameHandler(PApplet surface) {
		s = surface;
		objects = new LinkedList<GameObject>();
		
		up = false;
		down = false;
		left = false;
		right = false;
	}

	/*
	 * Sets the current map to mapName and uses the current player if it already exists.
	 * @param mapName The map to switch to.
	 */
	public void setMap(String mapName) {
		if (currentMap != null) {
			objects.clear();
		}
		currentMap = Assets.getMap(mapName);
		currentMap.populateGameObjects(getPlayer());
		
		//playerSpriteSheet = Assets.getSpriteSheet("playerSheet");
	}

	public Map getCurrentMap() {
		return currentMap;
	}
	
	public void addGameObject(GameObject obj) {
		objects.add(obj);
		//System.out.println("added a game object of " + obj.getClass());
	}

	/*
	 * @returns the current player; returns null if the player has not been created yet
	 */
	public Player getPlayer() {
		for (GameObject obj : objects) {
			if (obj instanceof Player)
				return (Player) obj;
		}
		return null;
	}

	/*
	 * The update method.
	 * This method is called every frame and calls the update 
	 * method on every object. Then, it calculates collisions and 
	 * determines which Bullets should be removed from the list of 
	 * objects. Finally, it moves each object via its respective
	 * velocity. 
	 * @param ellapsedTime The delta time after each frame used to 
	 * make velocities smooth and constant even when frames are slow.
	 */
	public void tick(float ellapsedTime) {
		
		LinkedList<Bullet> bulletsToRemove = new LinkedList<Bullet>();
		LinkedList<Enemy> enemiesThatCanShoot = new LinkedList<Enemy>();

		boolean hasCollidedX;
		boolean hasCollidedY;
		
		for (GameObject obj : objects) {
			
			obj.update(ellapsedTime);
			
			hasCollidedX = false;
			hasCollidedY = false;

			// wall collisions
			if(obj.isSolidVsWall()) {
				
				if (obj instanceof Creature) {
					Creature cr = (Creature)obj;
					boolean[] temp = creatureVsWall(cr, ellapsedTime);
					if(temp[0]) hasCollidedX = true;
					if(temp[1]) hasCollidedY = true;
				} 
				
				else if(obj instanceof Bullet) {
					Bullet b = (Bullet)obj;

					// if collided
					if(bulletVsWall(b, ellapsedTime)) {
						bulletsToRemove.add((Bullet)obj);
						hasCollidedX = true;
						hasCollidedY = true;
					}
				}
			}
			
			if(obj.isSolidVsGameObject()) {
				for(GameObject obj2 : objects) {
					if(obj == obj2) {
						continue;
					}
					// if the object is solid, they should not overlap 
					//insert code for moving object aside (using rectangle from obj2)
					if(obj2.isSolidVsGameObject()) {
						if(obj instanceof Creature && obj2 instanceof Creature) {
							boolean temp[] = creatureVsCreature((Creature)obj, (Creature)obj2, ellapsedTime);
							if(temp[0]) hasCollidedX = true;
							if(temp[1]) hasCollidedY = true;
						}
						// if its a bullet
						
					}
					
					// can overlap (the teleporter etc)
					if(obj == getPlayer()) {
						//if object is chest or teleporter or sign etc
					}
					
				}
			}
			
			if((obj instanceof DynamicObject) && !hasCollidedX) {
				DynamicObject m = (DynamicObject)obj;
				m.setPosX(m.getVelX() * ellapsedTime + m.getPosX());

			}
			if((obj instanceof DynamicObject) && !hasCollidedY) {
				DynamicObject m = (DynamicObject)obj;
				m.setPosY(m.getVelY() * ellapsedTime + m.getPosY());

			}
			
			if(obj instanceof Enemy) {
				Enemy temp = (Enemy)obj;
				if(temp.canShoot()) {
					enemiesThatCanShoot.add((Enemy)obj);
				}
			}

		}
		
		// destroy the bullets at the end of the loop
		for(GameObject destroyedBullet : bulletsToRemove) {
			objects.remove(destroyedBullet);
		}
		for(Enemy enemy : enemiesThatCanShoot) {
			enemy.shoot(s);
		}
		
	}

	/*
	 * Draws all of the GameObjects onto the screen. 
	 * @param offsetX The offset between the top-left of the screen and the 0th tile
	 * in the x direction.
	 * @param offsetY The offset between the top-left of the screen and the 0th tile
	 * in the x direction.
	 * @param tileWidth The number of pixels in the width of a tile.
	 * @param tileHeight The number of pixels in the height of a tile.
	 */
	public void drawObjects(float offsetX, float offsetY, int tileWidth, int tileHeight) {
		for (GameObject obj : objects) {
			obj.drawSelf((obj.getPosX() - offsetX) * tileWidth, (obj.getPosY() - offsetY) * tileWidth, tileWidth,
					tileHeight, s);

		}
	}

	/*
	 * Draws all of the tiles of the current map onto the screen.
	 * @param offsetX The offset between the top-left of the screen and the 0th tile
	 * in the x direction.
	 * @param offsetY The offset between the top-left of the screen and the 0th tile
	 * in the y direction.
	 * @param tileOffsetX The fraction of the tile that is visible on the left-most 
	 * column of the screen.
	 * @param tileOffsetY The fraction of the tile that is visible on the top-most 
	 * row of the screen.
	 * @visibleTilesX The number of tiles visible on the screen in the x direction.
	 * @visibleTilesY The number of tiles visible on the screen in the y direction.
	 * @param tileWidth The number of pixels in the width of a tile.
	 * @param tileHeight The number of pixels in the height of a tile.
	 */
	public void drawMap(float offsetX, float offsetY, float tileOffsetX, float tileOffsetY, 
			float visibleTilesX, float visibleTilesY, int tileWidth, int tileHeight) {
		for (int x = -1; x < visibleTilesX + 2; x++) {
			for (int y = -1; y < visibleTilesY + 2; y++) {
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
	
	/*
	 * Displays the players statistics like health.
	 */
	public void displayStats() {
		s.fill(255, 0, 0);
		s.text("HP: " + getPlayer().getHealth() + "/" + getPlayer().getMaxHealth(), 20, 20);
	}

	/*
	 * @return whether the up key is pressed or not.
	 */
	public boolean getUp() {
		return up;
	}

	/*
	 * @return whether the down key is pressed or not.
	 */
	public boolean getDown() {
		return down;
	}

	/*
	 * @return whether the left key is pressed or not.
	 */
	public boolean getLeft() {
		return left;
	}

	/*
	 * @return whether the right key is pressed or not.
	 */
	public boolean getRight() {
		return right;
	}

	/*
	 * Sets the booleans for whether keys for movement have been pressed.
	 * The movement keys are the arrow keys and WASD. 
	 */
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
			setMap("testRoom4");
			
		} 
		else if (s.key == 'r' || s.key == 'R') {
			setMap("testRoom");
			
		} 
		else if (s.key == 'm' || s.key == 'M') {
			setMap("testRoom2");
		}
		
	}

	/*
	 * Sets the booleans for whether keys for movement have been released.
	 * The movement keys are the arrow keys and WASD. 
	 */
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
	
	/*
	 * @param cr The creature being checked for a collision with a wall
	 * @param ellapsedTime Used to make velocity calculations smooth at different frame rates.
	 * @return a boolean array representing whether a collision occurred in the x and/or y direction.
	 * The returned array at index 0 tells if there was a collision in the X direction and 
	 * the returned array at index 1 tells if there was a collision in the Y direction.
	 */
	public boolean[] creatureVsWall(Creature cr, float ellapsedTime) {
		// movement/collisions
		newPosX = cr.getVelX() * ellapsedTime + cr.getPosX();
		newPosY = cr.getVelY() * ellapsedTime + cr.getPosY();
		boolean hasCollidedX = false;
		boolean hasCollidedY = false;

		if (newPosX < 0) {
			newPosX = 0;
			hasCollidedX = true;
		}
		if (newPosY < 0) {
			newPosY = 0;
			hasCollidedY = true;
		}

		// x direction
		if (cr.getVelX() < 0 && (currentMap.isSolidTile((int) newPosX,
				(int) (cr.getPosY() + BORDER_OFFSET_FOR_CREATURES))
				|| currentMap.isSolidTile((int) newPosX,
						(int) (cr.getPosY() + 1 - BORDER_OFFSET_FOR_CREATURES)))) {
			cr.setVelX(0);
			newPosX = (int) newPosX + 1;
			hasCollidedX = true;

		} else if (cr.getVelX() > 0 && (currentMap.isSolidTile((int) newPosX + 1,
				(int) (cr.getPosY() + BORDER_OFFSET_FOR_CREATURES))
				|| currentMap.isSolidTile((int) newPosX + 1,
						(int) (cr.getPosY() + 1 - BORDER_OFFSET_FOR_CREATURES)))) {
			cr.setVelX(0);
			newPosX = (int) newPosX;
			hasCollidedX = true;

		}

		// y dir
		if (cr.getVelY() < 0 && (currentMap.isSolidTile(
				(int) (cr.getPosX() + BORDER_OFFSET_FOR_CREATURES), (int) newPosY)
				|| currentMap.isSolidTile(
						(int) (cr.getPosX() + 1 - BORDER_OFFSET_FOR_CREATURES),
						(int) newPosY))) {
			cr.setVelY(0);
			newPosY = (int) newPosY + 1;
			hasCollidedY = true;

		} else if (cr.getVelY() > 0 && (currentMap.isSolidTile(
				(int) (cr.getPosX() + BORDER_OFFSET_FOR_CREATURES), (int) newPosY + 1)
				|| currentMap.isSolidTile(
						(int) (cr.getPosX() + 1 - BORDER_OFFSET_FOR_CREATURES),
						(int) newPosY + 1))) {
			cr.setVelY(0);
			newPosY = (int) newPosY;
			hasCollidedY = true;

		}
		if(hasCollidedX) cr.setPosX(newPosX);
		if(hasCollidedY) cr.setPosY(newPosY);

		return new boolean[] {hasCollidedX, hasCollidedY};
	}
	
	
	
	/*
	 * @param b The Bullet being checked for a collision with a wall
	 * @param ellapsedTime Used to make velocity calculations smooth at different frame rates.
	 * @return Whether the bullet collided with a wall or not
	 */
	public boolean bulletVsWall(Bullet b, float ellapsedTime) {
		// movement/collisions
		newPosX = b.getVelX() * ellapsedTime + b.getPosX();
		newPosY = b.getVelY() * ellapsedTime + b.getPosY();

		if (newPosX < 0 || newPosY < 0)
			return true;

		Rectangle2D.Double bounds = b.getBounds(); // old position
		float x = (float)bounds.x;
		float y = (float)bounds.y;
		
		float width = (float)bounds.width;
		float height = (float)bounds.height;
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
		return false;
	}
	
	/*
	 * @param obj1 The first object being checked for a collision with the the second object
	 * @param obj2 The second object being checked for a collision with the the first object
	 * @param ellapsedTime Used to make velocity calculations smooth at different frame rates.
	 * @return a boolean array representing whether a collision occurred in the x and/or y direction.
	 * The returned array at index 0 tells if there was a collision in the X direction and 
	 * the returned array at index 1 tells if there was a collision in the Y direction.
	 */
	public boolean[] creatureVsCreature(Creature obj1, Creature obj2, float ellapsedTime) {		
		//change this method to return just a boolean because interact() should handle knockback
		if(!obj1.getBounds().intersects(obj2.getBounds())) {
			return new boolean[] {false, false};
		}
		
		boolean hasCollidedX = false;
		boolean hasCollidedY = false;
		newPosX = obj1.getVelX() * ellapsedTime + obj1.getPosX();
		newPosY = obj1.getVelY() * ellapsedTime + obj1.getPosY();
		
		Rectangle2D r1 = obj1.getBounds();
		Rectangle2D r2 = obj1.getBounds();

		// x direction
		if(newPosX < (obj2.getPosX() + r2.getWidth()) && (newPosX + r1.getWidth()) > obj2.getPosX() &&
				obj1.getPosY() < (obj2.getPosY() + r2.getHeight()) && (obj1.getPosY() + r1.getHeight()) > obj2.getPosY()) {
			hasCollidedX = true;
			if (obj1.getVelX() < 0)
				newPosX = obj2.getPosX() + (float)r2.getWidth();
			else if(obj1.getVelX() > 0)
				newPosX = obj2.getPosX() - (float)r2.getWidth();
			obj1.setVelX(0);
		}
		
		// y direction
		if(newPosY < (obj2.getPosY() + r2.getHeight()) && (newPosY + r1.getHeight()) > obj2.getPosY() &&
				obj1.getPosX() < (obj2.getPosX() + r2.getWidth()) && (obj1.getPosX() + r1.getWidth()) > obj2.getPosX()) {
			hasCollidedX = true;
			if (obj1.getVelX() < 0)
				newPosY = obj2.getPosY() + (float)r2.getHeight();
			else if(obj1.getVelX() > 0)
				newPosY = obj2.getPosY() - (float)r2.getHeight();
			obj1.setVelY(0);
		}
				
		/*
		
		if (obj1.getVelX() < 0 && (currentMap.isSolidTile((int) newPosX,
				(int) (obj1.getPosY() + BORDER_OFFSET_FOR_CREATURES))
				|| currentMap.isSolidTile((int) newPosX,
						(int) (obj1.getPosY() + 1 - BORDER_OFFSET_FOR_CREATURES)))) {
			obj1.setVelX(0);
			newPosX = (int) newPosX + 1;
			hasCollidedX = true;

		} else if (obj1.getVelX() > 0 && (currentMap.isSolidTile((int) newPosX + 1,
				(int) (obj1.getPosY() + BORDER_OFFSET_FOR_CREATURES))
				|| currentMap.isSolidTile((int) newPosX + 1,
						(int) (obj1.getPosY() + 1 - BORDER_OFFSET_FOR_CREATURES)))) {
			obj1.setVelX(0);
			newPosX = (int) newPosX;
			hasCollidedX = true;

		}

		// y dir
		if (obj1.getVelY() < 0 && (currentMap.isSolidTile(
				(int) (obj1.getPosX() + BORDER_OFFSET_FOR_CREATURES), (int) newPosY)
				|| currentMap.isSolidTile(
						(int) (obj1.getPosX() + 1 - BORDER_OFFSET_FOR_CREATURES),
						(int) newPosY))) {
			obj1.setVelY(0);
			newPosY = (int) newPosY + 1;
			hasCollidedY = true;

		} else if (obj1.getVelY() > 0 && (currentMap.isSolidTile(
				(int) (obj1.getPosX() + BORDER_OFFSET_FOR_CREATURES), (int) newPosY + 1)
				|| currentMap.isSolidTile(
						(int) (obj1.getPosX() + 1 - BORDER_OFFSET_FOR_CREATURES),
						(int) newPosY + 1))) {
			obj1.setVelY(0);
			newPosY = (int) newPosY;
			hasCollidedY = true;

		}
		*/
		if(hasCollidedX) obj1.setPosX(newPosX);
		if(hasCollidedY) obj1.setPosY(newPosY);

		return new boolean[] {hasCollidedX, hasCollidedY};
	}


}
