package com.rougeprogrammers.shootthecat.objects.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.rougeprogrammers.shootthecat.objects.Cat;
import com.rougeprogrammers.shootthecat.stages.GameStage;

// TODO: Auto-generated Javadoc
/**
 * The Class Obstacle.
 */
public abstract class Obstacle extends Model implements Disposable {

	/**
	 * Instantiates a new obstacle.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @param gameStage
	 *            the game stage
	 */
	public Obstacle(float x, float y, float width, float height, GameStage gameStage) {
		super(x, y, width, height, gameStage);
	}

	/**
	 * Action in contact with the cat.
	 *
	 * @param cat
	 *            the cat
	 */
	public abstract void action(Cat cat);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.utils.Disposable#dispose()
	 */
	@Override
	public void dispose() {
		gameStage.getWorld().destroyBody(body);
		gameStage.getActors().removeValue(this, false);
		Gdx.app.log(TAG, "destroyed");
	}

}
