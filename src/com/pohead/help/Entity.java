package com.pohead.help;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Entity {
	Sprite sprite;
	
	GameScreen screen;

	private boolean destroyed;
	
	public Entity(Sprite sprite, GameScreen screen) {
		this.sprite = sprite;
		this.screen=screen;
	}
	/**
	 * precondition: must not be destroyed.
	 */
	public abstract void update(float dt);
	
	public void destroy() {
		destroyed = true;
	}
	public boolean isDestroyed() {
		return destroyed;
	}
}
