
import processing.core.PApplet;

public class GameWindow extends Window {
	
	private GameObjectHandler handler;
	private Map room;
	private float cameraX, cameraY;
	
	private int tileWidth = 32;
	private int tileHeight = 32;

	//integers that are repeatedly reassigned in the draw method:
	private int visibleTilesX, visibleTilesY;
	private float offsetX, offsetY, tileOffsetX, tileOffsetY;
	private GameObject player;
	
	public GameWindow(PApplet surface) {
		super(surface);
		handler = new GameObjectHandler(surface);

		// room = Assets.getRoom("testRoom")
		room = new Map("testRoom", null, null, handler);
	}

	public void setup() {
		cameraX = s.width/2;
		cameraY = s.height/2;
		player = handler.getPlayer();
		System.out.println(handler.getPlayer());
	}

	public void draw() {
		s.background(0);
		
		cameraX = player.getPosX();
		cameraY = player.getPosY();
		//System.out.println(player.getPosX());
		
		visibleTilesX = s.width/tileWidth;
		visibleTilesY = s.height/tileHeight;

		// distance from camera to topLeft
		offsetX = cameraX - visibleTilesX/2f;
		offsetY = cameraY - visibleTilesY/2f;
		
		tileOffsetX = (offsetX - (int)offsetX) * tileWidth;
		tileOffsetY = (offsetY - (int)offsetY) * tileHeight;

		
		for(int x = -1; x < visibleTilesX+2; x++) {
			for(int y = -1; y < visibleTilesY+2; y++) {
				int tile = room.getTile(x + (int)offsetX, y + (int)offsetY);
				if(tile == 0) {
					s.fill(255);
				}
				else if(tile == 1) {
					s.fill(0, 0, 255);
				} else {
					s.fill(0);
				}
				s.rect(x * tileWidth - tileOffsetX, y * tileHeight - tileOffsetY, tileWidth, tileHeight);
			}
		}
		
		handler.tick();
		handler.draw(offsetX, offsetY, tileWidth, tileHeight);
	}
	
	public void keyPressed() {
		handler.keyPressed();
	}
	
	public void keyReleased() {
		handler.keyReleased();
	}

}
