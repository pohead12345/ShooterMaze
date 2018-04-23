package com.pohead.help;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * THIS PLAYER PLAYS THE SHIP SHOOTER GAME.
 * 
 * @author A
 *
 */
public class PlayerShip extends Entity {

	public PlayerShip(Sprite sprite, GameScreen screen) {
		super(sprite, screen);
		sprite.setPosition(200, 0);
	}

	float speed = 300;

	@Override
	public void update(float dt) {
		if (!isDead) {
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
				sprite.setX(sprite.getX() - speed * dt);
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
				sprite.setX(sprite.getX() + speed * dt);
			if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
				shoot();
			
			//boundary checks
			if (sprite.getX() < 0)
				sprite.setX(0);
			if (sprite.getX() > Constants.WIDTH/2-sprite.getWidth())
				sprite.setX(Constants.WIDTH/2-sprite.getWidth());
		} else if (screen.lives >= 0){
			deadTimeCounter+=dt;
			if(deadTimeCounter >= respawnTime) {
				isDead=false;
				sprite.setPosition(200,0);
				deadTimeCounter = 0;
			}
		}
	}
	private float deadTimeCounter;
	float respawnTime = 1.5f;
	
	float projectileSpeed = 1400;

	private void shoot() {

		int nPlayerBullets = 0;
		for (Projectile p : screen.projectiles)
			if (p.isPlayers)
				nPlayerBullets++;
		if (nPlayerBullets < 2) {
			Sprite sprite = new Sprite(new Texture("playerbullet.png"));
			sprite.setPosition(this.sprite.getX() + this.sprite.getWidth() / 2 - sprite.getWidth() / 2,
					this.sprite.getY() + this.sprite.getHeight() / 2 - sprite.getHeight() / 2);
			screen.projectiles.add(new Projectile(new Vector2(0, projectileSpeed), true, sprite, screen));
			screen.playerShoot.play(.3f);
		}
	}

	private boolean isDead;

	public boolean isDead() {
		return isDead;
	}

	/**
	 * every time player gets hit, lose a life
	 */
	public void die() {
		screen.lives--;
		isDead = true;
		screen.playerDeath.play(.8f);
	}

	public void render(SpriteBatch batch) {
		if(!isDead)
		sprite.draw(batch);
		
	}

}
