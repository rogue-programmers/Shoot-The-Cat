package com.rougeprogrammers.shootthecat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.rougeprogrammers.shootthecat.screens.RougesScreen;
import com.rougeprogrammers.shootthecat.utils.Assets;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main extends Game {

	/** The tag. */
	protected final String TAG = this.getClass().getSimpleName();

	/** The assets. */
	public static Assets assets;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.ApplicationListener#create()
	 */
	@Override
	public void create() {
		Gdx.app.log(TAG, "created");
		setScreen(new RougesScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

}
