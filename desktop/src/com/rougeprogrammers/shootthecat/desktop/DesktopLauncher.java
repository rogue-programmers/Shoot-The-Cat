package com.rougeprogrammers.shootthecat.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rougeprogrammers.shootthecat.Main;
import com.rougeprogrammers.shootthecat.utils.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class DesktopLauncher.
 */
public class DesktopLauncher {

	/**
	 * The main method.
	 *
	 * @param arg
	 *            the arguments
	 */
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Shoot The Cat";
		config.width = (int) Constants.WIDTH;
		config.height = (int) Constants.HEIGHT;
		config.resizable = false;
		config.addIcon("textures/ic_launcher.png", FileType.Internal);
		new LwjglApplication(new Main(), config);
	}

}
