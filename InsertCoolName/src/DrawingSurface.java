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
		background(0);
		stroke(255);
		x = width/2;
		y = height/2;
		h = 0;
		colorMode(HSB);
	}
	
	public void draw() {
		point(x, y);
		x += ((int)(Math.random()*3) - 1) * 5;
		y += ((int)(Math.random()*3) - 1) * 5;
		stroke(h, 255, 255);
		h += 0.5;
		h %= 255;
	}
	
	
}
