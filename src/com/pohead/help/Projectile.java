package com.pohead.help;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Entity{
	public Projectile(Vector2 disp, boolean isPlayers, Sprite sprite, GameScreen screen) {
		super(sprite, screen);
		this.isPlayers = isPlayers;
		this.disp=disp;
	}

	/**
	 * the displacement vector that is added to th eposition each frame
	 */
	public Vector2 disp;
	
	boolean isPlayers;

	@Override
	public void update(float dt) {
		if(sprite.getY() > Constants.HEIGHT || sprite.getY() < 0)
			destroy();
		
		sprite.setPosition(sprite.getX()+disp.x*dt, sprite.getY()+disp.y*dt);
	}
	

}
