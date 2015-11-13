package com.rougeprogrammers.shootthecat.objects.obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.rougeprogrammers.shootthecat.Main;
import com.rougeprogrammers.shootthecat.objects.Cat;
import com.rougeprogrammers.shootthecat.objects.Ground;
import com.rougeprogrammers.shootthecat.objects.models.Obstacle;
import com.rougeprogrammers.shootthecat.objects.models.ObjectType;
import com.rougeprogrammers.shootthecat.objects.models.ObjectType.Type;
import com.rougeprogrammers.shootthecat.stages.GameStage;
import com.rougeprogrammers.shootthecat.utils.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class TNT.
 */
public class TNT extends Obstacle {

	/** The Constant TNT_WIDTH. */
	public static final float WIDTH = 50;

	/** The Constant TNT_HEIGHT. */
	public static final float HEIGHT = 50;

	/** The Constant TNT_Y. */
	public static final float Y = Ground.Y + (Ground.HEIGHT + HEIGHT) / 2;

	/** The Constant TNT_FORCE. */
	public static final Vector2 FORCE = new Vector2(0.5f, 4);

	/** The texture region. */
	private TextureRegion textureRegion;

	/** The effect. */
	private ParticleEffect effect;

	/** The is blown. */
	private boolean exploded = false;

	/** The explosion sound. */
	private Sound explosionSound;

	/**
	 * Instantiates a new tnt.
	 *
	 * @param x
	 *            the x
	 * @param gameStage
	 *            the game stage
	 */
	public TNT(float x, GameStage gameStage, int index) {
		super(x, Y, WIDTH, HEIGHT, gameStage, index);
		explosionSound = Main.assets.getTntExplosionSound();
		textureRegion = Main.assets.getTntTextureRegion();
		effect = new ParticleEffect();
		effect.load(Gdx.files.internal("particle/bomb.p"), Gdx.files.internal("particle"));
		effect.setPosition(x, Y);
		effect.start();
		
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
		body.createFixture(shape, Obstacle.DENSITY);
		shape.dispose();
		body.setUserData(new ObjectType(Type.TNT, index));
		return body;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rougeprogrammers.shootthecat.objects.models.Obstacle#action(com.
	 * rougeprogrammers.shootthecat.objects.Cat)
	 */
	@Override
	public void action(Cat cat, Vector2[] contactPoints) {
		body.setActive(false);
		exploded = true;
		explosionSound.setVolume(explosionSound.play(), 0.1f);
		cat.shoot(FORCE, contactPoints[0]);
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
		if (exploded)
			effect.draw(batch, Gdx.graphics.getDeltaTime());
		else
			batch.draw(textureRegion, getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
	}

}
