import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import processing.core.PApplet;

public class DrawingSurface extends PApplet {

	private WindowHandler wh;
	
	public static void main(String[] args) {
		System.out.println("hello world");
		
		JFrame frame = new JFrame();
	      JPanel panel = new JPanel();

	 int result =  JOptionPane.showConfirmDialog(null, "Play Game?", "REVENGE OF THE TOLIET PAPER", JOptionPane.YES_NO_OPTION);
	      
	if (result == JOptionPane.YES_OPTION)
		PApplet.main("DrawingSurface");
	else 
		System.out.println("BYE");
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
		surface.setTitle("REVENGE OF THE TOLIET PAPER");
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
