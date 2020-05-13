package running;

//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;

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
		PApplet.main("DrawingSurface");
		
//	int result =  JOptionPane.showConfirmDialog(null, "Play Game?", "REVENGE OF THE TOLIET PAPER", JOptionPane.YES_NO_OPTION);
//	      
//	if (result == JOptionPane.YES_OPTION)
//		
//	else 
//		System.out.println("BYE");
	}
	/**
	 * Creates a WindowHandler and adds all the windows to it
	 */
	public DrawingSurface() {
		wh = new WindowHandler(this);
		wh.addWindow(new GameWindow(this));
		wh.addWindow(new MenuWindow(this));
		wh.switchWindow(1);
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
			wh.switchWindow(1);
			wh.getCurrentWindow().setup();
		}
		if(key == '0') {
			wh.switchWindow(0);
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
