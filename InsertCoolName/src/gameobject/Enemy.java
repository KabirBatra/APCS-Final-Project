package gameobject;

import assets.Assets;
import assets.SpriteSheet;
import processing.core.PApplet;
import processing.core.PImage;
import running.GameHandler;

/*
 * Represents an enemy in the game
 * @author Kaie Chen and Kabir Batra
 */
public class Enemy extends Creature {
	
	private static SpriteSheet ss = Assets.getSpriteSheet("slimeSheet");

	private GameHandler handler;

	private boolean isShooting;
	private int coolDown = 0;
	private int maximumCoolDown;
	
	private boolean canMoveX = true;
	private boolean canMoveY = true;

	public Enemy(float x, float y, int fireRate, String name, GameHandler handler) {
		super(x, y, name, ss);
		this.handler = handler;
		velX = 1;
		velY = 1;
		// maxVel = speed;
		maxSpeed = 3; // speed should be predefined per class that extends enemy
		isShooting = false;
		maximumCoolDown = fireRate;
		health = 50;
		maxHealth = 50;
	}

	/*
	 * Moves the enemy using the player's position
	 */
	public void update(float ellapsedTime) {
		super.update(ellapsedTime);
		// ai movement per tick
		
		if(state == AnimationState.DEAD) {
			isShooting = false;
			return; // dont do anything if dead
		}
		
		Player thePlayer = handler.getPlayer();

		float enemyMaxSpeed = this.getMaxSpeed();

		float playerX = thePlayer.getPosX();
		float playerY = thePlayer.getPosY();

		float enemyX = this.getPosX();
		float enemyY = this.getPosY();

		float theDiffX = playerX - enemyX;
		float theDiffY = playerY - enemyY;
		float theDist = (float) (Math.sqrt(theDiffX * theDiffX + theDiffY * theDiffY));

		float theAngle = (float) Math.atan(theDiffY / (double) theDiffX);
		if (theDiffX < 0) {
			theAngle += Math.PI;
		}

		// checking to see if there is clear line between enemy and player
		float bulletX = enemyX;
		float bulletY = enemyY;
		int checkingX = (int) (enemyX);
		int checkingY = (int) (enemyY);
		int aproxPlayerX = (int) (playerX);
		int aproxPlayerY = (int) (playerY);

		// Must be close so the shots can actually hit
		canMoveX = false;
		canMoveY = false;
		while (!handler.getCurrentMap().isSolidTile(checkingX, checkingY)) {
			//System.out.println(theDist);
			if (checkingX == aproxPlayerX && checkingY == aproxPlayerY && theDist < 10) {
				isShooting = true;
				checkingX = (int) enemyX;
				checkingY = (int) enemyY;
				canMoveX = true;
				canMoveY = true;
				break;
			}
			isShooting = false;
			bulletX += Math.cos(theAngle);
			bulletY += Math.sin(theAngle);
			checkingX = (int) (bulletX);
			checkingY = (int) (bulletY);

		}

		int nextPosX = (int) (enemyX + Math.cos(theAngle));
		int nextPosY = (int) (enemyY + Math.sin(theAngle));

		// if there is a block in the way the enemy will try to move closer to the
		// player
		if (!handler.getCurrentMap().isSolidTile(nextPosX, nextPosY)) {
			if (canMoveX) {

				this.setVelX((float) (enemyMaxSpeed * Math.cos(theAngle)));

				// System.out.println("this shouldn't print if it is success success!");

			}
			if (canMoveY) {
				this.setVelY((float) (enemyMaxSpeed * Math.sin(theAngle)));
			}
		}
	}

	public void setCanMoveX(boolean maybe) {
		canMoveX = maybe;
	}

	public void setCanMoveY(boolean maybe) {
		canMoveY = maybe;
	}

	/*
	 * @return whether the enemy is in the state of shooting or not.
	 */
	public boolean isShooting() {
		return isShooting;
	}

	/*
	 * Draws the enemy as a green square
	 */
	public void drawSelf(float x, float y, int tileWidth, int tileHeight, PApplet s) {
//		s.fill(0, 255, 0);
//		s.rect(x, y, tileWidth, tileHeight);
		if(currentSprite != null) {
			PImage img = new PImage((java.awt.Image)currentSprite); 
			s.image(img, x, y, tileWidth, tileHeight);
			s.fill(255,0,0);
			s.rect(x, y, tileWidth, 10);
			s.fill(0,255,0);
			s.rect(x, y, tileWidth * (float)health/maxHealth, 10);
		}
		else {
			s.fill(0);
			s.rect(x, y, tileWidth, tileHeight);
		}
	}

	/*
	 * Called when a player or bullet collides with the enemy. Does knock-back
	 */
	public boolean onInteract(GameObject obj) {
		if(obj instanceof Player && state != AnimationState.DEAD) {
			attack((Player)obj); // shouldnt attack if dead
			return true;
		}
		if(obj instanceof Bullet && state != AnimationState.DEAD) {
			//take knock back 
//			velX *= -1; // temporary code
//			velY *= -1;
			return true;
		}
		return false;
	}

	/*
	 * Creates a bullet object traveling in the direction of the player.
	 */
	private float angle;

	public void shoot() {

		Player closestPlayer = null;
		float closestDistanceSquared = -1;
		float distanceSquared;
		for (GameObject obj : handler.objects) {
			if (obj instanceof Player) {

				distanceSquared = (posX - obj.posX) * (posX - obj.posX) + (posY - obj.posY) * (posY - obj.posY);

				if (closestPlayer == null || distanceSquared < closestDistanceSquared) {
					closestPlayer = (Player) obj;
					closestDistanceSquared = distanceSquared;
				}
			}
		}

		if (closestPlayer != null) {
			angle = (float) Math.atan((posY - closestPlayer.posY) / (posX - closestPlayer.posX));
			if (posX > closestPlayer.posX) {
				angle += Math.PI;
			}
		} else {
			angle = 0; // shoot in the direction moving (using enum's index?)
		}

		if (coolDown == maximumCoolDown) {
			NormalBullet bullet = new NormalBullet(posX + 0.5f, posY + 0.5f, angle, this, "308");
			handler.addGameObject(bullet);
			coolDown = 0;
		} else {
			coolDown++;
		}

	}
	
	public void attack(Player p) {
		p.deltaHealth(-5);
	}



}
