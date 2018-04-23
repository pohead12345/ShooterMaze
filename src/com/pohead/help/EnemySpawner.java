package com.pohead.help;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class EnemySpawner {
	
	public Timer timer1,timer2 ; //one spawner on each side of the screen. timer1 is left, timer2 right

	GameScreen screen;
	public EnemySpawner(GameScreen screen) {
		timer1 = new Timer();timer2=new Timer();
		this.screen=screen;

	}
	Random rand = new Random();

	int n; // number of enemies in a wave.
	float delay; // time delay between enemy spawning

	public void update(float dt) {

		if(timer1.isEmpty()) {
			//final EnemyType type = ;
			final EnemyPattern pattern = EnemyPattern.randomPattern();
			final int n = rand.nextInt(3) + 3; //[3,6]
			final float initialHeight = Constants.HEIGHT*(rand.nextFloat()*0.5f+0.25f); //0.25 to 0.75x
			final boolean isLarge = Math.random() < 0.2;
					
			timer1.scheduleTask(new Task() {
				public void run() {
					screen.enemies.add(new EnemyShip(EnemyType.randomType(),pattern,false,isLarge,initialHeight, new Sprite(new Texture("downenemy.png")),screen));
				}
			}, 0.5f,0.5f,n);
		}
		
		if(timer2.isEmpty()) {
			//final EnemyType type = EnemyType.randomType();
			final EnemyPattern pattern = EnemyPattern.randomPattern();
			final int n = rand.nextInt(3) + 3; //[3,6]
			final float initialHeight = Constants.HEIGHT*(rand.nextFloat()*0.5f+0.25f); //0.25 to 0.75x
			final boolean isLarge = Math.random() < 0.2;
			timer2.scheduleTask(new Task() {
				public void run() {
					screen.enemies.add(new EnemyShip(EnemyType.randomType(),pattern,true,isLarge,initialHeight,new Sprite(new Texture("downenemy.png")),screen));
				}
			}, 0.5f,0.5f,n);
		}
			
	}

		
	
}
