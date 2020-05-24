package gameobject;

import running.GameHandler;

public class EnemyBoss extends Enemy {

	public EnemyBoss(float x, float y, int fireRate, String name, GameHandler handler) {
		super(x, y,  24, name, handler);
		health = 1000;
		maxHealth = 1000;
		maxSpeed = 1;
		// TODO Auto-generated constructor stub
	}

}
