package com.rougeprogrammers.shootthecat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.rougeprogrammers.shootthecat.stages.GameStage;

// TODO: Auto-generated Javadoc
/**
 * The Class GameScreen.
 */
public class GameScreen extends ScreenAdapter {

	/** The tag. */
	protected final String TAG = this.getClass().getSimpleName();

	/** The game stage. */
	private GameStage gameStage;

	/**
	 * Instantiates a new game screen.
	 */
	public GameScreen() {
		Gdx.input.setCatchBackKey(true);
		Gdx.app.log(TAG, "created");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.ScreenAdapter#show()
	 */
	@Override
	public void show() {
		gameStage = new GameStage(this);
		Gdx.app.log(TAG, "started");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.ScreenAdapter#render(float)
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameStage.draw();
		gameStage.act(delta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.ScreenAdapter#pause()
	 */
	@Override
	public void pause() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.ScreenAdapter#resume()
	 */
	@Override
	public void resume() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.ScreenAdapter#dispose()
	 */
	@Override
	public void dispose() {

	}

}
