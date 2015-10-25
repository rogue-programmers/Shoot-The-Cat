package com.rougeprogrammers.shootthecat.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.rougeprogrammers.shootthecat.Main;
import com.rougeprogrammers.shootthecat.utils.Constants;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenAccessor;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

// TODO: Auto-generated Javadoc
/**
 * The Class RougesScreen.
 */
public class RougesScreen implements Screen, TweenAccessor<RougesScreen>, TweenCallback {

	/** The tag. */
	protected final String TAG = this.getClass().getSimpleName();

	/** The Constant APLPHA_TYPE. */
	private static final int APLPHA_TYPE = 0;

	/** The game. */
	private Game game;

	/** The manager. */
	private TweenManager manager;

	/** The alpha. */
	private float alpha = 0f;

	/** The batch. */
	private SpriteBatch batch;

	/** The logo. */
	private TextureRegion logo;

	/** The rect. */
	private Rectangle rect;

	/** The camera. */
	private OrthographicCamera camera;

	/**
	 * Instantiates a new rouges screen.
	 *
	 * @param game
	 *            the game
	 */
	public RougesScreen(Game game) {
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		logo = Main.assets.getLogoTextureRegion();
		rect = new Rectangle(Constants.WIDTH / 2, Constants.HEIGHT / 2, logo.getRegionWidth(), logo.getRegionHeight());
		manager = new TweenManager();
		Tween.registerAccessor(this.getClass(), this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		Tween.to(this, APLPHA_TYPE, 2f).target(1f).repeatYoyo(1, 0.5f).delay(0.5f).setCallback(this).start(manager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		Color color = batch.getColor();
		batch.setColor(color.r, color.g, color.b, alpha);
		batch.draw(logo, rect.x - rect.width / 2, rect.y - rect.height / 2, rect.width, rect.height);
		batch.end();
		manager.update(delta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see aurelienribon.tweenengine.TweenAccessor#getValues(java.lang.Object,
	 * int, float[])
	 */
	@Override
	public int getValues(RougesScreen target, int tweenType, float[] returnValues) {
		returnValues[0] = target.alpha;
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see aurelienribon.tweenengine.TweenAccessor#setValues(java.lang.Object,
	 * int, float[])
	 */
	@Override
	public void setValues(RougesScreen target, int tweenType, float[] newValues) {
		target.alpha = newValues[0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see aurelienribon.tweenengine.TweenCallback#onEvent(int,
	 * aurelienribon.tweenengine.BaseTween)
	 */
	@Override
	public void onEvent(int type, BaseTween<?> source) {
		if (type == TweenCallback.COMPLETE) {
			game.setScreen(new MenuScreen(game));
		}
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#dispose()
	 */
	@Override
	public void dispose() {
		batch.dispose();
	}

}
