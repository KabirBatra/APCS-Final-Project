
import processing.core.PApplet;

public class GameWindow extends Window {
	
	private GameHandler handler;
	private float cameraX, cameraY;
	
	private int tileWidth = 64;
	private int tileHeight = 64;

	//integers that are repeatedly reassigned in the draw method:
	private int visibleTilesX, visibleTilesY;
	private float offsetX, offsetY, tileOffsetX, tileOffsetY;
	private GameObject player;
	
	private float ellapsedTime = 0;
	private int previousTime = 0;
	
	public GameWindow(PApplet surface) {
		super(surface);
		handler = new GameHandler(surface);
		new Assets(handler); // initializes all of the assets and creates gameObjects
		handler.setMap("testRoom4", null);
		
	}
	
	public void setup() {
		s.fill(255);
		s.noStroke();
		
		player = handler.getPlayer();
		cameraX = player.getPosX();
		cameraY = player.getPosY();
		System.out.println(handler.getPlayer() + " exists");
	}
	
	public void draw() {
		s.background(0);
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
		
		if(offsetX >= handler.getCurrentMap().getWidth() - visibleTilesX) offsetX = handler.getCurrentMap().getWidth() - visibleTilesX;
		if(offsetY >= handler.getCurrentMap().getHeight() - visibleTilesY) offsetY = handler.getCurrentMap().getHeight() - visibleTilesY;

		
		tileOffsetX = (offsetX - (int)offsetX) * tileWidth;
		tileOffsetY = (offsetY - (int)offsetY) * tileHeight;

		handler.drawMap(offsetX, offsetY, tileOffsetX, tileOffsetY, visibleTilesX, visibleTilesY, tileWidth, tileHeight);
		handler.drawObjects(offsetX, offsetY, tileWidth, tileHeight);
		handler.displayStats();
		
	}
	
	public void keyPressed() {
		handler.keyPressed();
	}
	
	public void keyReleased() {
		handler.keyReleased();
	}

}
