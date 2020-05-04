
import processing.core.PApplet;

public class Enemy extends Creature {

	private GameHandler handler;
	private float maxSpeed;
	
	public Enemy(float x, float y, float speed, GameHandler handler, String name, SpriteSheet ss) {
		super(x, y, name, ss);
		this.handler = handler;
		velX = 1;
		velY = 1;
		maxSpeed = speed;
	}

	public void update(float ellapsedTime) {
		super.update(ellapsedTime);
		//ai movement per tick
		//if(ellapsedTime == 0)
		velX = (int)(Math.random() * 15) - 7;
		velY = (int)(Math.random() * 15) - 7;
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
		for(GameObject obj : handler.objects) {
			if(obj instanceof Player) {
				
				distanceSquared = (posX - obj.posX)*(posX - obj.posX) + (posY - obj.posY)*(posY - obj.posY);
				
				if(closestPlayer == null || distanceSquared < closestDistanceSquared) {
					closestPlayer = (Player)obj;
					closestDistanceSquared = distanceSquared;
				}
			}
		}
		float angle;

		if(closestPlayer != null) {
			angle = (float)Math.atan((posY - closestPlayer.posY)/(posX - closestPlayer.posX));
			if(posX > closestPlayer.posX) {
				angle+=Math.PI;
			}
		} else {
			angle = 0; // shoot in the direction moving (using enum's index?)
		}
		handler.addGameObject(new NormalBullet(handler.getPlayer().getPosX()+0.5f, handler.getPlayer().getPosY()+0.5f, angle, "308"));
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

}
