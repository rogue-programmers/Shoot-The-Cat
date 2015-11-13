package com.rougeprogrammers.shootthecat.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.rougeprogrammers.shootthecat.Main;
import com.rougeprogrammers.shootthecat.objects.models.SimpleModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Cannon.
 */
public class Cannon extends SimpleModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6574197808750875836L;

	/** The Constant CANNON_WIDTH. */
	public static final float WIDTH = 300;

	/** The Constant CANNON_HEIGHT. */
	public static final float HEIGHT = 200;

	/** The Constant CANNON_X. */
	public static final float X = 200;

	/** The Constant CANNON_Y. */
	public static final float Y = Ground.Y + (Ground.HEIGHT + HEIGHT) / 2;

	/** The exploded. */
	private boolean exploded = false;

	/** The effect. */
	private ParticleEffect effect;

	/** The fire sound. */
	private Sound fireSound;

	/**
	 * Instantiates a new cannon.
	 */
	public Cannon() {
		super(X, Y, WIDTH, HEIGHT);
		textureRegion = Main.assets.getCannonTextureRegion();
		fireSound = Main.assets.getCannonFireSound();
		effect = new ParticleEffect();
		effect.load(Gdx.files.internal("particle/cannon.p"), Gdx.files.internal("particle"));
		effect.setPosition(x + 150, y + 70);
		effect.start();
	}

	/**
	 * Explode.
	 *
	 * @param cat
	 *            the cat
	 */
	public void explode(Cat cat) {
		exploded = true;
		fireSound.setVolume(fireSound.play(), 0.1f);
		cat.shoot(new Vector2(MathUtils.random(1.0f, 1.5f), MathUtils.random(1.0f, 2.0f)), cat.getPosition().add(2, 2));
		Gdx.app.log(TAG, "exploded");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rougeprogrammers.shootthecat.objects.models.SimpleModel#draw(com.
	 * badlogic.gdx.graphics.g2d.Batch)
	 */
	@Override
	public void draw(Batch batch) {
		super.draw(batch);
		if (exploded) {
			effect.draw(batch, Math.max(1 / 60f, Gdx.graphics.getDeltaTime()));
		}
	}

}
