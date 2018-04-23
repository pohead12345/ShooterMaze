package com.pohead.help;



public enum EnemyPattern { //default starts from left side of screen.
	CORNER_CW, CORNER_CCW, //circular motion with the corner as the center of the circle.
	ZOOM, //zoom is just going from one side of screen to another horizontally
	DIAGONAL, //starts from left and goes northeast
	//U, //u shaped
	SINE; //sine wave

	
	public static EnemyPattern randomPattern() {
		return values()[(int)(Math.random()*values().length)];
	}
}
