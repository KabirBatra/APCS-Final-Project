import processing.core.PApplet;

public abstract class Window {
	
	protected PApplet s;
	
	public Window(PApplet surface) {
		//this.handler = handler;
		this.s = surface;
	}
	public abstract void setup();	
	public abstract void draw();
	
}
