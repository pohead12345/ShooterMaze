package com.pohead.help;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;

public class VictoryScreen implements Screen {
	private Pohead game;
	Sound victoryTheme;
	Rectangle menuButton;
	Texture victoryImage;

	// int arrowsKilled, secondsElapsed, livesRemaining;

	BitmapFont font;
	String text;

	public VictoryScreen(Pohead game) {
		this.game = game;
		victoryTheme = Gdx.audio.newSound(Gdx.files.internal("VICTORYTHEME.wav"));
		menuButton = new Rectangle(269, 500, 256, 76);
		victoryImage = new Texture("victory.png");
		GameScreen gs = ((GameScreen) (game.getScreen()));
		int arrowsKilled = gs.enemiesKilled;
		int secondsElapsed = gs.secondsElapsed;
		int livesRemaining = gs.lives;
		font = new BitmapFont();
		text = "Arrows killed: " + arrowsKilled + "\nTime Elapsed: " + secondsElapsed + " seconds \nLives Remaining: "
				+ livesRemaining;

	}

	@Override
	public void show() {
		victoryTheme.play();

	}

	@Override
	public void render(float delta) {
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
			if (menuButton.contains(Gdx.input.getX(), Gdx.input.getY())) {
				game.setScreen(new MainMenuScreen(game));
			}
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();

		game.batch.draw(victoryImage, 0, 0);
		font.draw(game.batch, text, 370, 238);

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
