package com.rougeprogrammers.shootthecat.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.rougeprogrammers.shootthecat.Main;
import com.rougeprogrammers.shootthecat.objects.models.Model;
import com.rougeprogrammers.shootthecat.objects.models.ObjectType;
import com.rougeprogrammers.shootthecat.stages.GameStage;
import com.rougeprogrammers.shootthecat.utils.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class Cat.
 */
public class Cat extends Model {

	/** The texture region. */
	private TextureRegion textureRegion;

	/** The shoot sound. */
	private Sound shootSound;

	/** The ouch sound. */
	private Sound ouchSound;

	/**
	 * Instantiates a new cat.
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
	public Cat(float x, float y, float width, float height, GameStage gameStage) {
		super(x, y, width, height, gameStage);
		textureRegion = Main.assets.getCatTextureRegion();
		shootSound = Main.assets.getCatShootSound();
		ouchSound = Main.assets.getCatOuchSound();
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
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x * Constants.WORLD_TO_BOX, y * Constants.WORLD_TO_BOX);
		Body body = gameStage.getWorld().createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(getWidth() / 2 * Constants.WORLD_TO_BOX, getHeight() / 2 * Constants.WORLD_TO_BOX);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = Constants.CAT_DENSITY;
		fixtureDef.friction = Constants.CAT_FRICTION;
		fixtureDef.restitution = Constants.CAT_RESTITUTION;
		body.createFixture(fixtureDef);
		shape.dispose();
		body.setUserData(ObjectType.CAT);
		return body;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.scenes.scene2d.Actor#act(float)
	 */
	@Override
	public void act(float delta) {
		super.act(delta);
		setZIndex(getParent().getChildren().size);
		if (getVelocityX() < 0) {
			setLinearVelocity(Math.abs(getVelocityX()), getVelocityY());
		}
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
		batch.draw(textureRegion, getX() - getWidth() / 2, getY() - getHeight() / 2, getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}

	/**
	 * Gets the velocity x.
	 *
	 * @return the velocity x
	 */
	public float getVelocityX() {
		return body.getLinearVelocity().x;
	}

	/**
	 * Gets the velocity y.
	 *
	 * @return the velocity y
	 */
	public float getVelocityY() {
		return body.getLinearVelocity().y;
	}

	/**
	 * play ouch sound.
	 */
	public void ouch() {
		ouchSound.play();
	}

	/**
	 * Shoot.
	 *
	 * @param force
	 *            the force
	 */
	public void shoot(Vector2 force) {
		body.applyForceToCenter(force, true);
		shootSound.setVolume(shootSound.play(), 1);
	}

	/**
	 * Sets the density.
	 *
	 * @param density
	 *            the new density
	 */
	public void setDensity(float density) {
		body.getFixtureList().first().setDensity(density);
	}

	/**
	 * Gets the density.
	 *
	 * @return the density
	 */
	public float getDensity() {
		return body.getFixtureList().first().getDensity();
	}

	/**
	 * Sets the restitution.
	 *
	 * @param restitution
	 *            the new restitution
	 */
	public void setRestitution(float restitution) {
		body.getFixtureList().first().setRestitution(restitution);
	}

	/**
	 * Gets the restitution.
	 *
	 * @return the restitution
	 */
	public float getRestitution() {
		return body.getFixtureList().first().getRestitution();
	}

	/**
	 * Sets the linear velocity.
	 *
	 * @param vX
	 *            the v x
	 * @param vY
	 *            the v y
	 */
	public void setLinearVelocity(float vX, float vY) {
		body.setLinearVelocity(vX, vY);
	}

	/**
	 * Gets the body.
	 *
	 * @return the body
	 */
	public Body getBody() {
		return body;
	}

}
