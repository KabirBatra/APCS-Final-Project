import processing.core.PApplet;

public class DrawingSurface extends PApplet {

	private WindowHandler wh;
	
	public static void main(String[] args) {
		System.out.println("hello world");
		PApplet.main("DrawingSurface");
	}
	
	public DrawingSurface() {
		wh = new WindowHandler(this);
		wh.addWindow(new GameWindow(this));
	}
	
	public void settings() {
		size(32*8,32*8);
	}
	
	public void setup() {
		surface.setResizable(true);
		surface.setTitle("CoolNameForGame");
		surface.setLocation(0, 0);
		fill(255);
		noStroke();
		wh.getCurrentWindow().setup();
	}
	
	public void draw() {
		//background(0);
		wh.getCurrentWindow().draw();
	}
	
	public void keyPressed() {
		wh.getCurrentWindow().keyPressed();
	}
	
	public void keyReleased() {
		wh.getCurrentWindow().keyReleased();
	}
	
	
}
