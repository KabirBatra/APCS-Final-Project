package window;

import processing.core.PApplet;
import running.*;

/**
 * Window that shows Game Over screen
 */
public class GameOverWindow extends Window {
	private long sec = 0;
	private long frames = 0;

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
			s.fill(255, 0, 0);
			s.textSize(70);
			s.textAlign(s.CENTER);
			s.text("GAME OVER", s.width/2, s.height/2);

			s.stroke(120);
			s.fill(150);

			if (overButton(s.width / 4, s.height * 3 / 4, s.mouseX, s.mouseY, s.width / 2, s.height / 8)) {
					s.fill(70);
					if (s.mousePressed && s.mouseButton == s.LEFT) {
						System.out.println("BOOOP!");

						((DrawingSurface)(s)).reset();
					}

				}

			
				s.rect(s.width / 4, s.height * 3 / 4, s.width / 2, s.height / 8);
			
				s.fill(125, 0, 0);
				s.textSize(40);
				s.textAlign(s.CENTER);
				s.text("Try Again?", s.width/2, s.height *  3 / 4 + s.height / 16);
		} 
		else {

			s.fill(0, sec);
			s.rect(0, 0, s.width, s.height);

		}
	}

	boolean overButton(float rectX3, float rectY3, float mouseX, float mouseY, float f, float g) {
		if (mouseX >= rectX3 && mouseX <= rectX3 + f && mouseY >= rectY3 && mouseY <= rectY3 + g) {
			return true;
		} else {
			return false;
		}
	}

	public void keyPressed() {}

	public void keyReleased() {}
}
