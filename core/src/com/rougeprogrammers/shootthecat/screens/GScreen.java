package com.rougeprogrammers.shootthecat.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.rougeprogrammers.shootthecat.stages.GameStage;

import aurelienribon.tweenengine.BaseTween;

// TODO: Auto-generated Javadoc
/**
 * The Class GScreen.
 */
public class GScreen extends ScreenModel {

	/**
	 * Instantiates a new g screen.
	 *
	 * @param game
	 *            the game
	 * @param camera
	 *            the camera
	 */
	public GScreen(Game game, OrthographicCamera camera) {
		super(game, camera);
		Gdx.input.setCatchBackKey(true);
		stage = new GameStage(this, camera);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rougeprogrammers.shootthecat.screens.ScreenModel#render(float)
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render(delta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rougeprogrammers.shootthecat.screens.ScreenModel#onEvent(int,
	 * aurelienribon.tweenengine.BaseTween)
	 */
	@Override
	public void onEvent(int type, BaseTween<?> source) {
		if (type == COMPLETE) {
			fading = false;
			if (source.getUserData().equals("fade_out")) {

			} else if (source.getUserData().equals("fade_in")) {
				dispose();
				Gdx.app.exit();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rougeprogrammers.shootthecat.screens.ScreenModel#getValues(com.
	 * rougeprogrammers.shootthecat.screens.ScreenModel, int, float[])
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rougeprogrammers.shootthecat.screens.ScreenModel#setValues(com.
	 * rougeprogrammers.shootthecat.screens.ScreenModel, int, float[])
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

}
