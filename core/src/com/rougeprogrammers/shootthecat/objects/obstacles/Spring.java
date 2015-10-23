package com.rougeprogrammers.shootthecat.objects.obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.rougeprogrammers.shootthecat.Main;
import com.rougeprogrammers.shootthecat.objects.Cat;
import com.rougeprogrammers.shootthecat.objects.models.ObjectType;
import com.rougeprogrammers.shootthecat.objects.models.Obstacle;
import com.rougeprogrammers.shootthecat.stages.GameStage;
import com.rougeprogrammers.shootthecat.utils.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class Spring.
 */
public class Spring extends Obstacle {

	/** The texture region. */
	private TextureRegion textureRegion;

	/** The sound. */
	private Sound sound;

	/**
	 * Instantiates a new spring.
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
	public Spring(float x, float y, float width, float height, GameStage gameStage) {
		super(x, y, width, height, gameStage);
		textureRegion = Main.assets.getSpringTextureRegion();
		sound = Main.assets.getSpringSound();
		Gdx.app.log(TAG, "created");
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
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(getWidth() / 2 * Constants.WORLD_TO_BOX, getHeight() / 2 * Constants.WORLD_TO_BOX);
		body.createFixture(shape, Constants.OBSTACLES_DENSITY);
		shape.dispose();
		body.setUserData(ObjectType.SPRING);
		return body;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rougeprogrammers.shootthecat.objects.models.Obstacle#action(com.
	 * rougeprogrammers.shootthecat.objects.Cat)
	 */
	@Override
	public void action(Cat cat) {
		body.setActive(false);
		sound.setVolume(sound.play(), 0.1f);
		// cat.shoot(Constants.SPRING_FORCE);
		cat.shoot(new Vector2(cat.getVelocityX() * 10, Math.abs(cat.getVelocityY() * 100)));
		Gdx.app.log(TAG, "acted");
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
	}

}
