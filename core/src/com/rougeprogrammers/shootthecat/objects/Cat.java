package com.rougeprogrammers.shootthecat.objects;

import static com.rougeprogrammers.shootthecat.utils.Constants.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;
import com.badlogic.gdx.physics.box2d.joints.MotorJointDef;
import com.rougeprogrammers.shootthecat.Main;
import com.rougeprogrammers.shootthecat.objects.models.Model;
import com.rougeprogrammers.shootthecat.objects.models.ObjectType;
import com.rougeprogrammers.shootthecat.stages.GameStage;
import com.rougeprogrammers.shootthecat.utils.BodyEditorLoader;
import com.rougeprogrammers.shootthecat.utils.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class Cat.
 */
public class Cat extends Model {

	/** The texture region. */
	// private TextureRegion textureRegion;

	/** The shoot sound. */
	private Sound shootSound;

	/** The ouch sound. */
	private Sound ouchSound;

	/** The loader. */
	private BodyEditorLoader loader;

	/** The main body. */
	private Part mainBody;

	/** The head. */
	private Part head;

	/** The tail. */
	private Part tail;

	/** The front_legs. */
	private Part front_legs;

	/** The beyond_legs. */
	private Part beyond_legs;

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
		// textureRegion = Main.assets.getCatTextureRegion();
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
		// BodyDef bodyDef = new BodyDef();
		// bodyDef.type = BodyType.DynamicBody;
		// bodyDef.position.set(x * Constants.WORLD_TO_BOX, y *
		// Constants.WORLD_TO_BOX);
		// Body body = gameStage.getWorld().createBody(bodyDef);
		// PolygonShape shape = new PolygonShape();
		// shape.setAsBox(getWidth() / 2 * Constants.WORLD_TO_BOX, getHeight() /
		// 2 * Constants.WORLD_TO_BOX);
		// FixtureDef fixtureDef = new FixtureDef();
		// fixtureDef.shape = shape;
		// fixtureDef.density = Constants.CAT_DENSITY;
		// fixtureDef.friction = Constants.CAT_FRICTION;
		// fixtureDef.restitution = Constants.CAT_RESTITUTION;
		// body.createFixture(fixtureDef);
		// shape.dispose();
		// body.setUserData(ObjectType.CAT);

		// BodyEditorLoader loader = new
		// BodyEditorLoader(Gdx.files.internal("bodies/cat.json"));
		// BodyDef bodyDef = new BodyDef();
		// bodyDef.position.set(x * Constants.WORLD_TO_BOX, y *
		// Constants.WORLD_TO_BOX);
		// bodyDef.type = BodyType.DynamicBody;
		// FixtureDef fixtureDef = new FixtureDef();
		// fixtureDef.density = Constants.CAT_DENSITY;
		// fixtureDef.friction = Constants.CAT_FRICTION;
		// fixtureDef.restitution = Constants.CAT_RESTITUTION;
		// Body body = gameStage.getWorld().createBody(bodyDef);
		// loader.attachFixture(body, "Cat", fixtureDef, getWidth() *
		// Constants.WORLD_TO_BOX);
		// Vector2 origin = loader.getOrigin("Cat", getWidth() *
		// Constants.WORLD_TO_BOX).cpy().scl(Constants.BOX_TO_WORLD);
		// setOrigin(origin.x, origin.y);
		// body.setUserData(ObjectType.CAT);

		loader = new BodyEditorLoader(Gdx.files.internal("bodies/cat_parts.json"));

		mainBody = new Part(CAT_X, CAT_Y, 50, CAT_DENSITY, CAT_FRICTION, CAT_RESTITUTION, "cat_body");
		head = new Part(CAT_X + 50, CAT_Y + 50, 50, CAT_DENSITY, CAT_FRICTION, CAT_RESTITUTION, "cat_head");
		tail = new Part(CAT_X - 50, CAT_Y + 50, 20, CAT_DENSITY, CAT_FRICTION, CAT_RESTITUTION, "cat_tail");
		front_legs = new Part(CAT_X + 30, CAT_Y - 30, 30, CAT_DENSITY, CAT_FRICTION, CAT_RESTITUTION, "cat_front_legs");
		beyond_legs = new Part(CAT_X - 30, CAT_Y - 30, 30, CAT_DENSITY, CAT_FRICTION, CAT_RESTITUTION,
				"cat_beyond_legs");

		// bodyTextureRegion = new TextureRegion(new
		// Texture(Gdx.files.internal("textures/cat/cat_body.png")));
		// headTextureRegion = new TextureRegion(new
		// Texture(Gdx.files.internal("textures/cat/cat_head.png")));
		// tailTextureRegion = new TextureRegion(new
		// Texture(Gdx.files.internal("textures/cat/cat_tail.png")));
		// frontLegsTextureRegion = new TextureRegion(new
		// Texture(Gdx.files.internal("textures/cat/cat_front_legs.png")));
		// beyondLegsTextureRegion = new TextureRegion(
		// new Texture(Gdx.files.internal("textures/cat/cat_beyond_legs.png")));

		// bodyOrigin = loader.getOrigin(name, scale)

		Body cat_body = mainBody.body;
		Body cat_head = head.body;
		Body cat_tail = tail.body;
		Body cat_front_legs = front_legs.body;
		Body cat_beyond_legs = beyond_legs.body;

		joint(cat_body, cat_head, 25, 20);
		joint(cat_body, cat_tail, -30, +30);
		joint(cat_body, cat_front_legs, +20, -20);
		joint(cat_body, cat_beyond_legs, -20, -20);

		return cat_body;
	}

	/**
	 * Joint.
	 *
	 * @param a
	 *            the a
	 * @param b
	 *            the b
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	private void joint(Body a, Body b, float x, float y) {
		MotorJointDef def = new MotorJointDef();

		def.angularOffset = 0;
		def.bodyA = a;
		def.bodyB = b;
		def.collideConnected = true;
		def.correctionFactor = 0.5f;
		def.linearOffset.set(x * WORLD_TO_BOX, y * WORLD_TO_BOX);
		def.maxForce = 100;
		def.maxTorque = 100;
		def.type = JointType.MotorJoint;

		// WeldJointDef def = new WeldJointDef();
		//
		// def.bodyA = a;
		// def.bodyB = b;
		// def.collideConnected = true;
		// def.dampingRatio = 0.5f;
		// def.frequencyHz = 3f;
		// def.localAnchorA.set(0, 0);
		// def.localAnchorB.set(x, y);
		// def.referenceAngle = 0f;
		// def.type = JointType.WeldJoint;

		gameStage.getWorld().createJoint(def);
	}

	/**
	 * The Class Part.
	 */
	private class Part {

		/** The body. */
		Body body;

		/** The texture region. */
		TextureRegion textureRegion;

		/** The origin. */
		Vector2 origin;

		/** The width. */
		float width;

		/** The height. */
		float height;

		/**
		 * Instantiates a new part.
		 *
		 * @param x
		 *            the x
		 * @param y
		 *            the y
		 * @param width
		 *            the width
		 * @param density
		 *            the density
		 * @param friction
		 *            the friction
		 * @param restitution
		 *            the restitution
		 * @param name
		 *            the name
		 */
		public Part(float x, float y, float width, float density, float friction, float restitution, String name) {
			BodyDef bodyDef = new BodyDef();
			bodyDef.position.set(x * WORLD_TO_BOX, y * WORLD_TO_BOX);
			bodyDef.type = BodyType.DynamicBody;
			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.density = density;
			fixtureDef.friction = friction;
			fixtureDef.restitution = restitution;
			body = gameStage.getWorld().createBody(bodyDef);
			loader.attachFixture(body, name, fixtureDef, width * Constants.WORLD_TO_BOX);
			body.setUserData(ObjectType.CAT);
			body.setSleepingAllowed(false);
			textureRegion = new TextureRegion(new Texture(Gdx.files.internal("textures/cat/" + name + ".png")));
			origin = loader.getOrigin(name, width * WORLD_TO_BOX).cpy().scl(BOX_TO_WORLD);
			this.width = width;
			height = textureRegion.getRegionHeight() * width / textureRegion.getRegionWidth();
		}

		/**
		 * Gets the x.
		 *
		 * @return the x
		 */
		public float getX() {
			return body.getPosition().x * BOX_TO_WORLD;
		}

		/**
		 * Gets the y.
		 *
		 * @return the y
		 */
		public float getY() {
			return body.getPosition().y * BOX_TO_WORLD;
		}

		/**
		 * Gets the rotation.
		 *
		 * @return the rotation
		 */
		public float getRotation() {
			return body.getAngle() * MathUtils.radiansToDegrees;
		}

	}

	// private Body body(float x, float y, float width, float density, float
	// friction, float restitution, String name) {
	// BodyDef bodyDef = new BodyDef();
	// bodyDef.position.set(x * Constants.WORLD_TO_BOX, y *
	// Constants.WORLD_TO_BOX);
	// bodyDef.type = BodyType.DynamicBody;
	// FixtureDef fixtureDef = new FixtureDef();
	// fixtureDef.density = density;
	// fixtureDef.friction = friction;
	// fixtureDef.restitution = restitution;
	// Body body = gameStage.getWorld().createBody(bodyDef);
	// loader.attachFixture(body, name, fixtureDef, width *
	// Constants.WORLD_TO_BOX);
	// body.setUserData(ObjectType.CAT);
	// body.setSleepingAllowed(false);
	// return body;
	// }

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
		// batch.draw(textureRegion, getX() - getWidth() / 2, getY() -
		// getHeight() / 2, getOriginX(), getOriginY(),
		// getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		batch.draw(mainBody.textureRegion, mainBody.getX() - mainBody.width / 2, mainBody.getY() - mainBody.height / 2,
				mainBody.origin.x, mainBody.origin.y, mainBody.width, mainBody.height, 1, 1, mainBody.getRotation());

		batch.draw(head.textureRegion, head.getX() - head.width / 2, head.getY() - head.height / 2, head.origin.x,
				head.origin.y, head.width, head.height, 1, 1, head.getRotation());

		batch.draw(tail.textureRegion, tail.getX() - tail.width / 2, tail.getY() - tail.height / 2, tail.origin.x,
				tail.origin.y, tail.width, tail.height, 1, 1, tail.getRotation());

		batch.draw(front_legs.textureRegion, front_legs.getX() - front_legs.width / 2,
				front_legs.getY() - front_legs.height / 2, front_legs.origin.x, front_legs.origin.y, front_legs.width,
				front_legs.height, 1, 1, front_legs.getRotation());

		batch.draw(beyond_legs.textureRegion, beyond_legs.getX() - beyond_legs.width / 2,
				beyond_legs.getY() - beyond_legs.height / 2, beyond_legs.origin.x, beyond_legs.origin.y,
				beyond_legs.width, beyond_legs.height, 1, 1, beyond_legs.getRotation());
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
		// body.getFixtureList().first().setRestitution(restitution);
		mainBody.body.getFixtureList().first().setRestitution(restitution);
		head.body.getFixtureList().first().setRestitution(restitution);
		tail.body.getFixtureList().first().setRestitution(restitution);
		front_legs.body.getFixtureList().first().setRestitution(restitution);
		beyond_legs.body.getFixtureList().first().setRestitution(restitution);
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
		// body.setLinearVelocity(vX, vY);
		mainBody.body.setLinearVelocity(vX, vY);
		head.body.setLinearVelocity(vX, vY);
		tail.body.setLinearVelocity(vX, vY);
		front_legs.body.setLinearVelocity(vX, vY);
		beyond_legs.body.setLinearVelocity(vX, vY);
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
