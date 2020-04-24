
import processing.core.PApplet;

public class GameWindow extends Window {
	
	PApplet s;
	Map room;
	
	public GameWindow(PApplet surface) {
		this.s = surface;
		room = new Map("forest", null, null);
	}

	public void setup() {
		
	}

	public void draw() {
		s.background(0);
		for(int w = 0; w < room.getWidth(); w++) {
			for(int h = 0; h < room.getHeight(); h++) {
				if(room.getTile(w, h) == 0) {
					s.fill(0);
				} else {
					s.fill(0, 0, 255);
				}
				s.rect(w * 32, h * 32, 32, 32);
			}
		}
	}
	

}
