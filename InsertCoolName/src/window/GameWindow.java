package window;

import assets.Assets;
import gameobject.GameObject;
import processing.core.PApplet;
import running.GameHandler;
import running.WindowHandler;

/*
 * Represents the window of running the game. Uses the GameHandle to do most of the work. 
 * Sets all of the initial conditions and controls the camera.
 * @author Kabir Batra
 */
public class GameWindow extends Window {
	
	private GameHandler handler;
	private float cameraX, cameraY;
	
	private int tileWidth = 64;
	private int tileHeight = 64;

	//integers that are repeatedly reassigned in the draw method:
	private int visibleTilesX, visibleTilesY;
	private float offsetX, offsetY, tileOffsetX, tileOffsetY;
	private GameObject player;
	
	private float ellapsedTime;
	private int previousTime;
	
	/*
	 * Instantiates the Assets class and stores a reference to the Processing PApplet 
	 * for to draw things.
	 */
	public GameWindow(WindowHandler wh, PApplet surface) {
		super(wh, surface);
		handler = new GameHandler(surface);
		new Assets(handler); // initializes all of the assets
		handler.setMap("Level1");
		
	}
	
	/*
	 * Initializes the camera position
	 */
	public void setup() {
		s.fill(255);
		s.noStroke();
		
		player = handler.getPlayer();
		cameraX = player.getPosX();
		cameraY = player.getPosY();
		System.out.println(handler.getPlayer() + " exists");
	}
	
	/*
	 * Updates the camera and calls GameHandler's tick and draw methods.
	 * Also calculates values to display tiles properly 
	 */
	public void draw() {
		s.background(0);
		if(previousTime == 0) previousTime = s.millis();
		ellapsedTime = (s.millis() - previousTime)/1000f;
		previousTime = s.millis();
		
		handler.tick(ellapsedTime);
		
		visibleTilesX = s.width/tileWidth;
		visibleTilesY = s.height/tileHeight;

		// smooth camera
		cameraX -= (cameraX - player.getPosX()) * 0.07f;
		cameraY -= (cameraY - player.getPosY()) * 0.07f;
				
		// distance from camera to topLeft
		offsetX = cameraX - visibleTilesX/2f;
		offsetY = cameraY - visibleTilesY/2f;
		
		if(offsetX < 0) offsetX = 0;
		if(offsetY < 0) offsetY = 0;
		
		if(offsetX > handler.getCurrentMap().getWidth() - visibleTilesX) offsetX = handler.getCurrentMap().getWidth() - visibleTilesX;
		if(offsetY > handler.getCurrentMap().getHeight() - visibleTilesY) offsetY = handler.getCurrentMap().getHeight() - visibleTilesY;

		
		tileOffsetX = (offsetX - (int)offsetX) * tileWidth;
		tileOffsetY = (offsetY - (int)offsetY) * tileHeight;

		handler.drawMap(offsetX, offsetY, tileOffsetX, tileOffsetY, visibleTilesX, visibleTilesY, tileWidth, tileHeight);
		handler.drawObjects(offsetX, offsetY, tileWidth, tileHeight);
		handler.displayStats();
		
	}
	
	/**
	 * Calls the keyPressed method of the GameHandler
	 */
	public void keyPressed() {
		handler.keyPressed();
	}
	
	/**
	 * Calls the keyReleased method of the GameHandler
	 */
	public void keyReleased() {
		handler.keyReleased();
	}

}
