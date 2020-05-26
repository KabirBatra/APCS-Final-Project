package gameobject;

import assets.Assets;
import assets.SpriteSheet;
import processing.core.PApplet;
import processing.core.PImage;
import running.GameHandler;
/**
 * This creates an enem
 * @author kaiechen
 *
 */
public class EnemyBoss extends Enemy {
	
	protected static SpriteSheet ss = Assets.getSpriteSheet("BossSheet");

	public EnemyBoss(float x, float y, int fireRate, String name, GameHandler handler) {
		super(x, y, 6, name, handler);

		health = 600;
		maxHealth = 600;
		maxSpeed = 1;

		// TODO Auto-generated constructor stub
	}

	public void drawSelf(float x, float y, int tileWidth, int tileHeight, PApplet s) {
//		s.fill(0, 255, 0);
//		s.rect(x, y, tileWidth, tileHeight);
		// hello
		setSpriteSheet(ss);
		if (currentSprite != null) {
			PImage img = new PImage((java.awt.Image) currentSprite);
			s.image(img, x, y, tileWidth, tileHeight);
			s.fill(255, 0, 0);
			s.rect(x, y, tileWidth, 10);
			s.fill(0, 255, 0);
			s.rect(x, y, tileWidth * (float) health / maxHealth, 10);
		} else {
			s.fill(0);
			s.rect(x, y, tileWidth, tileHeight);
		}
	}

}
