package com.pohead.help;

public class BetterContactHandler {
	GameScreen screen;

	public BetterContactHandler(GameScreen screen) {
		this.screen = screen;
	}

	public void update() {
		// SPRITES ARE HITBOXES
		// CHECK IF EVERY ENEMY OVERLAPS WITH EVERY PROJECTILE FROM PLAYER.

		for (EnemyShip e : screen.enemies) {
			for (Projectile p : screen.projectiles)
				if (p.isPlayers)
					if (e.sprite.getBoundingRectangle().overlaps(p.sprite.getBoundingRectangle())) {
						e.killedByPlayer();
						p.destroy();
					}
		}

		// now check if every enemy projectile overlaps with player
		if (!screen.playerShip.isDead())
			for (Projectile p : screen.projectiles) {
				if (!p.isPlayers)
					if (screen.playerShip.sprite.getBoundingRectangle().overlaps(p.sprite.getBoundingRectangle())) {
						screen.playerShip.die();
						p.destroy();
					}
			}
	}

}
