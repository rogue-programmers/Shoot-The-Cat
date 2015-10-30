package com.rougeprogrammers.shootthecat.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.rougeprogrammers.shootthecat.stages.MenuStage;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Back;

// TODO: Auto-generated Javadoc
/**
 * The Class MScreen.
 */
public class MScreen extends ScreenModel {

	/** The Constant TABLE_X_TYPE. */
	private static final int TABLE_X_TYPE = 1;

	/**
	 * Instantiates a new m screen.
	 *
	 * @param game
	 *            the game
	 * @param camera
	 *            the camera
	 */
	public MScreen(Game game, OrthographicCamera camera) {
		super(game, camera);
		stage = new MenuStage(this, camera);
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
	 * @see com.rougeprogrammers.shootthecat.screens.ScreenModel#show()
	 */
	@Override
	public void show() {
		((MenuStage) stage).startMusic();
		super.show();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rougeprogrammers.shootthecat.screens.ScreenModel#onEvent(int,
	 * aurelienribon.tweenengine.BaseTween)
	 */
	@Override
	public void onEvent(int type, BaseTween<?> source) {
		if (type == TweenCallback.COMPLETE) {
			if (source.getUserData().equals("fade_out")) {
				Tween.to(this, TABLE_X_TYPE, 1f).target(0).ease(Back.OUT).start(tweenManager);
			} else if (source.getUserData().equals("fade_in")) {
				dispose();
				game.setScreen(new GScreen(game, camera));
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
		MenuStage stage = (MenuStage) target.stage;
		switch (tweenType) {
		case FADE_TYPE:
			returnValues[0] = target.alpha;
			break;
		case TABLE_X_TYPE:
			returnValues[0] = stage.getButtonTableX();
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
		MenuStage stage = (MenuStage) target.stage;
		switch (tweenType) {
		case FADE_TYPE:
			target.alpha = newValues[0];
			stage.setMusicVolume(1 - newValues[0]);
			break;
		case TABLE_X_TYPE:
			stage.setButtonTableX(newValues[0]);
			break;
		default:
			break;
		}
	}

}
