package window;
import processing.core.PApplet;
import running.*;

/**
 * Window that shows Game Over screen
 */
public class GameOverWindow extends Window {
	long sec = 0 ;
	long frames = 0;
	public GameOverWindow(WindowHandler wh, PApplet surface) {
		super(wh, surface);
	}
	
	public void setup() {
		s.fill(255, 0, 0);
	}
	
	public void draw() {
		frames++;
		sec = frames / 10;
	
		if (sec >= 18) {
			s.background(0);
			s.noStroke();
			s.fill(255,0,0);
			s.background(0);
			s.textAlign(s.CENTER);
			s.text("GAME OVER", s.width/2, s.height/2);
			//display the button to restart the game
			//call this method: ((DrawingSurface)(s)).reset();
			if(sec >= 30) 
				((DrawingSurface)(s)).reset();
		} 
		else {
			s.fill(0, sec);
			s.rect(0, 0, s.width, s.height);
		}
	}

	public void keyPressed() {}
	public void keyReleased() {}
	
	
}
