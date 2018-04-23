package com.pohead.help;



import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
/**
 * 
 * @author A
 *
 */
public class GameScreen implements Screen {
	private Pohead game;

	public Pohead getGame() {
		return game;
	}
	//left side: shipshooter
	public PlayerShip playerShip;
	public Array<Projectile> projectiles = new Array<Projectile>();
	public Array<EnemyShip> enemies = new Array<EnemyShip>();
	
	//right side: mazegame
	MazeHandler mazeHandler;
	BetterContactHandler contactHandler = new BetterContactHandler(this);
	
	private FitViewport gamePort;

	EnemySpawner spawner;
	
	public int enemiesKilled;
	public int secondsElapsed;
	
	
	public Sound enemyDeath, enemyShoot, playerShoot, playerDeath;
	Music inGameTheme;
	public GameScreen(Game game) {
		// atlas = new TextureAtlas("Mario_and_Enemies.pack");
		
		this.game = (Pohead) game;
		gamePort = new FitViewport(800,600);

		Sprite playerSprite =new Sprite(new Texture("playership.png"));
		playerShip = new PlayerShip(playerSprite,this);

		mazeHandler = new MazeHandler(this);

		spawner = new EnemySpawner(this);
		
		enemyDeath=Gdx.audio.newSound(Gdx.files.internal("enemydeath.wav"));
		enemyShoot=Gdx.audio.newSound(Gdx.files.internal("enemyshoot.wav"));
		playerShoot=Gdx.audio.newSound(Gdx.files.internal("playershoot.wav"));
		playerDeath=Gdx.audio.newSound(Gdx.files.internal("playerdeath.wav"));
		
		inGameTheme = Gdx.audio.newMusic(Gdx.files.internal("ingametheme.mp3"));
		inGameTheme.setLooping(true);
		inGameTheme.play();
	}

	private float secondsCounter;

	public void update(float dt) {
		secondsCounter+=dt;
		if(secondsCounter>1) {
			secondsElapsed++;
			secondsCounter = 0;
		}
		
		if(lives <= 0) {
			inGameTheme.stop();
			game.setScreen(new GameOverScreen(game));
		}
		
		removeDestroyedEntities();
		contactHandler.update();
		mazeHandler.update(dt);
		playerShip.update(dt);
		spawner.update(dt);
		for (Projectile p : projectiles)
			p.update(dt);
		for(EnemyShip e : enemies)
			e.update(dt);

		
	}

	private void removeDestroyedEntities() {
		for (int i = 0; i < projectiles.size; i++) {
			if (projectiles.get(i).isDestroyed())
				projectiles.removeIndex(i);
		}
		for (int i = 0; i < enemies.size; i++) {
			if (enemies.get(i).isDestroyed())
				enemies.removeIndex(i);
		}
	}

	private void handleEscapeKey() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			if (isPaused)
				resume();
			else
				pause();
		}

	}

	BitmapFont help = new BitmapFont();


	
	@Override
	public void render(float delta) {
		handleEscapeKey();
		if(!isPaused)
			update(delta);
		else {
			
		}
		// Clear the game screen with Black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		SpriteBatch batch = game.batch;

		batch.begin();

		playerShip.render(batch);
		for (Projectile p : projectiles)
			p.sprite.draw(batch);
		for (EnemyShip e : enemies) {
			e.sprite.draw(batch);
		}
		
		mazeHandler.render(batch);
//		
//		if(victory) {
//			help.getData().setScale(5);
//			
//			help.draw(batch, "VICTORY", Constants.WIDTH/2-help.getSpaceWidth()/2, Constants.HEIGHT/2-help.getSpaceWidth()/2);
//			help.getData().setScale(1);
//		}
//		help.draw(batch, "projectiles: "+projectiles.size+"\nenemies: "+enemies.size+"\nFPS: "+1/delta, 50,50);
//		help.draw(batch, "lives: "+lives, 150,150);
		
		help.draw(batch, "LIVES REMAINING: "+lives+"\nArrows Killed: "+enemiesKilled+"\nTime Elapsed: "+secondsElapsed,
				250, 100);
		if(isPaused)
			help.draw(game.batch, "PAUSED!", 200,300);
		batch.end();

//		if (gameOver()) {
//			game.setScreen(new GameOverScreen(game));
//			dispose();
//		}
	}


	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);

	}

	public boolean isPaused;
	public int lives = 5;
	//public boolean victory;

	@Override
	public void pause() {
		//System.out.println("PAUSED");
		isPaused = true;
		spawner.timer1.stop();
		spawner.timer2.stop();
	}

	@Override
	public void resume() {
		//System.out.println("RESUME");
		isPaused = false;
		spawner.timer1.start();
		spawner.timer2.start();

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {


	}
}
