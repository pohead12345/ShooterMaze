package com.pohead.help;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.pohead.help.MazeHandler.MazeCrawler;

public class EnemyShip extends Entity {

	private boolean spawnRight;


	private Random rand = new Random();

	private boolean large; //if this is large do the movement 3 times.
	
	public EnemyShip(EnemyType enemyType, EnemyPattern pattern, boolean spawnRight, boolean large, float height, Sprite sprite, GameScreen screen) {
		super(sprite, screen);
		this.spawnRight = spawnRight;
		this.enemyType = enemyType;
		enemyPattern = pattern;
		this.large=large;
		if(large)
		sprite.setSize(60,60);

		switch (enemyType) {
		case DOWN:
			sprite.setTexture(new Texture("downenemy.png"));
			break;
		case LEFT:
			sprite.setTexture(new Texture("leftenemy.png"));
			break;
		case RIGHT:
			sprite.setTexture(new Texture("rightenemy.png"));
			break;
		case UP:
			sprite.setTexture(new Texture("upenemy.png"));
			break;

		}

		
		speed = 150;
		amplitude = 70;
		this.height = height;
		
	}

	float speed;
	float amplitude; //only applicable to sine pattern
	float height; //not applicable to circular
	
	EnemyType enemyType;

	EnemyPattern enemyPattern;

	/**
	 * called when player's projectile touches this enemy
	 */
	public void killedByPlayer() {
		screen.enemiesKilled++;
		MazeCrawler mc = screen.mazeHandler.mazeCrawler;
		int n = 2; //if enemy is large do movement n+1 times
		switch (enemyType) {
		case DOWN:
			mc.moveDown();
			if(large) {for(int i = 0; i < n; i++) mc.moveDown();}
			break;
		case LEFT:
			mc.moveLeft();
			if(large) {for(int i = 0; i < n; i++) mc.moveLeft();}
			break;
		case RIGHT:
			mc.moveRight();
			if(large) {for(int i = 0; i < n; i++) mc.moveRight();}
			break;
		case UP:
			mc.moveUp();
			if(large) {for(int i = 0; i < n; i++) mc.moveUp();}
			break;
		}

		screen.enemyDeath.play(.5f);
		destroy();
	}

	private float timeElapsed;

	private float lifeTime = 4;

	@Override
	public void update(float dt) {
		timeElapsed += dt;
		if (Math.random() > .999) //0.1% chance per frame to fire
			shoot(dt);

		if (timeElapsed > lifeTime)
			destroy();
//lots of inefficient copy+paste things here i may clean up later
		int r = 150; // radius of curvature

		switch (enemyPattern) {

		case CORNER_CCW:

			if (!spawnRight) {
				sprite.setX((float) (r * Math.cos(timeElapsed + Math.PI * 1.5))); // parametric form: x=r*cost+a
				sprite.setY((float) (r * Math.sin(timeElapsed + Math.PI * 1.5) + Constants.HEIGHT));
			} else {
				sprite.setX((float) (r * Math.cos(timeElapsed + Math.PI) + Constants.WIDTH/2));
				sprite.setY((float) (r * Math.sin(timeElapsed + Math.PI) + Constants.HEIGHT));
			}
			break;
		case CORNER_CW:
			if (!spawnRight) {
				sprite.setX((float) (r * Math.cos(-timeElapsed))); // parametric form: x=r*cost+h
				sprite.setY((float) (r * Math.sin(-timeElapsed) + Constants.HEIGHT));
			} else {
				sprite.setX((float) (r * Math.cos(-timeElapsed + 3*Math.PI/2) + Constants.WIDTH/2));
				sprite.setY((float) (r * Math.sin(-timeElapsed + 3*Math.PI/2) + Constants.HEIGHT));
			}
			break;
		case DIAGONAL:
			sprite.setX(timeElapsed*speed*(spawnRight?-1:1)+ (spawnRight?+Constants.WIDTH/2:0));
			sprite.setY(height+timeElapsed*speed);
			break;
		case ZOOM:
			sprite.setX(timeElapsed*speed*(spawnRight?-1:1) + (spawnRight?+Constants.WIDTH/2:0));
			sprite.setY(height); 
			break;
		case SINE:
			sprite.setX(timeElapsed*speed* (spawnRight?-1:1) + (spawnRight?+Constants.WIDTH/2:0)); 
			sprite.setY((float) (amplitude*Math.sin(10*timeElapsed) + height));
			break;
		
		default:
			break; // inefficient will change later

		}
	}

	private void shoot(float dt) {
		Sprite sprite = new Sprite(new Texture("enemybullet.png"));
		sprite.setPosition(this.sprite.getX() + this.sprite.getWidth() / 2 - sprite.getWidth() / 2,
				this.sprite.getY() + this.sprite.getHeight() / 2 - sprite.getHeight() / 2);
		screen.projectiles.add(new Projectile(new Vector2(0, -300), false, sprite, screen));
		screen.enemyShoot.play(.3f);
	}

}
