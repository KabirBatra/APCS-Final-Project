package window;
import processing.core.PApplet;
import running.WindowHandler;

/*
 * Window that shows Game Over screen
 */
public class GameOverWindow extends Window {
	
	public GameOverWindow(WindowHandler wh, PApplet surface) {
		super(wh, surface);
	}
	public void setup() {
		s.fill(255, 0, 0);
	}
	public void draw() {
		s.background(0);
		s.text("GAME OVER", s.width/2, s.height/2);
	}
	public void keyPressed() {}
	public void keyReleased() {}
}
