package com.rougeprogrammers.shootthecat.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
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

	/** The Constant CAT_WIDTH. */
	public static final float WIDTH = 75;

	/** The Constant CAT_HEIGHT. */
	public static final float HEIGHT = 60;

	/** The Constant CAT_X. */
	public static final float X = 340;

	/** The Constant CAT_Y. */
	public static final float Y = 200;

	/** The Constant CAT_DENSITY. */
	public static final float DENSITY = 0.9f;

	/** The Constant CAT_FRICTION. */
	public static final float FRICTION = 0.1f;

	/** The Constant CAT_RESTITUTION. */
	public static final float RESTITUTION = 0.6f;

	/** The Constant CAT_OUCH_SPEED. */
	public static final float OUCH_SPEED = -5;

	/** The shoot sound. */
	private Sound shootSound;

	/** The ouch sound. */
	private Sound ouchSound;

	/** The texture region. */
	private TextureRegion textureRegion;

	/** The atlas regions. */
	private Array<AtlasRegion> atlasRegions;

	/** The animation. */
	private Animation animation;

	/** The atlas. */
	private TextureAtlas atlas;

	/** The bloods. */
	private Array<Blood> bloods;

	/** The state time. */
	private float stateTime;

	/** The running. */
	private boolean running;

	/**
	 * Instantiates a new cat.
	 *
	 * @param gameStage
	 *            the game stage
	 */
	public Cat(GameStage gameStage) {
		super(X, Y, WIDTH, HEIGHT, gameStage);
		shootSound = Main.assets.getCatShootSound();
		ouchSound = Main.assets.getCatOuchSound();
		atlas = Main.assets.getCatTextureAtlas();
		atlasRegions = atlas.getRegions();
		textureRegion = atlasRegions.get(6);
		animation = new Animation(0.2f, atlasRegions, PlayMode.LOOP);
		bloods = new Array<Blood>();
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
		bodyDef.linearVelocity.x = 1;
		Body body = gameStage.getWorld().createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(getWidth() / 2 * Constants.WORLD_TO_BOX, getHeight() / 2 * Constants.WORLD_TO_BOX);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = DENSITY;
		fixtureDef.friction = FRICTION;
		fixtureDef.restitution = RESTITUTION;
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
		for (Blood blood : bloods) {
			if (getX() - blood.x > Constants.WIDTH) {
				bloods.removeValue(blood, false);
			}
		}
		if (running) {
			stateTime += delta;
			textureRegion = animation.getKeyFrame(stateTime);
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
		for (Blood blood : bloods) {
			blood.draw(batch);
		}
	}

	/**
	 * Die.
	 */
	public void die() {
		textureRegion = atlas.findRegion("cat6");
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
	 *
	 * @param contactPoints
	 *            the contact points
	 */
	public void ouch(Vector2[] contactPoints) {
		textureRegion = atlas.findRegion("cat" + MathUtils.random(0, 5));
		for (int i = 0; i < contactPoints.length; i++) {
			bloods.add(new Blood(contactPoints[i].x * Constants.BOX_TO_WORLD,
					contactPoints[i].y * Constants.BOX_TO_WORLD));
		}
		ouchSound.play();
	}

	/**
	 * Shoot.
	 *
	 * @param force
	 *            the force
	 * @param point
	 *            the point
	 */
	public void shoot(Vector2 force, Vector2 point) {
		body.applyForce(force, point, true);
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
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Vector2 getPosition() {
		return body.getPosition();
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
