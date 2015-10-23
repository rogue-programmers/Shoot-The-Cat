package com.rougeprogrammers.shootthecat.utils;

import com.badlogic.gdx.math.Vector2;

// TODO: Auto-generated Javadoc
/**
 * The Class Constants.
 */
public class Constants {

	/** The Constant WIDTH. */
	public static final float WIDTH = 800;

	/** The Constant HEIGHT. */
	public static final float HEIGHT = 480;

	/** The Constant GRAVITY. */
	public static final Vector2 GRAVITY = new Vector2(0, -10);

	/** The Constant WORLD_TO_BOX. */
	public static final float WORLD_TO_BOX = 0.01f;

	/** The Constant BOX_TO_WORLD. */
	public static final float BOX_TO_WORLD = 100;

	/** The Constant FPS. */
	public static final float FPS = 1 / 60f;

	/** The Constant BACKGROUND_WIDTH. */
	public static final float BACKGROUND_WIDTH = WIDTH * 2;

	/** The Constant BACKGROUND_HEIGHT. */
	public static final float BACKGROUND_HEIGHT = HEIGHT;

	/** The Constant BACKGROUND_X. */
	public static final float BACKGROUND_X = WIDTH / 2;

	/** The Constant BACKGROUND_Y. */
	public static final float BACKGROUND_Y = HEIGHT / 2;

	/** The Constant GROUND_WIDTH. */
	public static final float GROUND_WIDTH = WIDTH *1.5f;

	/** The Constant GROUND_HEIGHT. */
	public static final float GROUND_HEIGHT = 40;

	/** The Constant GROUND_X. */
	public static final float GROUND_X = WIDTH / 2;

	/** The Constant GROUND_Y. */
	public static final float GROUND_Y = GROUND_HEIGHT / 2;

	/** The Constant GROUND_DENSITY. */
	public static final float GROUND_DENSITY = 1f;

	/** The Constant GROUND_BODY_TEXTURE_SCALER. */
	public static final float GROUND_BODY_TEXTURE_SCALER = 4;

	/** The Constant CAT_WIDTH. */
	public static final float CAT_WIDTH = 100;

	/** The Constant CAT_HEIGHT. */
	public static final float CAT_HEIGHT = 90;

	/** The Constant CAT_X. */
	public static final float CAT_X = 340;

	/** The Constant CAT_Y. */
	public static final float CAT_Y = 200;

	/** The Constant CAT_DENSITY. */
	public static final float CAT_DENSITY = 0.5f;

	/** The Constant CAT_FRICTION. */
	public static final float CAT_FRICTION = 0.1f;

	/** The Constant CAT_RESTITUTION. */
	public static final float CAT_RESTITUTION = 0.6f;

	/** The Constant MIN_OUCH_SPEED. */
	public static final float CAT_OUCH_SPEED = -2;

	/** The Constant OBSTACLES_DENSITY. */
	public static final float OBSTACLES_DENSITY = 0.6f;

	/** The Constant TNT_WIDTH. */
	public static final float TNT_WIDTH = 50;

	/** The Constant TNT_HEIGHT. */
	public static final float TNT_HEIGHT = 50;

	/** The Constant TNT_Y. */
	public static final float TNT_Y = GROUND_Y + (GROUND_HEIGHT + TNT_HEIGHT) / 2;

	/** The Constant TNT_FORCE. */
	public static final Vector2 TNT_FORCE = new Vector2(200, 300);

	/** The Constant CANNON_WIDTH. */
	public static final float CANNON_WIDTH = 300;

	/** The Constant CANNON_HEIGHT. */
	public static final float CANNON_HEIGHT = 200;

	/** The Constant CANNON_X. */
	public static final float CANNON_X = 200;

	/** The Constant CANNON_Y. */
	public static final float CANNON_Y = GROUND_Y + (GROUND_HEIGHT + CANNON_HEIGHT) / 2;

	/** The Constant THORN_WIDTH. */
	public static final float THORN_WIDTH = 100;

	/** The Constant THORN_HEIGHT. */
	public static final float THORN_HEIGHT = 100;

	/** The Constant THORN_Y. */
	public static final float THORN_Y = GROUND_Y + (GROUND_HEIGHT + THORN_HEIGHT) / 2;

	/** The Constant SPRING_WIDTH. */
	public static final float SPRING_WIDTH = 50;

	/** The Constant SPRING_HEIGHT. */
	public static final float SPRING_HEIGHT = 70;

	/** The Constant SPRING_Y. */
	public static final float SPRING_Y = GROUND_Y + (GROUND_HEIGHT + SPRING_HEIGHT) / 2;

	/** The Constant SPRING_FORCE. */
	public static final Vector2 SPRING_FORCE = new Vector2(80, 200);

}
