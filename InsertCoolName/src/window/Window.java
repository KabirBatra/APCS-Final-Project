package window;
import processing.core.PApplet;
import running.WindowHandler;

/*
 * The super class of all windows. Contains a setup and draw method just 
 * like a regular processing sketch.
 * @author Kabir Batra
 */
public abstract class Window {
	
	protected WindowHandler wh;
	protected PApplet s;

	
	public Window(WindowHandler wh, PApplet surface) {
		this.wh = wh;
		this.s = surface;
	}
	public abstract void setup();	
	public abstract void draw();
	public void keyPressed() {}
	public void keyReleased() {}
}
