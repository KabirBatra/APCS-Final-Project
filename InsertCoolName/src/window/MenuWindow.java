package window;
import processing.core.PApplet;

public class MenuWindow extends Window {

	public MenuWindow(PApplet surface) {
		super(surface);
	}

	public void setup() {
		s.background(0);
		s.fill(0, 255, 180);
		s.noStroke();
	}

	public void draw() {
		s.rect(10, 10, 50, 50);
	}

}
