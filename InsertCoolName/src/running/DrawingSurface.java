package running;

import processing.core.PApplet;
import window.GameWindow;
import window.MenuWindow;
/**
 * This is the main method of the project that inherits Processing's PApplet
 * @author Kabir Batra
 */
public class DrawingSurface extends PApplet {

	private WindowHandler wh;
	
	/*
	 * Starts running the Processing Applet
	 * @param args not used
	 */
	public static void main(String[] args) {
		System.out.println("hello world");
		PApplet.main("running.DrawingSurface");
	}
	
	/**
	 * Creates a WindowHandler and adds all the windows to it
	 */
	public DrawingSurface() {
		wh = new WindowHandler();
		wh.addWindow("game", new GameWindow(this));
		wh.addWindow("menu", new MenuWindow(this));
		wh.setCurrentWindow("menu");
	}
	
	/**
	 * Settings of the Processing PApplet
	 */
	public void settings() {
		size(600,600);
	}
	
	
	/**
	 * Calls the setup method of the current window in the WindowHandler
	 */
	public void setup() {
		surface.setResizable(true);
		surface.setTitle("REVENGE OF THE TOLIET PAPER");
		surface.setLocation(0, 0);
		wh.getCurrentWindow().setup();
	}
	
	
	/**
	 * Draws the current window of the WindowHandler
	 */
	public void draw() {
		//background(0);
		wh.getCurrentWindow().draw();
		
	}
	
	
	/**
	 * Calls the keyPressed method of the current window
	 */
	public void keyPressed() {
		wh.getCurrentWindow().keyPressed();
		
		if(key == '1') {
			wh.setCurrentWindow("menu");
			wh.getCurrentWindow().setup();
		}
		if(key == '0') {
			wh.setCurrentWindow("game");
			wh.getCurrentWindow().setup();
		}
	}
	
	
	/**
	 * Calls the keyReleased method of the current window
	 */
	public void keyReleased() {
		wh.getCurrentWindow().keyReleased();
	}
	
	
}
