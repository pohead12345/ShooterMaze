package com.pohead.help;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AUtils {
	public static Sprite newSprite(String file, float x, float y, float width,float height) {
		Sprite sprite = new Sprite(new Texture(file));
		sprite.setPosition(x, y);
		sprite.setSize(width, height);
		return sprite;
	}
}
