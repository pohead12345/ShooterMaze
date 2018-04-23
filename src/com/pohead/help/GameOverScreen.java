package com.pohead.help;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class GameOverScreen implements Screen {
	Sound deathTheme;
	Texture gameOverImg;
	Pohead game;
	Rectangle yes, no;

	public GameOverScreen(Pohead game) {
		deathTheme = Gdx.audio.newSound(Gdx.files.internal("deaththeme.wav"));
		gameOverImg = new Texture("gameover.png");
		this.game = game;
		yes = new Rectangle(80, 399, 256, 87);
		no = new Rectangle(446, 397, 253, 90);

	}

	@Override
	public void show() {
		deathTheme.play();
	}
	float timeCount;
	@Override
	public void render(float delta) {
		timeCount+=delta;
		if(timeCount >= 2.62f) {
			deathTheme.stop();
		}
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			if (yes.contains(Gdx.input.getX(), Gdx.input.getY())) {
				game.setScreen(new GameScreen(game));
			} else if (no.contains(Gdx.input.getX(), Gdx.input.getY())) {
				game.setScreen(new MainMenuScreen(game));

			}
		}
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();

		game.batch.draw(gameOverImg, 0, 0);

		game.batch.end();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
