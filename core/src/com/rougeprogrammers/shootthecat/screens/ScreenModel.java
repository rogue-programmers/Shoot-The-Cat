package com.rougeprogrammers.shootthecat.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.rougeprogrammers.shootthecat.utils.Constants;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenAccessor;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

// TODO: Auto-generated Javadoc
/**
 * The Class ScreenModel.
 */
public abstract class ScreenModel implements Screen, TweenAccessor<ScreenModel>, TweenCallback {

	/** The tag. */
	protected final String TAG = this.getClass().getSimpleName();

	/** The Constant FADE_TYPE. */
	protected static final int FADE_TYPE = 0;

	/** The game. */
	protected Game game;

	/** The shape renderer. */
	protected ShapeRenderer shapeRenderer;

	/** The fading. */
	protected boolean fading;

	/** The alpha. */
	protected float alpha = 1;

	/** The tween manager. */
	protected TweenManager tweenManager;

	/** The stage. */
	protected Stage stage;

	/** The camera. */
	protected OrthographicCamera camera;

	/**
	 * Instantiates a new screen model.
	 *
	 * @param game
	 *            the game
	 * @param camera
	 *            the camera
	 */
	public ScreenModel(Game game, OrthographicCamera camera) {
		this.game = game;
		this.camera = camera;
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.setColor(0, 0, 0, 1);
		tweenManager = new TweenManager();
		Tween.registerAccessor(getClass(), this);
		Gdx.app.log(TAG, "created");
	}

	/**
	 * Fade out.
	 */
	public void fadeOut() {
		fading = true;
		Tween.to(this, FADE_TYPE, 2f).target(0).setUserData("fade_out").setCallback(this).start(tweenManager);
	}

	/**
	 * Fade in.
	 */
	public void fadeIn() {
		fading = true;
		Tween.to(this, FADE_TYPE, 2f).target(1).setUserData("fade_in").setCallback(this).start(tweenManager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see aurelienribon.tweenengine.TweenCallback#onEvent(int,
	 * aurelienribon.tweenengine.BaseTween)
	 */
	@Override
	public abstract void onEvent(int type, BaseTween<?> source);

	/*
	 * (non-Javadoc)
	 * 
	 * @see aurelienribon.tweenengine.TweenAccessor#getValues(java.lang.Object,
	 * int, float[])
	 */
	@Override
	public abstract int getValues(ScreenModel target, int tweenType, float[] returnValues);

	/*
	 * (non-Javadoc)
	 * 
	 * @see aurelienribon.tweenengine.TweenAccessor#setValues(java.lang.Object,
	 * int, float[])
	 */
	@Override
	public abstract void setValues(ScreenModel target, int tweenType, float[] newValues);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		fadeOut();
		Gdx.app.log(TAG, "started");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		stage.draw();
		if (fading) {
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(0, 0, 0, alpha);
			shapeRenderer.rect(0, 0, Constants.WIDTH, Constants.HEIGHT);
			shapeRenderer.end();
			Gdx.gl.glDisable(GL20.GL_BLEND);
		}
		tweenManager.update(delta);
		stage.act();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {

	}

	/**
	 * Gets the game.
	 *
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#dispose()
	 */
	@Override
	public void dispose() {
		fading = false;
		tweenManager.killAll();
		shapeRenderer.dispose();
		stage.dispose();
	}

}
