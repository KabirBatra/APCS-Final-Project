
import processing.core.PApplet;

public class Enemy extends Creature {

	private GameHandler handler;

	private boolean canShoot;
	public Enemy(float x, float y, String name, GameHandler handler, SpriteSheet ss) {
		super(x, y, name, ss);
		this.handler = handler;
		velX = 1;
		velY = 1;
		// maxVel = speed;
		maxSpeed = 5; // speed should be predefined per class that extends enemy
		canShoot = false;
	}

	public void update(float ellapsedTime) {
		super.update(ellapsedTime);
		// ai movement per tick
		// velX = (int)(Math.random() * 15) - 7;
		// velY = (int)(Math.random() * 15) - 7;

		// Must be called upon enemy object
		Enemy npc = this;

		Player thePlayer = handler.getPlayer();
		Enemy theEnemy = npc;

		float enemyMaxSpeed = theEnemy.getMaxSpeed();

		float playerX = thePlayer.getPosX();
		float playerY = thePlayer.getPosY();

		float enemyX = theEnemy.getPosX();
		float enemyY = theEnemy.getPosY();

		float theDiffX = playerX - enemyX;
		float theDiffY = playerY - enemyY;
		float theDist = (float) (Math.sqrt(Math.pow((double) theDiffX, 2d) + Math.pow((double) theDiffY, 2d)));

		float theRatio = theDiffY / theDiffX;
		float theAngle = (float) Math.atan((double) theRatio);
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

		while (!handler.getCurrentMap().isSolidTile(checkingX, checkingY)) {
			if (checkingX == aproxPlayerX && checkingY == aproxPlayerY && theDist < 10) {
				// npc.shoot(handler.getSurface());
				canShoot = true;
				checkingX = (int) enemyX;
				checkingY = (int) enemyY;
				break;
			}
			canShoot = false;
			bulletX += Math.cos(theAngle);
			bulletY += Math.sin(theAngle);
			checkingX = (int) (bulletX);
			checkingY = (int) (bulletY);

		}

		int nextPosX = (int) (enemyX + Math.cos(theAngle));
		int nextPosY = (int) (enemyY + Math.sin(theAngle));

		// if there is a block in the way the enemy will try to move closer to the
		// player
		if (handler.getCurrentMap().isSolidTile(nextPosX, nextPosY)) {

		} else {

			theEnemy.setVelX((float) (enemyMaxSpeed * Math.cos(theAngle)));
			theEnemy.setVelY((float) (enemyMaxSpeed * Math.sin(theAngle)));
			// System.out.println("this shouldn't print if it is success success!");

		}

	}



	public boolean canShoot() {
		return canShoot;
	}

	public void drawSelf(float x, float y, int tileWidth, int tileHeight, PApplet s) {
		s.fill(0, 255, 0);
		s.rect(x, y, tileWidth, tileHeight);
	}

	public void onInteract(GameObject obj) {

	}

	public void shoot(PApplet s) {
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
		float angle;

		if (closestPlayer != null) {
			angle = (float) Math.atan((posY - closestPlayer.posY) / (posX - closestPlayer.posX));
			if (posX > closestPlayer.posX) {
				angle += Math.PI;
			}
		} else {
			angle = 0; // shoot in the direction moving (using enum's index?)
		}
		handler.addGameObject(new NormalBullet(this.posX + 0.5f, this.posY + 0.5f, angle, this, "308"));
	}

}
