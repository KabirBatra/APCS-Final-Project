package running;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import assets.*;
import assets.map.Map;
import gameobject.*;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

/**
 * The class that controls and maintains nearly everything in the game. Updates,
 * collisions, controls, the GameObjects, and the current map are all stored
 * here.
 * 
 * @author Kabir Batra and Kaie Chen
 */
public class GameHandler {

	private PApplet s;
	private WindowHandler wh;

	// so that player can access it and shoot toward nearest enemy
	public LinkedList<GameObject> objects;

	private Map currentMap;
	// private SpriteSheet playerSpriteSheet;

	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;

	private int level = 0;
	private boolean transition = false;
	float frames = 0;

	private static final float BORDER_OFFSET_FOR_CREATURES = 0.1F; // values from 0.00001 - 0.1

	/**
	 * Creates the objects list and sets all of the current keys pressed to false.
	 * 
	 * @param wh      the WindowHandler for changing windows
	 * @param surface the PApplet for drawing things like the objects and the map
	 */
	public GameHandler(WindowHandler wh, PApplet surface) {
		this.wh = wh;
		s = surface;
		objects = new LinkedList<GameObject>();

		up = false;
		down = false;
		left = false;
		right = false;
	}

	/**
	 * Sets the current map to mapName and uses the current player if it already
	 * exists.
	 * 
	 * @param mapName The map to switch to.
	 */
	public void setMap(String mapName) {
		Player temp = getPlayer();
		if (currentMap != null) {
			objects.clear();
		}
		currentMap = Assets.getMap(mapName);
		currentMap.populateGameObjects(temp);

	}

	public Map getCurrentMap() {
		return currentMap;
	}

	public void addGameObject(GameObject obj) {
		objects.add(obj);
	}

	/**
	 * @returns the current player; returns null if the player has not been created
	 *          yet
	 */
	public Player getPlayer() {
		for (GameObject obj : objects) {
			if (obj instanceof Player)
				return (Player) obj;
		}
		return null;
	}

	/**
	 * The update method. This method is called every frame and calls the update
	 * method on every object. Then, it calculates collisions and determines which
	 * Bullets should be removed from the list of objects. Finally, it moves each
	 * object via its respective velocity.
	 * 
	 * @param ellapsedTime The delta time after each frame used to make velocities
	 *                     smooth and constant even when frames are slow.
	 */
	public void tick(float ellapsedTime) {

		if (getPlayer().getHealth() <= 0) {

			wh.setCurrentWindow("gameOver");

		}

		LinkedList<Bullet> bulletsToRemove = new LinkedList<Bullet>();
		LinkedList<Enemy> enemiesThatCanShoot = new LinkedList<Enemy>();

		PVector newPos = new PVector();

		boolean allEnemiesDead = true; // set to false if found enemy that is alive

		for (GameObject obj : objects) {

			obj.update(ellapsedTime);

			if (obj instanceof DynamicObject) {
				DynamicObject obj1 = (DynamicObject) obj;
				newPos.x = obj1.getPosX() + obj1.getVelX() * ellapsedTime;
				newPos.y = obj1.getPosY() + obj1.getVelY() * ellapsedTime;

				if (obj instanceof Creature) {
					Creature cr = (Creature) obj;
					creatureVsWall(cr, newPos);

				}

				else if (obj instanceof Bullet) {
					Bullet b = (Bullet) obj;
					if (bulletVsWall(b, newPos)) {
						bulletsToRemove.add((Bullet) obj);
					}
				}

				if (obj.isSolidVsGameObject()) {
					for (GameObject obj2 : objects) {
						if (obj == obj2) {
							continue;
						}
						// creature creature
						if (obj instanceof Creature && obj2 instanceof Creature) {
							if (creatureVsCreature((Creature) obj, (Creature) obj2, newPos))
								obj2.onInteract(obj);

							// obj.onInteract(obj2);
						}

						if (obj.getBounds().intersects(obj2.getBounds())) {
							// creature bullet
							if (obj instanceof Creature && obj2 instanceof Bullet) {
								if (obj2.onInteract(obj))
									if (bulletsToRemove.indexOf(obj2) == -1)
										bulletsToRemove.add((Bullet) obj2);
							}
							// bullet bullet
							else if (obj instanceof Bullet && obj2 instanceof Bullet) {
								if (bulletsToRemove.indexOf(obj) == -1)
									bulletsToRemove.add((Bullet) obj);
								if (bulletsToRemove.indexOf(obj2) == -1)
									bulletsToRemove.add((Bullet) obj2);
							}
						}
					}
				}
				// if not solid vs gameobject here
				obj.setPos(newPos.x, newPos.y);

				if (obj instanceof Enemy) {
					Enemy temp = (Enemy) obj;
					if (temp.isShooting()) {
						enemiesThatCanShoot.add((Enemy) obj);
					}

					if (!temp.isDead()) {
						allEnemiesDead = false;
					}
				}
			}

		}
		// after looping through all of the objects
		for (GameObject destroyedBullet : bulletsToRemove) {
			objects.remove(destroyedBullet);
		}

		for (Enemy enemy : enemiesThatCanShoot) {
			enemy.shoot();
		}

		if (allEnemiesDead) {
			transition = true;

			if (frames / 60 > 2) {
				frames = 0;
				transition = false;
				if (currentMap.startNextWave()) {

					System.out.println("THE NEXT WAVE HAS STARTED AND ENEMIES ARE BACK!");

					if (getPlayer().getHealth() <= getPlayer().getMaxHealth() / 2) {
						getPlayer().setHealth(getPlayer().getHealth() + getPlayer().getMaxHealth() / 2);
					} else {
						getPlayer().setHealth(getPlayer().getMaxHealth());
					}

				} else {

					if (level == 0) {
						getPlayer().setMaxHealth(600);
						getPlayer().setHealth(600);

					} else if (level >= 1) {
						getPlayer().setMaxHealth((600 + (100 * (level))));
						getPlayer().setHealth(((600 + (100 * level))));
					}
					// System.out.println("THE WAVES ARE OVER AND YOU WIN");

//					System.out.println(level + "this is the level");

					setMap("Level" + level);

					level++;

				}
			} else {
				frames++;
				System.out.println(frames);

			}
		}
	}

	/**
	 * Draws all of the GameObjects onto the screen.
	 * 
	 * @param offsetX    The offset between the top-left of the screen and the 0th
	 *                   tile in the x direction.
	 * 
	 * @param offsetY    The offset between the top-left of the screen and the 0th
	 *                   tile in the x direction.
	 * 
	 * @param tileWidth  The number of pixels in the width of a tile.
	 * 
	 * @param tileHeight The number of pixels in the height of a tile.
	 */
	public void drawObjects(float offsetX, float offsetY, int tileWidth, int tileHeight) {
		for (GameObject obj : objects) {
			obj.drawSelf((obj.getPosX() - offsetX) * tileWidth, (obj.getPosY() - offsetY) * tileWidth, tileWidth,
					tileHeight, s);
		}
		Player p = getPlayer();
		p.drawSelf((getPlayer().getPosX() - offsetX) * tileWidth, (p.getPosY() - offsetY) * tileWidth, tileWidth,
				tileHeight, s);
	}

	/**
	 * Draws all of the tiles of the current map onto the screen.
	 * 
	 * @param offsetX     The offset between the top-left of the screen and the 0th
	 *                    tile in the x direction.
	 * 
	 * @param offsetY     The offset between the top-left of the screen and the 0th
	 *                    tile in the y direction.
	 * 
	 * @param tileOffsetX The fraction of the tile that is visible on the left-most
	 *                    column of the screen.
	 * 
	 * @param tileOffsetY The fraction of the tile that is visible on the top-most
	 *                    row of the screen.
	 * 
	 * @visibleTilesX The number of tiles visible on the screen in the x direction.
	 * 
	 * @visibleTilesY The number of tiles visible on the screen in the y direction.
	 * 
	 * @param tileWidth  The number of pixels in the width of a tile.
	 * 
	 * @param tileHeight The number of pixels in the height of a tile.
	 */
	public void drawMap(float offsetX, float offsetY, float tileOffsetX, float tileOffsetY, float visibleTilesX,
			float visibleTilesY, int tileWidth, int tileHeight) {
		for (int x = -1; x < visibleTilesX + 1; x++) {
			for (int y = -1; y < visibleTilesY + 1; y++) {
				Type tile = currentMap.getTile(x + (int) offsetX, y + (int) offsetY);

//				s.image(img, a, b);
				SpriteSheet mapSheet = currentMap.getSpriteSheet();
				BufferedImage currentSprite = null;
				if (tile == Type.Wall) {
					currentSprite = mapSheet.getSprite(0, 0);
					// s.fill(0, 0, 255);
				} else if (tile == Type.Floor || tile == Type.Enemy || tile == Type.Player || tile == Type.EnemyBoss) {
					// s.fill(255);
					currentSprite = mapSheet.getSprite(1, 0);
				} else { // if its Type.None (doesnt exist)
							// s.fill(51);
					currentSprite = mapSheet.getSprite(2, 0);
				}
				// s.rect(x * tileWidth - tileOffsetX, y * tileHeight - tileOffsetY, tileWidth +
				// 1f, tileHeight + 1f);

				s.image(new PImage((java.awt.Image) currentSprite), x * tileWidth - tileOffsetX,
						y * tileHeight - tileOffsetY, tileWidth + 1f, tileHeight + 1f);
			}
		}
	}

	/**
	 * Displays the players statistics like health.
	 */
	public void displayStats() {
		s.fill(255, 0, 0);
		s.textAlign(s.CORNER);
		s.text("HP: " + getPlayer().getHealth() + "/" + getPlayer().getMaxHealth(), 20, 20);
	}

	/**
	 * @return whether the up key is pressed or not.
	 */
	public boolean getUp() {
		return up;
	}

	/**
	 * @return whether the down key is pressed or not.
	 */
	public boolean getDown() {
		return down;
	}

	/**
	 * @return whether the left key is pressed or not.
	 */
	public boolean getLeft() {
		return left;
	}

	/**
	 * @return whether the right key is pressed or not.
	 */
	public boolean getRight() {
		return right;
	}

	/**
	 * Sets the booleans for whether keys for movement have been pressed. The
	 * movement keys are the arrow keys and WASD.
	 */
	public void keyPressed() {
		if (s.keyCode == PConstants.UP || s.key == 'w' || s.key == 'W') {
			up = true;

		} else if (s.keyCode == PConstants.DOWN || s.key == 's' || s.key == 'S') {
			down = true;

		} else if (s.keyCode == PConstants.LEFT || s.key == 'a' || s.key == 'A') {
			left = true;

		} else if (s.keyCode == PConstants.RIGHT || s.key == 'd' || s.key == 'D') {
			right = true;

		} else if (s.key == ' ') {
			getPlayer().shoot(s);

		} else if (s.key == 'p' || s.key == 'P') {
			((DrawingSurface) (s)).getWindowHandler().setCurrentWindow("pause");
		}
		/*
		 * else if (s.key == 'f' || s.key == 'F') { setMap("testRoom4");
		 * 
		 * } else if (s.key == 'r' || s.key == 'R') { setMap("testRoom");
		 * 
		 * } else if (s.key == 'm' || s.key == 'M') { setMap("testRoom2"); } else if
		 * (s.key == 'b' || s.key == 'B') { setMap("Level1"); }
		 * 
		 * 
		 * 
		 */
	}

	/**
	 * Sets the booleans for whether keys for movement have been released. The
	 * movement keys are the arrow keys and WASD.
	 */
	public void keyReleased() {
		if (s.keyCode == PConstants.UP || s.key == 'w' || s.key == 'W') {
			up = false;

		} else if (s.keyCode == PConstants.DOWN || s.key == 's' || s.key == 'S') {
			down = false;

		} else if (s.keyCode == PConstants.LEFT || s.key == 'a' || s.key == 'A') {
			left = false;

		} else if (s.keyCode == PConstants.RIGHT || s.key == 'd' || s.key == 'D') {
			right = false;

		}

	}

	/**
	 * Sets all the current states of keys pressed to false
	 */
	public void releaseAllKeys() {
		up = false;
		down = false;
		left = false;
		right = false;

	}

	public boolean getTransition() {
		return transition;
	}

	/**
	 * Changes the value of the parameter newPos based on whether a collision
	 * occurred or not
	 * 
	 * @param cr     The creature being checked for a collision with a wall
	 * 
	 * @param newPos The new position of the creature that is being collision
	 *               checked
	 * 
	 */
	public void creatureVsWall(Creature cr, PVector newPos) {
		// movement/collisions

		if (newPos.x < 0) {
			newPos.x = 0;
		}
		if (newPos.y < 0) {
			newPos.y = 0;
		}

		// x direction
		if (cr.getVelX() < 0 && (currentMap.isSolidTile((int) newPos.x,
				(int) (cr.getPosY() + BORDER_OFFSET_FOR_CREATURES))
				|| currentMap.isSolidTile((int) newPos.x, (int) (cr.getPosY() + 1 - BORDER_OFFSET_FOR_CREATURES)))) {
			cr.setVelX(0);
			newPos.x = (int) newPos.x + 1;

		} else if (cr.getVelX() > 0
				&& (currentMap.isSolidTile((int) newPos.x + 1, (int) (cr.getPosY() + BORDER_OFFSET_FOR_CREATURES))
						|| currentMap.isSolidTile((int) newPos.x + 1,
								(int) (cr.getPosY() + 1 - BORDER_OFFSET_FOR_CREATURES)))) {
			cr.setVelX(0);
			newPos.x = (int) newPos.x;

		}

		// y dir
		if (cr.getVelY() < 0 && (currentMap.isSolidTile((int) (cr.getPosX() + BORDER_OFFSET_FOR_CREATURES),
				(int) newPos.y)
				|| currentMap.isSolidTile((int) (cr.getPosX() + 1 - BORDER_OFFSET_FOR_CREATURES), (int) newPos.y))) {
			cr.setVelY(0);
			newPos.y = (int) newPos.y + 1;

		} else if (cr.getVelY() > 0
				&& (currentMap.isSolidTile((int) (cr.getPosX() + BORDER_OFFSET_FOR_CREATURES), (int) newPos.y + 1)
						|| currentMap.isSolidTile((int) (cr.getPosX() + 1 - BORDER_OFFSET_FOR_CREATURES),
								(int) newPos.y + 1))) {
			cr.setVelY(0);
			newPos.y = (int) newPos.y;

		}

	}

	/**
	 * @param b      The Bullet being checked for a collision with a wall
	 * 
	 * @param newPos The new position of the bullet that is being collision checked
	 * 
	 * @return Whether the bullet collided with a wall or not
	 */
	public boolean bulletVsWall(Bullet b, PVector newPos) {
		// movement/collisions

		if (newPos.x < 0 || newPos.y < 0)
			return true;

		Rectangle2D.Double bounds = b.getBounds(); // old position
		float x = (float) bounds.x;
		float y = (float) bounds.y;

		float width = (float) bounds.width;
		float height = (float) bounds.height;
		// bounds.width;
		// bounds.height;

		// x direction
		if (b.getVelX() < 0 && (currentMap.isSolidTile((int) newPos.x, (int) y)
				|| currentMap.isSolidTile((int) newPos.x, (int) (y + height)))) {
			return true;
		} else if (b.getVelX() > 0 && (currentMap.isSolidTile((int) (newPos.x + width), (int) y)
				|| currentMap.isSolidTile((int) (newPos.x + width), (int) (y + height)))) {
			return true;
		}

		// y dir
		if (b.getVelY() < 0 && (currentMap.isSolidTile((int) (x), (int) newPos.y)
				|| currentMap.isSolidTile((int) (x + width), (int) newPos.y))) {
			return true;
		} else if (b.getVelY() > 0 && (currentMap.isSolidTile((int) (x), (int) (newPos.y + height))
				|| currentMap.isSolidTile((int) (x + width), (int) (newPos.y + height)))) {
			return true;
		}

		// no collision
		return false;
	}

	/**
	 * Changes the value of the parameter newPos based on whether a collision
	 * occurred or not
	 * 
	 * @param obj1   The first object being checked for a collision with the the
	 *               second object
	 * 
	 * @param obj2   The second object being checked for a collision with the the
	 *               first object
	 * 
	 * @param newPos The new position of the bullet that is being collision checked
	 * @return true if there was a collision
	 */
	public boolean creatureVsCreature(Creature obj1, Creature obj2, PVector newPos) {

		if (obj1.isDead() || obj2.isDead()) {
			return false;
		}

		boolean collisionOccurred = false;

		Rectangle2D r1 = obj1.getBounds();
		Rectangle2D r2 = obj1.getBounds();

		// x direction
		// DO NOT DELETE THIS
		if (newPos.x < (obj2.getPosX() + r2.getWidth()) && (newPos.x + r1.getWidth()) > obj2.getPosX()
				&& obj1.getPosY() < (obj2.getPosY() + r2.getHeight())
				&& (obj1.getPosY() + r1.getHeight()) > obj2.getPosY()) {
			if (obj1.getVelX() < 0)
				newPos.x = obj2.getPosX() + (float) r2.getWidth();
			else if (obj1.getVelX() > 0)
				newPos.x = obj2.getPosX() - (float) r2.getWidth();
			obj1.setVelX(0);
			collisionOccurred = true;
		}

		// y direction
		if (newPos.y < (obj2.getPosY() + r2.getHeight()) && (newPos.y + r1.getHeight()) > obj2.getPosY()
				&& obj1.getPosX() < (obj2.getPosX() + r2.getWidth())
				&& (obj1.getPosX() + r1.getWidth()) > obj2.getPosX()) {
			if (obj1.getVelY() < 0)
				newPos.y = obj2.getPosY() + (float) r2.getHeight();
			else if (obj1.getVelY() > 0)
				newPos.y = obj2.getPosY() - (float) r2.getHeight();
			obj1.setVelY(0);
			collisionOccurred = true;
		}
		return collisionOccurred;
	}
}