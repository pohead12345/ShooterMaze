package com.pohead.help;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class MainMenuScreen implements Screen{

	Pohead game;
	
	Rectangle playButton;
	Rectangle quitButton;
	
	Music titleScreenMusic;
	
	public MainMenuScreen(Pohead game) {
		this.game=game;
		titleScreen = new Texture("titlescreen.png");
		
		playButton = new Rectangle(269,347,241,68);
		quitButton = new Rectangle(268,467,423,79);
		
		titleScreenMusic = Gdx.audio.newMusic(Gdx.files.internal("titlescreen shipgalaga.mp3"));
		titleScreenMusic.play();
		titleScreenMusic.setLooping(true);
	}
	
	@Override
	public void show() {

	}
	Texture titleScreen;
	
	@Override
	public void render(float delta) {
		
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
			if(playButton.contains(Gdx.input.getX(), Gdx.input.getY())) {
				
				game.setScreen(new HelpScreen(game,titleScreenMusic));
			}
			else if(quitButton.contains(Gdx.input.getX(), Gdx.input.getY()))
				System.exit(0);
		

		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		
		game.batch.draw(titleScreen, 0,0);
		
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
