package com.rougeprogrammers.shootthecat.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
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

	private ParticleEffect effect;

	private Sound fireSound;

	/**
	 * Instantiates a new cannon.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public Cannon() {
		super(X, Y, WIDTH, HEIGHT);
		textureRegion = Main.assets.getCannonTextureRegion();
		fireSound = Main.assets.getCannonFireSound();
		effect = new ParticleEffect();
		effect.load(Gdx.files.internal("particle/cannon.p"), Gdx.files.internal("particle"));
		effect.setPosition(x + 150, y + 70);
		effect.start();
		Gdx.app.log(TAG, "created");
	}

	/**
	 * Explode.
	 * 
	 * @param cat
	 */
	public void explode(Cat cat) {
		exploded = true;
		fireSound.setVolume(fireSound.play(), 0.1f);
		// cat.shoot(new Vector2(250, 400));
		cat.shoot(new Vector2(2, 4), new Vector2(0, 0));
		Gdx.app.log(TAG, "exploded");
	}

	@Override
	public void draw(Batch batch) {
		super.draw(batch);
		if (exploded) {
			effect.draw(batch, Math.max(1 / 60f, Gdx.graphics.getDeltaTime()));
		}
	}

}
