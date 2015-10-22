package com.rougeprogrammers.shootthecat.objects.models;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.rougeprogrammers.shootthecat.stages.GameStage;
import com.rougeprogrammers.shootthecat.utils.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class Model.
 */
public abstract class Model extends Actor {

	/** The tag. For logs. */
	protected final String TAG = this.getClass().getSimpleName();

	/** The game stage. */
	protected GameStage gameStage;

	/** The body. */
	protected Body body;

	/**
	 * Instantiates a new model.
	 *
	 * @param x
	 *            the x position in pixel
	 * @param y
	 *            the y position in pixel
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @param gameStage
	 *            the game stage
	 */
	public Model(float x, float y, float width, float height, GameStage gameStage) {
		this.gameStage = gameStage;
		setWidth(width);
		setHeight(height);
		setOrigin(width / 2, height / 2);
		body = createBody(x, y);
		this.gameStage.addActor(this);
	}

	/**
	 * Creates the body. Define body type, position, fixtures and user data for
	 * the body.
	 *
	 * @param x
	 *            the x position
	 * @param y
	 *            the y position
	 * @return the body
	 */
	protected abstract Body createBody(float x, float y);

	/**
	 * Get scaled x position in pixel.
	 *
	 * @return the x
	 */
	@Override
	public float getX() {
		return body.getPosition().x * Constants.BOX_TO_WORLD;
	}

	/**
	 * get scaled y position in pixel.
	 *
	 * @return the y
	 */
	@Override
	public float getY() {
		return body.getPosition().y * Constants.BOX_TO_WORLD;
	}

	/**
	 * Get rotation of the body in degrees.
	 *
	 * @return the rotation
	 */
	@Override
	public float getRotation() {
		return body.getAngle() * MathUtils.radiansToDegrees;
	}

}
