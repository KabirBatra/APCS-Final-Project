package gameobject;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import assets.Assets;
import assets.SpriteSheet;
import gameobject.Creature.AnimationState;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This is the class that represents a normal bullet!
 * 
 * @author kaiechen
 *
 */
public class NormalBullet extends Bullet {

	private static SpriteSheet ss = Assets.getSpriteSheet("normalBulletSheet");

	protected static float speed = 13f;
	protected static int damage = 10;
	private BufferedImage currentSprite;

	private float timer;
	private int graphicsState;

	/**
	 * This creates a normalBullet object!
	 * @param x the x position of the bullet! (top left corner! Must be a valid value within the map!)
	 * @param y the y position of the bullet! (top left corner! Must be a valid value within the map!)
	 * @param angle the angle to which the bullet will travel! must be within - pi to pi
	 * @param shooter must be an initialized game object, as this marks who shoot this bullet!
	 * @param name the name of this bullet!
	 */
	public NormalBullet(float x, float y, float angle, GameObject shooter, String name) {
		super(x, y, angle, speed, shooter, name);
		timer = 0;
		graphicsState = (int) (Math.random() * 8);
	}

	public void update(float ellapsedTime) {
		timer += ellapsedTime;
		if (timer >= 0.1) {
			timer -= 0.1;
			graphicsState++;
			graphicsState %= 8;
		}
		currentSprite = ss.getSprite(graphicsState, 0);
	}

	public void drawSelf(float x, float y, int tileWidth, int tileHeight, PApplet s) {
		if (currentSprite != null) {
			PImage img = new PImage((java.awt.Image) currentSprite);
			s.image(img, x, y, tileWidth / 4f, tileHeight / 4f);
		}
		// s.rect(x, y, tileWidth/4f, tileHeight/4f);

	}

	public boolean onInteract(GameObject obj) {
		if (obj instanceof Creature && obj != shotBy && ((Creature) obj).state != AnimationState.DEAD) {

			attack((Creature) obj);
			return true;
		}
		return false;
	}

	public void attack(Creature cr) {
		cr.deltaHealth(-damage);
	}

	public Rectangle2D.Double getBounds() {
		return new Rectangle2D.Double(posX, posY, 1f / 4, 1f / 4);
	}

}
