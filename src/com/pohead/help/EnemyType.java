package com.pohead.help;

public enum EnemyType{
		UP,DOWN,LEFT,RIGHT;
		public static EnemyType randomType() {
			return values()[(int)(Math.random()*values().length)];
		}
	}
