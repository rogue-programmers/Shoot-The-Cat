package com.rougeprogrammers.shootthecat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.rougeprogrammers.shootthecat.screens.GameScreen;
import com.rougeprogrammers.shootthecat.screens.MenuScreen;
import com.rougeprogrammers.shootthecat.screens.RougesScreen;
import com.rougeprogrammers.shootthecat.utils.Assets;
import com.rougeprogrammers.shootthecat.utils.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main extends Game {

	/** The tag. */
	protected final String TAG = this.getClass().getSimpleName();

	/** The assets. */
	public static Assets assets;

	/** The camera. */
	private OrthographicCamera camera;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.ApplicationListener#create()
	 */
	@Override
	public void create() {
		Main.assets = new Assets();
		Main.assets.load();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);
		Gdx.app.log(TAG, "created");
		setScreen(new MenuScreen(this, camera));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Game#dispose()
	 */
	@Override
	public void dispose() {
		super.dispose();
	}

}
