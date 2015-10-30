package com.rougeprogrammers.shootthecat.objects.obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.rougeprogrammers.shootthecat.Main;
import com.rougeprogrammers.shootthecat.objects.Cat;
import com.rougeprogrammers.shootthecat.objects.Ground;
import com.rougeprogrammers.shootthecat.objects.models.ObjectType;
import com.rougeprogrammers.shootthecat.objects.models.Obstacle;
import com.rougeprogrammers.shootthecat.stages.GameStage;
import com.rougeprogrammers.shootthecat.utils.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class RunPower.
 */
public class RunPower extends Obstacle {

	/** The Constant WIDTH. */
	public static final float WIDTH = 50;

	/** The Constant HEIGHT. */
	public static final float HEIGHT = 50;

	/** The y. */
	public static float Y = Ground.Y + (Ground.HEIGHT + HEIGHT) / 2;

	/** The texture region. */
	private TextureRegion textureRegion;

	/**
	 * Instantiates a new run power.
	 *
	 * @param x
	 *            the x
	 * @param gameStage
	 *            the game stage
	 */
	public RunPower(float x, GameStage gameStage) {
		super(x, Y, WIDTH, HEIGHT, gameStage);
		textureRegion = Main.assets.getEnergyTextureRegion();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rougeprogrammers.shootthecat.objects.models.Obstacle#action(com.
	 * rougeprogrammers.shootthecat.objects.Cat,
	 * com.badlogic.gdx.math.Vector2[])
	 */
	@Override
	public void action(Cat cat, Vector2[] contactPoints) {
		body.setActive(false);
		cat.startRunning();
		Gdx.app.log(TAG, "acted");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rougeprogrammers.shootthecat.objects.models.Model#createBody(float,
	 * float)
	 */
	@Override
	protected Body createBody(float x, float y) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(x * Constants.WORLD_TO_BOX, y * Constants.WORLD_TO_BOX);
		Body body = gameStage.getWorld().createBody(bodyDef);
		CircleShape shape = new CircleShape();
		shape.setRadius(getWidth() / 2 * Constants.WORLD_TO_BOX);
		body.createFixture(shape, Obstacle.DENSITY);
		shape.dispose();
		body.setUserData(ObjectType.TNT);
		return body;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.badlogic.gdx.scenes.scene2d.Actor#draw(com.badlogic.gdx.graphics.g2d.
	 * Batch, float)
	 */
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(textureRegion, getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
		super.draw(batch, parentAlpha);
	}

}
