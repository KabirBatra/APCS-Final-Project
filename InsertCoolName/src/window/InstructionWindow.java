package window;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import running.WindowHandler;

/*
 * The Main Menu of the game
 * @author Kaie Chen
 */
public class InstructionWindow extends Window {

	private static PImage toiletPaper;

	/**
	 * Creates a menu window!
	 * 
	 * @param wh      must be initialized!
	 * @param surface must be initialized!
	 */
	public InstructionWindow(WindowHandler wh, PApplet surface) {
		super(wh, surface);
	}

	PFont zigBlack;

	/**
	 * This helps setup the menu!
	 */
	public void setup() {

		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getClassLoader().getResource("toiletPaper.png"));
		} catch (IOException e) {
			System.out.println("image load unsuccessful");
			e.printStackTrace();
		}
		toiletPaper = new PImage((java.awt.Image) img);

		s.background(0);
		s.fill(0);
		s.noStroke();

		zigBlack = s.createFont("Ziggurat-Black", 32);
		s.textFont(zigBlack);

	}

	float rectX, rectY, rectX2, rectY2, width, height;
	boolean overButton1 = false, overButton2 = false;

	/**
	 * This draws the menu Window!
	 */
	public void draw() {

		width = s.width;
		height = s.height;

		s.textAlign(s.CENTER);
		s.textSize(50);

		// This is the text

		s.fill(173, 0, 0);
		s.text("Instructions", width / 2, height / 10);

		// These are the keys
		s.fill(125);
		s.stroke(150);
		s.rect(width * 2 / 7, height / 7, width / 10, height / 10);
		s.rect(width / 7, height * 2 / 7, width / 10, height / 10);
		s.rect(width * 2 / 7, height * 2 / 7, width / 10, height / 10);
		s.rect(width * 3 / 7, height * 2 / 7, width / 10, height / 10);
		s.rect(width / 9, height * 4 / 9, width / 2, height / 10);

		// Text over Keys
		s.fill(0);
		s.textSize(50);
		s.text("W", width * 2 / 7 + width / 20, height / 7 + height / 13);
		s.text("A", width / 7 + width / 20, height * 2 / 7 + height / 13);
		s.text("S", width * 2 / 7 + width / 20, height * 2 / 7 + height / 13);
		s.text("D", width * 3 / 7 + width / 20, height * 2 / 7 + height / 13);
		s.text("SPACE BAR", width / 9 + width / 4, (height * 4 / 9) + height / 13);

		// Instructional text
		s.textAlign(s.LEFT);
		s.textSize(20);
		s.fill(173, 0, 0);
		s.text("W - move up \n A - move left \n S - move down \n D - move right \n Space bar - shoot a bullet \n    with autoaim!",
				width * 4 / 7 + width / 20, height / 7 + height / 13);
		s.textAlign(s.CENTER);
		s.fill(255);
		s.text("Kill ALL the slimes with toliet paper and don't die!! "
				+ "\n (slimes can and will shoot you and can hurt you by touching you)"
				+ "\n You have infinite ammo and your health is in the top left of the UI!"
				+ "\n Most importantly, HAVE FUN!"
			, width / 2, height *  7 / 12);
		

		// check to see if the curosr is over the buttons
		overButton1 = overButton(rectX, rectY, s.mouseX, s.mouseY, width / 4, height / 4);
		overButton2 = overButton(rectX2, rectY2, s.mouseX, s.mouseY, width / 4, height / 4);

		// this changes the color

		s.stroke(173, 0, 0);

		// Button 1
		s.fill(180);
		if (overButton1) {

			s.fill(90);

		}

		rectX = width / 8f;
		rectY = height * (8f / 10);
		s.rect(rectX, rectY, width / 4, height / 10);

		/////////////

		// Button 2

		s.fill(180);
		if (overButton2) {

			s.fill(90);

		}

		rectX2 = width * 5 / 8f;
		rectY2 = height * (8f / 10);
		s.rect(rectX2, rectY2, width / 4, height / 10);

		s.fill(173, 0, 0);
		s.textSize(20);
		s.text("Let's get to it!", rectX + width / 8, rectY + height / 20);
		s.text("Psych I am out", rectX2 + width / 8, rectY2 + height / 20);

		// I tried to use the void ousePresed() as in processing, but it didn't work so
		// this is probably going to
		// be the solution
		if (s.mousePressed && s.mouseButton == s.LEFT && overButton1) {
			System.out.println("LETS A GOOOO");

			// START GAME
			wh.setCurrentWindow("game");
			wh.getCurrentWindow().setup();

		}
		if (s.mousePressed && s.mouseButton == s.LEFT && overButton2) {
			System.out.println("Weak");
			System.exit(0);
		}

	}

	boolean overButton(float rectX3, float rectY3, float mouseX, float mouseY, float f, float g) {
		if (mouseX >= rectX3 && mouseX <= rectX3 + f && mouseY >= rectY3 && mouseY <= rectY3 + g) {
			return true;
		} else {
			return false;
		}
	}

}
