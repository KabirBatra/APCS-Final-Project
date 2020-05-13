package window;
import processing.core.PApplet;

/*
 * The super class of all windows. Contains a setup and draw method just 
 * like a regular processing sketch.
 * @author Kabir Batra
 */
public abstract class Window {
	
	protected PApplet s;
	
	public Window(PApplet surface) {
		//this.handler = handler;
		this.s = surface;
	}
	public abstract void setup();	
	public abstract void draw();
	public void keyPressed() {}
	public void keyReleased() {}
}
