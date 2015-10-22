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
	public Cannon(float x, float y, float width, float height) {
		super(x, y, width, height);
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
//		cat.shoot(new Vector2(250, 400));
		cat.shoot(new Vector2(0.1f, 0.1f));
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
	public void draw(Batch batch, float delta) {
		super.draw(batch, delta);
		if (exploded) {
			effect.draw(batch, delta);
		}
	}

//	private float offsetX(float x) {
//		return this.x - this.width / 2 + x;
//	}
//
//	private float offsetY(float y) {
//		return this.y - this.height / 2 + y;
//	}

	/**
	 * Inner draw.
	 *
	 * @param batch
	 *            the batch
	 * @param model
	 *            the model
	 */
	// private void innerDraw(Batch batch, SimpleModel model) {
	// batch.draw(model.getTextureRegion(), this.x - this.width / 2 + model.x -
	// model.width / 2,
	// this.y - this.height / 2 + model.y - model.height / 2, model.width,
	// model.height);
	// }

	/**
	 * The Class CannonExplosion.
	 */
	// private class CannonExplosion extends SimpleModel {
	//
	// /** The Constant serialVersionUID. */
	// private static final long serialVersionUID = 6219259919860023056L;
	//
	// /**
	// * Instantiates a new cannon explosion.
	// *
	// * @param x
	// * the x
	// * @param y
	// * the y
	// * @param width
	// * the width
	// * @param height
	// * the height
	// */
	// public CannonExplosion(float x, float y, float width, float height) {
	// super(x, y, width, height);
	// textureRegion = Main.assets.getExplosionTextureRegion();
	// }
	//
	// }

}
