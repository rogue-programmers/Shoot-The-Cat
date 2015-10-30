package com.rougeprogrammers.shootthecat.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.rougeprogrammers.shootthecat.Main;
import com.rougeprogrammers.shootthecat.utils.Constants;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.TweenCallback;

// TODO: Auto-generated Javadoc
/**
 * The Class RScreen.
 */
public class RScreen extends ScreenModel {

	/** The batch. */
	private SpriteBatch batch;

	/** The logo. */
	private TextureRegion logo;

	/** The rect. */
	private Rectangle rect;

	/**
	 * Instantiates a new r screen.
	 *
	 * @param game
	 *            the game
	 * @param camera
	 *            the camera
	 */
	public RScreen(Game game, OrthographicCamera camera) {
		super(game, camera);
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		logo = Main.assets.getLogoTextureRegion();
		rect = new Rectangle(Constants.WIDTH / 2, Constants.HEIGHT / 2, 267, 363);
		stage = new Stage();
	}

	/**
	 * Render.
	 *
	 * @param delta
	 *            the delta
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(logo, rect.x - rect.width / 2, rect.y - rect.height / 2, rect.width, rect.height);
		batch.end();
		super.render(delta);
	}

	/**
	 * On event.
	 *
	 * @param type
	 *            the type
	 * @param source
	 *            the source
	 */
	@Override
	public void onEvent(int type, BaseTween<?> source) {
		if (type == TweenCallback.COMPLETE) {
			if (source.getUserData().equals("fade_out")) {
				fadeIn();
			} else if (source.getUserData().equals("fade_in")) {
				dispose();
				game.setScreen(new MScreen(game, camera));
			}
		}
	}

	/**
	 * Gets the values.
	 *
	 * @param target
	 *            the target
	 * @param tweenType
	 *            the tween type
	 * @param returnValues
	 *            the return values
	 * @return the values
	 */
	@Override
	public int getValues(ScreenModel target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case FADE_TYPE:
			returnValues[0] = target.alpha;
			break;
		default:
			break;
		}
		return returnValues.length;
	}

	/**
	 * Sets the values.
	 *
	 * @param target
	 *            the target
	 * @param tweenType
	 *            the tween type
	 * @param newValues
	 *            the new values
	 */
	@Override
	public void setValues(ScreenModel target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case FADE_TYPE:
			target.alpha = newValues[0];
			break;
		default:
			break;
		}
	}

	/**
	 * Dispose.
	 */
	@Override
	public void dispose() {
		batch.dispose();
		super.dispose();
	}

}
