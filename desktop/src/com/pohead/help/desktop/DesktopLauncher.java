package com.pohead.help.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pohead.help.Pohead;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=800;
		config.height=600;
		config.title = "Galaga Maze";
		config.addIcon("icon.png",FileType.Internal);
		new LwjglApplication(new Pohead(), config);
	}
}
