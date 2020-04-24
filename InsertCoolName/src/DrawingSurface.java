import processing.core.PApplet;

public class DrawingSurface extends PApplet {
	
	int x, y;
	float h;
	
	public static void main(String[] args) {
		System.out.println("hello world");
		PApplet.main("DrawingSurface");
	}
	
	public void settings() {
		size(600,600);
	}
	
	public void setup() {
		surface.setResizable(true);
		surface.setTitle("CoolNameForGame");
		surface.setLocation(0, 0);
		background(0);
		fill(255);
		noStroke();
		x = width/2;
		y = height/2;
		h = 0;
		colorMode(HSB);
	}
	
	public void draw() {
		ellipse(x, y, 7, 7);
		x += ((int)(Math.random()*3) - 1) * 5;
		y += ((int)(Math.random()*3) - 1) * 5;
		fill(h, 255, 255);
		h += 0.5;
		h %= 255;
	}
	
	
}
