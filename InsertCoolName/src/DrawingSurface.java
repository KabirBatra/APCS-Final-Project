import processing.core.PApplet;

public class DrawingSurface extends PApplet {

	private WindowHandler wh;
	private int cameraX, cameraY;

	
	public static void main(String[] args) {
		System.out.println("hello world");
		PApplet.main("DrawingSurface");
	}
	
	public DrawingSurface() {
		wh = new WindowHandler(this);
		wh.addWindow(new GameWindow(this));
	}
	
	public void settings() {
		size(600,600);
	}
	
	public void setup() {
		surface.setResizable(true);
		surface.setTitle("CoolNameForGame");
		surface.setLocation(0, 0);
		fill(255);
		noStroke();
		cameraX = width/2;
		cameraY = height/2;
	}
	
	public void draw() {
		//background(0);
		wh.getCurrentWindow().draw();
	}
	
	
}
