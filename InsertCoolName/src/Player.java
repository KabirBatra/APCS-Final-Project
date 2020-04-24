import processing.core.PApplet;

public class Player extends Creature {

	public Player(float x, float y, String name) {
		super(x, y, name);
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawSelf(PApplet p) {
		// TODO Auto-generated method stub
		p.rect(posX, posY, 32, 32);
	}

	@Override
	public void onInteract(GameObject obj) {
		// TODO Auto-generated method stub
		
	}
}
