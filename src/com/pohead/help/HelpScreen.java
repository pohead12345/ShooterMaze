package com.pohead.help;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class HelpScreen implements Screen{
	Pohead game;
	Texture helpScreen;
	Rectangle goButton;
	public HelpScreen(Pohead game, Music music) {
		this.game=game;
		helpScreen = new Texture("helpscreen.png");
		goButton = new Rectangle(273,445,257,84);
		titleScreenMusic = music;
	}
	@Override
	public void show() {

		
	}
	Music titleScreenMusic;
	@Override
	public void render(float delta) {
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
			if(goButton.contains(Gdx.input.getX(), Gdx.input.getY())) {
				titleScreenMusic.stop();
				game.setScreen(new GameScreen(game));

			}
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		
		game.batch.draw(helpScreen, 0,0);
		
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
